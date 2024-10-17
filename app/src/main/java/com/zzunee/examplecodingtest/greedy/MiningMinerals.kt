package com.zzunee.examplecodingtest.greedy

import java.util.LinkedList

/**
 * [Lv 2] 광물캐기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/172927
 * 1. 가장 좋은 곡괭이 순으로 큐에 저장 (저장 시 곡괭이 개수만큼 저장하기)
 * 2. 광물은 5개씩 그룹화하여 다이아, 철, 돌 별 개수를 IntArray(arr)에 넣고 5개 체크 완료 시 리스트(mineralGroup)에 추가
 * 3. mineralGroup을 다이아, 철, 돌이 많은 순으로 내림차순 정렬 (다이아 곡괭이부터 소비하기 위함)
 * 4. 곡괭이와 광물 차를 비교해 피로도 계산
 */
class MiningMinerals {
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        var answer: Int = 0
        var mineralGroup = arrayOf<IntArray>() // index, count
        val queue = LinkedList<Int>()

        // 곡괭이는 전부 큐에 저장
        picks.mapIndexed { i, count ->
            repeat(count) {
                queue.offer(i)
            }
        }

        var arr = IntArray(3) { 0 }

        // 광물 5개씩 그룹
        for ((i, m) in minerals.withIndex()) {
            if (i >= queue.size * 5) {
                break
            }

            if (i != 0 && i % 5 == 0) {
                mineralGroup += arr
                arr = IntArray(3) { 0 }
            }

            arr[getMineralIndex(m)] += 1
        }

        mineralGroup += arr

        // 그룹 내림차순
        val sortMineral =
            mineralGroup.sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] })).reversed()

        for (m in sortMineral) {
            if (queue.isEmpty()) {   // 곡괭이 없으면 종료
                break
            }

            val pick = queue.poll()

            for ((i, count) in m.withIndex()) {
                answer += (count * getFatigue(pick, i))
            }
        }

        return answer
    }

    private fun getMineralIndex(str: String): Int = when (str) {
        "diamond" -> 0
        "iron" -> 1
        "stone" -> 2
        else -> -1
    }

    // 피로도
    private fun getFatigue(pick: Int, mineral: Int): Int {
        if (pick - mineral == 1)
            return 5
        else if (pick - mineral == 2)
            return 25
        return 1
    }
}