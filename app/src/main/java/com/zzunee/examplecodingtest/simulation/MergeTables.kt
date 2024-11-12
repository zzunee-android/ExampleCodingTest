package com.zzunee.examplecodingtest.simulation

/**
 * [Lv 3] 2023 KAKAO BLIND RECRUITMENT > 표 병합
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/150366
 * 1. 병합 여부를 나타내는 배열과 데이터를 담는 배열 선언
 * 2. 각 명령어 별 처리하며 병합된 셀에서는 부모의 데이터를 바라보게 만듦
 */
class MergeTables {
    fun solution(commands: Array<String>): Array<String> {
        var answer: Array<String> = arrayOf()
        val merged = Array(51) { i -> Array(51) { j -> Pair(i, j) } } // 병합된 표 (merged[i][j] -> 병합된 셀의 부모 좌표)
        val contents = Array(51) { Array(51) { "EMPTY" } } // 데이터 표 (contents[i][j] -> 데이터)

        for (command in commands) {
            val commandArray = command.split(" ")

            when (commandArray[0]) { // command
                "UPDATE" -> {
                    if (commandArray.size > 3) {
                        // “UPDATE r c value” --> contents[r][c] = value
                        val point = merged[commandArray[1].toInt()][commandArray[2].toInt()] // 병합 셀 좌표 가져오기 (병합 되지 않았으면 자기 자신)
                        contents[point.first][point.second] = commandArray[3]
                    } else {
                        // “UPDATE value1 value2” --> contents 내 value1을 전체 value2로 변경
                        for (i in contents.indices) {
                            for (j in contents[i].indices) {
                                if (contents[i][j] == commandArray[1]) {
                                    contents[i][j] = commandArray[2]
                                }
                            }
                        }
                    }
                }

                "MERGE" -> {
                    // “MERGE r1 c1 r2 c2” --> [r1][c1]과 [r2][c2] 병합
                    val p1 = merged[commandArray[1].toInt()][commandArray[2].toInt()]
                    val p2 = merged[commandArray[3].toInt()][commandArray[4].toInt()]

                    // point2가 부모인 경우 전체 point1으로 변경
                    for (i in merged.indices) {
                        for (j in merged[i].indices) {
                            if (merged[i][j] == p2) {
                                merged[i][j] = p1
                            }
                        }
                    }

                    if (contents[p1.first][p1.second] == "EMPTY") {
                        contents[p1.first][p1.second] = contents[p2.first][p2.second] // contents[r][c] --> EMPTY or 데이터
                    } else {
                        contents[p2.first][p2.second] = contents[p1.first][p1.second] // contents[r][c] --> 데이터
                    }
                }

                "UNMERGE" -> {
                    // “UNMERGE r c”
                    val point = merged[commandArray[1].toInt()][commandArray[2].toInt()]
                    val content = contents[point.first][point.second]

                    for (i in merged.indices) {
                        for (j in merged[i].indices) {
                            if (merged[i][j] == point) { // 병합된 셀
                                merged[i][j] = Pair(i, j) // 병합 해제
                                contents[i][j] = "EMPTY"
                            }
                        }
                    }

                    // contents[r][c] = 병합 셀에 있던 데이터를 가짐
                    contents[commandArray[1].toInt()][commandArray[2].toInt()] = content
                }

                else -> {
                    // “PRINT r c”
                    val point = merged[commandArray[1].toInt()][commandArray[2].toInt()]
                    answer += contents[point.first][point.second]
                }
            }
        }
        return answer
    }
}