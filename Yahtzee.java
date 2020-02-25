import java.util.*;

public class Yahtzee {
    static final int NUMBER_OF_DICE = 5;
    static final int NUM_REROLLS = 2;
    static int[] dice = new int[NUMBER_OF_DICE];
    Hashtable <String, Integer> scoreCard = new Hashtable <String, Integer>();
    scoreCard.put("threeOfAKind", 0);
    scoreCard.put("fourOfAKind", 0);
    scoreCard.put("fullHouse", 0);
    scoreCard.put("smallStraight", 0);
    scoreCard.put("largeStraight", 0);
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the game of Yahtzee!");
        rollDice();
        for (int i = 0; i < NUM_REROLLS; i++) {
            if (isYahtzee()) {
                break;
            }
            System.out.println(diceToString());
            System.out.print("Which dice do you want to reroll: ");
            rollDice(convert(keyboard.nextLine()));
        }
        dice = bubbleSort(dice);
        if (isThreeOfAKind()) {

        } else if (isFourOfAKind()) {

        } else if (isFullHouse()) {

        } else if (isSmallStraight()) {

        } else if (isLargeStraight()) {

        }
        System.out.println(diceToString());
        if (isYahtzee()) {
            System.out.println("You got Yahtzee!");
        } else {
            System.out.println("Sorry, better luck next time!");
        }
    }

    public static void rollDice(int[] diceToChange) {
        for (int i : diceToChange) {
            dice[i-1] = getRandomDieValue();
        }
    }

    public static void rollDice() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice[i] = getRandomDieValue();
        }
    }

    public static int getRandomDieValue() {
        return (int) (Math.random() * 6 + 1);
    }

    public static int[] convert(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int[] a = new int[st.countTokens()];
        int i = 0;

        while (st.hasMoreTokens()) {
            a[i++] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    public static boolean isYahtzee() {
        for (int el : dice) {
            if (el != dice[0]) {
                return false;
            }
        }
        return true;
    }

    public static String diceToString() {
        String result = "Your dice are: ";
        for (int el : dice) {
            result += el + " ";
        }
        return result;
    }

    public static int[] bubbleSort(int[] diceToSort) {
        int n = diceToSort.length;
        int tempDice = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (n - i - 1); j++) {
                if (diceToSort[j] > diceToSort[j + 1]) {
                    tempDice = diceToSort[j];
                    diceToSort[j] = diceToSort[j + 1];
                    diceToSort[j + 1] = tempDice;
                }
            }
        }
        return diceToSort;
    }

    public static boolean isThreeOfAKind() {
        for (i = 0; i < dice.length - 1; i++;) {
        if(dice[i-1] == dice[i] && dice[i] == dice[i+1]) {
            return true;
        }
    }
    return false;
    }

    public static boolean isFourOfAKind() {
        for (i = 0; i < dice.length - 1; i++;) {
        if(dice[i-1] == dice[i] && dice[i] == dice[i+1] && dice[i+1] == dice[i+2]) {
            return true;
        }
    }
    return false;
    }

    public static boolean isFullHouse() {
        if (dice[0] == dice[1] && dice[2] == dice[3] == dice[4]) {
            return true;
        } else if (dice[0] == dice[1] == dice[2] && dice[3] == dice[4]) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSmallStraight() {
      for (i = 0; i < dice.length - 2; i++;) {
          if (dice[i] != dice[i + 1] - 1) {
              boolean bool1 = false;
          }
      }
      for (i = 1; i < dice.length - 1; i++;) {
          if (dice[i] != dice[i + 1] - 1) {
              boolean bool2 = false;
          }
      }
      if (bool1 and bool2) {
          return false;
      }
      return true;
    }

    public static boolean isLargeStraight() {
        for (i = 0; i < dice.length - 1; i++;) {
            if (dice[i] != dice[i + 1] - 1) {
                return false;
            }
        }
        return true;
    }
}
