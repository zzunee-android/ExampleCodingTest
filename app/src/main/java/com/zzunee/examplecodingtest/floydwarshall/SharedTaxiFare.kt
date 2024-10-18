package com.zzunee.examplecodingtest.floydwarshall

/**
 * [Lv 3] 2021 KAKAO BLIND RECRUITMENT > 합승 택시 요금
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/72413
 * 1. fee 배열 선언하여 지점 간 요금 저장
 * 2. k를 경유하며 i -> j로 가는 최단 요금으로 fee 갱신
 * 3. 모든 정점을 경유지로, 시작 -> 경유, 경유 -> a도착, 경유 -> b도착 한 요금 합산
 */
class SharedTaxiFare {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer: Int = Int.MAX_VALUE
        val fee = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }

        // c <-> d 간 요금 저장
        for ((c, d, f) in fares) {
            fee[c][d] = f
            fee[d][c] = f
            fee[c][c] = 0
            fee[d][d] = 0
        }

        for (k in 1..n) { // 경유 지점
            for (i in 1..n) { // 시작 지점
                for (j in 1..n) { // 종료 지점
                    if (fee[i][k] != Int.MAX_VALUE && fee[k][j] != Int.MAX_VALUE) {
                        fee[i][j] = minOf(fee[i][j], fee[i][k] + fee[k][j])
                    }
                }
            }
        }

        for (i in 1..n) {
            answer = minOf(answer, fee[s][i] + fee[i][a] + fee[i][b])
        }

        return answer
    }
}