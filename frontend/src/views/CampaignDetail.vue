<template>
  <div class="campaign-detail-container">
    <div class="campaign-main">
      <!-- 이미지 섹션 -->
      <div class="campaign-image">
        <img :src="campaign.imageUrl" alt="캠페인 이미지" />
      </div>

      <!-- 정보 섹션 -->
      <div class="campaign-info">
        <h2 class="campaign-title">[{{ campaign.category.name }}] {{ campaign.title }}</h2>
        <p class="campaign-description">{{ campaign.description }}</p>

        <ul class="campaign-meta">
          <li><strong>모집기간:</strong> ~ {{ formatDate(campaign.endDate) }}</li>
          <li><strong>업체명:</strong> {{ campaign.title }}</li>
          <li><strong>진행 상태:</strong> {{ campaign.progressStatus }}</li>
        </ul>

        <div class="campaign-actions">
          <button class="apply-button">신청하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const campaign = ref({ category: {} })

onMounted(async () => {
  const { data } = await axios.get(`/api/campaign/detail/${route.params.id}`)
  campaign.value = data
})

const formatDate = (dateStr) => {
  const d = new Date(dateStr)
  return `${d.getFullYear()}.${String(d.getMonth() + 1).padStart(2, '0')}.${String(d.getDate()).padStart(2, '0')}`
}
</script>

<style scoped>
.campaign-detail-container {
  padding: 40px;
  display: flex;
  justify-content: center;
}

.campaign-main {
  display: flex;
  gap: 40px;
  max-width: 1000px;
  width: 100%;
  border: 1px solid #e0e0e0;
  border-radius: 12px;
  padding: 20px;
  background: #fff;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.campaign-image img {
  width: 400px;
  height: auto;
  border-radius: 8px;
  object-fit: cover;
}

.campaign-info {
  flex: 1;
}

.campaign-title {
  font-size: 1.8rem;
  font-weight: bold;
  margin-bottom: 12px;
}

.campaign-description {
  font-size: 1rem;
  margin-bottom: 20px;
  color: #333;
}

.campaign-meta {
  list-style: none;
  padding: 0;
  margin: 0 0 20px;
}

.campaign-meta li {
  margin-bottom: 10px;
  font-size: 0.95rem;
  color: #444;
}

.apply-button {
  background-color: #2cb148;
  color: white;
  font-weight: bold;
  border: none;
  padding: 12px 24px;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.apply-button:hover {
  background-color: #23953a;
}
</style>
