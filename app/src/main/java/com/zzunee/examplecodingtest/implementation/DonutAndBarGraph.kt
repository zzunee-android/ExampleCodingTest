package com.zzunee.examplecodingtest.implementation

/**
 * [Lv 2] 2024 KAKAO WINTER INTERNSHIP > 도넛과 막대 그래프
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/258711
 * 1. 정점 수 만큼 배열 생성
 * 2. 정점에 들어오는 간선의 수와 나가는 간선의 수 확인
 * 2-1. 추가 된 정점 : in=0, out>=2
 * 2-2. 막대 그래프의 수 : in>=1, out=0 (최상위 정점 기준)
 * 2-3. 8자 그래프의 수 : in>=2, out>=2 (가운데 정점 기준)
 * 2-4. 도넛 그래프의 수 : 총 그래프 개수(추가 정점의 out 간선 수) - 2-2 그래프수 - 2-3 그래프수
 */
class DonutAndBarGraph {
    fun solution(edges: Array<IntArray>): IntArray {
        val answer = IntArray(4)
        var MAX = Int.MIN_VALUE

        for ((a, b) in edges) {
            MAX = maxOf(MAX, maxOf(a, b))
        }

        val inCount = IntArray(MAX + 1)
        val outCount = IntArray(MAX + 1)

        for ((a, b) in edges) {
            outCount[a]++
            inCount[b]++
        }

        for (i in 1..MAX) {
            if (inCount[i] == 0 && outCount[i] >= 2) {
                answer[0] = i
            } else if (inCount[i] >= 1 && outCount[i] == 0) {
                answer[2]++
            } else if (inCount[i] >= 2 && outCount[i] == 2) {
                answer[3]++
            }
        }

        answer[1] = outCount[answer[0]] - answer[2] - answer[3]

        return answer
    }
}