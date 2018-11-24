package Network;

import Main.Util;

public class Neuron {

	protected int inputCount;
	
	protected int neuronNumber;
	
	protected float activationThreshold;
	
	protected HiddenLayer parent;
	
	protected float[] weights;
	
	protected float learningRate;
	
	public Neuron( HiddenLayer parent, int neuronNumber, float learningRate ){
		this.parent = parent;
		this.neuronNumber = neuronNumber;
		this.learningRate = learningRate;
		
		Util.print( "constructor " + this );
	}
	
	public Neuron(){
		
	}
	
	//Called to initialize this neuron
	public void init() {
		
		//Get input count
		inputCount = parent.getInputCount();
		
		//Create a learning rate
		//This is pulled from HW4 and is, as far as I know, completely arbitrary
		learningRate = 0.2f;
		
		//Set up weights
		weights = new float[ inputCount ];
		
		if( neuronNumber == 0 ){
			weights[ 0 ] = 0.5f;
			weights[ 1 ] = 0.4f;
			
			activationThreshold = 0.8f;
		}else{
			weights[ 0 ] = 0.9f;
			weights[ 1 ] = 1.0f;
			
			activationThreshold = -0.1f;
		}
		
//		for (int i = 0; i < weights.length; i++) {
//			setWeight( i, Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount ) );
//			Util.print( "Hidden Layer #" + this.parent.getLayerNumber() + ", Neuron #" + neuronNumber + ", Input #" + i +", Weight: " + getWeight( i ) );
//		}
		
		//Generate an activation threshold
//		activationThreshold = Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount );
		
	}
	
	//This neuron's activation function
	//Default is sigmoid but this can be overridden
	public float activationFunction( float x ){
		return 1f/( 1f + (float) Math.pow( Math.E, -x ) );
	}
	
	//Calculate this neuron's output
	public float calculateOutput(){
		
		//Sum all of the inputs times their weight
		float sum = 0;
		
		for (int i = 0; i < inputCount; i++) {
			
			sum += parent.getInput( i ) * weights[ i ];
		}
		
		//Subtract our activation threshold from the total
		sum -= activationThreshold;
		
		
		//Run the final sum through our activation function
		float output = activationFunction( sum );
		
		return output;
		
	}
	
	//Updates this neuron's weights
	public void updateWeights(){
		
		//Get the error gradient for this neuron
		float errorGradient = getErrorGradient();
		
		//Get the weight correction deltas for every input
		for (int i = 0; i < parent.getInputCount(); i++) {
			
			//Calculate our delta
			float weightDelta = learningRate * parent.getInput( i ) * errorGradient;
			
			//Update this weight with the delta
			weights[ i ] += weightDelta;
			
		}
	}
	
	//Calculates the error gradient
	public float getErrorGradient(){
		
		//Error gradient is output * ( 1 - output ) * summation for each of the next (numerically) layer's neuron's error gradient * their weight 
		
		//Get this neuron's output for the first part
		float output = calculateOutput();
		
		//Calculate the first part
		float firstPart = output * ( 1 - output );
		
		//Begin the summation
		
		//If the next layer is the output layer, we need to treat that a little bit differently
		
		if( parent.getLayerNumber() == parent.getParent().getHiddenLayers().length - 1 ){
			
			//Get the output layer
			OutputLayer outputLayer = parent.getParent().getOutputLayer();
			
			//Go over every neuron in the next layer
			float sum = 0;
			for (int i = 0; i < outputLayer.getOutputNeuronCount(); i++) {
				
				//Get the error gradient times the weight and add it to our sum
				//We can find the weight by looking up our neuron number in the weights array of the neuron in the next layer
				
				//Get the expected output for this output neuron from the training data
				float expectedOutput = parent.getParent().getOutputLayer().getExpectedOutputs()[ i ];
				float gradient = outputLayer.getOutputNeurons()[ i ].getErrorGradient( expectedOutput );
				float weight = outputLayer.getOutputNeurons()[ i ].getWeight( neuronNumber );
				
				sum += gradient * weight;
				
			}
			
			//Calculate output which is the first part times the sum
			return firstPart * sum;
			
		}else{
			
			//Get the next layer
			HiddenLayer nextLayer = parent.getParent().getHiddenLayers()[ parent.getLayerNumber() + 1 ];
			
			//Go over every neuron in the next layer
			float sum = 0;
			for (int i = 0; i < nextLayer.getNeuronCount(); i++) {
				
				//Get the error gradient times the weight and add it to our sum
				//We can find the weight by looking up our neuron number in the weights array of the neuron in the next layer
				sum += nextLayer.getNeurons()[ i ].getErrorGradient() * nextLayer.getNeurons()[ i ].getWeight( neuronNumber );
				
			}
			
			//Calculate output which is the first part times the sum
			return firstPart * sum;
			
		}
		
	}
	
	//Setter for weight
	public void setWeight( int inputIndex, float newWeight ){
		weights[ inputIndex ] = newWeight;
	}
	
	//Getter for weight
	public float getWeight( int inputIndex ){
		return weights[ inputIndex ];
	}
	
	//Getter for input count
	public int inputCount(){
		return inputCount;
	}
}
