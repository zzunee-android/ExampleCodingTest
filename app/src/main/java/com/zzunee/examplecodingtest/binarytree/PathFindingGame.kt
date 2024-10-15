package com.zzunee.examplecodingtest.binarytree

/**
 * [Lv 3] 2019 KAKAO BLIND RECRUITMENT > 길 찾기 게임
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42892
 * 💡주어진 배열 정렬 후 이진 트리 생성. root 기준으로 왼쪽과 오른쪽을 순회
 */
class PathFindingGame {
    private val preorder = mutableListOf<Int>()
    private val postorder = mutableListOf<Int>()

    private fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        val sortedNode = nodeinfo.mapIndexed { i, arr -> Node(i+1, arr[0], arr[1]) }
            .sortedWith(compareBy({ -it.y }, { it.x })) // y 기준 내림차순, x는 오름차순

        val root = sortedNode.first() // root 기준 정렬
        for(node in sortedNode.drop(1)) {
            insertNode(root, node)
        }

        checkNode(root)

        return arrayOf(preorder.toIntArray(), postorder.toIntArray())
    }

    private fun checkNode(root: Node?) {
        if(root == null) return

        preorder.add(root.index) // 전위 순회는 부모가 우선
        checkNode(root.left)
        checkNode(root.right)
        postorder.add(root.index) // 후위 순회는 부모가 나중
    }

    private fun insertNode(parent: Node, child: Node) {
        if(parent.x < child.x) { // 오른쪽에 추가
            if(parent.right == null) parent.right = child else insertNode(parent.right!!, child)
        } else { // 왼쪽에 추가
            if(parent.left == null) parent.left = child else insertNode(parent.left!!, child)
        }
    }

    data class Node(val index: Int, val x: Int, val y: Int, var left: Node? = null, var right: Node? = null)
}