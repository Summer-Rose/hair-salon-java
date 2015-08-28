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
}
