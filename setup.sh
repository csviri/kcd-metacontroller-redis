#!/bin/bash

kubectl apply -f https://github.com/kubernetes/minikube/issues/14631#issuecomment-1292420728
kubectl apply -f k8s/redisinstances.csviri.io-v1.yml
kubectl apply -f k8s/composite-controller.yaml