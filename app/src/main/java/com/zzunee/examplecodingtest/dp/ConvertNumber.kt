package com.zzunee.examplecodingtest.dp

import kotlin.math.*

/**
 * [Lv 2] 숫자 변환하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/154538
 * 1. dp에 연산횟수 저장
 * 2. +n, *2, *3을 인덱스로 하여 배열에 저장
 */
class ConvertNumber {
    fun solution(x: Int, y: Int, n: Int): Int {
        var answer = -1
        val dp = Array<Int>(y + 1) { 1_000_001 }
        dp[x] = 0

        for (index in x..y) {
            if (dp[index] == 1_000_001) { // 연산으로 도달하지 못하는 수
                continue
            }

            if (index + n <= y) {
                dp[index + n] = min(dp[index + n], dp[index] + 1)
            }

            if (index * 2 <= y) {
                dp[index * 2] = min(dp[index * 2], dp[index] + 1)
            }

            if (index * 3 <= y) {
                dp[index * 3] = min(dp[index * 3], dp[index] + 1)
            }

            if (dp[y] != 1_000_001) {
                answer = dp[y]
                break
            }
        }

        return answer
    }
}