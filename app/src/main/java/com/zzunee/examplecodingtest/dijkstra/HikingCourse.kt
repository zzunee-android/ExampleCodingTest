package com.zzunee.examplecodingtest.dijkstra

import java.util.*

/**
 * [Lv 3] 2022 KAKAO TECH INTERNSHIP > 등산코스 정하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/118669
 * 1. 우선순위 큐를 이용해 연결된 경로의 intensity 최소값을 갱신
 * 2. 시작점에서 봉우리까지 올라가는 단방향으로만 계산해도 최소 intensity 확인 가능
 * 3. 탐색 후 봉우리 인덱스에 저장된 intensity 확인
 */
class HikingCourse {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val answer: IntArray = IntArray(2) { Int.MAX_VALUE }
        val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() } // 등산 코드 (graph[i] = Pair(j, w) = i -> j까지 가는데 걸리는 시간 w)
        val intensities = Array(n + 1) { Int.MAX_VALUE } // 휴식 없이 이동해야 하는 가장 긴 시간
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second }) // intensity 가 낮은 순으로 정렬

        // 경로 초기화
        for ((i, j, w) in paths) {
            graph[i].add(j to w)
            graph[j].add(i to w)
        }

        // pq에 시작점과 intensity 넣기
        for (gate in gates) {
            pq.add(Pair(gate, 0))
            intensities[gate] = 0
        }

        while (pq.isNotEmpty()) {
            val (gate, intensity) = pq.poll()

            // 저장된 시간이 더 짧거나, 봉우리면 등산 완료로 계산하지 않음
            if (intensities[gate] < intensity || summits.contains(gate)) continue

            for ((route, weight) in graph[gate]) {
                val maxIntensity = maxOf(intensity, weight) // 새로운 경로까지 가는데 가장 오래걸리는 시간 확인

                if (maxIntensity < intensities[route]) { // 현재 저장된 시간보다 짧다면 pq 추가
                    intensities[route] = maxIntensity
                    pq.add(Pair(route, maxIntensity))
                }
            }
        }

        for (summit in summits) { // 봉우리 별 확인
            if (answer[1] > intensities[summit] ||  // intensity가 더 작거나
                (answer[1] == intensities[summit] && answer[0] > summit) // intensity는 같은데 봉우리 번호가 더 작으면 갱신
            ) {
                answer[0] = summit
                answer[1] = intensities[summit]
            }
        }

        return answer
    }
}