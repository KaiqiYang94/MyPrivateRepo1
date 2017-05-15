JFLAGS = -cp
JC = javac
JVM= java
CPATH= .:./exude-0.0.3.jar
MPJPATH= .:$MPJ_HOME/lib/mpj.jar:./gson.2.6.2.jar
FILE=

.SUFFIXES: #.java .class

.java.class:
		$(JC) $(JFLAGS) $(MPJPATH) getAttributes.java

CLASSES = \
		getAttributes.java

default: classes


javac: 
	$(JC) $(JFLAGS) $(CPATH) getAttributes.java

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class
run:
		$(JVM) $(JFLAGS) $(CPATH) getAttributes
