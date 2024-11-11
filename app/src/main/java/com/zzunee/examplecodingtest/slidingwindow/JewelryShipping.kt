package com.zzunee.examplecodingtest.slidingwindow

/**
 * [Lv 3] 2020 카카오 인턴십 > 보석 쇼핑
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/67258
 * 1. 보석의 종류 및 개수 확인
 * 2. 진열대 끝 번호를 확인하며 보석을 전부 구매할 수 있는지 확인
 * 3. 보석을 전부 구매할 수 있으면 시작 번호를 업데이트하며 가장 짧은 구간 탐색
 */
class JewelryShipping {
    fun solution(gems: Array<String>): IntArray {
        val answer = intArrayOf(0, Int.MAX_VALUE) // 시작번호, 종료 번호
        val jewelName = gems.distinct()     // 중복 없는 보석 이름
        val jewelMap = mutableMapOf<String, Int>() // 보석 이름, 개수

        if (jewelName.size == 1) {       // 보석 이름이 1개면 첫번째 구매
            return intArrayOf(1, 1)
        } else if (jewelName.size == gems.size) { // 보석 이름이 배열 길이와 같다면 전체 구매
            return intArrayOf(1, gems.size)
        }

        var startIndex = 0
        var minCount = Int.MAX_VALUE

        gems.forEachIndexed { i, gem ->
            jewelMap[gem] = jewelMap.getOrDefault(gem, 0) + 1

            while (jewelMap.size == jewelName.size) { // 모든 보석을 살 수 있음
                if (i - startIndex + 1 < minCount) { // 최소 구간 업데이트
                    answer[0] = startIndex + 1
                    answer[1] = i + 1
                    minCount = i - startIndex + 1
                }

                // 기존 startIndex 보석 개수 -1
                jewelMap[gems[startIndex]] = jewelMap.getOrDefault(gems[startIndex], 0) - 1

                // 보석이 없으면 map에서 삭제
                if (jewelMap[gems[startIndex]] == 0) {
                    jewelMap.remove(gems[startIndex])
                }

                // 시작 인덱스 갱신
                startIndex++
            }
        }

        return answer
    }
}