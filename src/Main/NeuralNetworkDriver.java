package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import Network.Network;

public class NeuralNetworkDriver {

	public static boolean debug = true;

	public static void main( String[] args ) throws FileNotFoundException {

		//Try to make it look more like windows
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		//Select a network definition file
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter( "Network Definition Files", "net" );
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory( new File( System.getProperty("user.dir") + "/data" ) );
		int returnVal = chooser.showOpenDialog( null );

		//Get the file
		File netDef = null;
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			netDef = chooser.getSelectedFile();
		}

		//If we didn't select a file, give an error and close
		if( netDef == null ) {
			Util.error( "You must select a network definition file before proceeding!" );
		}

		//Begin parsing the network definition file
		Scanner scanner = new Scanner( netDef );

		String netLine = "";

		//We need to read the entire file
		while( scanner.hasNextLine() ) {

			//Read in the next line
			netLine = scanner.nextLine();

			//Trim it
			netLine = netLine.trim();

			//Ignore commented lines and blank lines
			if( netLine.startsWith( "//" ) || netLine.equals( "" ) ) {
				continue;
			}

			//If this is our content line, we can stop
			break;

		}

		//	    Util.print( "Input count: " + net.inputLayer.inputCount );
		//	    Util.print( "Hidden layer count: " + net.hiddenLayers.length );
		//	    for (int i = 0; i < net.hiddenLayers.length; i++) {
		//			
		//	    	Util.print( "\t Hidden layer " + i + " neuron count: " + net.hiddenLayers[i].getNeuronCount() );
		//	    	
		//		}
		//	    Util.print( "Output count: " + net.outputLayer.outputCount );

		//Create a new network from the specifications we just read from the net file
		int[] networkArgs = Util.parseArgsInt( netLine );
		Network net = new Network( networkArgs );

		//Step 1: Initialize
		net.init();

		//Select a training data file
		chooser = new JFileChooser();
		filter = new FileNameExtensionFilter( "Training Data Files", "trn" );
		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory( new File( System.getProperty("user.dir") + "/data/training" ) );
		returnVal = chooser.showOpenDialog( null );

		//Get the file
		netDef = null;
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			netDef = chooser.getSelectedFile();
		}

		//If we didn't select a file, give an error and close
		if( netDef == null ) {
			JOptionPane.showMessageDialog(null, "You must select a training data file before proceeding!" );
			System.exit( 1 );
		}

		//Begin parsing the network definition file
		scanner = new Scanner( netDef );

		String trainingLine = "";

		ArrayList<float[][]> trainingDataArrayList = new ArrayList<float[][]>();
		
		//We need to read the entire file to get all the training data
		while( scanner.hasNextLine() ) {

			//Read in the next line
			trainingLine = scanner.nextLine();

			//Trim it
			trainingLine = trainingLine.trim();

			//Ignore commented lines and blank lines
			if( trainingLine.startsWith( "//" ) || trainingLine.equals( "" ) ) {
				continue;
			}

			//Parse the line
			float[][] parsedLine =  Util.parseTraining( trainingLine );
			
			//Save the training data into the training ArrayList
			trainingDataArrayList.add( parsedLine );
			
		}
		
		//Begin training on this data for N iterations
		float sumOfErrors = 1;
		
		while( sumOfErrors > 0.001f ){
			
			if( net.getEpoch() % 10000 == 0 ){
				debug = true;
			}else{
				debug = false;
			}
			
			Util.print( "Starting Epoch: " + net.getEpoch() );
			
			for (int j = 0; j < trainingDataArrayList.size(); j++) {
				
				float[][] trainingData = trainingDataArrayList.get( j );
				
				Util.print( "\tTraining Data set: " + j );
				
				float[] trainingInput = trainingData[0];
				float[] expectedOutput = trainingData[1];
				
				//Step 2: Activation
				float[] outputs = net.activate( trainingInput );

				for (int k = 0; k < outputs.length; k++) {
					Util.print( "\t\tOutput " + k + ": Expected " + expectedOutput[ k ] + " Got " + outputs[ k ] );
				}
				
				//Step 3: Weight Training
				net.weightTrain( expectedOutput );
				
				//Update the sum of errors with the largest error this epoch
				float newSumOfErrors = net.getSumOfErrors();
				if( newSumOfErrors > sumOfErrors ){
					sumOfErrors = newSumOfErrors;
				}
				
			}
			
			//Step 4: Iteration
			net.addEpoch();
			
			//Shuffle training data
			Collections.shuffle( trainingDataArrayList );
		}
		
		
		
		

	}



}
