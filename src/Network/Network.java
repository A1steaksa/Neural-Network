package Network;

public class Network {
	
	public InputLayer inputLayer;
	
	public HiddenLayer[] hiddenLayers;
	
	public OutputLayer outputLayer;
	
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
	
	//Initializes any values in the network that ned to be initialized
	public void init() {
		
		//Initialize all layers
		
		inputLayer.init();
		
		for (int i = 0; i < hiddenLayers.length; i++) {
			hiddenLayers[i].init();
		}
		
		outputLayer.init();
		
	}
}
