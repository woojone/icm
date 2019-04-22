package com.icm.challenge.calculate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.icm.challenge.enums.RankingEnum;
import com.icm.challenge.model.Card;
import com.icm.challenge.model.Player;

public class Comparing {

    public static int getComparing(Player o1, Player o2, RankingEnum rankingEnum) {
        int result = 0;
        switch (rankingEnum) {
            case ROYAL_FLUSH:
                result = 0;
                break;
            case STRAIGHT_FLUSH:
                result = seqComparing(o1, o2);
                break;
            case FOUR_OF_A_KIND:
                result = fourOfAKingCompare(o1, o2);
                break;
            case FULL_HOUSE:
                result = fullHouseCompare(o1, o2);
                break;
            case FLUSH:
                result = seqComparing(o1, o2);
                break;
            case STRAIGHT:
                result = seqComparing(o1, o2);
                break;
            case THREE_OF_A_KIND:
                result = threeOfAkingCompare(o1, o2);
                break;
            case TWO_PAIRS:
                result = twoPairsCompare(o1, o2);
                break;
            case ONE_PAIR:
                result = onePairCompare(o1, o2);
                break;
            case HIGH_CARD:
                result = seqComparing(o1, o2);
                break;
            default:
                seqComparing(o1, o2);
        }
        return result;
    }

    public static int fourOfAKingCompare(Player o1, Player o2) {
        Map < Integer, Integer > p1CardMap = o1.getCardsRankCountMap();
        Map < Integer, Integer > p2CardMap = o2.getCardsRankCountMap();
        return multiComparing(p1CardMap, p2CardMap, 4);
    }

    public static int fullHouseCompare(Player o1, Player o2) {
        Map < Integer, Integer > p1CardMap = o1.getCardsRankCountMap();
        Map < Integer, Integer > p2CardMap = o2.getCardsRankCountMap();
        return multiComparing(p1CardMap, p2CardMap, 3);
    }


    public static int onePairCompare(Player o1, Player o2) {
        Map < Integer, Integer > p1CardMap = o1.getCardsRankCountMap();
        Map < Integer, Integer > p2CardMap = o2.getCardsRankCountMap();
        return pairComparing(p1CardMap, p2CardMap, 2, 2);
    }

    public static int threeOfAkingCompare(Player o1, Player o2) {
        Map < Integer, Integer > p1CardMap = o1.getCardsRankCountMap();
        Map < Integer, Integer > p2CardMap = o2.getCardsRankCountMap();
        return multiComparing(p1CardMap, p2CardMap, 3);
    }

    public static int twoPairsCompare(Player o1, Player o2) {
        Map < Integer, Integer > p1CardMap = o1.getCardsRankCountMap();
        Map < Integer, Integer > p2CardMap = o2.getCardsRankCountMap();
        return pairComparing(p1CardMap, p2CardMap, 2, 3);
    }

    private static int multiComparing(Map < Integer, Integer > map1, Map < Integer, Integer > map2, int pair) {

        int p1Number = -1;
        int p2Number = -1;

        Iterator < Map.Entry < Integer, Integer >> it = map1.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry < Integer, Integer > next = it.next();
            if (next.getValue() == pair) {
                p1Number = next.getKey();
            }
        }

        Iterator < Map.Entry < Integer, Integer >> it2 = map2.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry < Integer, Integer > next = it2.next();
            if (next.getValue() == pair) {
                p2Number = next.getKey();
            }
        }

        if (p1Number > p2Number) {
            return -1;
        }
        if (p1Number < p2Number) {
            return 1;
        }

        return 0;
    }

    private static int seqComparing(Player p1, Player p2) {
        List < Card > p1Cards = p1.getCards();
        List < Card > p2Cards = p2.getCards();

        int size = 5;

        for (int i = 0; i < size; i++) {
            if (p1Cards.get(i).getRank().getNumber() < p2Cards.get(i).getRank().getNumber()) {
                return 1;
            }
            if (p1Cards.get(i).getRank().getNumber() > p2Cards.get(i).getRank().getNumber()) {
                return -1;
            }
            if (p1Cards.get(i).getRank().getNumber() == p2Cards.get(i).getRank().getNumber()) {
                continue;
            }
        }
        return 0;
    }

    private static int pairComparing(Map < Integer, Integer > map1, Map < Integer, Integer > map2, int pair, int maxPairLoopAddOne) {
        if (maxPairLoopAddOne - 1 == 0) {
            pair = 1;
        }
        int p1MaxNum = findMaxNumber(map1, pair);
        int p2MaxNum = findMaxNumber(map2, pair);

        if (p1MaxNum < p2MaxNum) {
            return 1;
        }
        if (p1MaxNum > p2MaxNum) {
            return -1;
        }
        if (p1MaxNum == p2MaxNum) {
            map1.remove(p1MaxNum);
            map2.remove(p2MaxNum);
            if (map1.size() == map2.size() && 0 == maxPairLoopAddOne - 1) {
                return pairComparing(map1, map2, pair - 1, 1);
            }
            return pairComparing(map1, map2, pair, maxPairLoopAddOne - 1);
        }
        return 0;
    }

    private static int findMaxNumber(Map < Integer, Integer > map, int pair) {
        int p1Number = -1;

        Iterator < Map.Entry < Integer, Integer >> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry < Integer, Integer > next = it.next();
            if (next.getValue() == pair) {
                int number = next.getKey();
                if (number > p1Number) {
                    p1Number = number;
                }
            }
        }
        return p1Number;
    }
}