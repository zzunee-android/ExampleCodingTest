package com.zzunee.examplecodingtest.greedy

/**
 * [Lv 2] 마법의 엘리베이터
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/148653
 * 1. 가장 오른쪽 자리 수(일의 자리)로 계산
 * 2. 한자리씩 감소시키며 연산 진행
 */
class MagicElevator {
    fun solution(storey: Int): Int {
        var answer = 0
        var num = storey

        while (num > 0) {
            val remain = num % 10 // 일의 자리 계단
            num /= 10 // 자릿 수 감소

            answer += when (remain) {
                in 0..4 -> { // 계단 내려감 (ex. 13 --> 10, 3칸 이동)
                    remain
                }
                in 6..10 -> { // 계단 올라감 (ex. 17 --> 20, 3칸 이동)
                    num += 1 // 자리수 상승
                    10 - remain
                }
                else -> {   // 5
                    if (num % 10 >= 5) { // 위, 아래 모두 동일하게 5칸 이동하니 다음 자리 수에 따라 변경
                        num += 1
                    }

                    remain
                }
            }
        }

        return answer
    }
}