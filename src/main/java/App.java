import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.List;


public class App {
    public static void main(String[] args) {
    	staticFileLocation("/public");
    	String layout = "templates/layout.vtl";

      get("/", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("stylists", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/add-stylist", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/add-stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/stylists", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String stylist = request.queryParams("stylist_name");
        Stylist newStylist = new Stylist(stylist);
        newStylist.save();
        model.put("stylists", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/delete/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int stylist_id = Integer.parseInt(request.params(":id"));
        Stylist.deleteStylistById(stylist_id);
        model.put("stylists", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/stylist/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int stylist_id = Integer.parseInt(request.params(":id"));
        model.put("clients", Client.getClientsByStylistId(stylist_id));
        model.put("stylist", Stylist.find(stylist_id));
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      get("/client/:id", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        String client_name = request.queryParams("client_name");
        int stylist_id = Integer.parseInt(request.queryParams("stylistId"));
        Client newClient = new Client(client_name, stylist_id);
        if (client_name != null) {
          newClient.save();
        }
        
        model.put("stylist", Stylist.find(Integer.parseInt(request.params(":id"))));
        model.put("clients", Client.getClientsByStylistId(stylist_id));
        
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());
  }
}
