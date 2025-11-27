# --- フロント(Vue)ビルド ---
FROM node:18-bullseye-slim AS frontend-build
WORKDIR /app
COPY frontend/package*.json ./frontend/
WORKDIR /app/frontend
RUN npm install --no-audit --no-fund
COPY frontend/ .
# 実行権限を付与
RUN chmod +x node_modules/.bin/* || true
RUN npm run build

# --- バックエンド(Spring Boot)ビルド ---
FROM maven:3.9.4-eclipse-temurin-21 AS backend-build
WORKDIR /app
COPY backend/pom.xml ./backend/
COPY backend/src ./backend/src
WORKDIR /app/backend
RUN mvn -B -DskipTests package

# --- 実行環境 ---
FROM eclipse-temurin:21-jre
WORKDIR /app
# Spring Bootのjarコピー
COPY --from=backend-build /app/backend/target/demo-0.0.1-SNAPSHOT.jar app.jar
# Vueの成果物をSpring Bootのstatic配下にコピー
COPY --from=frontend-build /app/frontend/dist ./static
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]