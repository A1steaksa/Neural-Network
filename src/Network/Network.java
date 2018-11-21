package Network;

import java.util.Arrays;

public class Network {
	
	private InputLayer inputLayer;
	
	private HiddenLayer[] hiddenLayers;
	
	private OutputLayer outputLayer;
	
	private int epoch = 0;
	
	//Args are in the format
	//Input layer count, hidden layer 1 neuron count, hidden layer 2 neuron count, ..., hidden layer n neuron count, output layer count
	public Network( int[] args ) {
		
		//Create the input layer
		inputLayer = new InputLayer( args[0] );
		
		//Create the hidden layer array
		hiddenLayers = new HiddenLayer[ args.length - 2 ];
		
		//Create the hidden layers
		for (int i = 1; i < args.length - 1; i++) {
			
			//Adjust the index for the hiddenLayers array
			int index = i - 1;
			
			//Create a new hidden layer with the size from the args
			hiddenLayers[index] = new HiddenLayer( this, args[i], index );
			
		}
		
		//Create the output layer
		outputLayer = new OutputLayer( args[ args.length - 1 ] );
		
	}
	
	//Called to initialize this network
	public void init() {
		
		//Initialize all layers
		
		inputLayer.init();
		
		for (int i = 0; i < hiddenLayers.length; i++) {
			hiddenLayers[i].init();
		}
		
		outputLayer.init();
		
	}
	
	//Called to activate the network and calculate output for it
	public void activate(){
		
		//Get outputs from every hidden layer in turn
		for (int i = 0; i < hiddenLayers.length; i++) {
			hiddenLayers[ i ].calculateOutputs();
		}
		
		//Calculate the final output from the output layer
		outputLayer.calculateFinalOutput();
		
	}
	
	//Getter for input layer
	public InputLayer getInputLayer(){
		return inputLayer;
	}
	
	//Getter for hidden layers
	public HiddenLayer[] getHiddenLayers(){
		return hiddenLayers;
	}
}
