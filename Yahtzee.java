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
        scoreCard.put("1", 0);
        scoreCard.put("2", 0);
        scoreCard.put("3", 0);
        scoreCard.put("4", 0);
        scoreCard.put("5", 0);
        scoreCard.put("6", 0);
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to the game of Yahtzee!");
        for (int h = 0; h < 7; h++) {
            rollDice();
            int i = 0;
            while (i < NUM_REROLLS) {
                if (isYahtzee()) {
                    break;
                }
                dice = bubbleSort(dice);
                System.out.println(diceToString());
                System.out.print("Which dice do you want to reroll (# # #) (press return if none): ");
                String response = keyboard.nextLine();
                if (response.isEmpty()) {
                    break;
                }
                rollDice(convert(response));
                i++;
            }
            while (true) {
                System.out.println(diceToString());
                if (isYahtzee()) {
                    scoreCard.put("Yahtzee", scoreCard.get("Yahtzee") + 50);
                    System.out.println("Congratulations, you got a Yahtzee that counts for 50 points");
                    break;
                }
                if (isLargeStraight()) {
                    if (scoreCard.get("largeStraight") == 0) {
                        scoreCard.put("largeStraight", scoreCard.get("largeStraight") + 40);
                        System.out.println("Congratulations, you got a Large Straight that counts for 40 points");
                        break;
                    }
                }
                if (isSmallStraight()) {
                    if (scoreCard.get("smallStraight") == 0) {
                        scoreCard.put("smallStraight", scoreCard.get("smallStraight") + 30);
                        System.out.println("Congratulations, you got a Small Straight that counts for 30 points");
                        break;
                    }
                }
                if (isFullHouse()) {
                    if (scoreCard.get("fullHouse") == 0) {
                        scoreCard.put("fullHouse", scoreCard.get("fullHouse") + 25);
                        System.out.println("Congratulations, you got a Full House that counts for 25 points");
                        break;
                    }
                }
                if (isFourOfAKind()) {
                    if (scoreCard.get("fourOfAKind") == 0) {
                        scoreCard.put("fourOfAKind", scoreCard.get("fourOfAKind") + sumOfDice());
                        System.out.println("Congratulations, you got a Four of A Kind that counts for " + sumOfDice() + " points");
                        break;
                    }
                }
                if (isThreeOfAKind()) {
                    if (scoreCard.get("threeOfAKind") == 0) {
                        scoreCard.put("threeOfAKind", scoreCard.get("threeOfAKind") + sumOfDice());
                        System.out.println("Congratulations, you got a Three of A Kind that counts for " + sumOfDice() + " points");
                        break;
                    }
                }
                if (scoreCard.get("Chance") == 0) {
                    scoreCard.put("Chance", scoreCard.get("Chance") + sumOfDice());
                    System.out.println("Congratulations, you got a Chance that counts for " + sumOfDice() + " points");
                    break;
                }
                while (true) {
                    System.out.println("Which number do you want to score in the upper section? (1, 2, 3, 4, 5, or, 6)");
                    String userChoice = keyboard.nextLine();
                    int numDice = 0;
                    int userChoiceInt = 0;
                    if (scoreCard.get(userChoice) == 0) {
                        userChoiceInt = Integer.parseInt(userChoice);
                        for (int value : dice) {
                            if (value == userChoiceInt) {
                                numDice += 1;
                            }
                        }
                    scoreCard.put(userChoice, numDice * userChoiceInt);
                    System.out.println("You got " + scoreCard.get(userChoice) + " points");
                    break;
                    } else {
                        System.out.println("Please choose a number that has not already been scored.");
                    }
                }
                break;
            }
        }
        printGrid();
    }

    /**
    * Returns the sum of the dice.
    * @return sum of the dice.
    */
    public static int sumOfDice() {
        int sum = 0;
        for (int el : dice) {
            sum += el;
        }
        return sum;
    }

    /**
    * Rerolls each die given in an integer array.
    * @param diceToChange type: int[], the dice that will be rerolled.
    */
    public static void rollDice(int[] diceToChange) {
        for (int i : diceToChange) {
            dice[i-1] = getRandomDieValue();
        }
    }

    /**
    * Populates the dice.
    */
    public static void rollDice() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice[i] = getRandomDieValue();
        }
    }

    /**
    * Returns a random integer ranging from 1 to 6.
    * @return random integer (1-6)
    */
    public static int getRandomDieValue() {
        return (int) (Math.random() * 6 + 1);
    }

    /**
    * Converts a string with integers separated by whitespaace into an integer array.
    * @param s type: String, consisting of integers separated by whitespace.
    * @return integer array
    */
    public static int[] convert(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int[] a = new int[st.countTokens()];
        int i = 0;

        while (st.hasMoreTokens()) {
            a[i++] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    /**
    * Returns a boolean value based on if there was a Yahtzee or not.
    * @return boolean
    */
    public static boolean isYahtzee() {
        for (int el : dice) {
            if (el != dice[0]) {
                return false;
            }
        }
        return true;
    }

    /**
    * Returns a string representation of the dice.
    * @return boolean
    */
    public static String diceToString() {
        dice = bubbleSort(dice);
        String result = "Your dice are: ";
        for (int el : dice) {
            result += el + " ";
        }
        return result;
    }

    /**
    * Returns an integer sorted in ascending numerical order.
    * @param diceToSort type: int[], the dice that need sorting.
    * @return boolean
    */
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

    /**
    * Returns a boolean value based on if there was a three of a kind or not.
    * @return boolean
    */
    public static boolean isThreeOfAKind() {
        for (int i = 0; i < 3; i++) {
        if(dice[i] == dice[i+1] && dice[i+1] == dice[i+2]) {
            return true;
        }
    }
    return false;
    }

    /**
    * Returns a boolean value based on if there was a four of a kind or not.
    * @return boolean
    */
    public static boolean isFourOfAKind() {
        for (int i = 0; i < 2; i++) {
        if(dice[i] == dice[i+1] && dice[i+1] == dice[i+2] && dice[i+2] == dice[i+3]) {
            return true;
        }
    }
    return false;
    }

    /**
    * Returns a boolean value based on if there was a full house or not.
    * @return boolean
    */
    public static boolean isFullHouse() {
        if (dice[0] == dice[1] && dice[2] == dice[3] && dice[3] == dice[4]) {
            return true;
        } else if (dice[0] == dice[1] && dice[1] == dice[2] && dice[3] == dice[4]) {
            return true;
        } else {
            return false;
        }
    }

    /**
    * Returns a boolean value based on if there was a small straight or not.
    * @return boolean
    */
    public static boolean isSmallStraight() {
          int j = 0;
          int[] newArray = new int[4];
          for (int i = 0; i < newArray.length; i++) {
              if (dice[i] != dice[i+1]) {
                  newArray[j] = dice[i];
                  j++;
              }
          }
          newArray[3] = dice[4];
          System.out.println(Arrays.toString(newArray));
          for (int i = 0; i < newArray.length - 1; i++) {
              if (newArray[i] != newArray[i+1] - 1) {
                  return false;
              }
              }
          return true;
      }

    /**
    * Returns a boolean value based on if there was a large straight or not.
    * @return boolean
    */
    public static boolean isLargeStraight() {
        for (int i = 0; i < dice.length - 1; i++) {
            if (dice[i] != dice[i + 1] - 1) {
                return false;
            }
        }
        return true;
    }
    public static void printGrid() {
        System.out.println("Score Card for Your Game: ");
        System.out.println("| Upper Section | Name           | Score |");
        System.out.println("|               | Ones           | " + Integer.toString(scoreCard.get("1")));
        System.out.println("|               | Twos           | " + Integer.toString(scoreCard.get("2")));
        System.out.println("|               | Threes         | " + Integer.toString(scoreCard.get("3")));
        System.out.println("|               | Fours          | " + Integer.toString(scoreCard.get("4")));
        System.out.println("|               | Fives          | " + Integer.toString(scoreCard.get("5")));
        System.out.println("|               | Sixes          | " + Integer.toString(scoreCard.get("6")));
        System.out.println("| Lower Section | Name           | Score |");
        System.out.println("|               | Yahtzee        | " + Integer.toString(scoreCard.get("Yahtzee")));
        System.out.println("|               | Large Straight | " + Integer.toString(scoreCard.get("largeStraight")));
        System.out.println("|               | Small Straight | " + Integer.toString(scoreCard.get("smallStraight")));
        System.out.println("|               | Full House     | " + Integer.toString(scoreCard.get("fullHouse")));
        System.out.println("|               | 4 of A Kind    | " + Integer.toString(scoreCard.get("fourOfAKind")));
        System.out.println("|               | 3 of A Kind    | " + Integer.toString(scoreCard.get("threeOfAKind")));
        System.out.println("|               | Chance         | " + Integer.toString(scoreCard.get("Chance")));
        int total = scoreCard.get("Yahtzee") + scoreCard.get("largeStraight") + scoreCard.get("smallStraight") + scoreCard.get("fullHouse") + scoreCard.get("fourOfAKind") + scoreCard.get("threeOfAKind") + scoreCard.get("Chance") + scoreCard.get("1") + scoreCard.get("2") + scoreCard.get("3") + scoreCard.get("4") + scoreCard.get("5") + scoreCard.get("6");
        System.out.println("Your final score was: " + total);
    }
}
