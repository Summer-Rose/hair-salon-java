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

  // @Test
  // public void addNewStylistButtonDirectsToAddNewForm() {
  //   goTo("http://localhost:4567/");
  //   click("button", withText("Add New Stylist"));
  //   assertThat(pageSource()).contains("Add a New Stylist");
  // }

  @Test
  public void newStylistAddedToMainPage() {
    Stylist newStylist = new Stylist("Summer");
    newStylist.save();
    goTo("http://localhost:4567/stylists");
    assertThat(pageSource()).contains("Summer");
  }

}
