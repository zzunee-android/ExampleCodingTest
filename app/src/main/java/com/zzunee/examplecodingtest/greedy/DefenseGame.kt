package com.zzunee.examplecodingtest.greedy

import java.util.*

/**
 * [Lv 2] 디펜스 게임
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/142085
 * 1. 내림차순 우선순위 큐 생성
 * 2. 무적권 사용하는 규칙
 * 2-1. 우선 가지고 있는 병사(N)로 게임 계속 진행
 * 2-2. 남은 병사(N)가 없을 경우, 가장 적이 많은 라운드를 무적권(K)으로 대체하고, 그때 사용한 병사는 다시 남은 병사(N)가 됨
 */
class DefenseGame {
    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        var answer: Int = 0
        val pq = PriorityQueue<Int>(reverseOrder())
        var N = n // 가지고 있는 병사의 수
        var K = k // 무적권 개수

        if (K >= enemy.size) { // 무적권 수가 라운드 수보다 많으면 모든 라운드 가능
            return enemy.size
        }

        for (e in enemy) {
            pq.offer(e)
            N -= e

            while (N < 0 && K > 0) { // 현재 라운드를 남은 병사로 해결할 수 없는 경우 무적권 1개 사용
                N += pq.poll()
                K--
            }

            if (N < 0) break // 남은 병사가 없으면 게임 종료

            answer++
        }

        return answer
    }
}