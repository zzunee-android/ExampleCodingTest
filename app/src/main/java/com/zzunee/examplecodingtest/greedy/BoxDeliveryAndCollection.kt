package com.zzunee.examplecodingtest.greedy

import kotlin.math.*

/**
 * [Lv 2] 2023 KAKAO BLIND RECRUITMENT > 택배 배달과 수거하기
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/150369
 * 1. 가장 먼 집부터 시작해 물류창고로 돌아오며 집을 하나씩 들리는 구조
 * 2. cap을 전부 채워 돌아와야 이동 거리가 짧아짐
 */
class BoxDeliveryAndCollection {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0
        val delivery = deliveries   // delivery[i] = i번째 집에 배달할 택배 상자의 개수
        val pickup = pickups        // pickup[i] = i번째 집에서 수거할 택배 상자의 개수
        var di = delivery.indexOfLast { it != 0 } // 가장 먼 곳부터
        var pi = pickups.indexOfLast { it != 0 }

        while (di >= 0 || pi >= 0) {
            var box = 0 // 박스 개수
            val startDi = di
            val startPi = pi

            while (di >= 0) {
                if (box + delivery[di] <= cap) {
                    box += delivery[di]     // di번째 집에 필요한 택배 전부 배달 가능
                    di--    // 다음 집도 들림
                } else {
                    delivery[di] -= (cap - box) // di번째 집에 필요한 택배 일부 배달 가능
                    break
                }
            }

            box = cap

            while (pi >= 0) {
                if (box - pickup[pi] >= 0) {
                    box -= pickup[pi]     // di번째 집에서 택배 상자 전체 수거 가능
                    pi--    // 다음 집도 들림
                } else {
                    pickup[pi] -= box     // di번째 집에서 택배 상자 일부 수거 가능
                    break
                }
            }

            answer += (max(startDi, startPi) + 1) * 2 // 초기 이동거리 * 왕복
        }

        return answer
    }
}