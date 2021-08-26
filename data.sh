#!/bin/bash
# example cloud init script to use with utils API
# install additional packages
sudo apt install -y apache2
# enable httpd service
sudo systemctl enable apache2
sudo systemctl start apache2
# create file in http document root
sudo rm /var/www/html/index.html
sudo echo "<p>hello world</p>" > /var/www/html/index.html