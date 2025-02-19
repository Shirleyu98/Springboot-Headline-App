FROM node:18-alpine AS builder
WORKDIR /app

# 复制 package.json 和 package-lock.json
COPY package.json package-lock.json ./

# 使用 npm ci 确保安装的是锁定的依赖版本
RUN npm ci

# 复制代码文件
COPY . .

# 运行构建
RUN npm run build

# 使用 nginx 作为服务器
FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html
EXPOSE 8001
CMD ["nginx", "-g", "daemon off;"]
