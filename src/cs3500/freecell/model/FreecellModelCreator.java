package cs3500.freecell.model;

import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import cs3500.freecell.model.hw04.MultiMoveFreecellModel;

public class FreecellModelCreator {

  public enum GameType {
    SINGLEMOVE,
    MULTIMOVE
  }

  public static FreecellModel<ICard> create(GameType type) {
    switch(type) {
      case SINGLEMOVE:
        return new SimpleFreecellModel();
      case MULTIMOVE:
        return new MultiMoveFreecellModel();
      default:
        throw new IllegalStateException("Unexpected value: " + type);
    }
  }
}
