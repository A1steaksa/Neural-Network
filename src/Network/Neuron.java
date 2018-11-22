package Network;

import Main.Util;

public class Neuron {

	protected int inputCount;
	
	protected int neuronNumber;
	
	protected float activationThreshold;
	
	protected HiddenLayer parent;
	
	protected float[] weights;
	
	public Neuron( HiddenLayer parent, int neuronNumber ){
		this.parent = parent;
		this.neuronNumber = neuronNumber;
	}
	
	public Neuron(){
		
	}
	
	//Called to initialize this neuron
	public void init() {
		
		//Get input count
		inputCount = parent.getInputCount();
		
		//Set up weights
		weights = new float[ inputCount ];
		for (int i = 0; i < weights.length; i++) {
			setWeight( i, Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount ) );
			Util.print( "Hidden Layer #" + this.parent.getLayerNumber() + ", Neuron #" + neuronNumber + ", Input #" + i +", Weight: " + getWeight( i ) );
		}
		
		//Generate an activation threshold
		activationThreshold = Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount );
		
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
