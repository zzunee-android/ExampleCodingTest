package com.zzunee.examplecodingtest.greedy

import java.util.*
import kotlin.math.abs

/**
 * [Lv 3] 2023 현대모비스 알고리즘 경진대회 예선 > 상담원 인원
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/214288
 * 1. 상담 유형 별 시작 시간, 종료 시간 확인
 * 2. 각 유형 별 상담 인원 수에 따른 대기 시간 차이 확인
 * 2-1. 추가 가능한 상담인원 수 = n-k (최소 유형 별 1명의 상담인원 필요)
 * 2-2. 우선순위 큐를 통해 대기 시간 확인
 * 3. 각 유형 별 상담원을 추가하며 가장 대기시간을 줄일 수 있는 유형 확인
 * 4. 상담원을 모두 배치할 때까지 반복
 */
class NumberOfCounselor {
    fun solution(k: Int, n: Int, reqs: Array<IntArray>): Int {
        val waitingTimes =
            Array(k) { IntArray(n - k + 1) }    // 상담 유형 별 대기 시간 (waitingTimes[i][j] = i 유형에 j+1명의 상담원이 배치 됐을 때 대기 시간)
        val reqMap = reqs.groupBy { it[2] - 1 }
            .mapValues {
                it.value.map {
                    listOf(
                        it[0],
                        it[0] + it[1]
                    )
                }
            } // key = 상담,유형 value = listOf(listOf(시작 시간, 종료 시간), ...)

        // 대기 시간
        for (j in 0..n - k) { // 가능한 상담원 수
            for (i in 0 until k) { // 유형
                if (reqMap.containsKey(i)) {
                    waitingTimes[i][j] = calculateWaitTime(reqMap[i]!!, j + 1)
                }
            }
        }

        var remain = n - k // 추가 배치 가능한 상담원 수
        val counselors = IntArray(k) { 1 } // 현재 유형 별 상담원 수

        // 상담원 추가 배치하며 확인
        while (remain > 0) {
            var maxTime = 0
            var maxType = 0

            for (i in 0 until k) { // 유형
                val counselor = counselors[i] - 1 // 현재 상담원 수 (인덱스라 -1)
                val waitingTime = waitingTimes[i][counselor] // 현재 상담원 수의 대기 시간
                val nextTime =
                    if (counselor + 1 <= n - k) waitingTimes[i][counselor + 1] else waitingTime // 상담원 1명 추가 후 대기 시간
                val diffTime = abs(waitingTime - nextTime)

                if (diffTime >= maxTime) { // 상담원 1명 추가할 경우 가장 많이 대기시간이 감소하는 유형 확인
                    maxTime = diffTime
                    maxType = i
                }
            }

            counselors[maxType]++ // 상담원 추가
            remain-- // 남은 인원 감소
        }

        // 상담원 수에 따른 대기시간 더하기
        return counselors.mapIndexed { i, count -> waitingTimes[i][count - 1] }.sum()
    }

    // 상담 유형 별 상담원 수에 따른 대기 시간 확인
    private fun calculateWaitTime(times: List<List<Int>>, count: Int): Int {
        val pq = PriorityQueue<Int>()
        var waitTime = 0

        for (t in times) {
            val (start, end) = t

            if (pq.isEmpty() || pq.size < count) { // 상담원이 여유 있을 때
                pq.add(end) // 인원 추가
            } else {
                val prevEndTime = pq.poll() // 상담 종료 시간

                if (start < prevEndTime) { // 대기 발생
                    waitTime += (prevEndTime - start)
                    pq.add(prevEndTime + (end - start)) // 이전 종료 시간 + 현재 수행 시간
                } else { // 대기 미발생
                    pq.add(end)
                }
            }
        }

        return waitTime
    }
}