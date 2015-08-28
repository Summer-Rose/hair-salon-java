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

  @Test
  public void equals_returnsTrueIfStylistsAreTheSame() {
    Stylist stylist1 = new Stylist("Obama");
    Stylist stylist2 = new Stylist("Obama");
    assertEquals(true, stylist1.equals(stylist2));
  }

  @Test
  public void save_assignsIdToNewStylist() {
    Stylist newStylist = new Stylist("Bart Simpson");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getStylistId());
    assertTrue(newStylist.equals(savedStylist));
  }
}
