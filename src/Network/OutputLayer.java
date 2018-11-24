package Network;

import Main.Util;

public class OutputLayer {

	private int inputCount;

	private int outputCount;

	private Network parent;

	private OutputNeuron[] outputNeurons;

	private float[] inputs;
	
	private float[] outputs;
	
	private float[] expectedOutputs;

	public OutputLayer( Network parent, int outputCount ) {
		this.outputCount = outputCount;
		this.parent = parent;
	}

	//Called to initialize this output layer
	public void init() {

		//Get input count from the last hidden layer
		inputCount = parent.getHiddenLayers()[ parent.getHiddenLayers().length - 1 ].getNeuronCount();

		//Create our output neurons
		outputNeurons = new OutputNeuron[ outputCount ];
		
		for (int i = 0; i < outputCount; i++) {
			outputNeurons[ i ] = new OutputNeuron( this, 0.1f );
		}

		//Init them
		for (int i = 0; i < outputCount; i++) {
			outputNeurons[ i ].init();
		}

	}
	
	//Updates the weights of all output neurons
	public void updateWeights( float[] trainingData ){
		
		//Cache the training data for later
		expectedOutputs = trainingData;
		
		//Update each output neuron
		for (int i = 0; i < outputNeurons.length; i++) {
			//Pass in the expected output for that neuron
			outputNeurons[ i ].updateWeights( trainingData[ i ] );
		}
		
	}

	//Calculates the final output from the network
	public float[] calculateFinalOutput(){

		//Create the outputs array
		outputs = new float[ outputNeurons.length ];
		
		//First, we need to get all of our inputs from the last hidden layer
		inputs = parent.getHiddenLayers()[ parent.getHiddenLayers().length - 1 ].getOutputs();

		//Then we get output from each output neuron and store them in our outputs array
		for (int i = 0; i < outputCount; i++) {
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
	
	//Getter for output neuron count
	public int getOutputNeuronCount(){
		return outputNeurons.length;
	}
	
	//Getter for output neurons
	public OutputNeuron[] getOutputNeurons(){
		return outputNeurons;
	}
	
	//Getter for expected outputs
	public float[] getExpectedOutputs(){
		return expectedOutputs;
	}

}
