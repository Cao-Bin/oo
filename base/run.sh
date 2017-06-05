#!/bin/sh

cd ~/IdeaProjects/test/oo/base

#docker rm -f config
#docker rm -f discovery
#docker rm -f gateway
#
#
#docker rmi com.cb/base/config:local-1.0.0-SNAPSHOT
#docker rmi com.cb/base/discovery:local-1.0.0-SNAPSHOT
#docker rmi com.cb/base/gateway:local-1.0.0-SNAPSHOT
#
#gradle buildDocker


docker run -d --network test --ip 172.18.0.203 -p 8888:8888 --hostname config --name config com.cb/base/config:local-1.0.0-SNAPSHOT
sleep 15
docker run -d --network test --ip 172.18.0.204 -p 8761:8761 --hostname discovery --name discovery com.cb/base/discovery:local-1.0.0-SNAPSHOT
sleep 15
docker run -d --network test --ip 172.18.0.205 -p 10000:10000 --hostname gateway  --name gateway com.cb/base/gateway:local-1.0.0-SNAPSHOT

