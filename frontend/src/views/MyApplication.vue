<template>
  <div class="my-application-wrapper">
    <h2>나의 체험단 신청 목록</h2>

    <div v-if="applications.length === 0" class="empty-message">
      신청한 캠페인이 없습니다.
    </div>

    <div v-for="application in applications" :key="application.applicationNo" class="application-card">
      <div class="application-content">
        <div class="text-section">
          <h3>{{ application.campaign.title }}</h3>
          <p><strong>신청 코멘트:</strong> {{ application.comment }}</p>
          <p><strong>상태:</strong> {{ application.status }}</p>
          <p><strong>신청일:</strong> {{ formatDate(application.createdAt) }}</p>
        </div>
        <button class="delete-button" @click="deleteApplication(application.applicationNo)">삭제</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const userNo = 1 // 🔹 임시: 실제 로그인된 유저 ID로 대체 필요
const applications = ref([])

const fetchApplications = async () => {
  try {
    const { data } = await axios.get(`/api/application/myapplication/${userNo}`)
    applications.value = data
  } catch (error) {
    console.error('신청 목록 불러오기 오류:', error)
  }
}

const deleteApplication = async (applicationNo) => {
  if (confirm('정말 삭제하시겠습니까?')) {
    try {
      await axios.delete(`/api/application/myapplication/delete/${applicationNo}`)
      applications.value = applications.value.filter(a => a.applicationNo !== applicationNo)
    } catch (error) {
      console.error('삭제 실패:', error)
      alert('삭제 중 오류가 발생했습니다.')
    }
  }
}

const formatDate = (dateString) => {
  const date = new Date(dateString)
  return date.toLocaleDateString('ko-KR', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit'
  })
}

onMounted(() => {
  fetchApplications()
})
</script>

<style scoped>
.my-application-wrapper {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
  font-family: 'Noto Sans KR', sans-serif;
}

h2 {
  font-size: 1.8rem;
  margin-bottom: 24px;
  text-align: center;
  color: #333;
}

.empty-message {
  text-align: center;
  color: #999;
  font-size: 1rem;
}

.application-card {
  border: 1px solid #ddd;
  padding: 20px;
  margin-bottom: 16px;
  border-radius: 10px;
  background-color: #fff;
}

.application-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text-section {
  flex: 1;
}

.text-section h3 {
  font-size: 1.2rem;
  margin-bottom: 8px;
  color: #2c3e50;
}

.text-section p {
  font-size: 0.95rem;
  margin: 4px 0;
}

.delete-button {
  background-color: #e74c3c;
  color: white;
  padding: 10px 16px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-size: 0.9rem;
}

.delete-button:hover {
  background-color: #c0392b;
}
</style>
