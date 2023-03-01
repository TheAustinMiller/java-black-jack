import java.util.*;
class Main {
  public static final int DEALER_STAND = 17;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    ArrayList<Card> myHand = new ArrayList<>();
    ArrayList<Card> splitHand = new ArrayList<>();
    ArrayList<Card> dealerHand = new ArrayList<>();
    Deck d = new Deck();
    d.initialize();
    d.shuffle();
    int hand = 0;

    System.out.println("WELCOME TO AJ's BLACKJACK PLAY'EM!");
    System.out.println("Please type the amount of cheddar you would like to start with:");
    int startingAmount = in.nextInt();
    while (startingAmount < 1) {
      System.out.println("You're gonna need more to play, man!");
      System.out.println("Please type the amount of cheddar you would like to start with:");
      startingAmount = in.nextInt();
    }
    System.out.println();
    int money = startingAmount;
    boolean stillPlaying = true;
    boolean firstMove = true;
    while (stillPlaying) {
      boolean canSplit = false;
      boolean canDouble = false;
      System.out.println("How much are you willing to bet on this hand?");
      int bet = in.nextInt();
      while (bet <= 0 || bet > money) {
        System.out.println("Invalid amount: Please enter a sufficient integer.");
        System.out.println("How much are you willing to bet on this hand?");
        bet = in.nextInt();
      }
      System.out.println("\nBEGIN HAND #" + ++hand);
      Card newCard = d.drawCard();
      myHand.add(newCard);
      newCard = d.drawCard();
      myHand.add(newCard);
      newCard = d.drawCard();
      dealerHand.add(newCard);
      newCard = d.drawCard();
      dealerHand.add(newCard);

      printHand(myHand, false);
      System.out.println("Sum: " + sumHand(myHand));
      printDealerHand(dealerHand);
      System.out.println();

      System.out.println("Will you: ");
      System.out.println("\t(1) Hit");
      System.out.println("\t(2) Stand");
      if (firstMove) {
        if (bet * 2 <= money) {
          System.out.println("\t(3) Double Down");
          canDouble = true;
          if (myHand.get(0).getVal().equals(myHand.get(1).getVal())) {
            System.out.println("\t(4) Split");
            canSplit = true;
          }
        }
      }
      int move = in.nextInt();
      while (move <= 0 || move > 4 || (!canSplit && move == 4) || (!canDouble && move == 3)) {
        System.out.println("Invalid Input. Please choose a possible option.");
      }

      while (move != 2) {
        switch(move) {
          case 1:
            myHand.add(d.drawCard());
            printHand(myHand, false);
            System.out.println("Sum: " + sumHand(myHand));
            printDealerHand(dealerHand);
            System.out.println();
        }
      }
      

      firstMove = false;
    }
  }

  public static void printSplitHand (ArrayList<Card> splitHand) {
    System.out.println();
    System.out.print("YOUR SECOND HAND: ");
    System.out.print(splitHand.get(0) + " - " + splitHand.get(1));
    System.out.println();
  }

  public static void printHand(ArrayList<Card> hand, boolean houseHand) {
    String whosehand = "";
    System.out.println();
    if (houseHand) {
      System.out.println("DEALER'S HAND: ");
    } else {
      System.out.print("YOUR HAND: ");
    }
    for (int i = 0; i < hand.size() - 1; i++) {
      System.out.print(hand.get(i) + " - ");
    }
    System.out.println(hand.get(hand.size() - 1));
    System.out.println();
  }

  public static void printDealerHand(ArrayList<Card> hand) {
    System.out.println();
    System.out.print("DEALER IS SHOWING: ");
    System.out.print(hand.get(0));
    System.out.println();
  }

  public static int sumHand(ArrayList<Card> hand) {
    int sum = 0;
    int aceCount = 0;
    for (int i = 0; i < hand.size(); i++) {
      if (hand.get(i).getVal() == "A") {
        aceCount++;
      } else if (hand.get(i).getVal() == "J") {
        sum += 10;
      } else if (hand.get(i).getVal() == "Q") {
        sum += 10;
      }  else if (hand.get(i).getVal() == "K") {
        sum += 10;
      } else {
        sum += Integer.parseInt(hand.get(i).getVal());
      }
    }
    while (aceCount > 0) {
      if (sum >= 11) {
        sum += 1;
      } else {
        sum += 11;
      }
      aceCount--;
    }
    return sum;
  }
}
