package com.icm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.icm.challenge.calculate.Comparing;
import com.icm.challenge.calculate.Ranking;
import com.icm.challenge.enums.CardRankEnum;
import com.icm.challenge.enums.CardSuitEnum;
import com.icm.challenge.model.Card;
import com.icm.challenge.model.Player;


public class Main {

    public static void main(String[] args) {

        int player1Hands = 0;
        int player2Hands = 0;
        
        File dataFile = null;
        if(args.length == 0) {
        	dataFile = new File("file\\poker-hands.txt");
        }else {
        	String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        	try {
				path = java.net.URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        	
        	int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
        	int lastIndex = path.lastIndexOf(File.separator) + 1;
        	path = path.substring(firstIndex, lastIndex);
        	
        	dataFile = new File(path + args[0]);
        }

        Integer[] columnSizes = {2, 3, 3, 3, 3, 3, 3, 3, 3, 3};
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            String[] array;
            Player player1 = null;
            Player player2 = null;

            String cardSuit = null;
            String cardRank = null;

            List < Card > player1Cards = new ArrayList < Card > ();
            List < Card > player2Cards = new ArrayList < Card > ();
            while ((line = br.readLine()) != null) {
                player1Cards = new ArrayList < Card > ();
                player2Cards = new ArrayList < Card > ();
                array = toStringArrayFromFixLength(line, columnSizes);

                for (int i = 0; i < array.length; i++) {
                    cardRank = array[i].substring(0, 1);
                    cardSuit = array[i].substring(1, 2);
                    Card card = new Card(toCardSuitEnum(cardSuit), toCardRankEnum(cardRank));
                    if (i < 5) {
                        player1Cards.add(card);
                    } else {
                        player2Cards.add(card);
                    }
                }
                player1 = new Player();
                Collections.sort(player1Cards);
                player1.setCards(player1Cards);

                player2 = new Player();
                Collections.sort(player2Cards);
                player2.setCards(player2Cards);

                int res = compare(player1, player2);

                if (res == 1) {
                    player2Hands++;
                } else if (res == -1) {
                    player1Hands++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Player 1: " + player1Hands + " hands");
        System.out.println("Player 2: " + player2Hands + " hands");
    }

    public static int compare(Player player1, Player player2) {

        int player1Priority = Ranking.getRanking(player1).getRankingEnum().getPriority();
        int player2Priority = Ranking.getRanking(player2).getRankingEnum().getPriority();

        if (player1Priority < player2Priority) {
            return 1;
        } else if (player1Priority > player2Priority) {
            return -1;
        } else if (player1Priority == player2Priority) {
            return Comparing.getComparing(player1, player2, Ranking.getRanking(player1).getRankingEnum());
        }
        return 0;
    }

    public static CardSuitEnum toCardSuitEnum(String str) {
        CardSuitEnum res = null;
        switch (str) {
            case "H":
                res = CardSuitEnum.HEARTS;
                break;
            case "D":
                res = CardSuitEnum.DIAMONDS;
                break;
            case "S":
                res = CardSuitEnum.SPADES;
                break;
            case "C":
                res = CardSuitEnum.CLUBS;
                break;
        }
        return res;
    }

    public static CardRankEnum toCardRankEnum(String str) {
        CardRankEnum res = null;
        switch (str) {
            case "2":
                res = CardRankEnum.CARD_TWO;
                break;
            case "3":
                res = CardRankEnum.CARD_THREE;
                break;
            case "4":
                res = CardRankEnum.CARD_FOUR;
                break;
            case "5":
                res = CardRankEnum.CARD_FIVE;
                break;
            case "6":
                res = CardRankEnum.CARD_SIX;
                break;
            case "7":
                res = CardRankEnum.CARD_SEVEN;
                break;
            case "8":
                res = CardRankEnum.CARD_EIGHT;
                break;
            case "9":
                res = CardRankEnum.CARD_NINE;
                break;
            case "T":
                res = CardRankEnum.CARD_TEN;
                break;
            case "J":
                res = CardRankEnum.CARD_JACK;
                break;
            case "Q":
                res = CardRankEnum.CARD_QUEUE;
                break;
            case "K":
                res = CardRankEnum.CARD_KING;
                break;
            case "A":
                res = CardRankEnum.CARD_ACE;
                break;
        }
        return res;
    }

    public static String[] toStringArrayFromFixLength(String fixedLengthStr, Integer[] columnSizes) {
        String[] data = new String[columnSizes.length];
        String dataValue = null;
        int pointer = 0;
        if (fixedLengthStr != null) {
            int size = columnSizes.length;
            int max = fixedLengthStr.length();
            int end = 0;
            for (int i = 0; i < size; i++) {
                end = pointer + columnSizes[i];
                if (end > max) {
                    end = max;
                }
                dataValue = fixedLengthStr.substring(pointer, end).trim();
                pointer += columnSizes[i];
                data[i] = dataValue;
            }
        }
        return data;
    }

}