package com.zzunee.examplecodingtest.binarysearch

/**
 * [Lv 2] PCCP 기출문제 > 2번 / 퍼즐 게임 챌린지
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/340212
 * 💡숙련도 기준으로 이분 탐색
 */
class PuzzleGameChallenge {
    fun solution(diffs: IntArray, times: IntArray, limit: Long): Int {
        var start = 1
        var end = 100_000

        if (diffs.size == 1) {
            return 1
        }

        while (start < end) {
            val level = (start + end) / 2

            if (canSolved(diffs, times, limit, level)) { //숙련도 낮춰도 됨
                end = level
            } else {
                start = level + 1
            }
        }

        return start
    }

    private fun canSolved(diffs: IntArray, times: IntArray, limit: Long, level: Int): Boolean {
        var totalTime: Long = 0L

        for (i in diffs.indices) {
            totalTime += if (diffs[i] <= level) { // 현재 난이도가 숙련도보다 낮으면 제한 시간 내에 풀 수 있음
                times[i]
            } else {
                // 틀리는 횟수 = (diffs[i] - level), 다시 푸는 데 걸리는 시간 = (times[i] + times[i-1])
                (diffs[i] - level) * (times[i] + times[i - 1]) + times[i]
            }

            if (totalTime > limit)
                return false
        }

        return true
    }
}