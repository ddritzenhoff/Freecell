package cs3500.freecell.controller;

import cs3500.freecell.model.FreecellModel;
import cs3500.freecell.model.hw02.ICard;
import cs3500.freecell.model.hw02.SimpleFreecellModel;
import java.io.InputStreamReader;

public class FreecellMain {

  public static void main(String[] args) {
    FreecellModel<ICard> model = new SimpleFreecellModel();
    FreecellController<ICard> stuff = new SimpleFreecellController(model,
        new InputStreamReader(System.in), System.out);
    stuff.playGame(model.getDeck(), 4, 2, false);
  }
}
