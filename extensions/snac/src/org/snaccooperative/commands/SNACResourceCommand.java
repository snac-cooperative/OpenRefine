package org.snaccooperative.commands;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snaccooperative.connection.SNACConnector;

import com.fasterxml.jackson.core.JsonGenerator;
import com.google.refine.commands.Command;
import com.google.refine.util.ParsingUtilities;

public class SNACResourceCommand extends Command {
    private String attribute;
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //HashMap<String, String> attribute = new HashMap<>();
        String temp = request.getParameter("dict");
        if (temp != null){
          attribute = temp;
        }
        System.out.println(temp);
        System.out.println(attribute);
        // String apikey = request.getParameter("snackey");
        // SNACConnector manager = SNACConnector.getInstance();
        // if (apikey != null) {
        //     manager.saveKey(apikey);
        // } else if ("true".equals(request.getParameter("logout"))) {
        //     manager.removeKey();
        // }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");

        Writer w = response.getWriter();
        JsonGenerator writer = ParsingUtilities.mapper.getFactory().createGenerator(w);

        writer.writeStartObject();
        writer.writeStringField("resource", attribute);
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
    }
}
