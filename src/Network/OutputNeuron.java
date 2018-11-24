package Network;

import Main.Util;

public class OutputNeuron extends Neuron{
	
	private OutputLayer parent;
	
	private float output;
	
	public OutputNeuron( OutputLayer parent, float learningRate ){
		
		this.parent = parent;
		this.learningRate = learningRate;
		
	}
	
	@Override
	//Called to initialize this neuron
	public void init() {
		
		//Get input count
		inputCount = parent.getInputCount();
		
		//Set up weights
		weights = new float[ inputCount ];
		for (int i = 0; i < weights.length; i++) {
			setWeight( i, Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount ) );
			Util.print( "Output Layer Neuron #" + neuronNumber + ", Input #" + i +", Weight: " + getWeight( i ) );
		}
		
//		weights[ 0 ] = -1.2f;
//		weights[ 1 ] = 1.1f;
//		activationThreshold = 0.3f;
		
		
		//Generate an activation threshold
		activationThreshold = Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount );
		
	}
	
	@Override
	public float calculateOutput(){
		
		//Sum up every input
		float sum = 0;
		for (int i = 0; i < parent.getInputCount(); i++) {
			//Take this input, times its weight, minus our activation threshold
			sum += parent.getInput( i ) * weights[ i ];
			
		}
		
		//Subtract the activation threshold
		sum -= activationThreshold;
		
		//Run that sum through our activation function
		output = activationFunction( sum );
		
		return output;
	}
	
	//Updates the weights of this output neuron
	public void updateWeights( float expectedOutput ){
		
		//Get the error gradient
		float errorGradient = getErrorGradient( expectedOutput );
		
		//Update every input's weight
		for (int i = 0; i < parent.getInputCount(); i++) {
			
			//Weight change amount is learningRate * input * errorGradient
			float weightChange = learningRate * parent.getInput( i ) * errorGradient;
			
			//Update this weight
			weights[ i ] += weightChange;
			
		}
		
	}
	
	//Calculates the error for this output neuron
	public float getError( float expectedOutput ){
		
		//Error is expected output - actual output
		float error = expectedOutput - output;
		
		return error;
	}
	
	//Calculates the error gradient for this output neuron
	public float getErrorGradient( float expectedOutput ){
		
		float gradient = output * ( 1 - output ) * getError( expectedOutput );
		
		//Error gradient is output * ( 1 - output ) * error
		return gradient;
		
	}
	
	
	
}
