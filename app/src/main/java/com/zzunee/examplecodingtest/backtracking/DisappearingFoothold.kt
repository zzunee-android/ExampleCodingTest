package com.zzunee.examplecodingtest.backtracking

/**
 * [Lv 3] 2022 KAKAO BLIND RECRUITMENT > 사라지는 발판
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92345
 * 1. A와 B를 서로 번갈아가며 재귀 호출하며 게임 상황 확인 (백트래킹)
 * 2. 이동 횟수 조건
 * 2-1. 현재 공격권이 있는 말이 이기는 경우 ---> 최소 이동
 * 2-2. 현재 공격권이 있는 말이 지는 경우 ---> 최대 이동
 */
class DisappearingFoothold {
    private val directions = listOf(Pair(0, -1), Pair(0, 1), Pair(-1, 0), Pair(1, 0))

    fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
        fun moved(depth: Int, aPos: Pair<Int, Int>, bPos: Pair<Int, Int>): Pair<Boolean, Int> {
            // depth에 따른 현재 이동 말 확인 (짝수 = A -> 항상 선공, 홀수 = B)
            val (x, y) = if (depth % 2 == 0) aPos else bPos

            if (board[x][y] == 0) { // 발판 없음
                return Pair(false, 0)
            }

            var canWin = false // 현재 말이 이기는지
            var minMove = Int.MAX_VALUE // 이긴다면 최소 횟수로
            var maxMove = 0 // 진다면 최대 횟수로

            for ((dx, dy) in directions) {
                val nextX = x + dx
                val nextY = y + dy

                if (nextX in board.indices && nextY in board[0].indices) {
                    if (board[nextX][nextY] == 1) {
                        // 이동
                        board[x][y] = 0

                        // 다른 말 확인
                        val (otherWin, moveCount) = if (depth % 2 == 0) {
                            // 현재 A 면 B 확인
                            moved(depth + 1, Pair(nextX, nextY), bPos)
                        } else {
                            // 현재 B 면 A 확인
                            moved(depth + 1, aPos, Pair(nextX, nextY))
                        }

                        // 복구
                        board[x][y] = 1

                        if (otherWin) { // 상대방이 승리
                            maxMove = maxOf(maxMove, moveCount + 1) // 최대한으로 도망
                        } else {    // 내가 승리
                            canWin = true
                            minMove = minOf(minMove, moveCount + 1) // 최소한으로 승리
                        }
                    }
                }
            }

            return if (canWin) Pair(true, minMove) else Pair(false, maxMove)
        }

        val answer = moved(0, Pair(aloc[0], aloc[1]), Pair(bloc[0], bloc[1])) // A위치, B 위치 전달

        return answer.second
    }
}