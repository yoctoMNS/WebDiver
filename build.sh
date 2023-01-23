#!/bin/bash
javac -d ./bin -encoding utf8 -classpath ./res -sourcepath ./src ./src/jp/xdomain/html/yoctomns/GameLauncher.java
if [ $? -eq 0 ]; then
    java -classpath ./res:./bin jp.xdomain.html.yoctomns.GameLauncher
fi
