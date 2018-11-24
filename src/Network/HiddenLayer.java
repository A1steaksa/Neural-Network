package Network;

import Main.Util;

public class HiddenLayer {

	private int neuronCount;

	private int layerNumber;

	private int inputCount;

	private float[] inputs;

	private float[] outputs;

	private Network parent;

	private Neuron[] neurons;

	public HiddenLayer( Network parent, int neuronCount, int layerNumber ) {
		this.neuronCount = neuronCount;
		this.parent = parent;
		this.layerNumber = layerNumber;
	}

	//Called once the network has been created
	public void init() {
		//Our input count is the number of neurons in the previous layer

		//Set up layer counts
		//If this is the first layer, then layer count is input count
		if( layerNumber == 0 ) {
			inputCount = parent.getInputLayer().getInputCount();
		}else {
			inputCount = parent.getHiddenLayers()[ layerNumber - 1 ].neuronCount;
		}

		//Create this layer's neurons
		neurons = new Neuron[ neuronCount ];

		for (int i = 0; i < neurons.length; i++) {
			neurons[ i ] = new Neuron( this, i, 0.2f );
		}

		//Now that all neurons are made, initialize them
		for (int i = 0; i < neuronCount; i++) {
			neurons[ i ].init();
		}

	}

	//Calculates the outputs for this hidden layer
	public void calculateOutputs(){

		//Create the outputs array
		outputs = new float[ neurons.length ];
		
		//Get this layer's inputs
		//If this is the first layer, we get our input from the input layer
		if( layerNumber == 0 ){
			inputs = parent.getInputLayer().getInputs();
		}else{
			//Otherwise, we get our inputs from the previous layer's outputs
			inputs = parent.getHiddenLayers()[ layerNumber - 1 ].getOutputs();
		}

		//Have each neuron in this layer calculate its output and save to outputs
		for (int i = 0; i < neurons.length; i++) {
			outputs[ i ] = neurons[ i ].calculateOutput();
		}
		
	}
	
	//Updates this layer's weights
	public void updateWeights(){
		
		//Update every neuron's weights
		for (int i = 0; i < neurons.length; i++) {
			neurons[ i ].updateWeights();
		}
		
	}

	//Getter for input count
	public int getInputCount(){
		return inputCount;
	}

	//Getter for an input
	public float getInput( int inputIndex ){
		return inputs[ inputIndex ];
	}

	//Getter for neuronCount
	public int getNeuronCount() {
		return neuronCount;
	}

	//Getter for layer number
	public int getLayerNumber(){
		return layerNumber;
	}

	//Getter for outputs
	public float[] getOutputs(){
		return outputs;
	}
	
	//Getter for parent
	public Network getParent(){
		return parent;
	}
	
	//Getter for neurons
	public Neuron[] getNeurons(){
		return neurons;
	}

}
