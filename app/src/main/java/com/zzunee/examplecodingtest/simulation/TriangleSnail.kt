package com.zzunee.examplecodingtest.simulation

/**
 * [Lv 2] 월간 코드 챌린지 시즌1 > 삼각 달팽이
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/68645
 * 1. 피라미드 형식 -> 직각삼각형으로 전환
 * 2. 범위를 벗어날 경우 방향 전환
 */
class TriangleSnail {
    fun solution(n: Int): IntArray {
        val map = Array(n) { IntArray(n) } // n x n 배열 초기화
        val directions = listOf(Pair(1, 0), Pair(0, 1), Pair(-1, -1))
        var num = 1
        var i = 0
        var j = 0
        var d = 0 // 현재 방향

        while (num <= n * (n + 1) / 2) {    // 열 하나 늘리고 /2
            map[i][j] = num++

            val nextX = i + directions[d].first
            val nextY = j + directions[d].second

            if (nextX in 0 until n && nextY in 0 until n && map[nextX][nextY] == 0) { // 범위 내에서만 동작
                i = nextX
                j = nextY
            } else {    // 방향 전환
                d = (d + 1) % 3
                i += directions[d].first
                j += directions[d].second
            }
        }

        // 값이 0이 아닌 값만 배열화
        return map.flatMap { it.filter { num -> num != 0 } }.toIntArray()
    }
}