import java.util.*;

public class Deck {
  private ArrayList<Card> deck;

  public Deck() {
    deck =  new ArrayList<>();
  }

  public void initialize() {
    String suit = "";
    String val = "";
    for (int i = 0; i < 52; i++) {
      if (i >= 0 && i < 13) {
        suit = "Spades";
      } else if (i >= 13 && i <= 25) {
        suit = "Hearts";
      } else if (i > 25 && i <= 38) {
        suit = "Clubs";
      } else {
        suit = "Diamonds";
      }
      if (i == 0 || i == 13 || i == 26 || 1 == 39) {
        val = "A";
      } else if (i == 10 || i == 23 || i == 36 || i == 49) {
        val = "J";
      } else if (i == 11 || i == 24 || i == 37 || i == 50) {
        val = "Q";
      } else if (i == 12 || i == 25 || i == 38 || i == 51) {
        val = "K";
      } else {
        int tmp = (i % 10) + 1;
        val = tmp + "";
      }
      Card c = new Card(suit, val);
      deck.add(c);
    }
  }

  public void print() {
    for (int i = 0; i < deck.size(); i++) {
      System.out.println(deck.get(i) + "\n");
    }
  }

  public Card drawCard() {
    Card c = deck.get(deck.size()-1);
    deck.remove(deck.size()-1);
    return c;
  }

  public void shuffle() {
    Collections.shuffle(deck);
  }
}
