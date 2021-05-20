import cs3500.freecell.model.hw02.Face;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestClass {
  Face val = Face.ACE;

  @Test
  public void test1() {
    assertEquals(val.getValue(), 1);
  }

}
