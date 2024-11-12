package com.zzunee.examplecodingtest.slidingwindow

/**
 * [Lv 2] 할인 행사
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/131127
 * 1. 제품 유형 별 구매 가능한 수량 배열 생성
 * 2. j일~10일 간 확인 하며 구매할 수 있는 제품의 수 확인
 * 3. 모든 제품을 구매할 수 있는 날짜 개수 확인
 */
class DiscountEvent {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer: Int = 0
        val cart = Array(want.size) { IntArray(discount.size) } // 원하는 제품 별 구매 가능한 수량 (cart[i][j] = i 제품을 j일~10일간 구매할 수 있는 수량)

        for (i in cart.indices) {
            for (j in cart[i].indices) {
                var count = 0

                for (k in j until j + 10) { // j일부터 10일동안 유효한 회원권
                    if (k >= cart[i].size) {
                        break
                    }

                    if (want[i] == discount[k]) { // 원하는 제품을 세일 기간에 구매할 수 있는 경우
                        count++
                    }
                }

                cart[i][j] = count
            }
        }

        for (j in cart[0].indices) { // 할인 행사 기간
            var canBuy = true

            for (i in cart.indices) {
                if (number[i] > cart[i][j]) { // j일에는 상품을 전부 구매하지 못함
                    canBuy = false
                    break
                }
            }

            if (canBuy) {
                answer++
            }
        }


        return answer
    }
}