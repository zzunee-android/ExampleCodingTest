package com.zzunee.examplecodingtest.simulation

/**
 * [Lv 2] 2022 KAKAO BLIND RECRUITMENT > 주차 요금 계산
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92341
 * 1. 출차 map과 시간 map을 선언하여 차량 번호 별 값 확인
 * 2. 출차하지 않은 차는 주차 시간을 최대로 계산
 * 3. 분 단위로 변환하여 확인 시 편리
 */
class CalculateParkingFee {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val timeMap = mutableMapOf<String, Int>() // 차량 번호, 주차 시간
        val inOutMap = mutableMapOf<String, Boolean>() // 차량 번호, 출차 여부 (true -> 입차, false -> 출차)

        // 누적 시간 계산
        for (record in records) { // ["05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT"]
            val car = record.split(" ")

            if (car[2] == "IN") {
                timeMap[car[1]] = timeMap.getOrDefault(car[1], 0) - calculateMinute(car[0])
                inOutMap[car[1]] = true
            } else {
                timeMap[car[1]] = timeMap.getOrDefault(car[1], 0) + calculateMinute(car[0])
                inOutMap[car[1]] = false
            }
        }

        // 출차하지 않은 차 한번에 계산
        for (map in timeMap) {
            val isIn = inOutMap.getOrDefault(map.key, false)

            if (isIn) {
                timeMap[map.key] = timeMap.getOrDefault(map.key, 0) + calculateMinute("23:59")
            }
            timeMap[map.key] = calculateFee(map.value, fees)
        }

        return timeMap.toSortedMap().values.toIntArray()
    }

    // 요금 계산
    // fees = [ 기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원) ]
    private fun calculateFee(time: Int, fees: IntArray): Int {
        val t = time - fees[0] // 단위 시간

        if (t <= 0) {
            return fees[1]
        }

        return fees[1] + ((t + fees[2] - 1) / fees[2] * fees[3])
    }

    // "hh:mm"을 분 단위로 변환
    private fun calculateMinute(time: String): Int {
        val t = time.split(":")
        return t[0].toInt() * 60 + t[1].toInt()
    }
}