package cs3500.freecell.model.hw02;

/**
 * Represents a non-existent card within the Freecell game. This is to be a replacement for the
 * null value.
 */
public class NoCard implements ICard {

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public Face getFace() {
    throw new IllegalStateException("NoCard doesn't have a face");
  }

  @Override
  public int getFaceValue() {
    throw new IllegalStateException("NoCard doesn't have a face value");
  }

  @Override
  public Suite getSuite() {
    throw new IllegalStateException("NoCard doesn't have a suite");
  }

  @Override
  public boolean sameColor(ICard card) {
    throw new IllegalStateException("NoCard doesn't have a color");
  }
}
