FROM node:18-bullseye-slim AS build
WORKDIR /app
# package-lock.json が存在しないリポジトリ向けに npm install を使う
# ただし再現性のためにはローカルで package-lock.json を生成してコミットすることを推奨します
COPY package*.json ./
RUN npm install --no-audit --no-fund
COPY . .
RUN npm run build

FROM nginx:stable-alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
