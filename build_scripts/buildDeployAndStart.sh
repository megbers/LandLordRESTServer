#!/bin/bash
echo "==> Stopping Tomcat"
cd /opt/tomcat/bin
./shutdown.sh

echo "==> Removing Old Application"
cd /opt/tomcat/webapps
rm -rf LandLordWebServices*

echo "==> Building Application"
cd /Users/frantic1337/Documents/workspace/LandLordAppRESTServer
mvn clean install

echo "==> Deploying Application"
cp /Users/frantic1337/Documents/workspace/LandLordAppRESTServer/target/LandLordWebServices.war /opt/tomcat/webapps/

echo "==> Starting Tomcat"
cd /opt/tomcat/bin
./startup.sh
