import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// プロキシのターゲットは環境変数 BACKEND_URL で上書き可能（デフォルト localhost）
const backendTarget = process.env.BACKEND_URL || 'http://localhost:8080'

export default defineConfig({
  plugins: [vue()],
  build: {
    outDir: 'dist'
  },
  server: {
    host: true,
    hmr: {
      host: 'localhost',
      port: 5173,
      protocol: 'ws'
    },
    proxy: {
      '/api': {
        target: backendTarget,
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/api/, '/api')
      }
    }
  }
})
