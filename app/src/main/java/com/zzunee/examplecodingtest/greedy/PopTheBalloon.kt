package com.zzunee.examplecodingtest.greedy

/**
 * [Lv 3] 월간 코드 챌린지 시즌1 > 풍선 터트리기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/68646
 * 1. 양방향으로 탐색하며 마지막까지 남을 가능성이 있는 풍선 확인
 * 2. 작은값은 1번만 왼->오, 오->왼 방향의 최소값을 저장하는 방식으로 확인
 */
class PopTheBalloon {
    fun solution(a: IntArray): Int {
        var leftToRight = Int.MAX_VALUE // 왼 -> 오 최소값
        var rightToLeft = Int.MAX_VALUE // 오 -> 왼 최소값
        val isAlive = BooleanArray(a.size)

        for (i in a.indices) {
            // 왼 -> 오로 탐색
            if (leftToRight > a[i]) { // 해당 풍선이 최소값이라면 살아남을 수 있음
                leftToRight = a[i]
                isAlive[i] = true
            }

            // 오 -> 왼으로 탐색
            if (rightToLeft > a[a.size - i - 1]) {
                rightToLeft = a[a.size - i - 1]
                isAlive[a.size - i - 1] = true
            }
        }

        return isAlive.count { it }
    }
}