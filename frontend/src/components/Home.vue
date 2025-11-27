<template>
  <div class="home">
    <header class="home-header">
      <h1>草野球チーム管理</h1>
      <p class="subtitle">スケジュール・道具・お金・選手紹介をスマートに管理</p>
    </header>

    <main class="grid">
      <ScheduleCard :events="events" />
      <EquipmentCard :items="equipment" />
      <FinanceCard :balance="balance" :transactions="transactions" />
      <PlayersCard :players="players" />
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import ScheduleCard from './ScheduleCard.vue'
import EquipmentCard from './EquipmentCard.vue'
import FinanceCard from './FinanceCard.vue'
import PlayersCard from './PlayersCard.vue'

const events = ref([
  { id: 1, date: '11/30 (日)', title: '練習試合 vs BlueBats', place: '中央公園', time: '10:00' },
  { id: 2, date: '12/07 (日)', title: '練習', place: '市民グラウンド', time: '09:00' },
])

const equipment = ref([
  { id: 1, name: 'バット（木製）', status: '良好' },
  { id: 2, name: 'グローブ（）', status: '要修理' },
  { id: 3, name: 'ユニフォーム', status: '揃っている' },
])

const balance = ref(12500)
const transactions = ref([
  { id: 1, date: '11/01', desc: '大会参加費', amount: -5000 },
  { id: 2, date: '11/15', desc: 'スポンサード割引', amount: 2000 },
])

const players = ref([
  { id: 1, name: '佐藤太郎', pos: 'ピッチャー', number: 1 },
  { id: 2, name: '鈴木花子', pos: 'キャッチャー', number: 2 },
  { id: 3, name: '田中一郎', pos: '内野手', number: 10 },
  { id: 4, name: '山田二郎', pos: '外野手', number: 5 },
])
</script>

<style scoped>
.home-header { text-align: left; margin-bottom: 18px }
.home-header h1 { margin: 0; font-size: 28px }
.subtitle { margin: 4px 0 0; color: #475569 }

.grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); gap: 18px }

@media (min-width: 900px) {
  .home-header { display:flex; justify-content:space-between; align-items:center }
}
</style>
