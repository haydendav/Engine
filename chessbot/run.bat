@echo off
cd /d "%~dp0"
echo Compiling chessbot...
call mvn clean compile -q
if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b %ERRORLEVEL%
)
echo.
echo Starting ChessBot...
echo.
java -cp "target\classes" com.chessbot.Main
pause
