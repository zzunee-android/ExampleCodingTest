package com.zzunee.examplecodingtest.backtracking

/**
 * [Lv 2] 소수 찾기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42839
 * 1. 문자를 하나씩 조합하며 소수를 만들 수 있는지 확인
 * 2. 소수 조건
 * 2-1. 1이하 소수 아님
 * 2-2. i~num의 제곱근 중, num이 i로 나눠지면 소수 아님
 * 2-3. 나머지는 소수
 * 3. StringBuilder가 일반 String보다 효율이 훨씬 좋음
 */
class FindPrimeNumber {
    fun solution(numbers: String): Int {
        val visited = BooleanArray(numbers.length) // 방문 여부
        val primeNumbers = mutableSetOf<Int>() // 소수 리스트 저장
        val sb = StringBuilder() // 메모리, 실행 시간 효율

        fun dfs() {
            for (i in numbers.indices) {
                if (!visited[i]) {
                    visited[i] = true
                    sb.append(numbers[i])
                    val num = sb.toString().toInt()

                    if (isPrime(num)) {
                        primeNumbers.add(num)
                    }

                    dfs()

                    sb.deleteCharAt(sb.length - 1);
                    visited[i] = false
                }
            }
        }

        dfs()
        return primeNumbers.size
    }

    private fun isPrime(num: Int): Boolean {
        if (num <= 1) return false

        var i = 2
        while (i * i <= num) {
            if (num % i++ == 0) return false
        }

        return true
    }
}