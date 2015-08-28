import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void getName_getsStylistsName_true() {
    Stylist newStylist = new Stylist("Summer");
    assertEquals("Summer", newStylist.getStylistName());
  }

  // @Test
  // public void getName_getsStylistsId_true() {
  //   Stylist newStylist = new Stylist("Summer");
  //   assertEquals(1, newStylist.getStylistId());
  // }

  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(Category.all().size(), 0);
  // }

}
