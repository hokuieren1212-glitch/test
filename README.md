## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:


Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


## Demo: Spring Boot + Vue + PostgreSQL + Nginx

This workspace contains a minimal demo showing how to wire together:

- Spring Boot (backend)
- Vue 3 + Vite (frontend)
- PostgreSQL (database)
- Nginx (front-facing server for static files and proxy)
- Docker Compose to run everything easily

Quick start (requires Docker & Docker Compose):

```powershell
docker-compose up --build
```

Then open `http://localhost` in your browser. The frontend will call `/api/hello`, which is proxied to the Spring Boot backend.

Project layout:

- `backend/` : Spring Boot application (Maven)
- `frontend/`: Vue + Vite app, served by `nginx` after build
- `docker-compose.yml`: orchestrates `db`, `backend`, `frontend`

If you want, I can add:

- a basic JPA entity + CRUD endpoints in the backend
- health checks, env-specific build notes, or a runnable JAR build step
- instructions for running locally without Docker
