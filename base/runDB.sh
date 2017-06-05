#!/bin/sh

cd ~/IdeaProjects/test/oo/base

docker rm -f mysql
docker rm -f redis
docker rm -f mongo

docker run -d --network test --ip 172.18.0.200  -p 3306:3306 --hostname mysql --name mysql -e MYSQL_ROOT_PASSWORD=root  -v /opt/docker-data/mysql:/var/lib/mysql mysql:5.7.18
docker run -d --network test --ip 172.18.0.201  -p 6379:6379 --hostname redis --name redis redis
docker run -d --network test --ip 172.18.0.202  -p 27017:27017 --hostname mongo --name mongo mongo
