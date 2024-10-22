package com.zzunee.examplecodingtest.bfs

/**
 * [Lv 3] 2021 KAKAO BLIND RECRUITMENT > 카드 짝 맞추기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/72415
 * 1. 카드를 뒤집을 수 있는 순열 확인 (permutation) --> [1,2,3], [1,3,2], [2,1,3] ...
 * 2. 리스트 별 이동 거리 계산 (시작 -> A -> B, 시작 -> B -> A 중 더 짧은 거리를 선택)
 * 3. bfs를 통한 위치 이동 조건
 * 3-1. 일반 이동 ----> 방향 키 조작에 따른 1칸 이동 후 이동 횟수(move) +1
 * 3-2. Ctrl 이동 ----> 가능할 때까지 1칸씩 이동하다가 다른 카드나 유효하지 않은 범위가 되면 이동 횟수(move) +1
 */
class CardMatching {
    private val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1)) // 상, 하, 좌, 우
    private val cardMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>() // key = 카드 종류, value = 위치 (무조건 2개)

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        var answer = Int.MAX_VALUE

        // 카드 종류 별 위치 저장
        for (i in board.indices) {
            for (j in board[i].indices) {
                if (board[i][j] != 0) {
                    // key = board[i][j] 가 cardMap에 있다면 위치만 추가
                    // 해당 key가 cardMap에 없다면 리스트 생성 후 위치 추가
                    cardMap.computeIfAbsent(board[i][j]) { mutableListOf() }.add(Pair(i, j))
                }
            }
        }

        // 가능한 카드 순열 확인
        val cardPermutations = permutation(cardMap.keys.toList())

        for (cards in cardPermutations) {
            val copyBoard = Array(4) { board[it].clone() } // 리스트 확인할 때마다 배열 복사
            val start = Pair(r, c)
            answer = minOf(answer, moveCount(copyBoard, start, cards))
        }

        return answer
    }

    // board에서 일치하는 카드 찾기
    private fun bfs(start: Pair<Int, Int>, target: Pair<Int, Int>, board: Array<IntArray>): Int {
        val visited = Array(4) { BooleanArray(4) } // 방문 여부
        val queue = ArrayDeque<Triple<Int, Int, Int>>() // (r, c, move)
        queue.add(Triple(start.first, start.second, 0))
        visited[start.first][start.second] = true

        while (queue.isNotEmpty()) {
            val (r, c, move) = queue.removeFirst() // x, y, 이동 횟수

            if (r == target.first && c == target.second) { // 목표한 카드에 도작 시 종료
                return move
            }

            // 한칸 이동
            for ((dr, dc) in directions) {
                val newR = r + dr
                val newC = c + dc

                if (newR in 0..3 && newC in 0..3 && !visited[newR][newC]) {
                    visited[newR][newC] = true
                    queue.add(Triple(newR, newC, move + 1))
                }
            }

            // Ctrl + 방향키 이동
            for ((dr, dc) in directions) {
                var newR = r
                var newC = c

                // 한 방향으로 카드가 있을 때까지 이동
                while (newR + dr in 0..3 && newC + dc in 0..3) {
                    newR += dr
                    newC += dc

                    if (board[newR][newC] != 0) { // 카드가 있는 경우
                        break
                    }
                }

                if (!visited[newR][newC]) {
                    visited[newR][newC] = true
                    queue.add(Triple(newR, newC, move + 1))
                }
            }
        }

        return 0
    }

    // 이동 횟수 계산
    // A -> B, B -> A 계산 후 최소값으로 저장
    private fun moveCount(board: Array<IntArray>, start: Pair<Int, Int>, cards: List<Int>): Int {
        var pos = start // 현재 시작 위치
        var move = 0 // 총 이동 횟수

        for (num in cards) {
            val card = cardMap[num]!!
            val firstCard = card[0]
            val secondCard = card[1]

            // A To B -> 시작점 -> A, A -> B로 이동한 거리 합 (+2 = 각 카드 뒤집는 Enter 수)
            val firstToSecond = bfs(pos, firstCard, board) + bfs(firstCard, secondCard, board) + 2
            val secondToFirst = bfs(pos, secondCard, board) + bfs(secondCard, firstCard, board) + 2

            // 최단 거리로 이동하며, 다음 시작 위치는 현재 뒤집은 카드 위치
            if (firstToSecond < secondToFirst) {
                move += firstToSecond
                pos = secondCard
            } else {
                move += secondToFirst
                pos = firstCard
            }

            // 카드 삭제
            board[firstCard.first][firstCard.second] = 0
            board[secondCard.first][secondCard.second] = 0
        }

        return move
    }

    // 가능한 카드 경우의 수
    private fun permutation(cards: List<Int>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun permute(list: MutableList<Int>) {
            if (list.size == cards.size) {
                result.add(list.toList())
                return
            }

            for (card in cards) {
                if (!list.contains(card)) {
                    list.add(card)
                    permute(list)
                    list.remove(card)
                }
            }
        }

        permute(mutableListOf())

        return result.toList()
    }
}