
# Install Metacontroller
kubectl apply -k https://github.com/metacontroller/metacontroller/manifests/production


Minikube host connection issue solution?
https://github.com/kubernetes/minikube/issues/14631#issuecomment-1292420728

Sample request:

```json
{
  "controller": {
    "kind": "CompositeController",
    "apiVersion": "metacontroller.k8s.io/v1alpha1",
    "metadata": {
      "name": "redis-instance-controller",
      "uid": "9f1175a7-8a59-4f9a-a82a-cdbe946a7dfe",
      "resourceVersion": "1631",
      "generation": 2,
      "creationTimestamp": "2023-05-12T17:32:27Z",
      "annotations": {
        "kubectl.kubernetes.io/last-applied-configuration": "{\"apiVersion\":\"metacontroller.k8s.io/v1alpha1\",\"kind\":\"CompositeController\",\"metadata\":{\"annotations\":{},\"name\":\"redis-instance-controller\"},\"spec\":{\"childResources\":[{\"apiVersion\":\"v1\",\"resource\":\"services\",\"updateStrategy\":{\"method\":\"InPlace\"}},{\"apiVersion\":\"apps/v1\",\"resource\":\"deployments\",\"updateStrategy\":{\"method\":\"InPlace\"}}],\"generateSelector\":true,\"hooks\":{\"sync\":{\"webhook\":{\"url\":\"http://host.minikube.internal:8080/sync\"}}},\"parentResource\":{\"apiVersion\":\"csviri.io/v1\",\"resource\":\"redisinstances\"}}}\n"
      },
      "managedFields": [
        {
          "manager": "kubectl-client-side-apply",
          "operation": "Update",
          "apiVersion": "metacontroller.k8s.io/v1alpha1",
          "time": "2023-05-12T17:40:17Z",
          "fieldsType": "FieldsV1",
          "fieldsV1": {
            "f:metadata": {
              "f:annotations": {
                ".": {},
                "f:kubectl.kubernetes.io/last-applied-configuration": {}
              }
            },
            "f:spec": {
              ".": {},
              "f:childResources": {},
              "f:generateSelector": {},
              "f:hooks": {
                ".": {},
                "f:sync": {
                  ".": {},
                  "f:version": {},
                  "f:webhook": {
                    ".": {},
                    "f:url": {}
                  }
                }
              },
              "f:parentResource": {
                ".": {},
                "f:apiVersion": {},
                "f:resource": {}
              }
            }
          }
        }
      ]
    },
    "spec": {
      "parentResource": {
        "apiVersion": "csviri.io/v1",
        "resource": "redisinstances"
      },
      "childResources": [
        {
          "apiVersion": "v1",
          "resource": "services",
          "updateStrategy": {
            "method": "InPlace",
            "statusChecks": {}
          }
        },
        {
          "apiVersion": "apps/v1",
          "resource": "deployments",
          "updateStrategy": {
            "method": "InPlace",
            "statusChecks": {}
          }
        }
      ],
      "hooks": {
        "sync": {
          "version": "v1",
          "webhook": {
            "url": "http://host.minikube.internal:8080/sync"
          }
        }
      },
      "generateSelector": true
    },
    "status": {}
  },
  "parent": {
    "apiVersion": "csviri.io/v1",
    "kind": "RedisInstance",
    "metadata": {
      "annotations": {
        "kubectl.kubernetes.io/last-applied-configuration": "{\"apiVersion\":\"csviri.io/v1\",\"kind\":\"RedisInstance\",\"metadata\":{\"annotations\":{},\"name\":\"redis1\",\"namespace\":\"default\"},\"spec\":{\"requireAuthentication\":true}}\n"
      },
      "creationTimestamp": "2023-05-12T17:42:28Z",
      "generation": 1,
      "managedFields": [
        {
          "apiVersion": "csviri.io/v1",
          "fieldsType": "FieldsV1",
          "fieldsV1": {
            "f:metadata": {
              "f:annotations": {
                ".": {},
                "f:kubectl.kubernetes.io/last-applied-configuration": {}
              }
            },
            "f:spec": {
              ".": {},
              "f:requireAuthentication": {}
            }
          },
          "manager": "kubectl-client-side-apply",
          "operation": "Update",
          "time": "2023-05-12T17:42:28Z"
        },
        {
          "apiVersion": "csviri.io/v1",
          "fieldsType": "FieldsV1",
          "fieldsV1": {
            "f:status": {}
          },
          "manager": "metacontroller",
          "operation": "Update",
          "subresource": "status",
          "time": "2023-05-12T17:42:28Z"
        }
      ],
      "name": "redis1",
      "namespace": "default",
      "resourceVersion": "1742",
      "uid": "abab2f91-bedc-4e22-8f9a-5c93795b37e0"
    },
    "spec": {
      "requireAuthentication": true
    },
    "status": {}
  },
  "children": {
    "Deployment.apps/v1": {},
    "Service.v1": {}
  },
  "related": {},
  "finalizing": false
}
```