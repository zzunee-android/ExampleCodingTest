package com.zzunee.examplecodingtest.geometry

/**
 * [Lv 2] 위클리 챌린지 > 교점에 별 만들기
 * 문제 링크 : https://chatgpt.com/c/6715ca9c-8bf0-800b-8330-3d85cddf35b9
 * 1. 교점을 구하는 수식 사용 -> 문제에 기재되어 있음
 * 2. 교점에서 최소 최대 x, y를 계산하여 격자판 크기 계산
 * 3. 좌표 평면과 격자판의 특징에 따라 x, y좌표 배치
 */
class CreateStars {
    fun solution(line: Array<IntArray>): Array<String> {
        val points = mutableSetOf<Pair<Int, Int>>()
        var minX = Int.MAX_VALUE
        var maxX = Int.MIN_VALUE
        var minY = Int.MAX_VALUE
        var maxY = Int.MIN_VALUE

        for (i in 0 until line.size - 1) {
            val (a, b, e) = line[i].map { it.toLong() } // 기준 직선

            for (j in 1 until line.size) {
                val (c, d, f) = line[j].map { it.toLong() } // 비교 직선
                val denominator = a * d - b * c // 분모

                if (denominator == 0L) continue // 평행하는 직선 (교점 없음)

                val xNumerator = b * f - e * d
                val yNumerator = e * c - a * f

                if (xNumerator % denominator == 0L && yNumerator % denominator == 0L) { // 정수인 경우에만 확인
                    val x = (xNumerator / denominator).toInt()
                    val y = (yNumerator / denominator).toInt()

                    points.add(Pair(x, y))
                    minX = minOf(minX, x)
                    maxX = maxOf(maxX, x)
                    minY = minOf(minY, y)
                    maxY = maxOf(maxY, y)
                }
            }
        }

        val stars = Array(maxY - minY + 1) { CharArray(maxX - minX + 1) { '.' } } // 격자판 생성

        for ((x, y) in points) {
            // maxY - y -----> y좌표 방향 전환 (좌표 평면에서는 y가 클수록 위에, 격자판에서는 y가 클수록 아래에 위치)
            // x - minX -----> x좌표를 양수로 전환 (minX가 격자판의 0번이 되도록 함)
            stars[maxY - y][x - minX] = '*'
        }

        return stars.map { it.joinToString("") }.toTypedArray()
    }
}