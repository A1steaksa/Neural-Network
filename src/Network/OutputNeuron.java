package Network;

import Main.Util;

public class OutputNeuron extends Neuron{
	
	OutputLayer parent;
	
	public OutputNeuron( OutputLayer parent ){
		
		this.parent = parent;
		
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
		
		//Generate an activation threshold
		activationThreshold = Util.randomRange( - 2.4f / (float) inputCount, 2.4f / (float) inputCount );
		
	}
	
	@Override
	public float calculateOutput(){
		
		//Sum up every input
		float sum = 0;
		for (int i = 0; i < parent.getInputCount(); i++) {
			//Take this input, times its weight, minus our activation threshold
			sum += parent.getInput( i ) * weights[ i ] - activationThreshold;	
		}
		
		//Run that sum through our activation function
		return activationFunction( sum );
		
	}
	
	
	
}
