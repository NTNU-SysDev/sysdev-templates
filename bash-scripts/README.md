## Scripts
Automatically sets up your environment/application on Ubuntu server.

#### Apache & MySQL Script
Installs Apache and MySQL, and creates a user table in the database.

Your web directory: `/var/www/html`

#### Spring Boot API Server Script
This script will install Java 8, MySQL server and the Spring Boot server.
Along with that, it will configure the API server and inject the required user table.

Configure file is located at `/var/www/spring-api/application.properties`

### How To Use
Download the scripts and run `bash [file]` (with elevated permissions) on the terminal. 
Further instructions will be shown on-screen.

### Quick install

#### Apache & MySQL
```bash
wget "https://raw.githubusercontent.com/NTNU-SysDev/sysdev-templates/master/bash-scripts/apache2-mysql.sh"
sudo bash apache2-mysql.sh
```

#### Spring Boot API Server
```bash
wget "https://raw.githubusercontent.com/NTNU-SysDev/sysdev-templates/master/bash-scripts/springboot.sh"
sudo bash springboot.sh
```
