package cs3500.freecell.model.hw02;

/**
 * Represents the face of the card.
 */
public enum Face {
  ACE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10),
  JACK(11),
  QUEEN(12),
  KING(13);

  private final int code;

  /**
   * Constructs a Face object.
   *
   * @param code the value to be assigned to the face.
   */
  Face(int code) {
    this.code = code;
  }

  /**
   * Gets the value of the Face card.
   *
   * @return the integer value of the Face card.
   */
  public int getValue() {
    return code;
  }

}
