#!/bin/bash

if [ $(id -u) == 0 ]; then
        echo
        echo
        echo '###########################'
        echo '# Spring-API Setup Script #'
        echo '###########################'
        echo
        echo 'This will download/install:'
        echo ' - Java 8'
        echo ' - MySQL Server'
        echo ' - Spring Boot API Server'
        echo
        read -n1 -p "Do you wish to continue? (Press y|Y for Yes, any other key for No): " CONTINUE
        echo
else
        echo "This script requires sudo privileges."
        exit
fi

if [ $CONTINUE != "y" ] && [ $CONTINUE != "Y" ]; then
        exit
fi

# Stores the MySQL password
read -s -p "Enter a new password for MySQL: " MYSQL_PASS
echo
read -s -p "Re-enter the password: " MYSQL_CONFIRM_PASS
echo

if [ $MYSQL_PASS != $MYSQL_CONFIRM_PASS ]; then
        echo "Passwords are not equal."
        exit
fi

# Updates system and adds JRE8
apt-get update -y
apt-get upgrade -y
sudo apt-get install openjdk-8-jre -y


# Installs and configures MySQL
debconf-set-selections <<< "mysql-server mysql-server/root_password password $MYSQL_PASS"
debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $MYSQL_PASS"

apt-get install expect -y
apt-get install mysql-server -y

expect -f - <<-CONF
        set timeout 1
        spawn mysql_secure_installation

	expect "Enter password for user root: "
	send "$MYSQL_PASS\r"

        expect "Press y|Y for Yes, any other key for No: "
        send "n\r"

	expect "Change the password for root ? ((Press y|Y for Yes, any other key for No) : "
	send "n\r"

        expect "Remove anonymous users? (Press y|Y for Yes, any other key for No) : "
        send "y\r"

        expect "Disallow root login remotely? (Press y|Y for Yes, any other key for No) : "
        send "n\r"

        expect "Remove test database and access to it? (Press y|Y for Yes, any other key for No) : "
        send "y\r"

        expect "Reload privilege tables now? (Press y|Y for Yes, any other key for No) : "
        send "y\r"

        expect eof
CONF


# Creates user table for the api
mysql -uroot -p"$MYSQL_PASS" <<-QUERY
        DROP DATABASE IF EXISTS spring_api;
        CREATE DATABASE spring_api;
        USE spring_api;
        CREATE TABLE user (
                name VARCHAR(255) NOT NULL,
                email VARCHAR(255) PRIMARY KEY,
                phone VARCHAR(8) UNIQUE NOT NULL,
                age INT(3) NOT NULL
        );
QUERY

# Downloads the Spring API server and its properties
wget -P /var/www/spring-api/ "https://archive.libane.tk/spring-api/server.jar"
wget -P /var/www/spring-api/ "https://archive.libane.tk/spring-api/application.properties"

# Edits the MySQL password in properties
if [ -f "/var/www/spring-api/server.jar" ] && [ -f "/var/www/spring-api/application.properties" ]; then
        sed -i "s/%MYSQL_PASS%/${MYSQL_PASS}/g" /var/www/spring-api/application.properties
else
        echo "Couldn't download files. Aborting..."
        exit
fi

# Installation finished
echo
echo "Installation success!"
echo "You can run the server by "
echo "executing 'java -Dspring.config.location=/var/www/spring-api/application.properties -jar /var/www/spring-api/server.jar'"
echo
echo "If you want to be able to use the terminal while running the server,"
echo "run it on a new session using the 'screen' command."
echo

#java -Dspring.config.location=/var/www/spring-api/application.properties -jar /var/www/spring-api/server.jar
