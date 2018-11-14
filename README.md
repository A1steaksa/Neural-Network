# Neural-Network

This is a simple, somewhat generalized system for creating multilayer perceptron networks

To get started you'll need to know the format of network definition files, training data files, and data files.

# Network definition files
Network definition files are what defines the layout of a network.  If you want to have 3 inputs, a hidden layer with 4 neurons, a layer with 5 neurons, and an output layer with 2 outputs, this is the file you would put that information into.

The format is:
Input layer count, hidden layer 1 neuron count, hidden layer 2 neuron cout, ..., hidden layer n neuron count, output layer count

For example, the example above would be:
3, 4, 5, 2
