package org.snaccooperative.exporters;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;

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


// import com.google.refine.model.changes.CellAtRow;
// import com.google.refine.model.changes.ColumnAdditionChange;

import org.snaccooperative.data.Resource;
import org.snaccooperative.data.Term;
import org.snaccooperative.data.Constellation;
import org.snaccooperative.data.Language;

import com.google.refine.model.Column;
import com.google.refine.model.Recon;
import com.google.refine.model.changes.ColumnAdditionChange;
import com.google.refine.model.changes.ColumnRemovalChange;
import com.google.refine.model.changes.CellAtRow;



/*
Required Fields:
  - Title:
    - getTitle()
    - setTitle(String)
  - Link:
    - getLink()
    - setLink(String)
  - Document Type:
    - getDocumentType()
    = setDocumentType(Term)
  - Repository:
    - getRepository()
    - setRepository(Constellation)
Other (non-required) fields:
  - ID
  - Display Entry
    - getDisplayEntry()
    - setDisplayEntry(String)
  - Abstract
    - getAbstract()
    - setAbstract(String)
  - Extent
    - getExtent()
    - setExtent(String)
  - Date
    - getDate()
    - setDate(String)
  - Language
    - getLanguages()
    - setLanguages(List<Language>)
    - addLanguage(Language)
  - Note
Term Object (Hardcoded lol)
  - "id": "696"
    - getID()
    - setID(int)
  - "term": "Archival Resource"
    - getTerm()
    - setTerm(String)
  - "type": "document_type"
    - getType()
    - setType(String)
Constellation Object
*/

public class SNACResourceCreator {
    public static HashMap<String, Integer> globalCellsLoc = new HashMap<String, Integer>();
    public static HashMap<String, String> match_attributes = new HashMap<String, String>();
    public static String idColumn = "";
    private static Project theProject = new Project();
    private static final SNACResourceCreator instance = new SNACResourceCreator();
    private static List<Resource> resources = new LinkedList<Resource>();
    public static List<String> csv_headers = new LinkedList<String>();

    // Internal Resource IDs that isn't part of the Resource data model

    public static List<Integer> resource_ids = new LinkedList<Integer>();
    private static List<CellAtRow> res_row_ids = new LinkedList<CellAtRow>();

    private static HashMap<String, String[]> language_code = new HashMap<String, String[]>();

    public static SNACResourceCreator getInstance() {
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
        return new JSONObject(match_attributes).toString();
    }

    public String getIdColumn(){
      return idColumn;
    }

    public static void setProject(Project p){
        theProject = p;
        csv_headers = theProject.columnModel.getColumnNames();
    }

    public static Resource getResource(int index){
      if(index < resources.size()){
        return resources.get(index);
      }
      else{
        return null;
      }
    }

    public static void clearResources(){
      resources.clear();
    }

    public static void validateColumnMatches(){

    }

    public void setUp(Project p, String JSON_SOURCE) throws Exception{
        setProject(p);
        updateColumnMatches(JSON_SOURCE);
        rowsToResources();
        exportResourcesJSON();
    }

    public void setIDCol(String idCol){
      idColumn = idCol;
    }

