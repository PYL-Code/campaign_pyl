<template>
  <div class="application-form-wrapper">
    <h2 class="form-title">체험단 신청하기</h2>

    <!-- 캠페인 정보 카드 -->
    <div class="info-card">
      <h3 class="section-title">신청 체험 정보</h3>
      <div class="card-content">
        <img :src="`http://localhost:9876/images/${campaign.imageUrl}`" alt="캠페인 이미지" class="campaign-image" />
        <div class="campaign-text">
          <h4 class="campaign-title">{{ campaign.title }}</h4>
          <p class="campaign-description">{{ campaign.description }}</p>
        </div>
      </div>
    </div>

    <!-- 유저 정보 카드 -->
    <div class="info-card">
      <h3 class="section-title">신청자 정보</h3>
      <div class="card-content users-info">
        <span class="users-name"><b>닉네임:</b> {{ users.nickname }}</span>
        <span class="users-email"><b>전화번호:</b> {{ users.phone }}</span>
        <span class="users-sns"><b>SNS:</b> {{users.postUrl}}</span>
      </div>
    </div>

    <!-- 신청 폼 -->
    <div class="info-card">
      <h3 class="section-title">신청하기</h3>
      <form @submit.prevent="submitApplication" class="form-section">
        <div class="form-group">
          <label for="comment">신청 코멘트</label>
          <textarea
              id="comment"
              v-model="application.comment"
              required
              placeholder="신청 이유나 포부를 적어주세요"
          ></textarea>
        </div>
        <button type="submit" class="submit-button">신청하기</button>
      </form>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted, onBeforeMount } from 'vue'
import axios from 'axios'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()
const userNo = 1 // 🔹 TODO 임시: 실제 구현에서는 로그인 사용자 ID로 대체해야 함
const campaignNo = route.params.id // 캠페인 ID를 URL에서 가져옴

// 초기값 설정
const application = ref({
  comment: '',
  status: 'PENDING',
  createdAt: new Date().toISOString(),
  campaign: {
    campaignNo: campaignNo
  },
  users: {
    userNo: userNo
  }
})

const campaign = ref({})
const users = ref({})

onMounted(async () => {
  try {
    const { data: campaignData } = await axios.get(`/api/campaign/detail/${route.params.id}`)
    campaign.value = campaignData

    const { data: userData } = await axios.get(`/api/application/users/select/${userNo}`)
    users.value = userData
    // console.log("userData: ", userData)
  } catch (error) {
    console.error('데이터 불러오기 오류:', error)
  }
})

onBeforeMount(async () => {
  const { data } = await axios.get(`/api/campaign/detail/${route.params.id}`)

  if (data.progressStatus !== 'ONGOING') {
    alert('현재 신청할 수 없는 캠페인입니다.')
    router.replace(`/campaign/detail/${route.params.id}`)
  }
})

const submitApplication = async () => {
  try {
    await axios.put('/api/application/insert', application.value)
    alert('신청이 완료되었습니다.')
    application.value.comment = ''
  } catch (error) {
    if (error.response && error.response.status === 409) {
      alert('이미 신청한 캠페인입니다.')  // ❗ 중복 신청 알림
    } else {
      alert('신청 중 오류가 발생했습니다.')
    }
    console.error(error)
  }
}
</script>

<style scoped>
.application-form-wrapper {
  background-color: #f9f9f9;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
  max-width: 720px;
  margin: 40px auto;
  font-family: 'Noto Sans KR', sans-serif;
}

.form-title {
  font-size: 2rem;
  font-weight: 700;
  text-align: center;
  margin-bottom: 30px;
  color: #333;
}

.info-card {
  background: #fff;
  border: 1px solid #eaeaea;
  border-radius: 10px;
  margin-bottom: 24px;
  padding: 20px;
}

.section-title {
  font-size: 1.2rem;
  font-weight: 600;
  margin-bottom: 15px;
  color: #2c3e50;
}

.card-content {
  display: flex;
  gap: 20px;
  align-items: flex-start;
}

.campaign-image {
  width: 120px;
  height: 120px;
  object-fit: cover;
  border-radius: 10px;
  border: 1px solid #ddd;
}

.campaign-text {
  flex: 1;
}

.campaign-title {
  font-size: 1.1rem;
  font-weight: bold;
  margin-bottom: 8px;
  color: #2c3e50;
}

.campaign-description {
  font-size: 0.95rem;
  color: #555;
}

.users-info {
  flex-direction: column;
  gap: 6px;
}

.users-name,
.users-email,
.users-sns {
  font-size: 0.95rem;
  color: #444;
}

.form-section {
  margin-top: 30px;
}

.form-group {
  margin-bottom: 20px;
  width: 100%;
}

label {
  display: block;
  font-weight: 600;
  margin-bottom: 8px;
  color: #333;
}

textarea {
  width: 100%;
  height: 120px;
  padding: 14px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  resize: vertical;
  font-family: inherit;
  box-sizing: border-box;
}

.submit-button {
  background-color: #2cb148;
  color: white;
  font-weight: bold;
  border: none;
  padding: 14px 28px;
  border-radius: 10px;
  cursor: pointer;
  font-size: 1rem;
  width: 100%;
  transition: background-color 0.2s ease;
}

.submit-button:hover {
  background-color: #23953a;
}

</style>
