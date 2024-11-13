package com.zzunee.examplecodingtest.dp

import kotlin.math.*

/**
 * [Lv 3] 숫자 타자 대회
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/136797
 * 1. keypad 좌표 저장
 * 2. 타겟 넘버에 따라 왼손과 오른손이 이동했을때 각각 필요한 가중치 계산
 * 2-1. 제자리 = 1
 * 2-2. 상하좌우 = 2
 * 2-3. 대각선 = 3
 * 2-4. 그 외 = 3 * (x나 y 이동 거리) + 2 * (x나 y이동거리 차)
 */
class NumberTypingContest {
    fun solution(numbers: String): Int {
        val keypad = mapOf(
            '1' to Pair(0, 0), '2' to Pair(0, 1), '3' to Pair(0, 2),
            '4' to Pair(1, 0), '5' to Pair(1, 1), '6' to Pair(1, 2),
            '7' to Pair(2, 0), '8' to Pair(2, 1), '9' to Pair(2, 2),
            '*' to Pair(3, 0), '0' to Pair(3, 1), '#' to Pair(3, 2)
        ) // key = Char, value = Pair<Int, Int>

        var dp = mutableMapOf<Pair<Char, Char>, Int>() // key = <왼손, 오른손>, value = 가중치
        dp['4' to '6'] = 0 // 시작 위치

        for (number in numbers) {
            val temp = mutableMapOf<Pair<Char, Char>, Int>()

            for ((hands, weight) in dp) {
                val (left, right) = hands

                // 왼손으로 number를 누르는 경우
                if (right != number) {
                    val newWeight = weight + calculateWeight(keypad[left]!!, keypad[number]!!)
                    val newKey = number to right // 왼손이 number로 이동
                    temp[newKey] = minOf(temp.getOrDefault(newKey, Int.MAX_VALUE), newWeight) // 최소값으로 갱신
                }

                // 오른손으로 number를 누르는 경우
                if (left != number) {
                    val newWeight = weight + calculateWeight(keypad[right]!!, keypad[number]!!)
                    val newKey = left to number // 오른손이 number로 이동
                    temp[newKey] = minOf(temp.getOrDefault(newKey, Int.MAX_VALUE), newWeight) // 최소값으로 갱신
                }
            }

            dp = temp
        }

        // 모든 경우 중 최소값을 반환
        return dp.values.minOrNull() ?: 0
    }

    // start -> end로 이동하는 가중치 계산
    private fun calculateWeight(start: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        val dx = abs(start.first - end.first)
        val dy = abs(start.second - end.second)

        return when {
            dx == 0 && dy == 0 -> 1 // 제자리
            dx + dy == 1 -> 2       // 상하좌우
            dx == 1 && dy == 1 -> 3 // 대각선
            else -> if (dx > dy) {
                3 * dy + 2 * (dx - dy) // dy만큼 대각선 + 남은 거리는 상하좌우 이동
            } else {
                3 * dx + 2 * (dy - dx) // dx만큼 대각선 + 남은 거리는 상하좌우 이동
            }
        }
    }
}