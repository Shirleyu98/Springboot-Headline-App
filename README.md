## News Headline App with CI/CD & GitOps

This project is an implementation of the news headline app from [this tutorial](https://www.bilibili.com/video/BV1AP411s7D7/?p=29&vd_source=4b6f260d8f4a2ff2fa4c78872b019102).

In addition to the original tutorial, I designed and deployed this app using a **CI/CD pipeline** with **GitOps, GitHub Actions, and Docker**.  
The deployment is standardized with **Helm Charts** and continuously delivered via **ArgoCD**, allowing real-time monitoring through the ArgoCD dashboard.

---

## How to Run This Project

### 1. Clone the Repository

`git clone git@github.com:Shirleyu98/Springboot-Headline-App.git cd Springboot-Headline-App`

### 2. Initialize Terraform

`cd terraform-configs terraform init terraform plan terraform apply`

### 3. Start Minikube

`minikube start -p springboot-headline-app`

### 4. Port-Forward Services with `kubectl`

`kubectl get svc kubectl port-forward svc/frontend <Port>:80 kubectl port-forward svc/backend <Port>:8080`

---

## Setting Up ArgoCD

If you want to set up **ArgoCD** manually, follow these steps:

### 1. Port-forward the ArgoCD Server

`kubectl port-forward svc/argocd-server -n argocd <Port>:443`

### 2. Edit `argocd-app.yaml`

- Update the `repoURL` to point to your repository.

### 3. Obtain the Initial ArgoCD Admin Password

`kubectl get secret argocd-initial-admin-secret -n argocd -o jsonpath="{.data.password}" | base64 -d`

### 4. Log in to ArgoCD via CLI

`argocd login localhost:<Port> --username admin --password <initial password> --insecure`

### 5. Authenticate ArgoCD to Monitor Your Repository


`argocd repo add <repoURL> --username <username> --password "<gitToken>" --server localhost:<Port> --upsert`

### 6. Register the Application in ArgoCD

`kubectl apply -f argocd-app.yaml`
