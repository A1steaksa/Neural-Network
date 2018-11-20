package Main;

public class Util {
	
	public static float[] parseArgs( String args ) {
		
		//Split the args by the comma delimiter
		String[] splitArgs = args.split( "," );
		
		//Create our empty output array
		float[] output = new float[ splitArgs.length ];
		
		//Convert all of the arguments to floats and add them to the output array
		for (int i = 0; i < splitArgs.length; i++) {
			
			output[i] = Float.parseFloat( splitArgs[i].trim() );
		}
		
		return output;
		
	}
	
	public static float[][] parseTraining( String args ){
		
		float[][] output = new float[2][];
		
		//Split the training data into inputs and outputs
		String[] splitArgs = args.split( "|" );
		
		System.out.println( splitArgs[0] );
		
		//Some basic error checking
		if( splitArgs.length != 2 ) {
			System.out.println( "Error: training data is malformed" );
			return null;
		}
		
		//Clean up the split data
		splitArgs[0] = splitArgs[0].trim();
		splitArgs[1] = splitArgs[1].trim();
		
		//Split the input data into an array
		String[] inputStrings = splitArgs[0].split( "," );
		
		//Split the output data into an array
		String[] outputStrings = splitArgs[1].split( "," );
		
		
		for (int i = 0; i < inputStrings.length; i++) {
			System.out.println( inputStrings[i] );
		}
		
		System.out.println( "------" );
		
		for (int i = 0; i < outputStrings.length; i++) {
			System.out.println( outputStrings[i] );
		}
		
		return output;
		
	}
	
}
