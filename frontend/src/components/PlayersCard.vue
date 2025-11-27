<template>
  <section class="card players">
    <div class="card-head">
      <h2>選手紹介</h2>
      <button class="sm">メンバー編集</button>
    </div>
    <div class="players-grid">
      <div class="player" v-for="p in players" :key="p.id">
        <div class="avatar">{{ initials(p.name) }}</div>
        <div class="info">
          <div class="name">{{ p.name }} <span class="num">#{{ p.number }}</span></div>
          <div class="pos">{{ p.pos }}</div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { toRefs } from 'vue'
const props = defineProps({ players: { type: Array, default: () => [] } })
const { players: playersRef } = toRefs(props)

function initials(name) {
  return name.split(' ').map(s => s[0] || '').join('').slice(0,2)
}
</script>

<style scoped>
.players-grid { display:grid; grid-template-columns: repeat(auto-fill, minmax(140px, 1fr)); gap:12px }
.player { display:flex; gap:10px; align-items:center; padding:10px; border-radius:10px; background:#fbfdff }
.avatar { width:48px; height:48px; border-radius:50%; background:linear-gradient(135deg,#60a5fa,#7c3aed); color:white; display:flex; align-items:center; justify-content:center; font-weight:700 }
.name { font-weight:700 }
.num { color:#64748b; font-weight:500; margin-left:6px }
.pos { color:#64748b; font-size:13px }
.card-head { display:flex; justify-content:space-between; align-items:center; margin-bottom:10px }
.sm { background:#7c3aed; color:#fff; border:none; padding:6px 10px; border-radius:8px; cursor:pointer }
</style>
