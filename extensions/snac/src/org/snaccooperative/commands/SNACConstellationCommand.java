package org.snaccooperative.commands;

import com.fasterxml.jackson.core.JsonGenerator;
import com.google.refine.commands.Command;
import com.google.refine.model.Project;
import com.google.refine.util.ParsingUtilities;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.*;
import org.snaccooperative.exporters.SNACConstellationCreator;

public class SNACConstellationCommand extends Command {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String dict = request.getParameter("dict");
    SNACConstellationCreator manager = SNACConstellationCreator.getInstance();
    if (dict != null) {
      Project p = getProject(request);
      try {
        manager.setUp(p, dict);
      } catch (Exception e) {
        e.printStackTrace();
        // System.out.println("Failed to set up Resources.");
      }
    }

    // Project p = getProject(request);
    // SNACResourceCreator.setProject(p);
    // List<Row> rows = p.rows;

    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type", "application/json");

    Writer w = response.getWriter();
    JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

    writer.writeStartObject();
    writer.writeStringField("constellation", manager.getColumnMatchesJSONString());
    writer.writeEndObject();
    writer.flush();
    writer.close();
    w.flush();
    w.close();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // doPost(request, response);
    SNACConstellationCreator manager = SNACConstellationCreator.getInstance();

    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type", "application/json");

    Writer w = response.getWriter();
    JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

    writer.writeStartObject();
    writer.writeStringField("constellation", manager.exportConstellationsJSON());
    writer.writeEndObject();
    writer.flush();
    writer.close();
    w.flush();
    w.close();
  }
}