    /**
    * Converts Project rows to Resources and store into Resources array
    *
    * @param none
    */
    public void rowsToResources(){
        // Clear LinkedList before adding resources
        resources.clear();
        List<Row> rows = theProject.rows;
        // RecordModel rm = theProject.recordModel;
        RecordModel rm = theProject.recordModel;
        int rec_size = rm.getRecordCount();
        for (int z = 0; z < rec_size; z++){
          Record rec_temp = rm.getRecord(z);
          int fromRowInd = rec_temp.fromRowIndex;
          int toRowInd = rec_temp.toRowIndex;
          List<Row> temp_rows = new LinkedList<Row>();
          for (int y = fromRowInd; y < toRowInd; y++){
            temp_rows.add(rows.get(y));
          }
          Resource temp = createResourceRecord(temp_rows);
          resources.add(temp);
        }


    }
    /**
    * Take a given Row and convert it to a Resource Object
    *
    * @param row (Row found in List<Row> from Project)
    * @return Resource converted from Row
    */
    public Resource createResourceRecord(List<Row> rows){
        Resource res = new Resource();
        for (int x = 0; x < csv_headers.size(); x++){
            String snac_header = match_attributes.get(csv_headers.get(x)).toLowerCase();
            if (snac_header == null || snac_header == ""){
                continue;
            }

            String temp_val;
            // If cell empty, set value to empty value
            if (rows.get(0).getCellValue(x) == null || rows.get(0).getCellValue(x) == ""){

                temp_val = "";
                // continue;
            } else{
                temp_val = rows.get(0).getCellValue(x).toString();
            }
            switch(snac_header){
              case "id":
                  try{
                      res.setID(Integer.parseInt(temp_val));
                      resource_ids.add(Integer.parseInt(temp_val));;
                      break;
                  }
                  catch (NumberFormatException e){
                      break;
                  }
              case "type":
                      Term t = new Term();
                      t.setType("document_type");
                      String term;
                      int type_id;
                      if (temp_val.equals("696") || temp_val.equals("ArchivalResource")){
                        type_id = 696;
                        t.setID(type_id);
                        term = "ArchivalResource";
                      } else if (temp_val.equals("697") || temp_val.equals("BibliographicResource")){
                        type_id = 697;
                        t.setID(type_id);
                        term = "BibliographicResource";
                      } else if (temp_val.equals("400479") || temp_val.equals("DigitalArchivalResource")){
                        type_id = 400479;
                        t.setID(type_id);
                        term = "DigitalArchivalResource";
                      } else {
                        System.out.println(temp_val + " is not a valid resource type.");
                        break;
                      }
                      t.setTerm(term);
                      res.setDocumentType(t);
                      break;
              case "title":
                  res.setTitle(temp_val);
                  break;
              case "display entry":
                  res.setDisplayEntry(temp_val);
                  break;
              case "link":
                  res.setLink(temp_val);
                  break;
              case "abstract":
                  res.setAbstract(temp_val);
                  break;
              case "extent":
                  res.setExtent(temp_val);
                  break;
              case "date":
                  res.setDate(temp_val);
                  break;
              case "language":
                  // If Languages haven't been made due to Script, then make new Languages with language
                  if(res.getLanguages().size() == 0){
                    for(int z = 1; z < rows.size() + 1; z++){
                      if(!temp_val.equals("")){
                        String checked_lang = detectLanguage(temp_val);

                        if(checked_lang != null){
                          Language lang = new Language();
                          Term t2 = new Term();
                          t2.setType(temp_val);
                          lang.setLanguage(t2);
                          res.addLanguage(lang);
                        }
                      }
                      // If there are more rows, then insert more languages
                      if(z != rows.size()){
                        if(rows.get(z).getCellValue(x)!=null){
                          temp_val = rows.get(z).getCellValue(x).toString();
                        }
                      }
                    }
                  // If Languages already exists then add onto them
                  }else{
                    for(int r = 0; r < res.getLanguages().size(); r++){
                      if(rows.get(r).getCellValue(x) == null){
                        continue;
                      }
                      temp_val = rows.get(r).getCellValue(x).toString();
                      if(!temp_val.equals("")){
                        String checked_lang = detectLanguage(temp_val);
                        if(checked_lang != null){
                          Term t2 = new Term();
                          t2.setType(temp_val);
                          res.getLanguages().get(r).setLanguage(t2);
                        }
                      }
                    }
                  }
                  break;
              case "script":
                  // If Languages haven't been made due to language, then make new Languages with Script Term
                  if(res.getLanguages().size() == 0){
                    for(int z = 1; z < rows.size() + 1; z++){
                      Language lang = new Language();
                      Term t3 = new Term();
                      t3.setType(temp_val);
                      lang.setScript(t3);
                      res.addLanguage(lang);
                      // If there are more rows, then insert more scripts
                      if(z != rows.size()){
                        if(rows.get(z).getCellValue(x)!=null){
                         temp_val = rows.get(z).getCellValue(x).toString();
                        }
                      }
                    }
                  // If Languages already exists then add onto them
                  }else{
                    for(int r = 0; r < res.getLanguages().size(); r++){
                      if(rows.get(r).getCellValue(x) == null){
                        continue;
                      }
                      temp_val = rows.get(r).getCellValue(x).toString();
                      Term t3 = new Term();
                      t3.setType(temp_val);
                      res.getLanguages().get(r).setScript(t3);
                    }
                  }
                  break;
              case "holding repository snac id":
                  Constellation cons = new Constellation();
                  if(!temp_val.equals("")){
                    try{
                      cons.setID(Integer.parseInt(temp_val));
                    }
                    catch(NumberFormatException e){
                      continue;
                    }
                  }
                  res.setRepository(cons);
                  break;
              default:
                  continue;
            }
        }
        return res;
    }


