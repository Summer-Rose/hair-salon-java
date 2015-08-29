import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void getClientName_returnsClientName_true() {
    Client newClient = new Client("Marge Simpson", 1);
    assertEquals("Marge Simpson", newClient.getClientName());
  }

  @Test
  public void getStylstId_returnsStylistId_true() {
    Client newClient = new Client("Marge Simpson", 1);
    assertEquals(1, newClient.getStylistId());
  }

  @Test
  public void deleteClientsById_true() {
    Stylist stylist = new Stylist("Edward Scissor Hands");
    stylist.save();
    Client client = new Client("Peter Griffin", stylist.getStylistId());
    client.save();
    Client.deleteClientById(client.getId());
    assertTrue(Client.all().size() == 0);
  }

  @Test
  public void save_newClientIsSavedIntoDatabaseWithId() {
    Client newClient = new Client("Homer Simpson", 1);
    newClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(newClient.getId(), savedClient.getId());
  }

  @Test
  public void equals_returnsTrueIfClientNamesAreTheSame() {
    Client client1 = new Client("Jerry Seinfeld", 1);
    Client client2 = new Client("Jerry Seinfeld", 1);
    assertTrue(client1.equals(client2));
  }

  @Test
  public void find_findsClientInDatabase_true() {
    Client client = new Client("George Michael", 1);
    client.save();
    Client savedClient = Client.find(client.getId());
    assertTrue(client.equals(savedClient));
  }

  @Test
  public void save_savesStylistIdIntoClientTableInDB_true() {
    Stylist stylist = new Stylist("Edward Scissor Hands");
    stylist.save();
    Client client = new Client("Jack Sparrow", stylist.getStylistId());
    client.save();
    Client savedClient = Client.find(client.getId());
    assertEquals(savedClient.getStylistId(), stylist.getStylistId());
  }
}
