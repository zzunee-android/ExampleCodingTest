package com.zzunee.examplecodingtest.dp

/**
 * [Lv 3] 카운트 다운
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/131129
 * 1. 가능한 모든 점수 조합을 set에 저장
 * 2. 1 ~ target까지 달성을 위한 다트 수 와 싱글/불의 개수 확인
 */
class Countdown {
    fun solution(target: Int): IntArray {
        val scoreSet = mutableSetOf<Int>()
        val dp = Array(target + 1) { Pair(Int.MAX_VALUE, 0) } // 다트 수, 싱글/불의 개수

        // 가능한 점수 조합
        for (i in 1..20) {
            scoreSet.add(i) // 싱글
            scoreSet.add(i * 2) // 더블
            scoreSet.add(i * 3) // 트리플
        }
        scoreSet.add(50) // 불

        dp[0] = Pair(0, 0)

        for (t in 1..target) { // target 달성
            for (score in scoreSet) { // 맞출 수 있는 점수
                if (t >= score) {
                    val (count, singleOrBull) = dp[t - score] // score을 맞추면 t 점수가 됨
                    val newSingleOrBull = if (score <= 20 || score == 50) singleOrBull + 1 else singleOrBull

                    // 더 적은 다트로 t 점수를 만들거나, 점수는 같은데 싱글/불 개수가 많을 경우
                    if (count + 1 < dp[t].first || (count + 1 == dp[t].first && newSingleOrBull > dp[t].second)) {
                        dp[t] = Pair(count + 1, newSingleOrBull)
                    }
                }
            }
        }

        return intArrayOf(dp[target].first, dp[target].second)
    }
}