#!/bin/sh
APPDIR="$(dirname -- "$(readlink -f -- "${0}")" )"
java -jar $APPDIR/target/QuizMundial-WebService-*.jar
