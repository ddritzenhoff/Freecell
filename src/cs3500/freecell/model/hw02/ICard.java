package cs3500.freecell.model.hw02;

/**
 * This interface represents different operations that an ICard model must support to return
 * various aspects of its state.
 */
public interface ICard {

  /**
   * Determines if the ICard contains valid fields.
   * @return true if contains valid fields and false otherwise.
   */
  boolean isValid();

  /**
   * Gets the Face field of the ICard.
   * @return the enum Face field.
   */
  Face getFace();

  /**
   * Gets the Face field value of the ICard.
   * @return the enum Face field value.
   */
  int getFaceValue();

  /**
   * Gets the Suite field of the ICard.
   * @return the enum Suite field.
   */
  Suite getSuite();

  /**
   * Determines if 'this' ICard and 'that' ICard contain the same colors.
   * @param card 'that' ICard to be compared against 'this' ICard.
   * @return true if the two ICards contain the same colors and false otherwise.
   */
  boolean sameColor(ICard card);


}
