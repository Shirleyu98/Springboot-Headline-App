resource "helm_release" "argocd" {
  name = "argocd"
  chart = "argo-cd"
  namespace = "argocd"

  repository = "https://argoproj.github.io/argo-helm"
  create_namespace = true   
  values = [
    <<EOF
    server:
      service:
        type: ClusterIP
    EOF
  ]
}