<template>
  <div class="campaign-detail-container">
    <div class="campaign-main">
      <!-- 이미지 섹션 -->
      <div class="campaign-image" :class="{ blurred: isReviewing }">
        <img :src="`http://localhost:9876${campaign.imageUrl}`" alt="캠페인 이미지" />
      </div>

      <!-- 정보 섹션 -->
      <div class="campaign-info">
        <h2 class="campaign-title">[{{ campaign.category.name }}] {{ campaign.title }}</h2>
        <p class="campaign-description">{{ campaign.description }}</p>

        <ul class="campaign-meta" v-if="!isReviewing">
          <li><b>모집기간:</b> {{ formatDate(campaign.startDate) }} ~ {{ formatDate(campaign.endDate) }}</li>
          <li><b>모집인원:</b> {{ campaign.recruitCount }}명</li>
          <li><b>신청인원:</b> {{campaign.currentCount}}명</li>
        </ul>

        <!-- 리뷰 작성 기간 표시 -->
        <p v-if="isReviewing" class="reviewing-notice">
          🔍 현재 리뷰 작성 기간입니다 ({{ formatDate(reviewStartDate) }} ~ {{ formatDate(reviewEndDate) }})
        </p>

        <div class="campaign-actions">
          <router-link :to="`/campaign/apply/${route.params.id}`">
            <button class="apply-button" :disabled="isReviewing">신청하기</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import { useRoute } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const campaign = ref({ category: {} })

const reviewStartDate = ref(null)
const reviewEndDate = ref(null)

const isReviewing = computed(() => {
  if (!campaign.value.endDate) return false

  const now = new Date()
  const endDate = new Date(campaign.value.endDate)

  reviewStartDate.value = endDate
  reviewEndDate.value = new Date(endDate)
  reviewEndDate.value.setDate(reviewEndDate.value.getDate() + 7)

  return now >= endDate && now <= reviewEndDate.value
})

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

.campaign-image {
  width: 400px;
  height: auto;
  border-radius: 8px;
  overflow: hidden;
}

.campaign-image img {
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 8px;
}

.campaign-image.blurred img {
  filter: blur(6px);
  opacity: 0.6;
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

.reviewing-notice {
  font-size: 1rem;
  color: #cc0000;
  margin-bottom: 20px;
  font-weight: 600;
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
  width: 100%;
}

.apply-button:disabled {
  background-color: #a5a5a5;
  cursor: not-allowed;
}

.apply-button:hover:not(:disabled) {
  background-color: #23953a;
}
</style>
