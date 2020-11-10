# cpl
Compilers Laboratories.

Use config-cpl.sh to install all tools needed for the CPL labs. 

### Configure ANTLR4 for command line

To make ANTLR work in command line you must set de CLASSPATH env variable.

```
cd /usr/local/lib
sudo curl -O https://www.antlr.org/download/antlr-4.8-complete.jar
export CLASSPATH=".:/usr/local/lib/antlr-4.8-complete.jar:$CLASSPATH"
alias antlr4='java -jar /usr/local/lib/antlr-4.8-complete.jar'
alias grun='java org.antlr.v4.gui.TestRig'
grun CPLang prog -gui program.txt
```

You can add the last command from above in the shell startup
script (\~/.bash_profile for bash shell):

It’s critical to have the dot, the current directory identifier, somewhere in the
CLASSPATH. Without that, the Java compiler and Java virtual machine won’t
see classes in the current directory.

### Make Intellij project work - Intellij Archive
1. Make sure you donwload .jre and .jdk of Java >= 11. To avoid any problems intall Java15. 
2. File -> Project structure -> Project -> Set Java version
3. File -> Project structure -> Libriaries -> Add .jar from antlr (you can find it on google)
4. File -> Project structure -> Modules -> Set Java JDK same version as above 

### How to build and run
1. To run in Intellij: Right click on .g4 -> Generate ANTLR Recognizer
2. Click Build & Run

##### Lab0 - Getting started
Check out the config-cpl.sh.

```
chmod +x config-cpl.sh
```

##### Lab1 - Cool exercise

##### Lab2 - Lexical analysis

##### Lab3 - Syntactic analysis
To see the tree on GUI run the following command:

```
alias grun='java org.antlr.v4.gui.TestRig'
grun CPLang prog -gui program.txt
```