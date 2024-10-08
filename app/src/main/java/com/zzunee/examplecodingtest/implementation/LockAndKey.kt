package com.zzunee.examplecodingtest.implementation

/**
 * 2020 KAKAO BLIND RECRUITMENT > 자물쇠와 열쇠
 * https://school.programmers.co.kr/learn/courses/30/lessons/60059
 * 자물쇠 배열의 크기를 3배 확장하고 x, y를 한칸씩 이동하며 전체 탐색
 * 현재 열쇠로 풀 수 없으면 90도씩 회전
 */
class LockAndKey {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val expandedLock = Array(lock.size * 3) { IntArray(lock.size * 3) }
        val N = lock.size

        // 자물쇠 배열 3배 확장
        for (i in lock.indices) {
            for (j in lock.indices) {
                expandedLock[i + N][j + N] = lock[i][j]
            }
        }

        // 열쇠 회전 및 확인
        var rotatedKey = key

        repeat(4) { // 4번 회전
            // 시작 범위 2배로 확장
            // 자물쇠의 왼쪽 위 -> 오른쪽 아래까지 내리며 체크
            for (i in 0 until N * 2) { // x 이동
                for (j in 0 until N * 2) { // y 이동
                    if (checkSolve(expandedLock, rotatedKey, i, j, N)) {
                        return true
                    }
                }
            }
            rotatedKey = rotate(rotatedKey)
        }

        return false
    }

    private fun checkSolve(lock: Array<IntArray>, key: Array<IntArray>, x: Int, y: Int, n: Int): Boolean {
        val expandedLock = lock.map { it.clone() }.toTypedArray()

        // 자물쇠에 키 값 더하기
        // 열쇠의 빈칸 + 자물쇠의 돌기 = 1
        // 열쇠의 돌기 + 자물쇠의 빈칸 = 1
        // 열쇠의 돌기 + 자물쇠의 돌기 = 2
        // 열쇠의 빈칸 + 자물쇠의 빈칸 = 0
        for (i in key.indices) {
            for (j in key.indices) {
                expandedLock[x + i][y + j] += key[i][j]
            }
        }

        // 자물쇠 중간 부분이 1인지 확인
        for (i in n until 2 * n) {
            for (j in n until 2 * n) {
                if (expandedLock[i][j] != 1) return false
            }
        }

        return true
    }

    private fun rotate(key: Array<IntArray>): Array<IntArray> {
        val n = key.size
        val rotated = Array(n) { IntArray(n) }

        for (i in key.indices) {
            for (j in key[i].indices) {
                rotated[i][j] = key[n - j - 1][i]
            }
        }

        return rotated
    }
}