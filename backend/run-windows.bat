@echo off
setlocal
cd /d "%~dp0"
echo Make sure MySQL Server is running and the paisatrack database exists.
set /p DB_PASSWORD=Enter your MySQL root password (press Enter if blank):
set DB_USERNAME=root
set DB_PASSWORD=%DB_PASSWORD%
call mvn spring-boot:run
pause
