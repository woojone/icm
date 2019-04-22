package com.icm.challenge.model;

import com.icm.challenge.enums.CardRankEnum;
import com.icm.challenge.enums.CardSuitEnum;

public class Card implements Comparable < Card > {

    private CardSuitEnum suit;
    private CardRankEnum rank;

    public Card(CardSuitEnum suit, CardRankEnum rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public CardSuitEnum getSuit() {
        return suit;
    }

    public int getRankNumber() {
        return this.rank.getNumber();
    }

    public void setSuit(CardSuitEnum suit) {
        this.suit = suit;
    }

    public CardRankEnum getRank() {
        return rank;
    }

    public void setRank(CardRankEnum rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (this.suit.getName() != card.suit.getName()) return false;
        return this.rank.getNumber() == card.rank.getNumber();

    }

    @Override
    public int compareTo(Card card2) {
        int card1Number = this.rank.getNumber();
        int card2Number = card2.rank.getNumber();

        if (card1Number < card2Number) {
            return 1;
        }
        if (card1Number > card2Number) {
            return -1;
        }
        return 0;
    }
}