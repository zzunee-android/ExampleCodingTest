package com.zzunee.examplecodingtest.bfs

import java.util.*
import kotlin.math.*

/**
 * [Lv 2] 2021 카카오 채용연계형 인턴십 > 거리두기 확인하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/81302
 * 1. 응시자 자리에 따라 다른 응시자와의 거리 확인
 * 2. 한명이라도 만족 안하면 반복 종료
 */
class CheckDistance {
    val d = listOf(Pair(0, -1), Pair(0, 1), Pair(-1, 0), Pair(1, 0))

    fun solution(places: Array<Array<String>>): IntArray {
        var answer: IntArray = intArrayOf()

        for (place in places) {
            var check = 1

            for (i in place.indices) {
                for (j in place[i].indices) {
                    if (place[i][j] == 'P') { // 응시자의 자리
                        if (isWrongSeat(place, Pair(i, j))) {
                            check = 0
                            break
                        }
                    }
                }

                if (check == 0) break
            }

            answer += check
        }

        return answer
    }

    private fun isWrongSeat(place: Array<String>, pos: Pair<Int, Int>): Boolean {
        val queue = LinkedList<Pair<Int, Int>>()

        queue.offer(pos)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()

            for ((dx, dy) in d) {
                val newX = x + dx
                val newY = y + dy

                // 응시자와 동일한 자리이거나 범위 아님
                if(x !in 0..4 || y !in 0..4 || (x == pos.first && y == pos.second)) {
                    continue
                }

                // 다른 응시자와의 거리
                val distance = abs(newX - pos.first) + abs(newY - pos.second)

                if (place[newX][newY] == 'P' && distance <= 2) { // 인접 자리 존재
                    return true
                }

                if (place[newX][newY] == 'O' && distance < 2) { // 빈 테이블이라 재확인
                    queue.offer(Pair(newX, newY))
                }
            }
        }

        return false
    }
}