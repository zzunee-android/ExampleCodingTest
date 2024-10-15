package com.zzunee.examplecodingtest.implementation

/**
 * [Lv 2] 2017 팁스타운 > 예상 대진표
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12985
 * 💡a와 b가 동일한 라운드로 갈때까지 절반으로 나누기
 */
class ExpectedMatchUp {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 0
        var aNum = a
        var bNum = b

        while (aNum != bNum) {
            answer++
            aNum = (aNum + 1) / 2
            bNum = (bNum + 1) / 2
        }

        return answer
    }
}