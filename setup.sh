#!/bin/bash

kubectl apply -k https://github.com/metacontroller/metacontroller/manifests/production
kubectl apply -f k8s/redisinstances.csviri.io-v1.yml
kubectl apply -f k8s/composite-controller.yaml