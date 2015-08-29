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
    String sql="SELECT stylist_id, stylist_name FROM stylists;";
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

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (stylist_name) VALUES (:stylist_name)";
      this.stylist_id = (int) con.createQuery(sql, true)
        .addParameter("stylist_name", this.stylist_name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Stylist find(int stylist_id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE stylist_id=:stylist_id;";
      Stylist Stylist = con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeAndFetchFirst(Stylist.class);
      return Stylist;
    }
  }

  public List<Client> getClients() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id=:stylist_id";
      return con.createQuery(sql)
        .addParameter("stylist_id", this.stylist_id)
        .executeAndFetch(Client.class);
    }
  }

  public static void deleteStylistById(int stylist_id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE stylist_id=:stylist_id;";
      con.createQuery(sql)
        .addParameter("stylist_id", stylist_id)
        .executeUpdate();
    }
  }
}
