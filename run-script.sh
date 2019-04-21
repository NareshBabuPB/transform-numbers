#!/usr/bin/env bash

echo "Installing maven and building the app..."
apk add --no-cache maven > maven_install.log && mvn clean package > build.log
echo "Installation complete..."

java -jar ./target/*.jar DriverProgram
