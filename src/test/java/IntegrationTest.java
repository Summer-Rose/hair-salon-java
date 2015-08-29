import org.fluentlenium.adapter.FluentTest;
import java.util.ArrayList;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Hair Salon");
  }

  @Test
  public void newStylistAddedToMainPage() {
    Stylist newStylist = new Stylist("Summer");
    newStylist.save();
    goTo("http://localhost:4567/stylists");
    assertThat(pageSource()).contains("Summer");
  }

  @Test
  public void newStylistFormDisplaysAddedStylistOnMainPage() {
    goTo("http://localhost:4567/add-stylist");
    fill("#stylist_name").with("Hobbes");
    submit(".btn");
    assertThat(pageSource()).contains("Hobbes");
  }

  //DOES NOT WORK
  // @Test
  // public void deleteIconDeletesStyistFromMainPage() {
  //   Stylist stylist = new Stylist("Hobbes");
  //   stylist.save();
  //   click("a", withValue(stylist.getStylistId()));
  //   assertFalse(pageSource()).contains("Hobbes");
  // }

  @Test
  public void clientIsDisplayedOnStylistPage() {
    Stylist stylist = new Stylist("Hobbes");
    stylist.save();
    Client client = new Client("Calvin", stylist.getStylistId());
    client.save();
    String path = String.format("http://localhost:4567/stylist/%d", stylist.getStylistId());
    goTo(path);
    assertThat(pageSource()).contains("Calvin");
  }
}
