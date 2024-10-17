package com.zzunee.examplecodingtest.dp

/**
 * [Lv 2] 멀리뛰기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12914
 * 1. 총 뛸 수 있는 칸(n) + 1로 dp 생성 --> dp[i] = i번째 칸에 도달할 수 있는 가짓 수
 * 2. 현재 칸(dp[i])에 도달하기 위한 방법 = 한 칸 전(dp[i-1])에서 뛰는 방법 + 두 칸 전(dp[i-2])에서 뛰는 방법
 */
class LongJump {
    fun solution(n: Int): Long {
        val dp = Array<Long>(n + 1) { 1L }

        for(i in 2..n) {
            dp[i] = (dp[i-2] + dp[i-1]) % 1234567
        }

        return dp.last()
    }
}