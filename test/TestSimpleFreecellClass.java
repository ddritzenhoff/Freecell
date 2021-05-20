import cs3500.freecell.model.hw02.Face;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSimpleFreecellClass {
  Face val = Face.ACE;

  // TODO: QUESTION: Do you suggest I test state by constructing a string from scratch?

  @Test
  public void test1() {
    assertEquals(val.getValue(), 1);
  }

}