    /**
    * Take a given Row and convert it to a Resource Object
    *
    * @param row (Row found in List<Row> from Project)
    * @return Resource converted from Row
    */
    public Resource createResourceRow(Row row){
        Resource res = new Resource();
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
                      res.setID(Integer.parseInt(temp_val));
                      resource_ids.add(Integer.parseInt(temp_val));
                      break;
                  }
                  catch (NumberFormatException e){
                      resource_ids.add(-1);
                      break;
                  }
              case "type":
                      Term t = new Term();
                      t.setType("document_type");
                      String term;
                      int type_id;
                      if (temp_val.equals("696") || temp_val.equals("ArchivalResource")){
                        type_id = 696;
                        t.setID(type_id);
                        term = "ArchivalResource";
                      } else if (temp_val.equals("697") || temp_val.equals("BibliographicResource")){
                        type_id = 697;
                        t.setID(type_id);
                        term = "BibliographicResource";
                      } else if (temp_val.equals("400479") || temp_val.equals("DigitalArchivalResource")){
                        type_id = 400479;
                        t.setID(type_id);
                        term = "DigitalArchivalResource";
                      } else {
                        System.out.println(temp_val + " is not a valid resource type.");
                        break;
                      }
                      t.setTerm(term);
                      res.setDocumentType(t);
                      break;
              case "title":
                  res.setTitle(temp_val);
                  break;
              case "display entry":
                  res.setDisplayEntry(temp_val);
                  break;
              case "link":
                  res.setLink(temp_val);
                  break;
              case "abstract":
                  res.setAbstract(temp_val);
                  break;
              case "extent":
                  res.setExtent(temp_val);
                  break;
              case "date":
                  res.setDate(temp_val);
                  break;
              case "language":
                  String checked_lang = detectLanguage(temp_val);
                  if(checked_lang != null){
                    Language lang = new Language();
                    Term t2 = new Term();
                    t2.setType(temp_val);
                    lang.setLanguage(t2);
                    res.addLanguage(lang);
                  }
                  break;
              case "holding repository snac id":
                  Constellation cons = new Constellation();
                  if(!temp_val.equals("")){
                    try{
                      cons.setID(Integer.parseInt(temp_val));
                    }
                    catch(NumberFormatException e){
                      continue;
                    }
                  }
                  res.setRepository(cons);
                  break;
              default:
                  continue;
            }
        }
        return res;
    }

    /**
    * Converts 2 Resources into format for Preview Tab
    *
    * @param none
    * @return String
    */
    public String obtainPreview(){
      String samplePreview = "";
      if (resources.size() == 0){
        samplePreview += "There are no Resources to preview.";
      }
      else{
        samplePreview += "Inserting " + resources.size() + " new Resources into SNAC." + "\n";

        int iterations = Math.min(resources.size(), 2);

        for(int x = 0; x < iterations; x++){
          Resource previewResource = resources.get(x);
          for(Map.Entry mapEntry: match_attributes.entrySet())
          {
              if(!((String)mapEntry.getValue()).equals("")){
                switch(((String)mapEntry.getValue()).toLowerCase()) {
                  case "id":
                    samplePreview+= "ID: " + previewResource.getID() + "\n";
                    break;
                  case "type":
                    Term previewTerm = previewResource.getDocumentType();
                    samplePreview+="Document Type: " + previewTerm.getTerm() + " (" + previewTerm.getID() +")\n";
                    break;
                  case "title":
                    samplePreview+="Title: " + previewResource.getTitle() + "\n";
                    break;
                  case "display entry":
                    samplePreview+="Display Entry: " + previewResource.getDisplayEntry() + "\n";
                    break;
                  case "link":
                    samplePreview+="Link: " + previewResource.getLink() + "\n";
                    break;
                  case "abstract":
                    samplePreview+="Abstract: " + previewResource.getAbstract() + "\n";
                    break;
                  case "extent":
                    samplePreview+="Extent: " + previewResource.getExtent() +  "\n";
                    break;
                  case "date":
                    samplePreview+="Date: " + previewResource.getDate() + "\n";
                    break;
                  case "language":
                    List<Language> languageList = previewResource.getLanguages();
                    String previewResourceLanguages = "Language(s): ";
                    if(languageList.size() == 0){
                      previewResourceLanguages = "Language(s): " + "\n" ;
                    }
                    else{
                      List<String> valid_lang = new LinkedList<String>();
                      for(int i=0; i<languageList.size();i++){
                        if (languageList.get(i).getLanguage() == null){
                          continue;
                        }
                        String lang_var = languageList.get(i).getLanguage().getType();
                        if(lang_var.equals("")){
                          continue;
                        }
                        valid_lang.add(language_code.get(lang_var)[1] + "(" + lang_var + ")");
                      }
                      for(int j = 0; j < valid_lang.size() - 1; j++){
                        previewResourceLanguages += valid_lang.get(j) + ", ";
                      }
                      previewResourceLanguages += valid_lang.get(valid_lang.size() - 1) + "\n";
                    }
                    samplePreview+= previewResourceLanguages;
                    break;
                  case "script":
                    List<Language> scriptList = previewResource.getLanguages();
                    String previewResourceScripts = "Script(s): ";
                    if(scriptList.size() == 0){
                      previewResourceScripts = "Script(s): " + "\n" ;
                    }
                    else{
                      List<String> valid_script = new LinkedList<String>();
                      for(int i=0; i<scriptList.size();i++){
                        if (scriptList.get(i).getScript() == null){
                          continue;
                        }
                        String lang_var = scriptList.get(i).getScript().getType();
                        if(lang_var.equals("")){
                          continue;
                        }
                        valid_script.add(lang_var);
                      }
                      for(int j = 0; j < valid_script.size() - 1; j++){
                        previewResourceScripts += valid_script.get(j) + ", ";
                      }
                      previewResourceScripts += valid_script.get(valid_script.size() - 1) + "\n";
                    }
                    samplePreview+= previewResourceScripts;
                    break;
                  case "holding repository snac id":
                    int repo_id = previewResource.getRepository().getID();
                    if(repo_id != 0){
                      samplePreview+="Repository ID: "+ Integer.toString(repo_id) + "\n";
                    } else{
                      samplePreview+="Repository ID: " + "\n";
                    }
                    break;
                  default:
                    break;
                }
              }
          }
        }
      }
      return samplePreview;

    }

    /*
    * Helps determine whether a given ISO language exists on the SNAC database
    *
    * @param lang (ISO language code)
    * @return lang_term or null (ISO language code found in API Request)
    */
    public String detectLanguage(String lang){
        // Insert API request calls for lang (if exists: insert into language dict, if not: return None)
        try{
          DefaultHttpClient client = new DefaultHttpClient();
          HttpPost post = new HttpPost("http://localhost/~josephglass/snac/rest/");
          String query = "{\"command\": \"vocabulary\",\"query_string\": \"" + lang + "\",\"type\": \"language_code\",\"entity_type\": null}";
          post.setEntity(new StringEntity(query,"UTF-8"));
          HttpResponse response = client.execute(post);
          String result = EntityUtils.toString(response.getEntity());
          JSONParser jp = new JSONParser();
          JSONArray json_result = (JSONArray)((JSONObject)jp.parse(result)).get("results");
          if (json_result.size() <= 0){
            return null;
          }
          else {
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

    /**
    * Converts Resource Array into one JSON Object used to export
    *
    * @param none
    * @return String (String converted JSONObject of Resource Array)
    */
    public String exportResourcesJSON(){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONParser jp = new JSONParser();
        for (int x = 0; x < resources.size(); x++){
          try{
              ja.add((JSONObject)jp.parse(Resource.toJSON(resources.get(x))));
          }
          catch (ParseException e){
            continue;
          }
        }
        jo.put("resources", ja);
        return jo.toString();

    }

    /**
    * Preps and attempts to upload the constellation
    *
    * @param apiKey
    * @param state upload environment of production or develop
    */
    public void uploadResources(String apiKey, String state){

    try{
        String opIns = ",\n\"operation\":\"insert\"\n},\"apikey\":\"" + apiKey +"\"";
        List<Resource> new_list_resources = new LinkedList<Resource>();
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost/~josephglass/snac/rest/");

        if(state == "prod") {
            post = new HttpPost("http://api.snaccooperative.org/");
        }


        System.out.println("Querying SNAC...");
        for(Resource temp_res : resources){
            String rtj = Resource.toJSON(temp_res);
              String api_query = "{\"command\": \"insert_resource\",\n \"resource\":" + rtj.substring(0,rtj.length()-1) + opIns + "}";
              StringEntity casted_api = new StringEntity(api_query,"UTF-8");
              post.setEntity(casted_api);
              HttpResponse response = client.execute(post);
              String result = EntityUtils.toString(response.getEntity());
              new_list_resources.add(insertID(result,temp_res));
          }
          resources = new_list_resources;
          System.out.println("Uploading Attempt Complete");
          test_insertID();
        }catch(IOException e){
          System.out.println(e);
        }
    }

    /**
    * Supposed to take the reply from the API and make a column field in the schema
    *
    * @param result the reply from the API including the SNAC ID (for new entries)
    * @param res the resource to add the ID into
    */
    public Resource insertID(String result, Resource res){
      JSONParser jp = new JSONParser();
    try{
        JSONObject jsonobj = (JSONObject)jp.parse(result);
        int new_id = Integer.parseInt((((JSONObject)jsonobj.get("resource")).get("id")).toString());
        if(new_id!=0){
          resource_ids.add(new_id);
          res.setID(new_id);
          return res;
        }
        else{
          resource_ids.add(null);
        }
    }
    catch (NullPointerException e){
      return res;
    }
    catch (ParseException e){
        System.out.println(e);
    }
    return res;
  }
    /**
    * Run this function after insertID (above) within SNACUploadCommand
    * Check if ID column exists (Need to see how to determine which column is "id" given different naming conventions)
    * If exists: Go through and set the cell values based on the resource_ids
    * If not: Create a new column "id" and insert cell values based on resource_ids
    */
  public void test_insertID(){
    boolean idColExists = false;
    int idColIndex = -1;
    List<Column> colList = theProject.columnModel.columns;
    List<CellAtRow> res_row_ids = new ArrayList<CellAtRow>();

    List<CellAtRow> record_ids = new ArrayList<CellAtRow>();

    if(!idColumn.equals("")){
      idColExists = true;
      for(Column c: colList){
        if(c.getOriginalHeaderLabel().equals(idColumn)){
          idColIndex = c.getCellIndex();
          break;
        }
      }
    }

    // Operation below creates new column "id" and insert cell values from uploaded Resource objects through SNAC API
    for (int x = 0; x < theProject.rows.size(); x++){
      Cell test_cell = new Cell(resource_ids.get(x), new Recon(0, null, null));

      // Cell test_cell = new Cell(x, new Recon(0, null, null));
      res_row_ids.add(new CellAtRow(x, test_cell));
    }

    //iterating by records instead of rows
    int rec_size = theProject.recordModel.getRecordCount();
    for (int z = 0; z < rec_size; z++){
      Record rec_temp = theProject.recordModel.getRecord(z);
      int fromRowInd = rec_temp.fromRowIndex;
      // int toRowInd = rec_temp.toRowIndex;
      Cell test_cell_r = new Cell(resource_ids.get(z), new Recon(0, null, null)); //how does resource_ids look when using records????

      record_ids.add(new CellAtRow(fromRowInd, test_cell_r));
    }

    if(idColExists){
    // replace existing col
      ColumnRemovalChange CRC = new ColumnRemovalChange(idColIndex);
      CRC.apply(theProject);
      // ColumnAdditionChange CAC = new ColumnAdditionChange("test_replace", idColIndex, res_row_ids);
      ColumnAdditionChange CAC = new ColumnAdditionChange("test_replace", idColIndex, record_ids);

      CAC.apply(theProject);
    }
    else {
      // ColumnAdditionChange CAC = new ColumnAdditionChange("testing_column", 0, res_row_ids);
      ColumnAdditionChange CAC = new ColumnAdditionChange("testing_column", 0, record_ids);
      CAC.apply(theProject);
    }

  }
}
