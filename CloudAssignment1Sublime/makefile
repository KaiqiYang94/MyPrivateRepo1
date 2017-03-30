JFLAGS = -cp
JC = javac
JVM= java 
CPATH= ./JAR/*:.
FILE=

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $(CPATH) $*.java

CLASSES = \
		Ring.java\
		TwitterGeoProcessing.java\
		Coordinate.java\
		GeoGrid.java\
		MPICommands.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
javarun:
		$(JVM) $(JFLAGS) $(CPATH) TwitterGeoProcessing

mpirun:
		mpirun -np 3 java $(JFLAGS) $(CPATH) TwitterGeoProcessing