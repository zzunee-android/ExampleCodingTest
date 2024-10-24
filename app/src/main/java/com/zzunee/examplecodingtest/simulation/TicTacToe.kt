package com.zzunee.examplecodingtest.simulation

/**
 * [Lv 2] 혼자서 하는 틱택토
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/160585
 * 1. board에 있는 O, X 개수 체크
 * 2. 가로, 세로, 대각선 경우에 대한 빙고 확인
 * 3. O, X에 따른 게임 규칙 확인
 * 3-1. 선공(O)이 없으면 후공도 없어야 함
 * 3-2. 선공(O)이 빙고면 xCount는 oCount-1, 후공(X)이 빙고면 xCount == oCount
 * 3-3. O, X 둘 다 빙고일 수 없음
 */
class TicTacToe {
    fun solution(board: Array<String>): Int {
        var oCount = 0 // O의 개수 (선공)
        var xCount = 0 // X의 개수 (후공)

        for (i in board.indices) {
            for (j in 0 until board[i].length) {
                if (board[i][j] == 'O') {    // 선공 위치
                    oCount++
                } else if (board[i][j] == 'X') {
                    xCount++   // 후공 위치
                }
            }
        }

        if (oCount == 0)       // 선공이 없을 때 후공 없음
            return if (xCount == 0) 1 else 0
        else if (xCount > oCount)    // 후공이 선공보다 많을 수 없음
            return 0
        else if (oCount - xCount > 1) // 선공과 후공의 개수 차이가 1개 이상 있을 수 없음
            return 0

        // 빙고 체크
        val oBingo = checkBingo('O', board)
        val xBingo = checkBingo('X', board)

        if (oBingo && xBingo)    // O, X 둘 다 빙고일 수 없음
            return 0
        else if (oBingo && oCount == xCount) // 빙고된 후에 게임이 계속 진행됨
            return 0
        else if (xBingo && oCount != xCount) // 빙고된 후에 게임이 계속 진행됨
            return 0

        return 1

    }

    // 빙고된 이후에도 계속 게임 진행하는지 확인
    private fun checkBingo(target: Char, board: Array<String>): Boolean {
        //가로
        for (i in board.indices) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] == target)
                return true
        }

        // 세로
        for (i in board.indices) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] == target)
                return true
        }

        // 대각선
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == target)
            return true

        // 대각선
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[1][1] == target)
            return true

        return false
    }
}