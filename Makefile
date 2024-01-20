.PHONY:

# Build and run the docker-compose app
docker_full_up:
	docker-compose down
	docker-compose build --no-cache
	docker-compose up
