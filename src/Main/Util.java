package Main;

public class Util {
	
	public static float[] parseArgs( String args ) {
		
		//Remove whitespace
		
		
		//Split the args by the comma delimiter
		String[] splitArgs = args.split( "," );
		
		//Create our empty output array
		float[] output = new float[ splitArgs.length ];
		
		//Convert all of the arguments to floats and add them to the output array
		for (int i = 0; i < splitArgs.length; i++) {
			
			//Error checking to avoid empty values
			if( splitArgs[i].trim().length() == 0 ) {
				continue;
			}
			
			output[i] = Float.parseFloat( splitArgs[i].trim() );
		}
		
		return output;
		
	}
	
	//Parses args and returns ints
	//This is a bad convenience function
	public static int[] parseArgsInt( String args ) {
		
		//Parse the args
		float[] parsedArgs = parseArgs( args );
		
		int[] output = new int[parsedArgs.length];
		
		//Convert them to ints
		for (int i = 0; i < parsedArgs.length; i++) {
			output[i] = (int) parsedArgs[i];
		}
		
		return output;
		
	}
	
	//Takes in a line of training data from a file and returns the values as two tables of floats
	//output[0] is the inputs
	//output[1] is the outputs
	public static float[][] parseTraining( String args ){
		
		float[][] output = new float[2][];
		
		//Split the training data into inputs and outputs
		String[] splitArgs = args.split( "\\|" );
		
		//Some basic error checking
		if( splitArgs.length != 2 ) {
			System.out.println( "Error: training data is malformed" );
			return null;
		}
		
		//Clean up the split data
		splitArgs[0] = splitArgs[0].trim();
		splitArgs[1] = splitArgs[1].trim();
		
		//Split the input data into an array
		float[] inputValues =  parseArgs( splitArgs[0] );
		
		//Split the output data into an array
		float[] outputValues =  parseArgs( splitArgs[1] );
		
		//Package inputs and outputs for return
		output[0] = inputValues;
		output[1] = outputValues;
		
		return output;
		
	}
	
}
