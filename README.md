# Neural-Network

This is a simple, somewhat generalized system for creating multilayer perceptron networks

To get started you'll need to know the format of network definition files, training data files, and data files.

# Network Definition Files
Network definition files are what defines the layout of a network.  If you want to have 3 inputs, a hidden layer with 4 neurons, a hidden layer with 5 neurons, and an output layer with 2 outputs, this is the file you would put that information into.

The format is:
Input layer count, hidden layer 1 neuron count, hidden layer 2 neuron count, ..., hidden layer n neuron count, output layer count

For example, the example above would be:
3, 4, 5, 2

# Training Data Files
Training data files contain the data that you use to train the network.
You can have as many training cases in a given file as you want, separated by line.

The format is:
Input 1, input 2, ..., input n, |, output 1, output 2, ..., output n

For example, the XOR training file looks like:
0, 0, |, 0

0, 1, |, 1

1, 0, |, 1

0, 0, |, 0

# Data Files
This is where you put data that you want to run through the trained network.
You can have as many data items in a given file as you want, separated by line.

The format is:
Input 1, input 2, ..., input n

For example, an XOR data file might look like:
0, 0

0, 1

1, 0

0, 0
