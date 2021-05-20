package cs3500.freecell.model.hw02;

/**
 * Represents the suite of a card deck.
 */
public enum Suite {

  // Represents all of the suites of a card
  HEART(SuiteColors.RED), SPADE(SuiteColors.BLACK), CLUB(SuiteColors.BLACK), DIAMOND(SuiteColors.RED);

  private SuiteColors color;

  /**
   * Constructs a Suite object.
   * @param color the color of the card.
   */
  Suite(SuiteColors color) {
    this.color = color;
  }

  /**
   * Gets the color of the suite.
   * @return the Suite's color.
   */
  public SuiteColors getColor() {
    return this.color;
  }

}
