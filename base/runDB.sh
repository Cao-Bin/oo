#!/bin/sh

cd ~/IdeaProjects/test/oo/base

docker rm -f mysql
docker rm -f redis
docker rm -f mongo
docker rm -f kafka

docker run -d --network test --ip 172.18.0.200  -p 3306:3306 --hostname mysql --name mysql -e MYSQL_ROOT_PASSWORD=root  -v /opt/docker-data/mysql:/var/lib/mysql mysql:5.7.18
docker run -d --network test --ip 172.18.0.201  -p 6379:6379 --hostname redis --name redis redis
docker run -d --network test --ip 172.18.0.202  -p 27017:27017 --hostname mongo --name mongo mongo
docker run -d --network test --ip 172.18.0.203  -p 9092:9092 --hostname kafka --name kafka registry.cn-hangzhou.aliyuncs.com/wyun/kafka:0.10.2.1_zk

