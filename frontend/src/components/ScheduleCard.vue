<template>
  <section class="card schedule">
    <div class="card-head">
      <h2>スケジュール</h2>
      <button class="sm">新規</button>
    </div>
    <ul class="list">
      <li v-for="e in events" :key="e.id">
        <div class="date">{{ e.date }}</div>
        <div class="info">
          <div class="title">{{ e.title }}</div>
          <div class="meta">{{ e.place }} · {{ e.time }}</div>
        </div>
      </li>
      <li v-if="events.length===0" class="empty">予定はありません</li>
    </ul>
  </section>
</template>

<script setup>
defineProps({ events: { type: Array, default: () => [] } })
</script>

<style scoped>
.card { padding: 16px; border-radius: 12px; background: white; box-shadow: 0 6px 18px rgba(15,23,42,0.06) }
.card-head { display:flex; justify-content:space-between; align-items:center; margin-bottom:10px }
.card-head h2 { margin:0; font-size:16px }
.sm { background:#06b6d4; color:#fff; border:none; padding:6px 10px; border-radius:8px; cursor:pointer }
.list { list-style:none; margin:0; padding:0 }
.list li { display:flex; gap:12px; padding:8px 0; border-top:1px solid #f1f5f9 }
.list li:first-child { border-top: none }
.date { font-weight:600; color:#0f172a; width:96px }
.title { font-weight:600 }
.meta { color:#64748b; font-size:13px }
.empty { color:#94a3b8; padding:8px 0 }
</style>
