#!/bin/bash
/usr/bin/git pull
rm -rf build/classes/main
mkdir -p build/classes/main
javac -cp lib/*:. -d build/classes/main src/net/pla1/pojo/response/*.java src/net/pla1/pojo/request/*.java src/net/pla1/googlevisiontesting01/*.java
java -cp build/classes/main:.:lib/* net.pla1.googlevisiontesting01.Main

