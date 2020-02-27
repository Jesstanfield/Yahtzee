import java.util.*;

public class Yahtzee {
    static final int NUMBER_OF_DICE = 5;
    static final int NUM_REROLLS = 2;
    static int[] dice = new int[NUMBER_OF_DICE];
    static Hashtable<String, Integer> scoreCard = new Hashtable <String, Integer>();
    public static void main(String[] args) {
        scoreCard.put("Yahtzee", 0);
        scoreCard.put("threeOfAKind", 0);
        scoreCard.put("fourOfAKind", 0);
        scoreCard.put("fullHouse", 0);
        scoreCard.put("smallStraight", 0);
        scoreCard.put("largeStraight", 0);
        scoreCard.put("Chance", 0);
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the game of Yahtzee!");
        rollDice();
        for (int i = 0; i < NUM_REROLLS; i++) {
            if (isYahtzee()) {
                break;
            }
            System.out.println(diceToString());
            System.out.print("Do you want to reroll? (Y/N): ");
            if (keyboard.nextLine() == "Y") {
                break;
            }
            System.out.print("Which dice do you want to reroll: ");
            rollDice(convert(keyboard.nextLine()));
        }
        dice = bubbleSort(dice);
        while (true) {
            System.out.println(diceToString());
            if (isYahtzee()) {
                scoreCard["Yahtzee"] = scoreCard.get("Yahtzee") + 50;
                System.out.println("Congratulations, you got a Yahtzee that counts for 50 points");
                break;
            }
            if (isLargeStraight()) {
                if (scoreCard["largeStraight"] == 0) {
                    scoreCard["largeStraight"] = 40;
                    System.out.println("Congratulations, you got a Large Straight that counts for 40 points");
                    break;
                }
            }
            if (isSmallStraight()) {
                if (scoreCard["smallStraight"] == 0) {
                    scoreCard["smallStraight"] = 30;
                    System.out.println("Congratulations, you got a Small Straight that counts for 30 points");
                    break;
                }
            }
            if (isFullHouse()) {
                if (scoreCard["fullHouse"] == 0) {
                    scoreCard["fullHouse"] = 25;
                    System.out.println("Congratulations, you got a Full House that counts for 25 points");
                    break;
                }
            }
            if (isFourOfAKind()) {
                if (scoreCard["fourOfAKind"] == 0) {
                    scoreCard["fourOfAKind"] = sumOfDice();
                    System.out.println("Congratulations, you got a Four of A Kind that counts for " + sumOfDice() + " points");
                    break;
                }
            }
            if (isThreeOfAKind()) {
                if (scoreCard["threeOfAKind"] == 0) {
                    scoreCard["threeOfAKind"] = sumOfDice();
                    System.out.println("Congratulations, you got a Three of A Kind that counts for " + sumOfDice() + " points");
                    break;
                }
            }
            if (scoreCard["Chance"] == 0) {
                scoreCard ["Chance"] = sumOfDice();
                System.out.println("Congratulations, you got a Chance that counts for " + sumOfDice() + " points");
                break;
            }
        }
        int total = scoreCard.get("Yahtzee") + scoreCard.get("largeStraight") + scoreCard.get("smallStraight") + scoreCard.get("fullHouse") + scoreCard.get("fourOfAKind") + scoreCard.get("threeOfAKind") + scoreCard.get("Chance");
        System.out.println("Your final score was: " + total);
    }

    public static int sumOfDice() {
        int sum = 0;
        for (int el : dice) {
            sum += el;
        }
        return sum;
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
        for (int i = 0; i < dice.length - 1; i++) {
        if(dice[i-1] == dice[i] && dice[i] == dice[i+1]) {
            return true;
        }
    }
    return false;
    }

    public static boolean isFourOfAKind() {
        for (int i = 0; i < dice.length - 1; i++) {
        if(dice[i-1] == dice[i] && dice[i] == dice[i+1] && dice[i+1] == dice[i+2]) {
            return true;
        }
    }
    return false;
    }

    public static boolean isFullHouse() {
        if (dice[0] == dice[1] && dice[2] == dice[3] && dice[3] == dice[4]) {
            return true;
        } else if (dice[0] == dice[1] && dice[1] == dice[2] && dice[3] == dice[4]) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSmallStraight() {
        boolean bool1;
        boolean bool2;
        for (int i = 0; i < dice.length - 2; i++) {
          if (dice[i] != dice[i + 1] - 1) {
              bool1 = false;
          }
      }
      for (int i = 1; i < dice.length - 1; i++) {
          if (dice[i] != dice[i + 1] - 1) {
              bool2 = false;
          }
      }
      if (bool1 & bool2) {
          return false;
      }
      return true;
    }

    public static boolean isLargeStraight() {
        for (int i = 0; i < dice.length - 1; i++) {
            if (dice[i] != dice[i + 1] - 1) {
                return false;
            }
        }
        return true;
    }
}
