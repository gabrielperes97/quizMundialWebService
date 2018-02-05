#!/bin/sh
APPDIR="$(dirname -- "$(readlink -f -- "${0}")" )"
cd $APPDIR
java -jar $APPDIR/target/QuizMundial-WebService-*.jar
