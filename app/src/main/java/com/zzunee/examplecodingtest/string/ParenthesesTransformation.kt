package com.zzunee.examplecodingtest.string

import java.util.*

/**
 * [Lv 2] 2020 KAKAO BLIND RECRUITMENT > 괄호 변환
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/60058
 * 1. Stack과 count를 통해 문자열 균형 확인
 * 2. 올바른 문자열은 v를 재귀 수행
 * 3. 올바르지 않은 문자열은 문제 조건에 따라 처리
 */
class ParenthesesTransformation {
    fun solution(p: String): String = splitString(p)

    private fun splitString(w: String): String {
        var count = 0
        val stack = Stack<Char>() // 스택에는 '('만 삽입

        for (i in w.indices) {
            if (w[i] == '(') {
                count -= 1
                stack.push(w[i])
            } else {
                count += 1
                if (stack.isNotEmpty()) {
                    stack.pop()
                }
            }

            if (count == 0) { // 균형 잡힌 괄호 문자열 생성
                val u = w.substring(0, i + 1) // 균형 잡힌 괄호 문자열
                val v = w.substring(i + 1) // 나머지 문자열 or 빈칸

                return if (stack.isEmpty()) {  // 올바른 괄호 문자열
                    val str = StringBuilder(u)
                    str.append(splitString(v)) // v를 다시 재귀적으로 수행
                    str.toString()
                } else {    // 올바르지 않은 괄호 문자열
                    rotateString(u, v)
                }
            }
        }

        return w
    }

    private fun rotateString(u: String, v: String): String {
        val str = StringBuilder("(") // 첫번째 문자로 '('
        str.append(splitString(v))       // v를 다시 재귀적으로 수행
        str.append(")")                  // 마지막 문자로 ')'

        for (i in 1 until u.length - 1) {
            str.append(if (u[i] == '(') ")" else "(") // u의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙임
        }

        return str.toString()
    }
}