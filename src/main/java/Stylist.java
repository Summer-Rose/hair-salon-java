import java.util.List;
import org.sql2o.*;

public class Stylist {
  private int stylist_id;
  private String stylist_name;

  public int getStylistId() {
    return stylist_id;
  }

  public String getStylistName() {
    return stylist_name;
  }

  public Stylist(String stylist_name) {
    this.stylist_name = stylist_name;
  }

  public static List<Stylist> all() {
    String sql="SELECT stylist_id, sytlist_name FROM stylists;";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
        .executeAndFetch(Stylist.class);
    }
  }

  @Override
  public boolean equals(Object otherStylist) {
    if(!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getStylistName().equals(newStylist.getStylistName());
    }
  }
}
