package com.zzunee.examplecodingtest.backtracking

import kotlin.math.*

/**
 * [Lv 3] 2020 KAKAO BLIND RECRUITMENT > 외벽 점검
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/60062
 * 1. 원 모양을 직선화 시킴 (배열을 2배 늘림)
 * 2. 시작 지점 변화에 따른 필요 인원 확인
 * 3. 인원 수 1명부터 시작해서 제한된 인원으로 전체 취약지점을 확인할 수 있는지 확인
 */
class CheckExternalWall {
    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        var answer = Int.MAX_VALUE
        val weaks = weak + weak.map { it + n } // 취약 지점 원 -> 직선화
        val friendList = permutation(dist.size) // 점검하는 친구 순서 조합

        for (friends in friendList) {
            for (i in weak.indices) { // 시작 지점
                var count = 1 // 점검 인원
                var position = weaks[i] + dist[friends[0]] // 현재 지점부터 첫번째 친구가 갈 수 있는 위치

                for (j in i until i + weak.size) { // 전체 취약 지점
                    if (position < weaks[j]) { // 현재 인원으로 해당 지점까지 점검 못함
                        count++ // 인원 추가
                        if (count > dist.size) {
                            count = Int.MAX_VALUE
                            break // 친구 전체 투입해도 불가
                        }
                        position = weaks[j] + dist[friends[count - 1]] // 인원 추가 시 갈 수 있는 위치
                    }
                }
                answer = min(answer, count)
            }
        }

        return if (answer == Int.MAX_VALUE) -1 else answer
    }

    // 점검하는 친구의 조합
    private fun permutation(count: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>() // 점검 순서 리스트

        fun dfs(list: MutableList<Int>) {
            if (count == list.size) {
                result.add(list.toList())
                return
            }

            for (i in 0 until count) {
                if (!list.contains(i)) {
                    list.add(i)
                    dfs(list)
                    list.removeAt(list.lastIndex)
                }
            }
        }

        dfs(mutableListOf())

        return result.toList()
    }
}