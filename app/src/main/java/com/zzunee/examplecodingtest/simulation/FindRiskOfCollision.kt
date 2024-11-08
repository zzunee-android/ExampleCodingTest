package com.zzunee.examplecodingtest.simulation

/**
 * [Lv 2] PCCP 기출문제 > 3번 / 충돌위험 찾기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/340211
 * 1. map 선언 : key = Triple<r 위치, c 위치, 시간>, value = 해당 좌표 및 시간에 위치하는 로봇의 수
 * 2. 로봇 이동 경로 -> r 전체 이동 후 c 이동
 * 3. map 값이 2 이상인 key 개수 확인
 */
class FindRiskOfCollision {
    private val map = mutableMapOf<Triple<Int, Int, Int>, Int>()

    fun solution(points: Array<IntArray>, routes: Array<IntArray>): Int {
        for (i in routes.indices) {
            val start = routes[i][0] // 시작 지점 번호 <Int>
            var startPoints = points[start - 1] // 시작 지점 좌표 <Int, Int>
            var time = 1 // 소요 시간
            addPoints(startPoints[0], startPoints[1], time) // 시작 지점 좌표 저장

            for (j in 1 until routes[i].size) {
                val end = routes[i][j] // 종료 지점 번호 <Int>
                val endPoints = points[end - 1] // 종료 지점 좌표 <Int, Int>
                time = moved(startPoints, endPoints, time)  // r, c 이동 및 이동 시간 재설정
                startPoints = endPoints // 현재 종료 지점을 다음 시작 지점으로
            }
        }

        return map.values.count { it > 1 }
    }

    private fun moved(sp: IntArray, ep: IntArray, t: Int): Int {
        var sr = sp[0]
        var sc = sp[1]
        val er = ep[0]
        val ec = ep[1]
        var time = t

        // r 좌표 먼저 이동
        while (sr != er) {
            sr += if (sr < er) 1 else -1
            addPoints(sr, sc, ++time)
        }

        // c 좌표 이동
        while (sc != ec) {
            sc += if (sc < ec) 1 else -1
            addPoints(sr, sc, ++time)
        }

        return time
    }

    private fun addPoints(r: Int, c: Int, t: Int) {
        val point = Triple(r, c, t)
        map[point] = map.getOrDefault(point, 0) + 1
    }
}