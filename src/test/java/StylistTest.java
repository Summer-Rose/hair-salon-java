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
  public void save_savesStylistIntoDatabase() {
    Stylist stylist = new Stylist("Tobias");
    stylist.save();
    assertTrue(Stylist.all().get(0).equals(stylist));
  }

  @Test
  public void find_assignsIdToNewStylist() {
    Stylist newStylist = new Stylist("Bart Simpson");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getStylistId());
    assertTrue(newStylist.equals(savedStylist));
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientList() {
    Stylist stylist = new Stylist("Rachel Green");
    stylist.save();
    Client client1 = new Client("Monica Geller", stylist.getStylistId());
    client1.save();
    Client client2 = new Client("Pheobe Buffet", stylist.getStylistId());
    client2.save();
    Client [] clients = new Client [] { client1, client2 };
    assertTrue(stylist.getClients().containsAll(Arrays.asList(clients)));
  }

}
