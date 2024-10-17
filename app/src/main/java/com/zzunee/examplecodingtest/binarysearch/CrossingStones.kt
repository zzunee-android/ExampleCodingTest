package com.zzunee.examplecodingtest.binarysearch

/**
 * [Lv 3] 2019 카카오 개발자 겨울 인턴십 > 징검다리 건너기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/64062
 * 1. stones 원소의 최대 값 = 건널 수 있는 최대 인원 = 200_000_000
 * 2. 건너는 인원을 절반(mid)씩 줄이며 현재 인원이 징검다리를 모두 건널 수 있는지 확인
 * 3. checkStone에서 인원이 징검다리 수보다 많으면 건너뛸 수 있는지(skip <= k) 확인
 */
class CrossingStones {
    fun solution(stones: IntArray, k: Int): Int {
        var start = 1
        var end = 200_000_000

        while(start <= end) {
            val mid = (start + end) / 2

            if(checkStone(stones, mid, k)) { // 건널 수 있음
                start = mid + 1
            } else {
                end = mid - 1
            }
        }

        return end
    }

    private fun checkStone(stones: IntArray, people: Int, k: Int) : Boolean {
        var skip = 0

        for(stone in stones) {
            if(stone < people) {
                skip++
                if(skip >= k) {
                    return false
                }
            } else {
                skip = 0
            }
        }
        return true
    }
}