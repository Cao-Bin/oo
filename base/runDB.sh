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
docker run -d --network test --ip 172.18.0.204  -p 5672:5672 -p 15672:15672 --hostname rabbit --name rabbit -e RABBITMQ_DEFAULT_VHOST=oo -e RABBITMQ_DEFAULT_USER=root -e RABBITMQ_DEFAULT_PASS=root rabbitmq:3.6.10-management
#其他
#docker run -d --network test --ip 172.18.0.199 --hostname kafkaBrokers --name kafkaBrokers --publish 9092:9092 --link zookeeper --env KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 --env KAFKA_ADVERTISED_HOST_NAME=192.168.7.36 --env KAFKA_ADVERTISED_PORT=9092 wurstmeister/kafka:latest
#docker run -d --network test --ip 172.18.0.205  -p 2181:2181 --hostname zookeeper --name zookeeper zookeeper:3.3
#docker run -d --network test --ip 172.18.0.206  -p 16010:16010 --hostname hbase --name hbase -v /opt/docker_volume/hbase:/hbase-data harisekhon/hbase:1.3
#docker run -it --network test --ip 172.18.0.207  -p 28080-28082:28080-28082 -p 9994-9996:9994-9996 -p 29994:29994 -p 29995-29996:29995-29996/udp --cap-add SYS_PTRACE --hostname pinpoint --name pinpoint -v /opt/docker_volume/pinpoint:/pinpoint yous/pinpoint
#docker run -it --network test --ip 172.18.0.207  -p 28080-28082:28080-28082 -p 9994-9996:9994-9996 -p 29994:29994 -p 29995-29996:29995-29996/udp --cap-add SYS_PTRACE --hostname pinpoint --name pinpoint -v /opt/docker_volume/pinpoint:/pinpoint cb/pinpoint

#docker run -d --network test --ip 172.18.0.101  -p 8080:8080 --hostname dubboadmin --name dubboadmin -e REGISTRY_ADDRESS=zookeeper://172.18.0.205:2181 registry.cn-hangzhou.aliyuncs.com/glodonedu/dubbo_admin:2.5
