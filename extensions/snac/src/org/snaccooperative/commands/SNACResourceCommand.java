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
import org.snaccooperative.exporters.SNACResourceCreator;

// import org.snaccooperative.exporters.SNACResourceCreator;

public class SNACResourceCommand extends Command {
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String idCol = request.getParameter("idCol");
    String dict = request.getParameter("dict");
    SNACResourceCreator manager = SNACResourceCreator.getInstance();
    if (dict != null) {
      Project p = getProject(request);
      try {
        manager.setUp(p, dict);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Failed to set up Resources.");
      }
    }
    if (idCol != null) {
      manager.setIDCol(idCol);
    }

    // Project p = getProject(request);
    // SNACResourceCreator.setProject(p);
    // List<Row> rows = p.rows;

    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type", "application/json");

    Writer w = response.getWriter();
    JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

    writer.writeStartObject();
    writer.writeStringField("resource", manager.getColumnMatchesJSONString());
    writer.writeStringField("idColumn", manager.getIdColumn());

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
    SNACResourceCreator manager = SNACResourceCreator.getInstance();

    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Type", "application/json");

    Writer w = response.getWriter();
    JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

    writer.writeStartObject();
    writer.writeStringField("resource", manager.exportResourcesJSON());
    writer.writeEndObject();
    writer.flush();
    writer.close();
    w.flush();
    w.close();
  }
}
