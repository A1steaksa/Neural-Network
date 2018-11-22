package Network;

public class OutputLayer {

	private int inputCount;

	private int outputCount;

	private Network parent;

	private OutputNeuron[] outputNeurons;

	float[] inputs;
	
	float[] outputs;

	public OutputLayer( Network parent, int outputCount ) {
		this.outputCount = outputCount;
		this.parent = parent;
	}

	//Called to initialize this output layer
	public void init() {

		//Get input count from the last hidden layer
		inputCount = parent.getHiddenLayers()[ parent.getHiddenLayers().length - 1 ].getNeuronCount();

		//Create our output neurons
		for (int i = 0; i < outputCount; i++) {
			outputNeurons[ i ] = new OutputNeuron( this );
		}

		//Init them
		for (int i = 0; i < outputCount; i++) {
			outputNeurons[ i ].init();
		}

	}

	//Calculates the final output from the network
	public float[] calculateFinalOutput(){

		//First, we need to get all of our inputs from the last hidden layer
		inputs = parent.getHiddenLayers()[ parent.getHiddenLayers().length - 1 ].getOutputs();

		//Then we get output from each output neuron and store them in our outputs array
		for (int i = 0; i < inputs.length; i++) {
			outputs[ i ] = outputNeurons[ i ].calculateOutput();
		}
		
		//Return the outputs we generated
		return outputs;

	}

	//Getter for an input
	public float getInput( int inputIndex ){
		return inputs[ inputIndex ];
	}

	//Getter for input count
	public int getInputCount(){
		return inputCount;
	}

}
