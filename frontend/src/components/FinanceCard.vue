<template>
  <section class="card finance">
    <div class="card-head">
      <h2>お金管理</h2>
      <div class="balance">残高: <span class="num">¥{{ balance }}</span></div>
    </div>
    <ul class="tx">
      <li v-for="t in transactions" :key="t.id">
        <div class="left">{{ t.date }} · {{ t.desc }}</div>
        <div :class="['amt', t.amount<0 ? 'neg' : 'pos']">{{ t.amount<0 ? '-' : '+' }}¥{{ Math.abs(t.amount) }}</div>
      </li>
      <li v-if="transactions.length===0" class="empty">取引履歴がありません</li>
    </ul>
  </section>
</template>

<script setup>
defineProps({ balance: { type: Number, default: 0 }, transactions: { type: Array, default: () => [] } })
</script>

<style scoped>
.card { padding: 16px; border-radius: 12px; background: white; box-shadow: 0 6px 18px rgba(15,23,42,0.06) }
.card-head { display:flex; justify-content:space-between; align-items:center; margin-bottom:10px }
.balance .num { font-weight:700; color:#0f172a }
.tx { list-style:none; margin:0; padding:0 }
.tx li { display:flex; justify-content:space-between; padding:8px 0; border-top:1px solid #f1f5f9 }
.tx li:first-child { border-top: none }
.amt.pos { color:#16a34a; font-weight:700 }
.amt.neg { color:#ef4444; font-weight:700 }
.empty { color:#94a3b8; padding:8px 0 }
</style>
