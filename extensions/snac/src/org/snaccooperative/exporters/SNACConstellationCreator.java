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

import org.snaccooperative.data.BiogHist;
import org.snaccooperative.data.Constellation;
import org.snaccooperative.data.SNACDate;
import org.snaccooperative.data.SNACFunction;
import org.snaccooperative.data.Term;
import org.snaccooperative.data.Constellation;
import org.snaccooperative.data.Language;
import org.snaccooperative.data.NameEntry;
import org.snaccooperative.data.Subject;
import org.snaccooperative.data.Place;
import org.snaccooperative.data.Occupation;
import org.snaccooperative.data.SameAs;

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

    public void updateColumnMatches(String JSON_SOURCE) {
        try {
          match_attributes = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);
        } catch (IOException e) {
          e.printStackTrace();
        }
    }

    public String getColumnMatchesJSONString() {
        return new JSONObject(match_attributes).toString();
    }

    public static void setProject(Project p) {
        theProject = p;
        csv_headers = theProject.columnModel.getColumnNames();
    }

    public void setUp(Project p, String JSON_SOURCE) {
        setProject(p);
        updateColumnMatches(JSON_SOURCE);
        rowsToConstellations();
        exportConstellationsJSON();
    }

    /**
    * Converts Project rows to Constellations and store into Constellations array
    *
    * @param none
    */
    public void rowsToConstellations() {
        // Clear LinkedList before adding constellations
        constellations.clear();
        List<Row> rows = theProject.rows;
        // RecordModel rm = theProject.recordModel;
        RecordModel rm = theProject.recordModel;
        int rec_size = rm.getRecordCount();
        for (int z = 0; z < rec_size; z++) {
          Record rec_temp = rm.getRecord(z);
          int fromRowInd = rec_temp.fromRowIndex;
          int toRowInd = rec_temp.toRowIndex;
          List<Row> temp_rows = new LinkedList<Row>();
          for (int y = fromRowInd; y < toRowInd; y++) {
            temp_rows.add(rows.get(y));
          }
          Constellation temp = createConstellationRecord(temp_rows);
          constellations.add(temp);
        }


    }

    /**
    * Take a given date type and return a properly set up term to match it
    * @param String
    * @return Term
    */
    public Term setDateTerms(String date_type) {
        date_type=date_type.toLowerCase();
        Term t=new Term();
        if(date_type.equals("active")) {
            t.setTerm("Active");
            t.setID(688);
            t.setType("date_type");
        }
        else if(date_type.equals("death")) {
            t.setTerm("Death");
            t.setID(690);
            t.setType("date_type");
        }
        else if(date_type.equals("birth")) {
            t.setTerm("Birth");
            t.setID(689);
            t.setType("date_type");
        }
        else if(date_type.equals("suspiciousdate")) {
            t.setTerm("SuspiciousDate");
            t.setID(691);
            t.setType("date_type");
        }
        else if(date_type.equals("establishment")) {
            t.setTerm("Establishment");
            t.setID(400484);
            t.setType("date_type");
        }
        else if(date_type.equals("disestablishment")) {
            t.setTerm("Disestablishment");
            t.setID(400485);
            t.setType("date_type");
        }
        else {
            t.setTerm("INVALID");
            t.setType("date_type");
            //error, the date types wasn't any of these 6 above
        }
        return t;
    }

    /**
    * Take a given date type and return a properly set up term to match it
    * @param String
    * @return Term
    */
    public Term determinePlaceRole(String place_role) {
        place_role = place_role.toLowerCase();
        Term t = new Term();
        if (place_role.equals("birth")) {
            t.setTerm("Birth");
            t.setID(400238);
            t.setType("place_role");
        }
        else if (place_role.equals("death")) {
            t.setTerm("Death");
            t.setID(400239);
            t.setType("place_role");
        }
        else if (place_role.equals("residence")) {
            t.setTerm("Residence");
            t.setID(400241);
            t.setType("place_role");
        }
        else if (place_role.equals("work")) {
            t.setTerm("Work");
            t.setID(400625);
            t.setType("place_role");
        }
        else {
            System.out.println("Invalid Place Role");
            t.setType("place_role");
        }
        return t;
    }


    /**
    * Take a given Row and convert it to a Constellation Object
    *
    * @param row (Row found in List<Row> from Project)
    * @return Constellation converted from Row
    */
    public Constellation createConstellationRecord(List<Row> rows) { //here
        Constellation con = new Constellation();
        con.setOperation("insert");
        List<String> temp_dates = new LinkedList<String>();
        List<String> temp_date_types = new LinkedList<String>();
        List<SNACDate> temp_SNACDates = new LinkedList<SNACDate>();

        // find date_type and place_role col,
        int date_type_index = -1;
        int place_role_index = -1;
        for (int col_index = 0; col_index < csv_headers.size(); col_index++){
            String temp_snac_header = match_attributes.get(csv_headers.get(col_index)).toLowerCase();
            if (temp_snac_header.equals("date type")) {
                date_type_index = col_index;
            } else if (temp_snac_header.equals("place role")) {
                place_role_index = col_index;
            }
        }

        for (int x = 0; x < csv_headers.size(); x++) {  // for each column, left to right
            String snac_header = match_attributes.get(csv_headers.get(x)).toLowerCase();
            if (snac_header == null || snac_header == "") {
                continue;
            }
            // Insert switch statements || Bunch of if statements for setters
            String temp_val;
            // If cell empty, set value to empty value
            if (rows.get(0).getCellValue(x) == null || rows.get(0).getCellValue(x) == "") {
                temp_val = "";
                // continue;
            } else {
                temp_val = rows.get(0).getCellValue(x).toString();
            }
            switch(snac_header) {
              case "id":
                  try {
                      con.setID(Integer.parseInt(temp_val));
                      constellation_ids.add(Integer.parseInt(temp_val));
                      break;
                  }
                  catch (NumberFormatException e) {
                      break;
                  }
              case "entity type":
                  //try {
                      Term t = new Term();
                      t.setType("entity_type");
                      String term;
                      int type_id;
                      if (temp_val.equals("person")) {
                          type_id = 700;
                          t.setID(type_id);
                          term = "person";
                      }
                      else if (temp_val.equals("family")) {
                          type_id = 699;
                          t.setID(type_id);
                          term = "family";
                      }
                      else if (temp_val.equals("corporateBody")) {
                          type_id = 698;
                          t.setID(type_id);
                          term = "corporateBody";
                      }
                      else {
                        System.out.println(temp_val + " is not a valid Constellation type.");
                        break;
                      }
                      t.setTerm(term);
                      con.setEntityType(t);
                      break;
              case "name entry":
                   /* Iterate through all the name entries, create a NameEntry object
                   * which will be added to a list of NameEntry.
                   * Add the list of NameEntry to the constellation.
                   *
                   */
                  List<NameEntry> name_list = new LinkedList<NameEntry>();
                  //temp_val = rows.get(c).getCellValue(x).toString();
                  if(!temp_val.equals("")) {
                    NameEntry nameEntryValue = new NameEntry();
                    nameEntryValue.setOriginal(temp_val);
                    name_list.add(nameEntryValue);
                    nameEntryValue.setPreferenceScore(99);        // TODO: Make only the first NE preferred?
                    nameEntryValue.setOperation("insert");
                  }

                  con.setNameEntries(name_list);
                  break;
              case "date":
                  for(int row_index = 0; row_index < rows.size(); row_index++) {
                    /*Set the value of the date */
                    // If cell empty, set value to empty value
                    if (rows.get(row_index).getCellValue(x) == null || rows.get(row_index).getCellValue(x) == "") {
                        temp_val = "";
                        // continue;
                    } else {
                        temp_val = rows.get(row_index).getCellValue(x).toString();
                    }
                    if(!temp_val.equals("")) {
                      if (date_type_index == -1) {
                          System.out.print("Date Type Column Not Found");
                      } else {
                          String current_date_type = rows.get(row_index).getCellValue(date_type_index).toString();
                          temp_date_types.add(current_date_type);
                      }
                      temp_dates.add(temp_val);
                    }
                  }

                  break;
              case "date type": //active, birth, death, suspiciousdate
                  // date_type_index = x;
                  break;
              case "subject":
                  /* Iterate through all the subject entries, create a Subject object
                   * which will be added to a list of Subject.
                   * Add the list of Subject to the constellation.
                   */

                  List<Subject> subject_list = new LinkedList<Subject>();
                  for(int z = 1; z < rows.size() + 1; z++) {
                    /*Set the value of the subject via a term. */
                      if(!temp_val.equals("")) {
                        Subject subjectValue = new Subject();
                        Term t1 = new Term();
                        t1.setType("subject");
                        t1.setTerm(temp_val);
                        subjectValue.setTerm(t1);
                        subject_list.add(subjectValue);
                        subjectValue.setOperation("insert");
                      }
                      // If there are more rows, then insert more subjects
                    if(z != rows.size()) {
                      if(rows.get(z).getCellValue(x)!=null) {
                        temp_val = rows.get(z).getCellValue(x).toString();
                      }
                      /* It is possible that a row may be empty so we continue on until we
                      * find one (or else this repeats the less non-empty row)
                      */
                      else {
                        temp_val="";
                      }
                    }
                  }
                  con.setSubjects(subject_list);
                  break;
              case "place":
                  /* Iterate through all the Place and place_type entries, create a list of Place objects
                   * which will be added to a list of Place.
                   */
                  List<Place> place_list = new LinkedList<Place>(); //TODO: JHG
                  // temp_val = rows.get(c).getCellValue(x).toString();

                  // Init AssociatedPlace Term
                  Term associated = new Term();
                  associated.setType("place_match");  //TODO: fix place
                  associated.setID(705);  //TODO: fix place
                  associated.setTerm("AssociatedPlace");  //TODO: fix place

                  for(int row_index = 0; row_index < rows.size(); row_index++) { // JHG changed from row_index = 1 and size()+1 to 0.
                      if (rows.get(row_index).getCellValue(x) == null || rows.get(row_index).getCellValue(x) == "") {
                          temp_val = "";
                      } else {
                          temp_val = rows.get(row_index).getCellValue(x).toString();
                      }
                      if(!temp_val.equals("")) {
                        /*Set the value of the place via a term. */
                        Place placeValue = new Place();
                        placeValue.setOriginal(temp_val);
                        placeValue.setType(associated);

                        // Find adjacent place role and set
                        if (place_role_index != -1) {
                            String current_place_role = rows.get(row_index).getCellValue(place_role_index).toString();
                            if (!current_place_role.isEmpty()) {
                                placeValue.setRole(determinePlaceRole(current_place_role));
                            }
                        }

                        place_list.add(placeValue);
                        placeValue.setOperation("insert");
                      }
                  }
                  con.setPlaces(place_list);
                  break;
              case "place role": // birth, death, residence, work
                  break;
              case "occupation":
                  /* Iterate through all the Occupation entries, create a Occupation object
                   * which will be added to a list of Occupation.
                   * Add the list of Occupation to the constellation.
                   */
                  List<Occupation> occupation_list = new LinkedList<Occupation>();

                  for(int z = 1; z < rows.size() + 1; z++) {
                      if((!temp_val.equals("")) && (temp_val.length()>=1)) {
                        /*Set the value of the occupation via a term. */
                        Occupation occupationValue = new Occupation();
                        Term t1 = new Term();
                        t1.setType("occupation");
                        t1.setTerm(temp_val);
                        occupationValue.setTerm(t1);
                        occupation_list.add(occupationValue);
                        occupationValue.setOperation("insert");
                      }
                      if(z != rows.size()) {
                        if(rows.get(z).getCellValue(x)!=null) {
                          temp_val = rows.get(z).getCellValue(x).toString();
                        }
                      /* It is possible that a row may be empty so we continue on until we
                      * find one (or else this repeats the less non-empty row)
                      */
                        else {
                          temp_val="";
                        }
                      }
                  }
                  con.setOccupations(occupation_list);
                  break;
              case "function":
                  /* Iterate through all the SNACFunction entries, create a Function object
                   * which will be added to a list of Functions.
                   * Add the list of Function to the constellation.
                   */
                  List<SNACFunction> SNACFunc_list = new LinkedList<SNACFunction>();
                  for(int z = 1; z < rows.size() + 1; z++) {
                    if(!temp_val.equals("")) {
                      /*Set the value of the subject via a term. */
                      SNACFunction snacFuncValue = new SNACFunction();
                      Term t1 = new Term();
                      t1.setType("function");
                      t1.setTerm(temp_val);
                      snacFuncValue.setTerm(t1);
                      SNACFunc_list.add(snacFuncValue);
                      snacFuncValue.setOperation("insert");
                    }

                      // If there are more rows, then insert more subjects
                    if(z != rows.size()) {
                      if(rows.get(z).getCellValue(x)!=null) {
                        temp_val = rows.get(z).getCellValue(x).toString();
                      }
                      /* It is possible that a row may be empty so we continue on until we
                      * find one (or else this repeats the less non-empty row)
                      */
                      else {
                        temp_val="";
                      }
                    }
                  }
                con.setFunctions(SNACFunc_list);
                break;
              case "bioghist":
                  /* Iterate through all the biogHist entries, create a Function object
                  * which will be added to a list of bioHists.
                  * Add the list of bioHist to the constellation.
                  */
                List<BiogHist> biogHists = new LinkedList<BiogHist>();
                if(!temp_val.equals("")) {
                  /*Set the value of the subject via a term. */
                  BiogHist biogHistValue = new BiogHist();
                  biogHistValue.setText(temp_val);
                  biogHists.add(biogHistValue);
                  biogHistValue.setOperation("insert");
                }
                con.setBiogHists(biogHists);
                break;
              case "sameas relation":
                    /* Iterate through all the sameAs entries, create a sameAs object
                   * which will be added to a list of sameAs.
                   * Add the list of sameAs to the constellation.
                   */
                  List<SameAs> sameAs_list = new LinkedList<SameAs>();
                  if(!temp_val.equals("")) {
                    /*Set the value of the subject via a term. */
                    SameAs sameAsValue = new SameAs();
                    sameAsValue.setURI(temp_val);
                    sameAs_list.add(sameAsValue);
                    sameAsValue.setOperation("insert");
                  }
                con.setSameAsRelations(sameAs_list);
                break;
              default:
                  continue;
            }
        }

        //Logic for date handling
        if(temp_date_types.size()==temp_dates.size() && temp_date_types.size()!=0) {
            //for(int x=0;x<temp_dates.size();x++) {
                SNACDate d = new SNACDate();
                SNACDate d2 = new SNACDate();
                if(temp_date_types.size()==1) {
                    Term temp_term=setDateTerms(temp_date_types.get(0));
                    d.setDate(temp_dates.get(0),temp_dates.get(0),temp_term);
                    d.setOperation("insert");
                    temp_SNACDates.add(d);
                }
                else if(temp_date_types.size()==2) { //this is under the assuption the rows are always in order of From and To date, aka Active->Death, Birth->Death, etc.
                    Term t1=new Term();
                    Term t2=new Term();
                    t1=setDateTerms(temp_date_types.get(0));
                    t2=setDateTerms(temp_date_types.get(1));
                    d.setRange(true);
                    d.setFromDate(temp_dates.get(0),temp_dates.get(0),t1);
                    d.setToDate(temp_dates.get(1),temp_dates.get(1),t2);
                    d.setOperation("insert");
                    temp_SNACDates.add(d);
                }
                else if(temp_date_types.size()==3) {
                    //TODO: JHG if there are more than 2 dates, insert them each as a seperate, non-range date
                        // make a for-loop to iterate and create

                    Term t1=new Term();
                    Term t2=new Term();
                    Term t3=new Term();
                    t1=setDateTerms(temp_date_types.get(0));
                    t2=setDateTerms(temp_date_types.get(1));
                    t3=setDateTerms(temp_date_types.get(2));
                    d.setRange(true);
                    d.setFromDate(temp_dates.get(0),temp_dates.get(0),t1);
                    d.setToDate(temp_dates.get(1),temp_dates.get(1),t2);
                    d2.setDate(temp_dates.get(2),temp_dates.get(2),t3);
                    d.setOperation("insert");
                    d2.setOperation("insert");
                    temp_SNACDates.add(d);
                    temp_SNACDates.add(d2);
                }
                else {
                    //error, you gave us 4+ dates?
                    // TODO: handle multiple dates
                }
            //}
            con.setDateList(temp_SNACDates);
        }
        return con;
    }


    /**
    * Take a given Row and convert it to a Constellation Object
    *
    * @param row (Row found in List<Row> from Project)
    * @return Constellation converted from Row
    */
    public Constellation createConstellationRow(Row row) {
        Constellation con = new Constellation();
        con.setOperation("insert");
        for (int x = 0; x < csv_headers.size(); x++) {
            String snac_header = match_attributes.get(csv_headers.get(x)).toLowerCase();
            if (snac_header == null || snac_header == "") {
                continue;
            }
            // Insert switch statements || Bunch of if statements for setters
            String temp_val;
            // If cell empty, set value to empty value
            if (row.getCellValue(x) == null || row.getCellValue(x) == "") {
                temp_val = "";
                // continue;
            } else {
                temp_val = row.getCellValue(x).toString();
            }
            switch(snac_header) {
              case "id":
                  try {
                      con.setID(Integer.parseInt(temp_val));
                      constellation_ids.add(Integer.parseInt(temp_val));
                      break;
                  }
                  catch (NumberFormatException e) {
                      break;
                  }
              case "type":
                  try {
                      Term t = new Term();
                      t.setType("document_type");
                      String term;
                      int type_id;
                      if (temp_val.equals("696") || temp_val.equals("ArchivalConstellation")) {
                        type_id = 696;
                        term = "ArchivalConstellation";
                      } else if (temp_val.equals("697") || temp_val.equals("BibliographicConstellation")) {
                        type_id = 697;
                        term = "BibliographicConstellation";
                      } else if (temp_val.equals("400479") || temp_val.equals("DigitalArchivalConstellation")) {
                        type_id = 400479;
                        term = "DigitalArchivalConstellation";
                      } else {
                        throw new NumberFormatException();
                      }
                      t.setID(type_id);
                      t.setTerm(term);
                      //con.setDocumentType(t);
                      break;
                  }
                  catch (NumberFormatException e) {
                      System.out.println(temp_val + " is not a valid constellation type.");
                      break;
                  }
                  catch (Exception e) {
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
              case "place role":
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
    public String obtainPreview() {
      String samplePreview = "";
      if (constellations.size() == 0) {
        samplePreview += "There are no Constellations to preview.";
      }
      else {
        samplePreview += "Inserting " + constellations.size() + " new Constellations into SNAC." + "\n";

        int iterations = Math.min(constellations.size(), 2);

        for(int x = 0; x < iterations; x++) {
          Constellation previewConstellation = constellations.get(x);
          // System.out.println(Constellation.toJSON(previewConstellation));
          for(Map.Entry mapEntry: match_attributes.entrySet()) {
              if(!((String)mapEntry.getValue()).equals("")) {
                switch(((String)mapEntry.getValue()).toLowerCase()) {
                  case "id":
                    samplePreview+= "ID: " + previewConstellation.getID() + "\n";
                    break;
                  case "entity type":
                    Term previewTerm = previewConstellation.getEntityType();
                    samplePreview+="Entity Type: " + previewTerm.getTerm() + " (" + previewTerm.getID() + ")\n";
                    break;
                  case "name entry":
                    samplePreview+="Name Entry: " + previewConstellation.getNameEntries() + "\n";
                    break;
                  case "date":
                    samplePreview+="Date: " + previewConstellation.getDateList() + "\n";
                    break;
                  case "subject":
                    samplePreview+="Subject: " + previewConstellation.getSubjects() + "\n";
                    break;
                  case "place":
                    samplePreview+="Place: " + previewConstellation.getPlaces() + "\n";
                    // TODO: Display place_role
                    break;
                  case "occupation":
                    samplePreview+="Occupation: " + previewConstellation.getOccupations() +  "\n";
                    break;
                  case "function":
                    samplePreview+="Function: " + previewConstellation.getFunctions() + "\n";
                    break;
                  case "bioghist":
                    samplePreview+="Bioghist: " + previewConstellation.getBiogHists() + "\n";
                    break;
                  case "sameas relation":
                    samplePreview+="Sameas relation: " + previewConstellation.getSameAsRelations()  + "\n";
                    break;
                  default:
                    break;
                }
              }
          }
          System.out.println(Constellation.toJSON(previewConstellation));
        }
      }
      return samplePreview;

    }

    /**
    * Converts Constellation Array into one JSON Object used to export
    *
    * @param none
    * @return String (String converted JSONObject of Constellation Array)
    */
    public String exportConstellationsJSON() {
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONParser jp = new JSONParser();
        for (int x = 0; x < constellations.size(); x++) {
          try {
              ja.add((JSONObject)jp.parse(Constellation.toJSON(constellations.get(x))));
          }
          catch (ParseException e) {
            continue;
          }
        }
        jo.put("constellations", ja);
        return jo.toString();

    }

    /**
    * Preps and attempts to upload the constellation
    *
    * @param apiKey
    * @param state upload environment of production or develop
    */
    public void uploadConstellations(String apiKey, String state) {
        String result="";
        try {
            String key = "\"apikey\":\"" + apiKey +"\",";
            List<Constellation> new_list_constellations = new LinkedList<Constellation>();
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://localhost/~josephglass/snac/rest/");
            if(state == "prod") {
                post = new HttpPost("http://api.snaccooperative.org/");
            }
            System.out.println("Querying SNAC...");
                  String ctj = Constellation.toJSON(temp_con);
                  String api_query = "{\"command\": \"update_constellation\",\n" + key +  "\n\"constellation\":" + ctj.substring(0,ctj.length()-1) + "}}";
                  StringEntity casted_api = new StringEntity(api_query,"UTF-8");
                  post.setEntity(casted_api);
                  HttpResponse response = client.execute(post);
                  result = EntityUtils.toString(response.getEntity());
              }
              else{ //SNAC ID not provided, should be a new Constellation
                  String ctj = Constellation.toJSON(temp_con);
                  String api_query = "{\"command\": \"insert_constellation\",\n" + key +  "\n\"constellation\":" + ctj.substring(0,ctj.length()-1) + "}}";
                  String api_query = "{\"command\": \"insert_and_publish_constellation\",\n" + key +  "\n\"constellation\":" + ctj.substring(0,ctj.length()-1) + "}}";
                  StringEntity casted_api = new StringEntity(api_query,"UTF-8");
                  post.setEntity(casted_api);
                  HttpResponse response = client.execute(post);
                  result = EntityUtils.toString(response.getEntity());
                  System.out.print(api_query);
                  System.out.print(result);
                  // new_list_constellations.add(insertID(result,temp_con));
              // }
            }
            System.out.println("Uploading Attempt Complete");
            // test_insertID();
        } catch(IOException e) {
          System.out.println(e);
        }

    }

    /**
    * Supposed to take the reply from the API and make a column field in the schema
    *
    * @param result the reply from the API including the SNAC ID (for new entries)
    * @param con the constellation to add the ID into
    */
    public Constellation insertID(String result, Constellation con) {
    JSONParser jp = new JSONParser();
    try {
        JSONObject jsonobj = (JSONObject)jp.parse(result);
        int new_id = Integer.parseInt((((JSONObject)jsonobj.get("constellation")).get("id")).toString());
        if(new_id!=0) {
          constellation_ids.add(new_id);
          con.setID(new_id);
          return con;
        }
        else {
          constellation_ids.add(null);
        }
    }
    catch (ParseException e) {
        System.out.println(e);
    }
    return con;
}


    /**
    * Preps and attempts to upload the constellation
    * Run this function after insertID (above) within SNACUploadCommand
    * Check if ID column exists (Need to see how to determine which column is "id" given different naming conventions)
    * If exists: Go through and set the cell values based on the constellation_ids
    * If not: Create a new column "id" and insert cell values based on constellation_ids
    */
  public void test_insertID() {

    // Operation below creates new column "id" and insert cell values from uploaded Constellation objects through SNAC API
    for (int x = 0; x < theProject.rows.size(); x++) {
      Cell test_cell = new Cell(x, new Recon(0, null, null));
      res_row_ids.add(new CellAtRow(x, test_cell));
    }
    ColumnAdditionChange CAC = new ColumnAdditionChange("testing_column", 0, res_row_ids);
    CAC.apply(theProject);
  }

}
