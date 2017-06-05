#!/bin/sh

cd ~

docker rm -f users
docker rmi com.cb/business/users/users-service:local-1.0.0-SNAPSHOT
cd ~/IdeaProjects/test/oo/business/users/users-service
gradle -x test clean buildDocker
docker run -d --network test --ip 172.18.0.100 -p 9001:9001 --hostname users --name users com.cb/business/users/users-service:local-1.0.0-SNAPSHOT

