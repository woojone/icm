package com.icm.challenge.enums;

public enum RankingEnum {
    	HIGH_CARD("High card", 1),
        ONE_PAIR("Pair", 2),
        TWO_PAIRS("Two pairs", 3),
        THREE_OF_A_KIND("Three of a kind", 4),
        STRAIGHT("Straight", 5),
        FLUSH("Flush", 6),
        FULL_HOUSE("Full house", 7),
        FOUR_OF_A_KIND("Four of a kind", 8),
        STRAIGHT_FLUSH("Straight flush", 9),
        ROYAL_FLUSH("Royal Flush", 10);

    private String type;
    private int priority;

    RankingEnum(String type, int priority) {
        this.type = type;
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}