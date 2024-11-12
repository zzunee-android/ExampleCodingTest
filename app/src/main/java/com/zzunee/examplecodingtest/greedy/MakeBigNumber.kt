package com.zzunee.examplecodingtest.greedy

/**
 * [Lv 2] 큰 수 만들기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42883
 * 1. 문자 수를 number.length - k 개로 지정
 * 2. 탐색 범위 내 최대 수로 시작 인덱스 변경
 */
class MakeBigNumber {
    fun solution(number: String, k: Int): String {
        val sb = StringBuilder()
        var start = 0 // 시작 인덱스
        var size = number.length - k // 추가할 수 있는 문자의 수

        while (size > 0) {
            if (start == number.length - size) { // 남은 문자 개수가 추가 해야 하는 문자의 수라면 전체 더하기
                sb.append(number.substring(start, number.length))
                break
            }

            var max = start
            for (i in start..(number.length - size)) { // 탐색 범위
                if (number[i] > number[max]) { // 탐색 범위 내 최대값 확인
                    max = i
                }
            }

            sb.append(number[max])
            size-- // 추가할 문자 수 감소
            start = max + 1 // 시작 인덱스는 최대값 다음 값
        }

        return sb.toString()
    }
}