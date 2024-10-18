package com.zzunee.examplecodingtest.dp

/**
 * [Lv 3] 가장 긴 팰린드롬
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12904
 * 1. 문자열 길이 만큼의 2차원 dp 생성 (dp[i][j] = s[i] ~ s[j]까지 팰린드롬 여부
 * 2. 문자열 길이 3~n(s.length)를 순회하며 시작 인덱스(start)부터 종료 인덱스(end)까지 펠린드롬인지 확인
 * 3. 팰린드롬 여부 판단 2가지(AND)
 * 3-1. 시작 문자와 종료 문자가 같은가? (s[start] == s[end])
 * 3-2. 중간 문자열이 팰린드롬 인가? (s[start+1] ~ s[end-1])
 */
class LongestPalindrome {
    fun solution(s: String): Int {
        val n = s.length
        var answer = 1
        val dp = Array(n) { BooleanArray(n) }

        for (i in 0 until n - 1) {
            dp[i][i] = true // 문자열 1개 팰린드롬 (자기 자신)

            if (s[i] == s[i + 1]) { // 문자열 2개 팰린드롬 (s[i], s[i+1])
                dp[i][i + 1] = true
                answer = 2
            }
        }

        dp[n - 1][n - 1] = true

        for (len in 3..n) { // 문자열 길이
            for (start in 0 until n - len + 1) { // 시작 인덱스 (n=5, len=2일때 시작 가능한 최대 인덱스는 3)
                val end = start + len - 1 // 종료 인덱스 (시작인덱스 + 문자열 길이 - 1)
                if (s[start] == s[end] && dp[start + 1][end - 1]) { // 중간 문자열 확인
                    dp[start][end] = true
                    answer = len
                }
            }
        }

        return answer
    }
}