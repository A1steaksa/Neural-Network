package Network;

import Main.Util;

public class HiddenLayer {

	private int neuronCount;
	
	private Network parent;
	
	private int layerNumber;
	
	private int inputCount;
	
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
		
		Util.print( inputCount );
		
	}
	
	//Getter for neuronCount
	public int getNeuronCount() {
		
		return neuronCount;
		
	}

}
