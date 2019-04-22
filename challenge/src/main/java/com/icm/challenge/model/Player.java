package com.icm.challenge.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private List < Card > cards;

    public void setCards(List < Card > cards) {
        this.cards = cards;
    }

    public List < Card > getCards() {
        return cards;
    }

    public Map < Integer, Integer > getCardsRankCountMap() {
        List < Card > cards = this.getCards();
        Map < Integer, Integer > rankCount = new HashMap < Integer, Integer > ();
        for (Card card: cards) {
            Integer number = new Integer(card.getRank().getNumber());
            if (!rankCount.containsKey(number)) {
                rankCount.put(number, 1);
            } else {
                rankCount.put(number, rankCount.get(number) + 1);
            }
        }
        return rankCount;
    }
}