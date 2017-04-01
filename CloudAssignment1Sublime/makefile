JFLAGS = -cp
JC = javac
JVM= java 
CPATH= ./gson.2.6.2.jar:./mpi.jar:.
MPJPATH= ./gson.2.6.2.jar:/Users/KaiqiYang/Documents/MasterCourse/Cloud/MPI_express/mpj-v0_44/lib/mpj.jar:.
FILE=

.SUFFIXES: .java .class

.java.class:
		$(JC) $(JFLAGS) $(CPATH) $*.java

CLASSES = \
		TwitterGeoProcessing.java\
		Coordinate.java\
		GeoGrid.java\
		MPICommands.java

default: classes

mpjc: 
	$(JC) $(JFLAGS) $(MPJPATH) *.java

mpjrun: 	
	mpjrun.sh -np -1 TwitterGeoProcessing


classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
javarun:
		$(JVM) $(JFLAGS) $(CPATH) TwitterGeoProcessing

mpirun:
		mpirun -np 8 java $(JFLAGS) $(CPATH) TwitterGeoProcessing