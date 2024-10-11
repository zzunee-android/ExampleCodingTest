package com.zzunee.examplecodingtest.implementation

/**
 * 2024 KAKAO WINTER INTERNSHIP > n + 1 카드게임
 * 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/258707
 * 카카오 해설 : https://tech.kakao.com/posts/610
 * 💡현재까지 뽑은 카드를 따로 저장하고 필요할 때마다 코인을 내고 가져옴
 */
class Nplus1CardGame {
    fun solution(coin: Int, cards: IntArray): Int {
        var answer: Int = 0
        val n = cards.size
        val target = n + 1
        val users = mutableSetOf<Int>() // 현재 손에 들고 있는 카드
        val remains = mutableSetOf<Int>() // 현재까지 뽑은 카드
        var coins = coin

        // 초기 손에 들고 있는 카드
        for (i in 0 until n / 3) {
            users.add(cards[i])
        }

        for (i in n / 3 until n step 2) {
            answer++ // 라운드 추가
            remains.add(cards[i])   // 카드 2장 추가
            remains.add(cards[i + 1]) // 카드 2장 추가

            var goNext = false // 다음 라운드로 갈 수 있는지

            // 코인 0개 사용 시
            for (card in users) {
                if (users.contains(target - card)) {
                    users.remove(card)
                    users.remove(target - card)
                    goNext = true
                    break
                }
            }

            // 코인 1개 사용 시
            if (!goNext && coins > 0) {
                for (card in remains) { // 현재까지 뽑은 카드 중 손에 들고 있는 것과 매칭할 수 있는지 확인
                    if (users.contains(target - card)) {
                        users.remove(target - card)
                        remains.remove(card)
                        coins--
                        goNext = true
                        break
                    }
                }
            }

            // 코인 2개 사용 시
            if (!goNext && coins > 1) {
                for (card in remains) {     // 현재까지 뽑은 카드끼리 매칭할 수 있는지 확인
                    if (remains.contains(target - card)) {
                        remains.remove(target - card)
                        remains.remove(card)
                        coins -= 2
                        goNext = true
                        break
                    }
                }
            }

            if (!goNext) {
                break
            } else {
                if (i + 2 >= n) { // 다음 라운드에는 카드가 없음
                    answer++
                }
            }
        }

        return answer
    }
}