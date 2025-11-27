<template>
  <div id="app">
    <Home />
  </div>
</template>

<script setup>
import Home from './components/Home.vue'
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap');

#app {
  font-family: 'Inter', system-ui, -apple-system, 'Segoe UI', Roboto, 'Helvetica Neue', Arial;
  background: linear-gradient(180deg, #f7fbff 0%, #eef6ff 100%);
  min-height: 100vh;
  padding: 28px;
  box-sizing: border-box;
  color: #0f172a;
}

button { margin-top: 1rem }
</style>
