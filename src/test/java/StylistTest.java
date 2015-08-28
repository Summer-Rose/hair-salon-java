import org.junit.*;
import org.sql2o.*;
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

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

}
