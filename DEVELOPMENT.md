# リアルタイム開発ガイド

このプロジェクトでは、フロントエンド（Vue + Vite）とバックエンド（Spring Boot + Java 21）をリアルタイムで開発できます。

## 🚀 クイックスタート

### 1. 開発環境の起動

```powershell
# プロジェクトディレクトリで実行
docker compose up
```

コマンド実行後、以下の3つのサービスが起動します:
- **DB (PostgreSQL)**: `localhost:5432` 
- **dev-backend**: `localhost:8080` (Spring Boot HMR対応)
- **dev-frontend**: `localhost:5173` (Vite HMR対応)

### 2. ブラウザでアクセス

```
http://localhost:5173
```

フロントエンドが表示されます。バックエンド呼び出しは自動的にプロキシ経由で `http://dev-backend:8080/api` へルーティングされます。

---

## 🔄 リアルタイム開発フロー

### フロントエンド（Vue）の開発

**ソースコード**: `./frontend/src/`

フロントエンドのファイルを編集すると、ブラウザに**自動的に変更が反映**されます（Hot Module Replacement / HMR）。

例：
```powershell
# ファイルを編集
# 例: frontend/src/App.vue に新規コンポーネント追加、スタイル変更など
# → ブラウザが自動更新（ページリロード不要）
```

### バックエンド（Spring Boot）の開発

**ソースコード**: `./backend/src/main/java/`

バックエンドのJavaファイルを編集すると、Docker内の `mvn spring-boot:run` が**自動的に再コンパイル・再起動**します。

例：
```powershell
# ファイルを編集
# 例: backend/src/main/java/com/example/demo/HelloController.java を変更
# → Maven が変更を検出・再コンパイル
# → Spring Boot が再起動
# → バックエンド API が更新される
```

---

## 📋 主な開発タスク

### タスク1: フロントエンドの UI を追加 / 更新

1. `./frontend/src/App.vue` をエディタで開く
2. コンポーネント / スタイル / ロジックを変更
3. **保存** → ブラウザが自動更新（数秒以内）

例：新規ボタン追加、フォーム追加、CSS調整など。

### タスク2: バックエンド API を追加 / 変更

1. `./backend/src/main/java/com/example/demo/` のファイルを編集
   - コントローラー / サービス / エンティティなど
2. **保存** → Maven が自動再コンパイル
3. コンテナのログをフォローして確認:
   ```powershell
   docker compose logs -f dev-backend
   ```

再起動ログが表示されたら、ブラウザでリクエストを送信して新 API をテスト。

### タスク3: DB（PostgreSQL）のスキーマ確認 / クエリ

```powershell
# テーブル一覧を確認
docker compose exec db psql -U postgres -d demo -c "\dt"

# 特定テーブルの内容を確認（例：greetings）
docker compose exec db psql -U postgres -d demo -c "select * from greetings order by created_at desc limit 10;"

# SQL クエリを直接実行
docker compose exec db psql -U postgres -d demo
# プロンプトが出たら SQL を入力（例: SELECT * FROM greetings;）
```

---

## 🔍 ログを監視する

### フロントエンドのログ（Vite dev server）

```powershell
docker compose logs -f dev-frontend
```

表示例:
```
dev-frontend-1  | VITE v4.5.0  ready in 1234 ms
dev-frontend-1  | ➜  Local:   http://localhost:5173
dev-frontend-1  | ➜  Network: http://0.0.0.0:5173
dev-frontend-1  | ➜  HMR: localhost:5173
```

ファイル保存時に:
```
dev-frontend-1  | ✓ 2 modules updated
```
と表示されれば HMR が機能しています。

### バックエンドのログ（Spring Boot）

```powershell
docker compose logs -f dev-backend
```

Java ファイル編集・保存時に Maven の再コンパイルログが出ます:
```
dev-backend-1  | [INFO] Changes detected - recompiling the module!
dev-backend-1  | [INFO] Compiling 3 source files with javac...
dev-backend-1  | [INFO] Starting Application...
dev-backend-1  | Started Application in 5.123 seconds
```

