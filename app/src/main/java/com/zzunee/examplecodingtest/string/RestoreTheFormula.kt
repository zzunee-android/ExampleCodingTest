package com.zzunee.examplecodingtest.string

/**
 * [Lv 3] PCCP 기출문제 > 4번 / 수식 복원하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/340210
 * 1. 주어진 수식(expressions)에 적용할 수 있는 진법(bases) 확인
 * 2. 가능한 진법을 다시 결과를 알 수 없는 수식(unknownExpression)에 적용
 * 3. unknownExpression의 결과가 일괄되게 나오면 정답, 아니면 "?" 삽입
 */
class RestoreTheFormula {
    private val bases = (2..9).toMutableList() // 가능한 n진수

    fun solution(expressions: Array<String>): Array<String> {
        var answer: Array<String> = arrayOf<String>()
        val unknownExpression = mutableListOf<String>() // 정답을 모르는 수식

        // 수식에서 가능한 n진수 찾기
        for (expression in expressions) {
            if (expression.last() == 'X') {
                unknownExpression.add(expression) // 결과 값을 모르는 수식은 리스트 추가
            }

            checkValidBase(expression) // n진수 체크
        }

        // 정답을 모르는 수식 확인
        for (unknown in unknownExpression) {
            val expression = unknown.split(" ")
            val result = checkUnknownResult(expression)
            answer += getExpression(expression, result)
        }

        return answer
    }

    // 현재 수식에 가능한 n진수 확인
    private fun checkValidBase(str: String) {
        val expression = str.split(" ")
        val iterator = bases.iterator()

        while (iterator.hasNext()) {
            val base = iterator.next()

            try {
                val a = expression[0].toInt(base) // 진법 변환한 a
                val b = expression[2].toInt(base) // 진법 변환한 b

                if (expression.last() != "X") {
                    val result = expression.last().toInt(base) // 수식에 있는 결과
                    val currentResult = if (expression[1] == "+") a + b else a - b // a, b를 통한 결과
                    if (currentResult != result) {
                        iterator.remove()
                    }
                }
            } catch (e: NumberFormatException) { // 현재 수식에 맞지 않는 진법
                iterator.remove()
            }
        }
    }

    // 정답을 모르는 수식 답 확인
    private fun checkUnknownResult(expression: List<String>): Int {
        var result = -1 // 진법 변환 결과

        for (base in bases) {
            val a = expression[0].toInt(base)
            val b = expression[2].toInt(base)
            val currentResult = if (expression[1] == "+") {
                (a + b).toString(base).toInt()
            } else {
                (a - b).toString(base).toInt()
            }

            if (result != -1 && result != currentResult) { // 결과가 이전과 같지 않으면 현재 진법이 확실하지 않음
                return -1
            }

            result = currentResult
        }

        return result
    }

    private fun getExpression(expression: List<String>, result: Int): String {
        val sb = StringBuilder()

        for (i in 0 until expression.size - 1) {
            sb.append(expression[i])
            sb.append(" ")
        }

        sb.append(if (result == -1) "?" else result)
        return sb.toString()
    }
}