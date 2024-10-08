package com.zzunee.examplecodingtest.greedy

/**
 * 월간 코드 챌린지 시즌3 > 공 이동 시뮬레이션
 * https://school.programmers.co.kr/learn/courses/30/lessons/87391
 * 볼 이동 경로의 마지막부터 역추적
 * 증가 -> 감소, 감소 -> 증가하며 가능한 시작 범위를 탐색
 */
class BallMovementSimulator {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var minX = x.toLong()
        var maxX = x.toLong()
        var minY = y.toLong()
        var maxY = y.toLong()

        for ((command, dx) in queries.reversed()) {
            when (command) {
                0 -> { // 열 감소 -> 열을 증가하며 범위 추적
                    if (minY != 0L) minY += dx
                    maxY = minOf(maxY + dx, m.toLong() - 1)
                }

                1 -> { // 열 증가 -> 열을 감소하며 범위 추적
                    if (maxY != m.toLong() - 1) maxY -= dx
                    minY = maxOf(minY - dx, 0)
                }

                2 -> { // 행 감소 -> 행을 증가하며 범위 추적
                    if (minX != 0L) minX += dx
                    maxX = minOf(maxX + dx, n.toLong() - 1)
                }

                else -> { // 행 증가 -> 행을 감소하며 범위 추적
                    if (maxX != n.toLong() - 1) maxX -= dx
                    minX = maxOf(minX - dx, 0)
                }
            }
            if (minX > maxX || minY > maxY) return 0
        }

        return (maxX - minX + 1) * (maxY - minY + 1)
    }
}