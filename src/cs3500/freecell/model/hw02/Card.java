package cs3500.freecell.model.hw02;

import java.util.Objects;

/**
 * Represents a card within the Freecell card game.
 */
public class Card implements ICard {

  private final Suite suite;
  private final Face face;

  /**
   * Constructs a {@code Card} object.
   *
   * @param suite the suite of the card.
   * @param face  the face value of the card.
   */
  public Card(Suite suite, Face face) {
    this.suite = Objects.requireNonNull(suite);
    this.face = Objects.requireNonNull(face);
  }

  public boolean isValid() {

    boolean isValid = false;

    for (Suite suiteMember : Suite.values()) {
      if (suiteMember.equals(this.suite)) {
        isValid = true;
        break;
      }
    }

    for (Face faceMember : Face.values()) {
      if (faceMember.equals(this.face)) {
        return isValid;
      }
    }

    return false;
  }

  @Override
  public Face getFace() {
    return this.face;
  }

  @Override
  public int getFaceValue() {
    return this.face.getValue();
  }

  @Override
  public Suite getSuite() {
    return this.suite;
  }

  @Override
  public boolean sameColor(ICard card) {

    return this.suite.getColor() == card.getSuite().getColor();

  }

  @Override
  public String toString() {

    String card_as_str = "";

    switch (this.face) {
      case ACE:
        card_as_str += "A";
        break;
      case TWO:
        card_as_str += "2";
        break;
      case THREE:
        card_as_str += "3";
        break;
      case FOUR:
        card_as_str += "4";
        break;
      case FIVE:
        card_as_str += "5";
        break;
      case SIX:
        card_as_str += "6";
        break;
      case SEVEN:
        card_as_str += "7";
        break;
      case EIGHT:
        card_as_str += "8";
        break;
      case NINE:
        card_as_str += "9";
        break;
      case TEN:
        card_as_str += "10";
        break;
      case JACK:
        card_as_str += "J";
        break;
      case QUEEN:
        card_as_str += "Q";
        break;
      case KING:
        card_as_str += "K";
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + this.face);
    }

    switch (this.suite) {
      case HEART:
        card_as_str += "♥";
        break;
      case SPADE:
        card_as_str += "♠";
        break;
      case CLUB:
        card_as_str += "♣";
        break;
      case DIAMOND:
        card_as_str += "♦";
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + this.suite);
    }

    return card_as_str;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.suite, this.face);
  }

  @Override
  public boolean equals(Object obj) {
    // you are really only checking to see if the face and suite are the same.
    if (!(obj instanceof Card)) {
      return false;
    }

    // at this point, you know that the obj is an instance of Card, so you can cast.
    Card that = (Card) obj;

    return this.suite == that.suite && this.face == that.face;
  }

}

