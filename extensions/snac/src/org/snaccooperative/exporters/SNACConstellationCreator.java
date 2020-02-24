package org.snaccooperative.exporters;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

import org.apache.http.*;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.snaccooperative.connection.SNACConnector;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.refine.commands.Command;
import com.google.refine.util.ParsingUtilities;
import com.google.refine.model.Project;
import com.google.refine.model.Row;
import com.google.refine.model.Cell;
import com.google.refine.model.Recon;
import com.google.refine.model.RecordModel;
import com.google.refine.model.Record;


import com.google.refine.model.changes.CellAtRow;
import com.google.refine.model.changes.ColumnAdditionChange;

import org.snaccooperative.data.Constellation;
import org.snaccooperative.data.Term;
import org.snaccooperative.data.Constellation;
import org.snaccooperative.data.Language;

public class SNACConstellationCreator {
    public static HashMap<String, String> match_attributes = new HashMap<String, String>();
    private static Project theProject = new Project();
    private static final SNACConstellationCreator instance = new SNACConstellationCreator();
    private static List<Constellation> constellations = new LinkedList<Constellation>();
    public static List<String> csv_headers = new LinkedList<String>();

    // Internal Constellation IDs that isn't part of the Constellation data model

    private static List<Integer> constellation_ids = new LinkedList<Integer>();
    private static List<CellAtRow> res_row_ids = new LinkedList<CellAtRow>();

    private static HashMap<String, String[]> language_code = new HashMap<String, String[]>();

    public static SNACConstellationCreator getInstance() {
        return instance;
    }

