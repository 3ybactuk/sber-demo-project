FROM postgres:11.5-alpine
COPY ./migrations/0001_init.sql /docker-entrypoint-initdb.d/