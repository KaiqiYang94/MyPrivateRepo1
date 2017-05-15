JFLAGS = -cp
JC = javac
JVM= java
CPATH= .
FILE=

.SUFFIXES: #.java .class

.java.class:
		$(JC) $(JFLAGS) $(CPATH) getAttributes.java

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
