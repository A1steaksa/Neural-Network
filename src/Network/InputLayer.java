package Network;

public class InputLayer {

	private int inputCount;
	
	private float[] inputs;
	
	public InputLayer( int inputCount ) {
		
		this.inputCount = inputCount;
		
	}

	public void init() {
		
	}
	
	//Getter for input count
	public int getInputCount(){
		return inputCount;
	}
	
	//Setter for inputs
	public void setInputs( float[] newInputs ){
		inputs = newInputs;
	}
	
	//Getter for an input
	public float getInput( int inputIndex ){
		return inputs[ inputIndex ];
	}
	
	//Getter for inputs
	public float[] getInputs(){
		return inputs;
	}
	
}
