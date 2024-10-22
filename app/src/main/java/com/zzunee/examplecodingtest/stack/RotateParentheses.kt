package com.zzunee.examplecodingtest.stack

import java.util.Stack

/**
 * [Lv 2] 월간 코드 챌린지 시즌2 > 괄호 회전하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/76502
 * 1. 왼쪽으로 x칸 만큼 회전한 새로운 문자열 생성
 * 2. "{, [, (" 이면 push, "), ], }" pop하여 규칙 준수하는지 확인
 */
class RotateParentheses {
    fun solution(s: String): Int {
        var answer: Int = 0

        for(i in s.indices) {
            val rotated = s.substring(i, s.length) + s.substring(0, i) // s를 왼쪽으로 i칸 만큼 회전

            if(checkString(rotated)) {
                answer++
            }
        }

        return answer
    }

    private fun checkString(s: String) : Boolean {
        val stack = Stack<Char>()

        for(i in s.indices) {
            if(stack.isEmpty()) { // 스택이 비어 있을 때
                if(s[i] == '}' || s[i] == ']' || s[i] == ')') // 닫는 괄호면 규칙 미준수
                    return false

                stack.push(s[i])
            }
            else {
                // stack의 최상위 괄호와 현재 괄호가 짝이면 pop
                when(stack.peek()) {
                    '{' -> if(s[i] == '}') stack.pop() else stack.push(s[i])
                    '[' -> if(s[i] == ']') stack.pop() else stack.push(s[i])
                    '(' -> if(s[i] == ')') stack.pop() else stack.push(s[i])
                    else -> return false // 닫는 괄호가 stack에 있으면 규칙 미준수
                }
            }
        }

        return stack.isEmpty()
    }
}