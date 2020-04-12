package org.snaccooperative.commands;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.google.refine.commands.Command;
import com.google.refine.util.ParsingUtilities;
import com.google.refine.model.Project;

public class SNACSchemaIssuesCommand extends Command  {
    private static List<String> errors = new LinkedList<String>();

   // @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get errors
        String error = request.getParameter("error");
        String flush = request.getParameter("flush");

        // Flush errors if asked
        if(flush != null && flush.equals("true")){
            errors.clear();
        }
        // Add errors to the command
        if(error != null){
            errors.add(error);
        }

        // Write JSON to response
        
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");

        Writer w = response.getWriter();
        JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

        writer.writeStartObject();
        writer.writeStringField("error", error);
        if (flush != null){
            writer.writeStringField("flush", flush);
        }
        writer.writeEndObject();
        writer.flush();
        writer.close();
        w.flush();
        w.close();        
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");

        Writer w = response.getWriter();
        JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);
        
        writer.writeStartObject();
        writer.writeStringField("errors", errorsToJSON());
        writer.writeEndObject();
        writer.flush();
        writer.close();
        w.flush();
        w.close();

    }

    private String errorsToJSON(){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONParser jp = new JSONParser();
        for (int x = 0; x < errors.size(); x++){
          try{
              ja.add((JSONObject)jp.parse(errors.get(x)));
          }
          catch (ParseException e){
            continue;
          }
        }
        jo.put("errors", ja);
        return jo.toString();
    }
}
