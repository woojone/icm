package com.icm.challenge.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.icm.challenge.enums.CardRankEnum;
import com.icm.challenge.enums.CardSuitEnum;
import com.icm.challenge.enums.RankingEnum;
import com.icm.challenge.model.Card;
import com.icm.challenge.model.Player;
import com.icm.challenge.model.RankingResult;

public class Ranking {

    public static RankingResult getRanking(Player player) {
        RankingResult result = null;

        do {
            result = isFlush(player);
            if (isFlush(player) != null) break;

            result = isFourOfAKind(player);
            if (isFourOfAKind(player) != null) break;

            result = isFullHouse(player);
            if (isFullHouse(player) != null) break;

            result = isOnePair(player);
            if (isOnePair(player) != null) break;

            result = isRoyalFlush(player);
            if (isRoyalFlush(player) != null) break;

            result = isStraightFlush(player);
            if (isStraightFlush(player) != null) break;

            result = isStraight(player);
            if (isStraight(player) != null) break;

            result = isThreeOfAKind(player);
            if (isThreeOfAKind(player) != null) break;

            result = isTwoPairs(player);
            if (isTwoPairs(player) != null) break;

            result = isHighCard(player);
            break;

        } while (false);

        result.setHighCard(player.getCards().get(0));
        return result;
    }

    private static RankingResult isTwoPairs(Player player) {

        RankingResult result = null;
        Map < Integer, Integer > rankCount = player.getCardsRankCountMap();

        boolean hasTwo = false;

        if (rankCount.size() == 3) {
            Iterator < Map.Entry < Integer, Integer >> it = rankCount.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry < Integer, Integer > next = it.next();
                if (next.getValue() == 2 || next.getValue() == 1) {
                    hasTwo = true;
                    break;
                }
            }
        }

        if (hasTwo) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.TWO_PAIRS);
        }

        return result;
    }

    private static RankingResult isThreeOfAKind(Player player) {

        RankingResult result = null;
        Map < Integer, Integer > rankCount = player.getCardsRankCountMap();

        boolean hasThree = false;

        Iterator < Map.Entry < Integer, Integer >> it = rankCount.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == 3) {
                hasThree = true;
                break;
            }
        }

        if (hasThree) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.THREE_OF_A_KIND);
        }

        return result;
    }

    private static RankingResult isStraight(Player player) {

        RankingResult result = null;

        List < Card > cards = player.getCards();
        if (!isSameSuit(cards)) {
            boolean isStraight = true;
            Card previousCard = null;
            for (Card card: cards) {
                if (previousCard != null) {
                    if (card.getRank().getNumber() - previousCard.getRank().getNumber() != -1) {
                        isStraight = false;
                        break;
                    }
                }
                previousCard = card;
            }
            if (isStraight == true) {
                result = new RankingResult();
                result.setRankingEnum(RankingEnum.STRAIGHT);
            }

        }

        return result;
    }

    private static RankingResult isStraightFlush(Player player) {

        RankingResult result = null;

        List < Card > cards = player.getCards();
        if (isSameSuit(cards)) {
            boolean isStraight = true;
            Card previousCard = null;
            for (Card card: cards) {
                if (previousCard != null) {
                    if (card.getRank().getNumber() - previousCard.getRank().getNumber() != -1) {
                        isStraight = false;
                        break;
                    }
                }
                previousCard = card;
            }
            if (isStraight == true) {
                result = new RankingResult();
                result.setRankingEnum(RankingEnum.STRAIGHT_FLUSH);
            }

        }

        return result;
    }

    private static RankingResult isRoyalFlush(Player player) {

        RankingResult result = null;

        List < Card > cards = player.getCards();
        if (isSameSuit(cards)) {
            List < CardRankEnum > ranks = new ArrayList < CardRankEnum > ();
            for (Card card: cards) {
                ranks.add(card.getRank());
            }
            if (ranks.contains(CardRankEnum.CARD_TEN) &&
                ranks.contains(CardRankEnum.CARD_JACK) &&
                ranks.contains(CardRankEnum.CARD_QUEUE) &&
                ranks.contains(CardRankEnum.CARD_KING) &&
                ranks.contains(CardRankEnum.CARD_ACE)) {
                result = new RankingResult();
                result.setRankingEnum(RankingEnum.ROYAL_FLUSH);
            }
        }

        return result;
    }

    private static RankingResult isOnePair(Player player) {

        RankingResult result = null;
        Map < Integer, Integer > rankCount = player.getCardsRankCountMap();

        boolean hasOne = false;

        if (rankCount.size() == 4) {
            Iterator < Map.Entry < Integer, Integer >> it = rankCount.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry < Integer, Integer > next = it.next();
                if (next.getValue() == 2 || next.getValue() == 1) {
                    hasOne = true;
                    break;
                }
            }
        }

        if (hasOne) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.ONE_PAIR);
        }

        return result;
    }

    private static RankingResult isHighCard(Player player) {

        RankingResult result = null;
        
        boolean allOne = false;

        List < Card > cards = player.getCards();
        if (!isSameSuit(cards)) {
            Card maxCard = cards.get(0);
            Card minCard = cards.get(cards.size() - 1);
            if (maxCard.getRank().getNumber() - minCard.getRank().getNumber() >= 5) {
                allOne = true;
            }
        }

        if (allOne) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.HIGH_CARD);
        }

        return result;
    }

    private static RankingResult isFullHouse(Player player) {

        RankingResult result = null;

        boolean isFullHouse = false;
        Map < Integer, Integer > rankCount = player.getCardsRankCountMap();

        if (rankCount.size() == 2) {
            Iterator < Map.Entry < Integer, Integer >> it = rankCount.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry < Integer, Integer > next = it.next();
                if (next.getValue() == 2 || next.getValue() == 3) {
                    isFullHouse = true;
                    break;
                }
            }
        }

        if (isFullHouse) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.FULL_HOUSE);
        }

        return result;
    }

    private static RankingResult isFlush(Player player) {

        RankingResult result = null;

        List < Card > cards = player.getCards();
        if (isSameSuit(cards)) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.FLUSH);
        }
        return result;
    }

    private static RankingResult isFourOfAKind(Player player) {

        RankingResult result = null;
        Map < Integer, Integer > rankCount = player.getCardsRankCountMap();

        boolean hasFour = false;

        Iterator < Map.Entry < Integer, Integer >> it = rankCount.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getValue() == 5 - 1) {
                hasFour = true;
                break;
            }
        }
        if (hasFour) {
            result = new RankingResult();
            result.setRankingEnum(RankingEnum.FOUR_OF_A_KIND);
        }

        return result;
    }

    private static boolean isSameSuit(List < Card > cards) {
        if (cards == null || cards.size() == 0) {
            return false;
        }
        if (cards.size() == 1) {
            return true;
        }
        if (cards.size() > 1) {
            Card card = cards.get(0);
            CardSuitEnum suitEnum = card.getSuit();
            for (int i = 1; i < cards.size(); i++) {
                if (suitEnum != cards.get(i).getSuit()) {
                    return false;
                }
            }
        }
        return true;
    }
}