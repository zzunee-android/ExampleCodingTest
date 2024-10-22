package com.zzunee.examplecodingtest.backtracking

/**
 * [Lv 3] 2022 KAKAO BLIND RECRUITMENT > 양과 늑대
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92343
 * 1. 부모(i)와 자식(graph[i])으로 graph 생성
 * 2. 단계 별 양과 늑대의 수에 따라 조건 종료
 * 2-1. 종료 : 늑대가 양과 같거나 클 경우
 * 2-2. 진행 : 다음 탐색 리스트에 현재 노드의 자식들 추가
 */
class SheepAndWolf {
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        var answer: Int = 0
        val graph = Array(info.size) { mutableListOf<Int>() }

        for((p, c) in edges) { // 부모 노드, 자식 노드
            graph[p].add(c)
        }

        fun dfs(node: Int, s: Int, w: Int, nodeList: List<Int>) {
            // sheep = 양의 수, wolf = 늑대의 수
            // xor = 같으면 0, 다르면 1
            val sheep = s + (info[node] xor 1)  // 양(0) xor 1 = 1, 늑대(1) xor 1 = 0
            val wolf = w + info[node] // 양 = 0, 늑대 = 1

            if(wolf >= sheep) { // 늑대가 크거나 같으면 종료
                return
            }

            answer = maxOf(answer, sheep) // 최대값 갱신

            val nextList = nodeList.toMutableList()
            nextList.remove(node) // 현재 노드 다음 탐색 리스트에서 삭제
            nextList.addAll(graph[node]) // 현재 노드의 자식을 다음 탐색 리스트에 추가

            for(nextNode in nextList) {
                dfs(nextNode, sheep, wolf, nextList)
            }
        }

        dfs(0, 0, 0, listOf(0))

        return answer
    }
}