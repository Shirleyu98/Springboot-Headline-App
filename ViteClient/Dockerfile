FROM node:18-alpine
WORKDIR /app

# 复制 package.json 和 package-lock.json
COPY package.json package-lock.json ./

# 安装依赖
RUN npm ci

# 复制源代码
COPY . .

# 构建项目
RUN npm run build

# 安装 serve 包来提供静态文件服务
RUN npm install -g serve

EXPOSE 8001

# 使用 serve 启动服务，指定端口 8001
CMD ["serve", "-s", "dist", "-l", "8001"]
