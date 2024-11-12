package com.zzunee.examplecodingtest.dp

/**
 * [Lv 3] 연속 펄스 부분 수열의 합
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/161988
 * 1. dp : 이전 값에서 펄스 연산 진행
 * 1-1. dp[0] = dp[1] + (n * 1)
 * 1-2. dp[1] = dp[0] + (n * -1)
 * 2. 연산 후 현재 최대값, dp[0], dp[1] 중 최대값 확인
 */
class SumOfPulse {
    fun solution(sequence: IntArray): Long {
        var answer: Long = 0
        val dp = Array(2) { 0L }
        sequence.forEach {
            val p = maxOf(0, dp[1] + it)
            val n = maxOf(0, dp[0] - it)
            dp[0] = p
            dp[1] = n
            answer = maxOf(answer, maxOf(dp[0], dp[1]))
        }
        return answer
    }
}