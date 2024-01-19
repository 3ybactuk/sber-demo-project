FROM postgres:11.5-alpine
COPY ./migrations/* /docker-entrypoint-initdb.d/