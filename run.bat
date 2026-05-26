@echo off 
cd /d %%~dp0 
echo ========================== 
echo Building Project... 
echo ========================== 
javac -cp "libs\mysql-connector-j-9.5.0.jar" -d . src\Main.java 
java -cp ".;libs\mysql-connector-j-9.5.0.jar" Main 
pause 
