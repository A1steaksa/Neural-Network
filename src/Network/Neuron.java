package Network;

import Main.Util;

public class Neuron {

	
	private HiddenLayer parent;
	
	private float weight;
	
	public Neuron( HiddenLayer parent ){
		this.parent = parent;
	}
	
	public void init() {
		
		//Set up weights
		setWeight( Util.randomRange( - 2.4f / (float) parent.getInputCount() , 2.4f / (float) parent.getInputCount() ) );
		
	}
	
	//Setter for weight
	public void setWeight( float newWeight ){
		weight = newWeight;
	}
	
	//Getter for weight
	public float getWeight(){
		return weight;
	}
}
