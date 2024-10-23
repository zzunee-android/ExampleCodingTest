package com.zzunee.examplecodingtest.implementation

/**
 * [Lv 2] 우박수열 정적분
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/134239
 * 1. 우박수(k)가 1이 될때까지 규칙 적용
 * 1-1. 우박수(collatz)가 짝수면, /2
 * 1-2. 우박수(collatz)가 홀수면, *3+1
 * 2. 면적 구하는 공식 = 사다리꼴 넓이 공식 = 높이 * (윗변+아랫변) / 2
 * 3. 요구한 x 범위에 따른 면적 계산 ([a, -b] --> x=a, x=n-b)
 */
class CollatzGraph {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        var answer: DoubleArray = doubleArrayOf()
        var collatz = k // 규칙에 따라 변하는 우박수
        var n = 0 // k가 1이 되는 횟수
        val width = mutableListOf<Double>()

        while (collatz != 1) {
            val nextK = if (collatz % 2 == 0) collatz / 2 else collatz * 3 + 1 // 다음 우박수
            width.add((nextK + collatz).toDouble() / 2) // 면적 저장
            n++ // 횟수 증가
            collatz = nextK
        }

        for ((startX, b) in ranges) {
            val endX = n + b
            var w: Double = 0.0

            if (startX > endX) { // 유효하지 않은 수
                w += (-1.0)
            } else {
                for (i in startX until endX) { // 요구한 면적의 크기 합산
                    w += width[i]
                }
            }

            answer += w
        }

        return answer
    }
}
