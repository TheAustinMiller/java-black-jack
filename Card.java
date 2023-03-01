public class Card {
  private String suit;
  private String val;

  public Card(String s, String v) {
    suit = s;
    val = v;
  }

  public String getVal() {
    return val;
  }

  public String getSuit() {
    return suit;
  }

  @Override
  public String toString() {
    return val + " of " + suit;
  }
}
