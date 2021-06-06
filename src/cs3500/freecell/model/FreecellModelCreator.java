package cs3500.freecell.model;

import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;

/**
 * This class represents a way to select which version of the freecell game should be played.
 */
public class FreecellModelCreator {

  /**
   * An enumeration used to differentiate between the singlemove freecell game and the multimove
   * freecell game.
   */
  public enum GameType {
    SINGLEMOVE,
    MULTIMOVE
  }

  /**
   * A factory method to produce the correct version of the freecell game depending on the enum
   * input.
   *
   * @param type the enumeration to specify which type of game is to be played.
   * @return the type of FreecellModel to be played.
   */
  public static FreecellModel<ICard> create(GameType type) {
    switch (type) {
      case SINGLEMOVE:
        return new SimpleFreecellModel();
      case MULTIMOVE:
        return new MultiMoveFreecellModel();
      default:
        throw new IllegalStateException("Unexpected value: " + type);
    }
  }
}
