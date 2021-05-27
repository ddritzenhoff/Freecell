package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.PileType;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.view.FreecellTextView;
import cs3500.freecell.view.FreecellView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
NOTES:
- A valid user input for a move is a sequence of three inputs (separated by spaces or newlines):
  - I'm pretty sure there can be garbage values in between. It's just important that you find three
  usable values.
  - Need a MockFreeCell or you will lose points.
  - You need to add logic for when the game hasn't ended but there is no 'q'
  - You will be given tests where you run out of inputs. You need to add logic here too.
  -
 */

public class SimpleFreecellController implements FreecellController<ICard> {

  private final Scanner sc;
  FreecellModel<ICard> model;
  private final FreecellView view;

  public SimpleFreecellController(FreecellModel<ICard> model, Readable rd, Appendable ap)
      throws IllegalArgumentException {

    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    if (ap == null) {
      throw new IllegalArgumentException("appendable cannot be null");
    }

    if (rd == null) {
      throw new IllegalArgumentException("readable cannot be null");
    }

    // at this point, none of the inputs are null.

    this.model = model;
    this.sc = new Scanner(rd);
    this.view = new FreecellTextView(this.model, ap);
  }

  private void write(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Could not transmit message.");
    }
  }

  private void writeBoard() {
    try {
      this.view.renderBoard();
    } catch (IOException e) {
      throw new IllegalStateException("Could not render board.");
    }
  }

  @Override
  public void playGame(List<ICard> deck, int numCascades, int numOpens, boolean shuffle)
      throws IllegalStateException, IllegalArgumentException {

    if (deck == null) {
      throw new IllegalArgumentException("Deck cannot be null.");
    }

    // try starting the game
    try {
      this.model.startGame(deck, numCascades, numOpens, shuffle);
    } catch (IllegalArgumentException e) {
      write("Could not start game.");
      return;
    }

    // at this point in time, the game has started.

    while (!this.model.isGameOver()) {

      // TODO: this may not be necessary.
      writeBoard();
      write("\n");

      String info;

      PileType sourcePileType;
      PileType destinationPileType;

      int sourcePileIndex;
      int cardIndex;
      int destinationPileIndex;

      // handling source pile
      while (!isValidPileInput(info = this.sc.next())) {
        write("Invalid move. Try again. Source pile is invalid.\n");
      }

      // at this point, we know that we have a valid string.
      if (quittingGame(info)) {
        write("Game quit prematurely.\n");
        return;
      }

      sourcePileIndex = Integer.parseInt(info.substring(1)) - 1;
      sourcePileType = getPileType(info.charAt(0));

      // handling card index
      while (!isValidCardIndex(info = this.sc.next())) {
        write("Invalid move. Try again. Card index is invalid.\n");
      }

      // at this point, we know that we have a valid string.
      if (quittingGame(info)) {
        write("Game quit prematurely.\n");
        return;
      }

      cardIndex = Integer.parseInt(info) - 1;

      while (!isValidPileInput(info = this.sc.next())) {
        write("Invalid move. Try again. Destination pile is invalid.\n");
      }

      // at this point, we know that we have a valid string.
      if (quittingGame(info)) {
        write("Game quit prematurely.\n");
        return;
      }

      destinationPileIndex = Integer.parseInt(info.substring(1)) - 1;
      destinationPileType = getPileType(info.charAt(0));

      // moving

      try {
        this.model.move(sourcePileType, sourcePileIndex, cardIndex, destinationPileType,
            destinationPileIndex);
        // TODO: I don't think I need this.
//        writeBoard();
//        write("\n");
      } catch (IllegalArgumentException e) {
        write("Invalid move. Try again\n");
      }
    }

    // at this point, the game has been won. This is due to the way I implemented GameOver.

    writeBoard();
    write("\nGame over.");

  }

  private boolean quittingGame(String input) {
    return input.equals("q") || input.equals("Q");
  }

  private boolean isValidCardIndex(String input) {
    /*
    To get a valid card index, you need the following things:
    - must be either 'q' or 'Q'
    - the input needs to be at least of size 1
    - the string must be able to convert to a number.
     */

    if (quittingGame(input)) {
      return true;
    }

    if (input.length() < 1) {
      return false;
    }

    // at this point, you know you have a string of at least size 1

    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  private boolean isValidPileInput(String input) {
    /*
    To get a valid pile input, you need the following things:
    - must be either 'q' or 'Q'
    - the input needs to be at least of size 2 (if it's not the former case)
    - the first character needs to be either O, C, or F
     */

    if (quittingGame(input)) {
      return true;
    }

    if (input.length() < 2) {
      return false;
    }

    // at this point, you know that the input is at least of size 2

    char firstChar = input.charAt(0);
    switch (firstChar) {
      case 'C':
      case 'F':
      case 'O':
        break;
      default:
        return false;
    }

    // at this point, you know the first char in the input is a C, F, or O

    String numStr = input.substring(1);

    try {
      Integer.parseInt(numStr);
    } catch (NumberFormatException e) {
      return false;
    }

    return true;
  }

  public PileType getPileType(char c) {

    switch (c) {
      case 'C':
        return PileType.CASCADE;
      case 'F':
        return PileType.FOUNDATION;
      case 'O':
        return PileType.OPEN;
      default:
        throw new IllegalArgumentException("Impossible PileType.");
    }
  }

}
