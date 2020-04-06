package org.snaccooperative.commands;

import java.io.IOException;
import java.io.Writer;
import java.util.*;

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
import com.google.refine.model.Row;
import com.google.refine.model.Cell;
import com.google.refine.ProjectManager;

import org.snaccooperative.exporters.SNACConstellationCreator;
import org.snaccooperative.exporters.SNACResourceCreator;
import org.snaccooperative.connection.SNACConnector;

public class SNACUploadCommand extends Command {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // String API_key = request.getParameter("apikey");
        String state =  request.getParameter("state");
        String dataType = request.getParameter("dataType");
        SNACResourceCreator res_manager = SNACResourceCreator.getInstance();
        SNACConstellationCreator con_manager = SNACConstellationCreator.getInstance();
        if(dataType.contains("GET_Resource")){
            SNACConnector key_manager = SNACConnector.getInstance();
            String API_key = key_manager.getKey();
            res_manager.uploadResources(API_key, state);
        }
        else if(dataType.contains("GET_Constellation")){
            SNACConnector key_manager = SNACConnector.getInstance();
            String API_key = key_manager.getKey();
            con_manager.uploadConstellations(API_key, state);
        }
        // System.out.println("Key: "+ API_key);
        // System.out.println("State: "+ state);
        


        // Project p = getProject(request);
        // SNACResourceCreator.setProject(p);
        // List<Row> rows = p.rows;

        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");

        Writer w = response.getWriter();
        JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

        writer.writeStartObject();
        if(dataType.equals("GET_Resource")){   
            writer.writeStringField("done", res_manager.getColumnMatchesJSONString());
        }
        else if(dataType.equals("GET_Constellation")){
            writer.writeStringField("done", con_manager.getColumnMatchesJSONString());
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
        doPost(request, response);

        /*
        *
        * Wait...how do I make this work for both?
        *
        */

        SNACResourceCreator manager = SNACResourceCreator.getInstance();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");

        Writer w = response.getWriter();
        JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

        writer.writeStartObject();
        writer.writeStringField("doneGet", manager.getColumnMatchesJSONString());
        writer.writeEndObject();
        writer.flush();
        writer.close();
        w.flush();
        w.close();
    }
}
