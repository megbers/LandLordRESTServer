#!/bin/bash
echo "==> Stopping Tomcat"
cd /mindtap/tomcat/bin
./shutdown.sh

echo "==> Removing Old Application"
cd /mindtap/tomcat/webapps
rm -rf LandLordWebServices*

echo "==> Building Application"
cd /Users/megbers/Documents/workspaces/landlord2/LandLordWebServices\ Maven\ Webapp/
mvn clean install

echo "==> Deploying Application"
cp ~/Documents/workspaces/landlord2/LandLordWebServices\ Maven\ Webapp/target/LandLordWebServices.war /mindtap/tomcat/webapps/

echo "==> Starting Tomcat"
cd /mindtap/tomcat/bin
./startup.sh
