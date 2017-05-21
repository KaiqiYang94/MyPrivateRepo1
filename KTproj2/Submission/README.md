
The results of the test data is in the folder TestResults.

1. run the following to compile the code 
		javac getNegAttributes.java
		javac getPosAttributes.java

2. run the following to output the generated attributes
		java getPosAttributes > FileName1.txt
 		java getNegAttributes > FileName2.txt
 	Then the attribtues will be ranked based on the purity score which is mentioned in the report can be found in those two files.
 	negAttr.txt and posAttr.txt represent the respective example output from the program.
 	The user could pick the top ones to put in the GenerateArff.java.
 	And remove the duplicated attributes.

3. 	In the GenerateArff.java, the user would be able to change the mode inside of the program. The modes includes dev, train, and test.
	Run the following command.
		javac GenerateArff.java
		java GenerateArff > FileName.arff
	The programm will out put the arff file.
	ArffDevID.arff, ArffTrainID.arff and ArffTestID.arff are the example output from the program.
	In addition, the ArffDevNoID.arff and ArffTrainNoID.arff are almost the same output for the dev and train tweets the only difference is without the feature "id".