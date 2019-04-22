package com.icm.challenge.model;

import com.icm.challenge.enums.RankingEnum;

public class RankingResult {

    private Card highCard;
    private RankingEnum rankingEnum;

    public Card getHighCard() {
        return highCard;
    }

    public void setHighCard(Card highCard) {
        this.highCard = highCard;
    }

    public RankingEnum getRankingEnum() {
        return rankingEnum;
    }

    public void setRankingEnum(RankingEnum rankingEnum) {
        this.rankingEnum = rankingEnum;
    }
}