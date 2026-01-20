#!/bin/bash
cd "$(dirname "$0")"
echo "Compiling chessbot..."
mvn clean compile -q
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi
echo ""
echo "Starting ChessBot..."
echo ""
java -cp "target/classes" com.chessbot.Main
