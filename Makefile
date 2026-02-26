.PHONY: all jar docker_build docker_up

all: jar docker_build docker_up

jar:
	mvn clean package
docker_build:
	docker-compose build --no-cache
docker_up:
	docker-compose up