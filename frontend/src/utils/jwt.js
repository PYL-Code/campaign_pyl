// src/utils/jwt.js
import { jwtDecode } from 'jwt-decode'

export function getUserFromToken() {
    const token = localStorage.getItem('token')
    if (!token) return null

    try {
        const decoded = jwtDecode(token)
        return decoded.userNo // 백엔드 JWT에 userNo라는 필드가 있다고 가정
    } catch (e) {
        console.error('JWT 디코딩 실패:', e)
        return null
    }
}