### DB のログ

```powershell
docker compose logs -f db
```

---

## 🛠️ トラブルシューティング

### フロントエンドがリロードされない

1. `docker compose logs -f dev-frontend` で Vite のエラーを確認
2. ファイルの構文エラーがないか確認（WebStorm / VS Code の linter を使用）
3. `docker compose restart dev-frontend` で再起動

### バックエンドが再起動しない

1. Java の構文エラーがないか確認（IDE の error チェック機能を使用）
2. `docker compose logs -f dev-backend` で Maven エラーを確認
3. エラーが出ている場合、該当ファイルを修正して再度保存
4. 必要に応じて `docker compose restart dev-backend` で再起動

### API 呼び出しで 404 が出る

1. バックエンド API エンドポイントが正しいか確認
2. Spring Boot が正常に起動しているか確認:
   ```powershell
   docker compose logs dev-backend | Select-String -Pattern "Started Application"
   ```
3. ブラウザのコンソール（F12 → Console）で実際のリクエスト URL を確認

### DB に接続できない

1. Postgres が起動しているか確認:
   ```powershell
   docker compose logs db | Select-String -Pattern "database system is ready"
   ```
2. 接続情報を確認（`backend/src/main/resources/application.properties`）:
   - URL: `jdbc:postgresql://db:5432/demo`
   - ユーザー: `postgres`
   - パスワード: `postgres`

---

## 📦 ファイル構成

```
.
├── backend/
│   ├── src/main/java/com/example/demo/
│   │   ├── Application.java          (メインクラス)
│   │   ├── HelloController.java      (API コントローラー)
│   │   ├── Greeting.java             (JPA エンティティ)
│   │   ├── GreetingRepository.java   (データアクセス層)
│   │   └── GreetingService.java      (ビジネスロジック)
│   ├── src/main/resources/
│   │   └── application.properties    (DB 接続設定)
│   ├── Dockerfile                    (本番イメージ用)
│   └── pom.xml                       (Maven 設定)
├── frontend/
│   ├── src/
│   │   ├── App.vue                   (メインコンポーネント)
│   │   └── main.js                   (エントリポイント)
│   ├── Dockerfile                    (本番イメージ用)
│   ├── package.json
│   ├── vite.config.js                (Vite 設定 — HMR 有効)
│   └── nginx.conf                    (本番 Nginx 設定)
├── docker-compose.yml                (本番環境用)
├── docker-compose.override.yml       (開発環境用 — dev-backend/dev-frontend)
└── DEVELOPMENT.md                    (このファイル)
```

---

## 🔌 ネットワーク接続

開発環境内では、以下のように通信します:

- **ブラウザ → Vite dev server**: `http://localhost:5173` (ホストマシン)
- **Vite → Spring Boot API**: Docker Compose ネットワーク内で `http://dev-backend:8080/api` へプロキシ
- **Spring Boot → PostgreSQL**: Docker Compose ネットワーク内で `jdbc:postgresql://db:5432/demo` へ接続

---

## 🎯 よく使うコマンド

```powershell
# 開発環境の起動（フォアグラウンド）
docker compose up

# 開発環境の起動（バックグラウンド）
docker compose up -d

# 開発環境の停止
docker compose down

# 特定サービスのログ監視
docker compose logs -f dev-backend
docker compose logs -f dev-frontend
docker compose logs -f db

# サービスの再起動
docker compose restart dev-backend
docker compose restart dev-frontend

# 全サービスの再ビルド・再起動
docker compose up --build

# 一度にすべてをクリーンアップ（コンテナ・ボリューム削除）
docker compose down -v
```

---

## 💡 ヒント

- **IDE のファイル自動保存**: VS Code / WebStorm の自動保存機能を有効にすると、編集直後に HMR が動作します
- **複数ファイルの同時編集**: フロント・バック両方を同時に開いて開発できます
- **コンテナ内で `npm install` 済み**: `node_modules` は Docker ボリュームにキャッシュされ、次回起動時に再利用されます
- **デバッグ**: 本番環境に切り替える前に、開発環境で十分にテストしてください
