<template>
  <section class="card equipment">
    <div class="card-head">
      <h2>道具管理</h2>
      <button class="sm">追加</button>
    </div>
    <ul class="list">
      <li v-for="it in items" :key="it.id">
        <div class="name">{{ it.name }}</div>
        <div class="status" :class="it.status.toLowerCase().includes('要') ? 'warn' : 'ok'">{{ it.status }}</div>
      </li>
      <li v-if="items.length===0" class="empty">道具が登録されていません</li>
    </ul>
  </section>
</template>

<script setup>
defineProps({ items: { type: Array, default: () => [] } })
</script>

<style scoped>
.card-head { display:flex; justify-content:space-between; align-items:center; margin-bottom:10px }
.card { padding: 16px; border-radius: 12px; background: linear-gradient(180deg,#ffffff,#fbfdff); box-shadow: 0 6px 18px rgba(2,6,23,0.04) }
.list { list-style:none; margin:0; padding:0 }
.list li { display:flex; justify-content:space-between; padding:8px 0; border-top:1px solid #f1f5f9 }
.list li:first-child { border-top: none }
.status.ok { color:#16a34a; font-weight:600 }
.status.warn { color:#d97706; font-weight:700 }
.empty { color:#94a3b8; padding:8px 0 }
</style>
