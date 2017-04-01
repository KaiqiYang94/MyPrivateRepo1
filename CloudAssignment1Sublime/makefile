JFLAGS = -cp
JC = javac
JVM= java
CPATH= ./gson.2.6.2.jar:./mpi.jar:.
MPJPATH= .:$MPJ_HOME/lib/mpj.jar:./gson.2.6.2.jar
FILE=

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $(MPJPATH) $*.java

CLASSES = \
		TwitterGeoProcessing.java\
		Coordinate.java\
		GeoGrid.java\
		MPICommands.java

default: classes


mpjc: 
	javac -cp .:$MPJ_HOME/lib/mpj.jar:./gson.2.6.2.jar TwitterGeoProcessing.java
	#$(JC) $(JFLAGS) $(MPJPATH) TwitterGeoProcessing.java

mpjrun:
	mpjrun.sh -np 1 -cp .:PJ_HOME/lib/mpj.jar:./gson.2.6.2.jar TwitterGeoProcessing

	#mpjrun.sh -np 1  $(JFLAGS) $(MPJPATH) TwitterGeoProcessing


classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
javarun:
		$(JVM) $(JFLAGS) $(CPATH) TwitterGeoProcessing

mpirun:
		mpirun -np 8 java $(JFLAGS) $(CPATH) TwitterGeoProcessing