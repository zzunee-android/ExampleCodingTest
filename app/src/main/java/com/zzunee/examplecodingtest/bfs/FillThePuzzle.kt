package com.zzunee.examplecodingtest.bfs

import java.util.*
import kotlin.math.abs

/**
 * [Lv 3] 퍼즐 조각 채우기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/84021
 * 1. 게임보드의 빈 칸과, 테이블에 놓인 퍼즐의 좌표 확인
 * 2. 빈 칸과 퍼즐을 하나씩 맞추기
 * 2-1. 빈칸의 좌표 개수와 퍼즐의 좌표 개수가 다르면 조건 만족 X
 * 2-2. x좌표 간 차, y 좌표 간 차가 동일하면 만족
 * 2-3. 퍼즐이 빈칸에 맞지 않을 경우 90도 회전 * 4
 * 2-4. 최종적으로 일지하는 퍼즐 발견 시 사용한 것이므로 리스트에서 삭제
 */
class FillThePuzzle {
    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        var answer: Int = 0
        val empties = findPuzzles(game_board, 0)
        val puzzles = findPuzzles(table, 1)

        for (empty in empties) {
            for (puzzle in puzzles) {
                if (matchPuzzle(empty, puzzle)) {
                    answer += empty.size
                    puzzles.remove(puzzle) // 퍼즐 사용 완료
                    break
                }
            }
        }

        return answer
    }

    private fun matchPuzzle(empty: List<Point>, puzzle: List<Point>): Boolean {
        var rotatePuzzle = puzzle

        // 좌표 개수가 같지 않으면 퍼즐 조각이 비어 있으므로 사용 불가
        if (empty.size != rotatePuzzle.size) {
            return false
        }

        // 현재, 90도, 190도, 270도 회전 시 맞는지 확인
        for (i in 0 until 4) {
            if (isSame(empty, rotatePuzzle)) {
                return true
            }
            rotatePuzzle = rotate(rotatePuzzle) // 퍼즐 회전
        }

        return false
    }

    // 모양이 같은지 비교
    private fun isSame(empty: List<Point>, puzzle: List<Point>): Boolean {
        for (j in 1 until empty.size) {
            val dxEmpty = empty[j].x - empty[j - 1].x // j-1번과 j번 좌표 차에 따른 비교
            val dyEmpty = empty[j].y - empty[j - 1].y // j-1번과 j번 좌표 차에 따른 비교
            val dxPuzzle = puzzle[j].x - puzzle[j - 1].x
            val dyPuzzle = puzzle[j].y - puzzle[j - 1].y

            // 빈칸과 퍼즐의 x, y 좌표 차가 일치해야 동일한 모양 (정렬 되어 있기 때문)
            if (dxEmpty != dxPuzzle || dyEmpty != dyPuzzle) {
                return false
            }
        }

        return true
    }

    // 좌표 회전
    private fun rotate(puzzles: List<Point>): List<Point> {
        val rotatePuzzle = mutableListOf<Point>()

        for (p in puzzles) {
            rotatePuzzle.add(Point(p.y, -p.x)) // (x, y) 회전 --> (y, -x)
        }

        return rotatePuzzle.sortedWith(compareBy({ it.x }, { it.y })).toList()
    }

    // 퍼즐 찾기
    // find : 0 = game_board의 빈 칸, 1 = table의 퍼즐
    private fun findPuzzles(board: Array<IntArray>, find: Int): MutableList<List<Point>> {
        val puzzles = mutableListOf<List<Point>>() // 퍼즐

        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] == find) {
                    val points = bfs(i, j, find, board)
                    val sortedPointList = points.sortedWith(compareBy({ it.x }, { it.y }))
                    puzzles.add(sortedPointList) // 하나의 퍼즐 완성
                }
            }
        }

        return puzzles
    }

    // 퍼즐 좌표 탐색
    private fun bfs(x: Int, y: Int, find: Int, board: Array<IntArray>): List<Point> {
        val d = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
        val queue = ArrayDeque<Point>()
        val points = mutableListOf<Point>() // 퍼즐 좌표들
        board[x][y] = abs(find - 1)
        queue.add(Point(x, y))

        while (queue.isNotEmpty()) {
            val point = queue.removeFirst()
            points.add(point)

            for ((dx, dy) in d) {
                val newX = point.x + dx
                val newY = point.y + dy

                if (newX in board.indices && newY in board[0].indices && board[newX][newY] == find) {
                    board[newX][newY] = abs(find - 1)
                    queue.add(Point(newX, newY))
                }
            }
        }

        return points.toList()
    }

    data class Point(val x: Int, val y: Int)
}