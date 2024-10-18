package com.zzunee.examplecodingtest.greedy

import java.util.Stack

/**
 * [Lv 2] 택배 상자
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/131704
 * 1. 가장 마지막에 보관한 상자부터 뺄 수 있음 =====> Stack
 * 2. 박스를 싣는 조건
 * 2-1. 현재 박스 번호가 메인 벨트의 가장 앞(order[i])
 * 2-2. 현재 박스 번호가 보조 벨트의 가장 뒤(subBelt.peek())
 */
class DeliveryBox {
    fun solution(order: IntArray): Int {
        var answer: Int = 0
        val subBelt = Stack<Int>() // 보조 컨테이너 벨트
        var i = 0
        var box = 1 // 상자 번호

        while (box <= order.size) {
            var currentBox = order[i] // 현재 실어야 하는 상자 번호

            while (box == currentBox || (subBelt.isNotEmpty() && subBelt.peek() == currentBox)) {
                if (box == currentBox) { // 메인 컨테이너 벨트에 있음
                    box++
                } else { // 보조 컨테이너 벨트에 있음
                    subBelt.pop()
                }

                answer++
                i++

                if (i >= order.size) {
                    break
                }

                currentBox = order[i]
            }

            subBelt.push(box++)
        }

        return answer
    }
}