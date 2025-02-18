terraform {
  required_providers {
    minikube = {
      source = "scott-the-programmer/minikube"
      version = "0.4.4"
    }
  }
}

provider "minikube" {
  # Configuration options
  kubernetes_version = "v1.31.0"
}

resource "minikube_cluster" "minikube_docker" {
    driver = "docker"
    cluster_name = "springboot-headline-app"
    addons = [
        "default-storageclass",
        "storage-provisioner"
    ]
}