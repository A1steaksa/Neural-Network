package Network;

import Main.Util;

public class HiddenLayer {

	private int neuronCount;
	
	private Network parent;
	
	private int layerNumber;
	
	private int inputCount;
	
	private Neuron[] neurons;
	
	public HiddenLayer( Network parent, int neuronCount, int layerNumber ) {
		this.neuronCount = neuronCount;
		this.parent = parent;
		this.layerNumber = layerNumber;
	}
	
	//Called once the network has been created
	public void init() {
		//Our input count is the number of neurons in the previous layer
		
		//If this is the first layer, then layer count is input count
		if( layerNumber == 0 ) {
			inputCount = parent.inputLayer.inputCount;
		}else {
			inputCount = parent.hiddenLayers[layerNumber - 1].neuronCount;
		}
		
		//Create this layer's neurons
		neurons = new Neuron[ neuronCount ];
		
		for (int i = 0; i < neurons.length; i++) {
			neurons[ i ] = new Neuron( this );
		}
		
		//Now that all neurons are made, initialize them
		for (int i = 0; i < neuronCount; i++) {
			neurons[ i ].init();
			
			System.out.println( neurons[ i ].getWeight() );
			
		}
		
	}
	
	//Getter for input count
	public int getInputCount(){
		return inputCount;
	}
	
	//Getter for neuronCount
	public int getNeuronCount() {
		
		return neuronCount;
		
	}

}
