import java.util.*;

public class Yahtzee {
  static final int NUMBER_OF_DICE = 5;
  static final int NUM_REROLLS = 2;
  static int[] dice = new int[NUMBER_OF_DICE];

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    rollDice();

    for (int i = 0; i < NUM_REROLLS; i++) {
      if (isYahtzee()) {
        break;
      }
      System.out.println(diceToString());
      System.out.print("Which dice do you want to reroll: ");
      rollDice(convert(keyboard.nextLine()));
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
}
