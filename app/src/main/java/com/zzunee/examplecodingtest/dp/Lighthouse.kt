package com.zzunee.examplecodingtest.dp

/**
 * [Lv 3] 등대
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/133500
 * 1. 등대 on, off 여부에 따른 dp 배열 선언
 * 1-1. dp[i][0] --> i번 등대를 끈 경우, 자식 등대는 on인 개수를 더함
 * 1-2. dp[i][1] --> i번 등대를 켠 경우, 자식 등대는 on, off 상관 없음
 */
class Lighthouse {
    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        val route = Array(n + 1) { mutableListOf<Int>() } // 등대 간 연결 정보
        val visited = BooleanArray(n + 1) // 각 등대 방문 여부
        val dp = Array(n + 1) { IntArray(2) }

        // 부모와 그에 인접한 노드들 저장
        for ((start, end) in lighthouse) {
            route[start].add(end)
            route[end].add(start)
        }

        dfs(1, route, visited, dp)

        return minOf(dp[1][0], dp[1][1]) // 루트 등대의 최소값 반환
    }

    private fun dfs(
        i: Int,
        route: Array<MutableList<Int>>,
        visited: BooleanArray,
        dp: Array<IntArray>,
    ) {
        visited[i] = true
        dp[i][0] = 0    //현재 등대 off -> 자식 노드는 무조건 on
        dp[i][1] = 1    //현재 등대 on  -> 자식 노드는 on 또는 off

        for (childNode in route[i]) { // 연결된 등대 확인
            if (!visited[childNode]) {
                dfs(childNode, route, visited, dp)
                dp[i][0] += dp[childNode][1] // 현재 등대 off -> 자식 노드 on인 값 더하기
                dp[i][1] += minOf(
                    dp[childNode][0],
                    dp[childNode][1]
                ) // 현재 등대 on -> 자식 노드 on, off 중 최소값 더하기
            }
        }
    }
}