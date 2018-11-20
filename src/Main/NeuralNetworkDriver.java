package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import Network.Network;

public class NeuralNetworkDriver {
	
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
	    chooser.setCurrentDirectory( new File( System.getProperty("user.dir") ) );
	    int returnVal = chooser.showOpenDialog( null );
	    
	    //Get the file
	    File netDef = null;
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	netDef = chooser.getSelectedFile();
	    }
	    
	    //If we didn't select a file, give an error and close
	    if( netDef == null ) {
	    	JOptionPane.showMessageDialog(null, "You must select a network definition file before proceeding!" );
	    	System.exit( 1 );
	    }
	    
	    //Begin parsing the network definition file
	    Scanner scanner = new Scanner( netDef );
	    
	    String line = "";
	    
	    //We need to read the entire file
	    while( scanner.hasNextLine() ) {
	    	
	    	//Read in the next line
	    	line = scanner.nextLine();
	    	
	    	//Trim it
	    	line = line.trim();
	    	
	    	//Ignore commented lines and blank lines
	    	if( line.startsWith( "//" ) || line.equals( "" ) ) {
	    		continue;
	    	}
	    	
	    	//If this is our content line, we can stop
	    	break;
	    	
	    }
	    
	    //Create a new network from the specifications we just read from the net file
	    int[] networkArgs = Util.parseArgsInt( line );
	    Network net = new Network( networkArgs );
	    
	    
	    //Check it
	    //System.out.println( "Input Size: " + net.inputLayer.inputCount );
	    //System.out.println( "Hidden Layer Count: " + net.inputLayer.inputCount );

	    /*
	    
		//Select a training data file
		chooser = new JFileChooser();
	    filter = new FileNameExtensionFilter( "Training Data Files", "trn" );
	    chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory( new File( System.getProperty("user.dir") ) );
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
	    
	    line = "";
	    
	    //We need to read the entire file to get all the training data
	    while( scanner.hasNextLine() ) {
	    	
	    	//Read in the next line
	    	line = scanner.nextLine();
	    	
	    	//Trim it
	    	line = line.trim();
	    	
	    	//Ignore commented lines and blank lines
	    	if( line.startsWith( "//" ) || line.equals( "" ) ) {
	    		continue;
	    	}
	    	
	    	float[][] parsedLine =  Util.parseTraining( line );
	    	
	    }
	    */
		
	}
	
	
	
}
