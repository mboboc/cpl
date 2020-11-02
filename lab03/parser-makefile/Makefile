ANTLR4=java -jar /usr/local/lib/antlr-4.8-complete.jar

.PHONY: run clean build

build: Test.class

Test.class: Test.java CPLangParser.java CPLangLexer.java
	javac $^

CPLangParser.java: CPLangParser.g4 CPLangLexer.java
	$(ANTLR4) -visitor -listener CPLangParser.g4

CPLangLexer.java: CPLangLexer.g4
	$(ANTLR4) $^

clean:
	rm -f *.class CPLang*.java *.interp *.tokens

run: Test.class
	java Test
