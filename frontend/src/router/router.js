import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import CampaignDetail from '../views/CampaignDetail.vue'
import ApplicationForm from "../views/ApplicationForm.vue"; // 캠페인 상세 페이지

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/campaign/detail/:id',
            name: 'detail',
            component: CampaignDetail
        },
        {
            path: '/campaign/apply/:id',
            name: 'application',
            component: ApplicationForm
        }
    ]
})

export default router