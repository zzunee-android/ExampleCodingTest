package com.zzunee.examplecodingtest.implementation

/**
 * 2020 KAKAO BLIND RECRUITMENT > 기둥과 보 설치
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/60061
 * 💡각 기둥과 보를 Set에 넣고 삭제, 설치마다 구조물 상태 확인 (기둥과 보가 유효한 조건 체크가 핵심)
 */
class PillarAndCloth {
    private fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        val structures = mutableSetOf<Triple<Int, Int, Int>>()

        for ((x, y, type, action) in build_frame) {
            val frame = Triple(x, y, type)

            // 일단 삭제 또는 설치하고 이후 구조물이 유효한지 확인해서 복원 여부 결정
            if (action == 0) {  //삭제
                structures.remove(frame)
                if (!isValid(structures)) structures.add(frame)
            } else { // 설치
                structures.add(frame)
                if (!isValid(structures)) structures.remove(frame)
            }
        }

        // 오름차순
        return structures.map { intArrayOf(it.first, it.second, it.third) }
            .sortedWith(compareBy({ it[0] }, { it[1] }, { it[2] }))
            .toTypedArray()
    }

    // 기둥이 유효한가?
    // 기둥이 유효한 조건 : 바닥 위, 다른 기둥 위, 보의 한쪽 끝 위(양쪽 보 체크)
    private fun isValidPillar(x: Int, y: Int, structures: MutableSet<Triple<Int, Int, Int>>): Boolean {
        return (y == 0 || structures.contains(Triple(x, y - 1, 0)) ||
                structures.contains(Triple(x - 1, y, 1)) || structures.contains(Triple(x, y, 1)))
    }

    // 보가 유효한가?
    // 보가 유효한 조건 : 한쪽 끝이 기둥 위(양쪽 기둥 체크), 양쪽 끝이 다른 보와 동시에 연결
    private fun isValidCloth(x: Int, y: Int, structures: MutableSet<Triple<Int, Int, Int>>): Boolean {
        return (structures.contains(Triple(x, y - 1, 0)) ||
                structures.contains(Triple(x + 1, y - 1, 0)) ||
                (structures.contains(Triple(x - 1, y, 1)) && structures.contains(Triple(x + 1, y, 1))))
    }

    // 현재 구조물이 유효한가?
    private fun isValid(structures: MutableSet<Triple<Int, Int, Int>>): Boolean {
        for ((x, y, type) in structures) {
            if (type == 0) {
                if (!isValidPillar(x, y, structures)) return false
            } else {
                if (!isValidCloth(x, y, structures)) return false
            }
        }

        return true
    }
}