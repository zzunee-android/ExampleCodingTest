package com.zzunee.examplecodingtest.implementation

/**
 * [Lv 2] 2022 KAKAO BLIND RECRUITMENT > k진수에서 소수 개수 구하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92335
 * 1. n을 k진수로 변환 후 각 자리수가 0인 인덱스 번호 저장
 * 2. 0과 0사이 숫자를 통한 소수 판별
 */
class FindPrimeNumber {
    fun solution(n: Int, k: Int): Int {
        var answer: Int = 0
        val number = n.toString(k) // k진수로 변환
        val zeroList = arrayListOf<Int>() // 0이 포함된 인덱스 저장
        var prevIndex = 0

        // value=0인 인덱스 저장
        for (i in number.indices) {
            if (number[i] == '0') {
                zeroList += i
            }
        }

        for (index in zeroList) {
            if (checkedPrime(number, prevIndex, index)) {
                answer++
            }
            prevIndex = index + 1
        }

        // 마지막 0 ~ 문자열 끝까지 소수 여부 추가 확인
        if (checkedPrime(number, prevIndex, number.length)) {
            answer++
        }

        return answer
    }

    // 0과 0사이 수가 소수인지 확인
    private fun checkedPrime(number: String, prevIndex: Int, index: Int): Boolean {
        val numStr = number.substring(prevIndex, index)

        return !(numStr.isEmpty() || !isPrime(numStr.toLong()))
    }

    //소수 판별
    private fun isPrime(num: Long): Boolean {
        if (num <= 1) return false // 0, 1은 소수 아님

        var i: Long = 2L
        while (i * i <= num) { // 제곱근까지만
            if (num % i++ == 0L) return false
        }

        return true
    }
}