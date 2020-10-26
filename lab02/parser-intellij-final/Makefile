# Bash aliases are not expanded inside non-interactive shells, so we have
# to manually define the meaning of "ANTLR4".
ANTLR4=java -jar /home/mada/an4/cpl/lab02/antlr-4.8-complete.jar
.PHONY: run clean build


build: Test.class


# Delete the auto-generated files
clean:
	rm -f *.interp *.tokens *.class CPLangLexer.java


# Test.java is hardcoded to read the input from "program.txt".
# Make sure that the CLASSPATH contains the current folder and the antlr4 jar.
run: Test.class
	java Test


Test.class: Test.java CPLangLexer.java
	javac $^


CPLangLexer.java: CPLangLexer.g4
	$(ANTLR4) $^
