# lab03 

### Configure command line - Makefile Archive

```
export CLASSPATH=".:/usr/local/lib/antlr-4.8-complete.jar:$CLASSPATH"
sudo cp antlr-4.8-complete.jar /usr/local/lib/antlr-4.8-complete.jar

alias grun='java org.antlr.v4.gui.TestRig'
grun CPLang prog -gui program.txt
```


### Make Intellij project work - Intellij Archive
1. Make sure you donwload .jre and .jdk of Java >= 11. To avoid any problems intall Java15. 
2. File -> Project structure -> Project -> Set Java version
3. File -> Project structure -> Libriaries -> Add .jar from antlr (you can find it on google)
4. File -> Project structure -> Modules -> Set Java JDK same version as above 

### How to build and run
1. To run in Intellij: Right click on .g4 -> Generate ANTLR Recognizer
2. Click Build & Run