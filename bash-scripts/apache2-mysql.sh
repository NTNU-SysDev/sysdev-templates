#!/bin/bash

if [ $(id -u) == 0 ]; then
        echo
        echo
        echo '################################'
        echo '# Apache2 & MySQL Setup Script #'
        echo '################################'
        echo
        echo 'This will download/install:'
        echo ' - Apache2'
        echo ' - MySQL Server'
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

# Updates system and installs Apache2
apt-get update -y
apt-get upgrade -y
apt-get install apache2 -y


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

# Installation finished
echo
echo "Installation success!"
echo