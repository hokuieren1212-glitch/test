# 開発用ガイド

このプロジェクトで素早く開発を始めるための手順をまとめます。

前提
- Docker Desktop が起動している（Compose v2 を推奨）
- （ローカルで直接開発する場合）Node.js と npm、Java と Maven がインストール済みであること

1) コンテナ内で開発モードを起動（推奨）
- `docker-compose.override.yml` が用意されています。これを使うとソースをコンテナにマウントしてホットリロードできます。

PowerShell で:

```powershell
cd 'C:\Users\user\Documents\test'
# BuildKit を有効にして起動
$env:DOCKER_BUILDKIT=1
docker compose up --build
```

- フロントエンドの開発サーバは `http://localhost:5173`（Vite）
- バックエンドは `http://localhost:8080`（Spring Boot、`mvn spring-boot:run`）

■ 個別に開発サービスだけ起動する（推奨）
開発専用サービス `dev-backend` と `dev-frontend` を使って、ホストのソースをコンテナにマウントして動かします（既存の本番イメージと衝突しません）。

PowerShell で:
```powershell
cd 'C:\Users\user\Documents\test'
# クリーンアップ
docker compose down --volumes --remove-orphans

# BuildKit を有効にしてイメージを確実に取得し、開発サービスだけ起動
$env:DOCKER_BUILDKIT=1
docker pull node:18-bullseye-slim
docker pull maven:3.9.4-eclipse-temurin-21

# dev サービスを明示して起動（フォアグラウンド）
docker compose -f docker-compose.yml -f docker-compose.override.yml up --build dev-backend dev-frontend db

# バックグラウンドで起動したい場合（-d を追加）
docker compose -f docker-compose.yml -f docker-compose.override.yml up -d --build dev-backend dev-frontend db
```

2) ローカルで直接開発する場合
- フロントエンド（ホットリロード）:

```powershell
cd 'C:\Users\user\Documents\test\frontend'
# Node が入っていれば
npm install
npm run dev
# ブラウザで http://localhost:5173 を開く
```

- バックエンド（ホットリロード）:

```powershell
cd 'C:\Users\user\Documents\test\backend'
mvn -DskipTests spring-boot:run
# ブラウザまたは curl で http://localhost:8080/api/hello を確認
```

3) 開発補助
- `frontend/vite.config.js` は `/api` へのプロキシをサポートしています。コンテナ内開発では `BACKEND_URL=http://backend:8080` を使用します。
- `frontend/package-lock.json` を生成してコミットすると `npm ci` が使えるようになり、本番ビルドの再現性が向上します。
- 開発用の Maven キャッシュは `m2-cache` ボリュームに保存されます。

トラブルシュート
- Docker が動かない/接続できない場合は Docker Desktop を起動してください。
- `npm` が無い（ローカル開発）場合は Node.js をインストールしてください（公式インストーラか Chocolatey / nvm-windows を推奨）。

必要なら私が次の作業も自動でやります:
- `backend/Dockerfile` を BuildKit cache 対応に変える（`--mount=type=cache`）
- `package-lock.json` を生成してコミットする手順の詳細ガイド

以上。問題や追加の希望があれば教えてください。
