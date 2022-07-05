#!/bin/sh

docker network create cassandra || true

docker pull cassandra:4.0.4