    public void updateColumnMatches(String JSON_SOURCE){
        try {
          match_attributes = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public String getColumnMatchesJSONString(){
        // System.out.println(new JSONObject(match_attributes).toString());
        return new JSONObject(match_attributes).toString();
    }

    public static void setProject(Project p){
        theProject = p;
        csv_headers = theProject.columnModel.getColumnNames();
        // for (int x = 0; x < csv_headers.size(); x++){
        //    System.out.println(csv_headers.get(x));
        // }
    }

    public void setUp(Project p, String JSON_SOURCE){
        setProject(p);
        updateColumnMatches(JSON_SOURCE);
        rowsToConstellations();
        exportConstellationsJSON();
        // test_insertID();
    }

    /**
    * Converts Project rows to Constellations and store into Constellations array
    *
    * @param none
    */
    public void rowsToConstellations(){
        // Clear LinkedList before adding constellations
        constellations.clear();
        List<Row> rows = theProject.rows;
        // RecordModel rm = theProject.recordModel;
        RecordModel rm = theProject.recordModel;
        int rec_size = rm.getRecordCount();
        System.out.println(rec_size);
        for (int z = 0; z < rec_size; z++){
          Record rec_temp = rm.getRecord(z);
          int fromRowInd = rec_temp.fromRowIndex;
          int toRowInd = rec_temp.toRowIndex;
          List<Row> temp_rows = new LinkedList<Row>();
          for (int y = fromRowInd; y < toRowInd; y++){
            temp_rows.add(rows.get(y));
          }
          Constellation temp = createConstellationRecord(temp_rows);
          constellations.add(temp);
        }

        // for (int x = 0; x < rows.size(); x++){
        //   Constellation temp = createConstellationRow(rows.get(x));
        //   constellations.add(temp);
        // }

    }
    /**
    * Take a given Row and convert it to a Constellation Object
    *
    * @param row (Row found in List<Row> from Project)
    * @return Constellation converted from Row
    */
    public Constellation createConstellationRecord(List<Row> rows){ //here
        Constellation con = new Constellation();
        for (int x = 0; x < csv_headers.size(); x++){
            String snac_header = match_attributes.get(csv_headers.get(x)).toLowerCase();
            if (snac_header == null || snac_header == ""){
                continue;
            }
            // Insert switch statements || Bunch of if statements for setters
            String temp_val;
            // If cell empty, set value to empty value
            if (rows.get(0).getCellValue(x) == null || rows.get(0).getCellValue(x) == ""){
                // Should it be empty or continue without adding?
                temp_val = "";
                // continue;
            } else{
                temp_val = rows.get(0).getCellValue(x).toString();
            }
            switch(snac_header){
              case "id":
                  try{
                      con.setID(Integer.parseInt(temp_val));
                      constellation_ids.add(Integer.parseInt(temp_val));
                      // System.out.println("ID: " + temp_val);
                      break;
                  }
                  catch (NumberFormatException e){
                      break;
                  }
              case "type":
                  try{
                      Term t = new Term();
                      t.setType("Constellation");
                      String term;
                      int type_id;
                      if (temp_val.equals("696") || temp_val.equals("person")){
                        type_id = 696;
                        t.setID(type_id);
                        term = "person";
                      } else if (temp_val.equals("697") || temp_val.equals("corporateBody")){
                        type_id = 697;
                        t.setID(type_id);
                        term = "corporateBody";
                      }else {
                        throw new NumberFormatException();
                      }
                      t.setTerm(term);

                      //con.setDocumentType(t);
                      break;
                  }
                  catch (NumberFormatException e){
                      System.out.println(temp_val + " is not a valid constellation type.");
                      break;
                  }
                  catch (Exception e){
                    System.out.println(e);
                    break;
                  }
              case "name entry":
//                  con.setNameEntries();
                  break;
              case "date":
//                  con.set
                  break;
              case "subject":
//                  con.setSubjects();
                  break;
              case "place":
//                  con.setPlace();
                  break;
              case "occupation":
//                  con.setOccupations();
                  break;
              case "function":
//                  con.setFunctions()
                  break;
              case "blog history":
//                  con.setBlogHists();
                  break;
              default:
                  //a case for null would imply a list, how to handle? should be separate from default
                  break;
            }
        }
        return con;
    }


    /**
    * Take a given Row and convert it to a Constellation Object
    *
    * @param row (Row found in List<Row> from Project)
    * @return Constellation converted from Row
    */
    public Constellation createConstellationRow(Row row){
        Constellation con = new Constellation();
        for (int x = 0; x < csv_headers.size(); x++){
            String snac_header = match_attributes.get(csv_headers.get(x)).toLowerCase();
            if (snac_header == null || snac_header == ""){
                continue;
            }
            // Insert switch statements || Bunch of if statements for setters
            String temp_val;
            // If cell empty, set value to empty value
            if (row.getCellValue(x) == null || row.getCellValue(x) == ""){
                // Should it be empty or continue without adding?
                temp_val = "";
                // continue;
            } else{
                temp_val = row.getCellValue(x).toString();
            }
            switch(snac_header){
              case "id":
                  try{
                      con.setID(Integer.parseInt(temp_val));
                      constellation_ids.add(Integer.parseInt(temp_val));
                      // System.out.println("ID: " + temp_val);
                      break;
                  }
                  catch (NumberFormatException e){
                      break;
                  }
              case "type":
                  try{
                      Term t = new Term();
                      t.setType("document_type");
                      String term;
                      int type_id;
                      if (temp_val.equals("696") || temp_val.equals("ArchivalConstellation")){
                        type_id = 696;
                        t.setID(type_id);
                        term = "ArchivalConstellation";
                      } else if (temp_val.equals("697") || temp_val.equals("BibliographicConstellation")){
                        type_id = 697;
                        t.setID(type_id);
                        term = "BibliographicConstellation";
                      } else if (temp_val.equals("400479") || temp_val.equals("DigitalArchivalConstellation")){
                        type_id = 400479;
                        t.setID(type_id);
                        term = "DigitalArchivalConstellation";
                      } else {
                        throw new NumberFormatException();
                      }
                      t.setTerm(term);
                      //con.setDocumentType(t);
                      break;
                  }
                  catch (NumberFormatException e){
                      System.out.println(temp_val + " is not a valid constellation type.");
                      break;
                  }
                  catch (Exception e){
                    System.out.println(e);
                    break;
                  }
              case "name entry":
                  break;
              case "date":
                  break;
              case "subject":
                  break;
              case "place":
                  break;
              case "occupation":
                  break;
              case "function":
                  break;
              case "blog history":
                  break;
              default:
                  break;
            }
        }
        return con;
    }

    /**
    * Converts 2 Constellations into format for Preview Tab
    *
    * @param none
    * @return String
    */
    public String obtainPreview(){
      String samplePreview = "";
      if (constellations.size() == 0){
        samplePreview += "There are no Constellations to preview.";
      }
      else{
        samplePreview += "Inserting " + constellations.size() + " new Constellations into SNAC." + "\n";

        int iterations = Math.min(constellations.size(), 2);

        for(int x = 0; x < iterations; x++){
          Constellation previewConstellation = constellations.get(x);
          System.out.println(Constellation.toJSON(previewConstellation));
          for(Map.Entry mapEntry: match_attributes.entrySet())
          {
              if(!((String)mapEntry.getValue()).equals("")){
                System.out.println(((String)mapEntry.getValue()).toLowerCase());
                switch(((String)mapEntry.getValue()).toLowerCase()) {
                  case "id":
                    //samplePreview+= "ID: " + previewConstellation.getID() + "\n";
                    break;
                  case "type":
                    // Term previewTerm = previewConstellation.getDocumentType();
                    // samplePreview+="Document Type: " + previewTerm.getTerm() + " (" + previewTerm.getID() +")\n";
                    break;
                  case "name entry":
                    //samplePreview+="Title: " + previewConstellation.getTitle() + "\n";
                    break;
                  case "date":
                    //samplePreview+="Display Entry: " + previewConstellation.getDisplayEntry() + "\n";
                    break;
                  case "subject":
                    //samplePreview+="Link: " + previewConstellation.getLink() + "\n";
                    break;
                  case "place":
                    //samplePreview+="Abstract: " + previewConstellation.getAbstract() + "\n";
                    break;
                  case "occupation":
                    //samplePreview+="Extent: " + previewConstellation.getExtent() +  "\n";
                    break;
                  case "function":
                    //samplePreview+="Date: " + previewConstellation.getDate() + "\n";
                    break;
                  default:
                    break;
                }
              }
          }
        }
      }
      //System.out.println(samplePreview);
      return samplePreview;

    }

    /*
    * Helps determine whether a given ISO language exists on the SNAC database
    *
    * @param lang (ISO language code)
    * @return lang_term or null (ISO language code found in API Request)
    */

    /*
    public String detectLanguage(String lang){
        // Insert API request calls for lang (if exists: insert into language dict, if not: return None)
        try{
          DefaultHttpClient client = new DefaultHttpClient();
          HttpPost post = new HttpPost("http://snac-dev.iath.virginia.edu/api/");
          String query = "{\"command\": \"vocabulary\",\"query_string\": \"" + lang + "\",\"type\": \"language_code\",\"entity_type\": null}";
          post.setEntity(new StringEntity(query,"UTF-8"));
          HttpResponse response = client.execute(post);
          String result = EntityUtils.toString(response.getEntity());
          JSONParser jp = new JSONParser();
          JSONArray json_result = (JSONArray)((JSONObject)jp.parse(result)).get("results");
          // System.out.println(json_result);
          if (json_result.size() <= 0){
            return null;
          }
          else{
            JSONObject json_val = (JSONObject)json_result.get(0);
            String lang_id = (String)json_val.get("id");
            String lang_desc = (String)json_val.get("description");
            String lang_term = (String)json_val.get("term");
            String[] lang_val = {lang_id, lang_desc};
            language_code.put(lang_term, lang_val);
            return lang_term;
          }
        }
        catch(IOException e){
          return null;
        }
        catch(ParseException e){
          return null;
        }
    }
    */

    /**
    * Converts Constellation Array into one JSON Object used to export
    *
    * @param none
    * @return String (String converted JSONObject of Constellation Array)
    */
    public String exportConstellationsJSON(){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONParser jp = new JSONParser();
        for (int x = 0; x < constellations.size(); x++){
          try{
              ja.add((JSONObject)jp.parse(Constellation.toJSON(constellations.get(x))));
          }
          catch (ParseException e){
            continue;
          }
        }
        jo.put("constellations", ja);
        //System.out.println(jo.toString());
        return jo.toString();

    }

    public void uploadConstellations(String apiKey, String state) {
        String result="";
        try{
            String opIns = ",\n\"operation\":\"insert\"\n},\"apikey\":\"" + apiKey +"\"";
            List<Constellation> new_list_constellations = new LinkedList<Constellation>();
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://snac-dev.iath.virginia.edu/api/");


            if(state == "prod") {
                post = new HttpPost("http://api.snaccooperative.org/");
                System.out.println(state);
            }
            //System.out.println(state);


            System.out.println("Querying SNAC...");
            for(Constellation temp_res : constellations){
                  String rtj = Constellation.toJSON(temp_res);
                  String api_query = "{\"command\": \"insert_constellation\",\n \"constellation\":" + rtj.substring(0,rtj.length()-1) + opIns + "}";
                  // System.out.println("\n\n" + api_query + "\n\n");
                  StringEntity casted_api = new StringEntity(api_query,"UTF-8");
                  post.setEntity(casted_api);
                  HttpResponse response = client.execute(post);
                  result = EntityUtils.toString(response.getEntity());
                  //System.out.println("RESULT:" + result);
                  new_list_constellations.add(insertID(result,temp_res));
              }
              constellations = new_list_constellations;
              System.out.println("Uploading Attempt Complete");
            }catch(IOException e){
              System.out.println(e);
            }

    }

    public Constellation insertID(String result, Constellation con){
    JSONParser jp = new JSONParser();
    try{
        JSONObject jsonobj = (JSONObject)jp.parse(result);
        int new_id = Integer.parseInt((((JSONObject)jsonobj.get("constellation")).get("id")).toString());
        if(new_id!=0){
          constellation_ids.add(new_id);
          con.setID(new_id);
          return con;
        }
        else{
          constellation_ids.add(null);
        }
    }
    catch (ParseException e){
        System.out.println(e);
    }
    return con;
}

  public void test_insertID(){
    // Run this function after insertID (above) within SNACUploadCommand
    // Check if ID column exists (Need to see how to determine which column is "id" given different naming conventions)
    // If exists: Go through and set the cell values based on the constellation_ids
    // If not: Create a new column "id" and insert cell values based on constellation_ids


    // Operation below creates new column "id" and insert cell values from uploaded Constellation objects through SNAC API
    for (int x = 0; x < theProject.rows.size(); x++){
      Cell test_cell = new Cell(x, new Recon(0, null, null));
      res_row_ids.add(new CellAtRow(x, test_cell));
    }
    ColumnAdditionChange CAC = new ColumnAdditionChange("testing_column", 0, res_row_ids);
    CAC.apply(theProject);
  }

  public static void main(String[] args) {
      System.out.println("Hello");
  }
}
