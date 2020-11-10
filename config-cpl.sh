#!/bin/bash

###############################################################################
# Script Name	: config-cpl.sh
# Description	: Configuration script for the Compilers Course
# Args		: -
# Author	: Madalina Boboc
# Email		: madaeboboc@gmail.com
###############################################################################

# Install ANLTR
echo "Installing ANTLR 4.8..." 
cd /usr/local/lib
sudo curl -O http://www.antlr.org/download/antlr-4.8-complete.jar

# Install Java-14, Spim, some random library
echo "Installing Java 14..."
sudo apt-get install openjdk-14-jdk spim libxkbcommon-x11-0 -y

# Install QtSpim
curl -L https://sourceforge.net/projects/spimsimulator/files/qtspim_9.1.22_linux64.deb/download --output qtspim_9.1.22_linux64.deb
sudo dpkg -i qtspim_9.1.22_linux64.deb

# Install Cool interpreter
curl https://curs.upb.ro/mod/resource/view.php?id=42347 --output cool-interpreters.zip
unzip cool-interpreters.zip
cd cool-interpreters.zip
chmod +x cool
cd ..

# Install IntelliJ
sudo snap install intellij-idea-educational --classic

# Install ANTLR4 Plugin Intellij
echo "Installation complete. Now you need to install the ANTLR4 Plugin in Intellij"

# Set alias for intellij command
echo "Set alias in ~/.bashrc for intellij-idea-educational to be only intellij. Check out https://github.com/mboboc/scripts-and-fun for example of linux bashrc file."
