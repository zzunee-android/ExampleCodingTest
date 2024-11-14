package com.zzunee.examplecodingtest.binarysearch

/**
 * [Lv 4] 징검다리
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/43236
 * 1. 바위들을 정렬한 후 거리의 최대값(거리의 최소값 중 가장 큰 값)에 따른 비교
 * 2. 이분탐색 범위 : 0~distance(총 거리)
 * 3. mid보다 거리가 짧으면 바위 제거
 */
class SteppingStones {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var answer = 0
        rocks.sort() // 바위 정렬

        var start = 0
        var end = distance

        while (start <= end) {
            val mid = (start + end) / 2 // 거리의 최대값
            var prev = 0    // 이전 바위의 위치
            var remove = 0  // 제거한 바위 수

            for (rock in rocks) {
                if (rock - prev < mid) { // mid 거리보다 바위 사이의 거리가 짧으면 바위 제거
                    remove++
                } else {    // 거리 유지 가능하면 바위 위치 옮기기
                    prev = rock
                }
            }

            if (distance - prev < mid) remove++

            if (remove <= n) { // 바위 제거 가능한 범위면 거리를 더 멀리 갱신
                answer = mid
                start = mid + 1
            } else {
                end = mid - 1
            }

        }

        return answer
    }
}