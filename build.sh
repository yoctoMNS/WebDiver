#!/bin/bash
javac -Xlint:deprecation\
    -Xdiags:verbose\
    -Xlint:unchecked\
    -deprecation\
    -d ./bin\
    -encoding utf8\
    -classpath ./res\
    -sourcepath ./src\
    ./src/jp/xdomain/html/yoctomns/GameLauncher.java
if [ $? -eq 0 ]; then
    java -classpath ./res:./bin jp.xdomain.html.yoctomns.GameLauncher
else
    printf '\033[31m%s\n\033[0m' 'build is failed.'
fi
