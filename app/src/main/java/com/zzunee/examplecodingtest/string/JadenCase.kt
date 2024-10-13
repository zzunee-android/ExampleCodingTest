package com.zzunee.examplecodingtest.string

/**
 * [Lv 2] JadenCase 문자열 만들기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12951
 * 공백(" ") 기준으로 분리하고 첫글자만 대문자 처리
 */
class JadenCase {
    // String Builder 사용
    fun solution(s: String): String {
        val sb = StringBuilder()
        val words = s.split(" ")
        var index = 0

        for ((i, word) in words.withIndex()) {
            if (word.isEmpty()) {
                sb.append(" ")
                index++
            } else {
                sb.append(word[0].uppercase())
                sb.append(word.substring(1).lowercase())

                index += word.length
                if (index < s.length - 1) {
                    sb.append(" ")
                    index++
                }
            }
        }

        return sb.toString()
    }

    // joinToString 사용 -> 실행 시간이 더 걸림
//    fun solution(s: String): String = s.split(" ").joinToString(" ") { word ->
//        if (word.isEmpty()) {
//            word
//        } else {
//            word.substring(0, 1).uppercase() + word.substring(1).lowercase()
//        }
//    }
}