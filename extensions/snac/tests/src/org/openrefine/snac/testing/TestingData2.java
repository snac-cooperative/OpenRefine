/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2018 Antonin Delpeuch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/
package org.openrefine.snac.testing;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import javax.servlet.ServletException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.google.refine.RefineTest;

/*
import org.openrefine.wikidata.schema.WbLanguageConstant;
import org.openrefine.wikidata.schema.WbMonolingualExpr;
import org.openrefine.wikidata.schema.WbStringConstant;
import org.openrefine.wikidata.schema.entityvalues.ReconItemIdValue;
import org.openrefine.wikidata.schema.entityvalues.ReconPropertyIdValue;
import org.wikidata.wdtk.datamodel.helpers.Datamodel;
import org.wikidata.wdtk.datamodel.interfaces.Claim;
import org.wikidata.wdtk.datamodel.interfaces.ItemIdValue;
import org.wikidata.wdtk.datamodel.interfaces.PropertyIdValue;
import org.wikidata.wdtk.datamodel.interfaces.Statement;
import org.wikidata.wdtk.datamodel.interfaces.StatementRank;
*/
import com.google.refine.model.Cell;
import com.google.refine.model.Project;
import com.google.refine.model.Recon;
import com.google.refine.model.ReconCandidate;

public class TestingData2 {

    public static final String simpleCsv = "id\n1";

    public static final String resourceCsv = "id,type,title,display entry,link,abstract,extent,date,language,script,holding repository snac id\n"
            + "1,696,Title1,display_entry1,http://record1test.com,abstract_example1,extent1,2020,eng,English,12345\n"
            + "2,697,Title2,display_entry2,http://record2test.com,abstract_example2,extent2,2020,eng,English,12346\n"
            + "3,400479,Title3,display_entry3,http://record3test.com,abstract_example3,extent3,2020,eng,English,12347";

    public static final String resourceRecordCsv = "id,type,title,display entry,link,abstract,extent,date,language,script,holding repository snac id\n"
            + "2,696,Title2,display_entry2,http://record2test.com,abstract_example2,extent2,2020,eng,English,12346\n"
            + "1,697,Title1,display_entry1,http://record1test.com,abstract_example1,extent1,2020,eng,English,12345\n"
            + ",,,,,,,,kor,,";

    public static final String resourceRecordCsv2 = "id,type,title,display entry,link,abstract,extent,date,script,language,holding repository snac id\n"
            + "1,400479,Title1,display_entry1,http://record1test.com,abstract_example1,extent1,2020,English,eng,12345\n"
            + ",,,,,,,,Korean,,";

    public static final String resourceCsv2 = "title,link,abstract,language,id,type,extent,date\n"
            + "A,B,C,eng,E,696,G,H\n"
            + "A1,B1,C1,eng,E1,697,G1,H1\n";

    public static final String inceptionCsv = "subject,inception,reference\n"
            + "Q1377,1919,http://www.ljubljana-slovenia.com/university-ljubljana\n" + "Q865528,1965,";
    public static final String inceptionWithNewCsv = "subject,inception,reference\n"
            + "Q1377,1919,http://www.ljubljana-slovenia.com/university-ljubljana\n" + "Q865528,1965,\n"
            + "new uni,2016,http://new-uni.com/";
    public static final String inceptionWithNewQS = "Q1377\tP571\t+1919-01-01T00:00:00Z/9"
            + "\tS854\t\"http://www.ljubljana-slovenia.com/university-ljubljana\""
            + "\tS813\t+2018-02-28T00:00:00Z/11\n" + "Q865528\tP571\t+1965-01-01T00:00:00Z/9"
            + "\tS813\t+2018-02-28T00:00:00Z/11\n" + "CREATE\n" + "LAST\tP571\t+2016-01-01T00:00:00Z/9"
            + "\tS854\t\"http://new-uni.com/\"" + "\tS813\t+2018-02-28T00:00:00Z/11\n";

    public static final String constellationCsv = "subject,place,occupation\n"
            + "Kaijus,Kyoto,Demolition\n"
            + "kaijus,Tokyo,Demolition";

    public static String jsonFromFile(String filename)
            throws IOException {
        InputStream f = TestingData2.class.getClassLoader().getResourceAsStream(filename);
        String decoded = IOUtils.toString(f);
        return decoded.trim();
    }
    // public class ApiKeyTest extends RefineTest {
    //     @Test
    //     public void testNoCredentials() throws ServletException, IOException {
    //         command.doPost(request, response);
    //         assertEquals("null", writer.toString());
    //     }
    // }
/*
    public static ItemIdValue newIdA = makeNewItemIdValue(1234L, "new item A");
    public static ItemIdValue newIdB = makeNewItemIdValue(4567L, "new item B");
    public static ItemIdValue matchedId = makeMatchedItemIdValue("Q89", "eist");
    public static ItemIdValue existingId = Datamodel.makeWikidataItemIdValue("Q43");

    protected static PropertyIdValue pid = Datamodel.makeWikidataPropertyIdValue("P38");

    public static class ReconStub extends Recon {
        public ReconStub(long id, long judgmentHistoryEntry) {
            super(id, judgmentHistoryEntry);
        }
    }

    public static Recon makeNewItemRecon(long id) {
        Recon recon = new ReconStub(id, 382398L); // we keep the same judgment id because it is ignored
        recon.identifierSpace = "http://www.wikidata.org/entity/";
        recon.schemaSpace = "http://www.wikidata.org/prop/direct/";
        recon.judgment = Recon.Judgment.New;
        return recon;
    }

    public static Recon makeMatchedRecon(String qid, String name, String[] types) {
        Recon recon = Recon.makeWikidataRecon(123456L);
        recon.match = new ReconCandidate(qid, name, types, 100.0);
        recon.candidates = Collections.singletonList(recon.match);
        recon.judgment = Recon.Judgment.Matched;
        return recon;
    }

    public static Recon makeMatchedRecon(String qid, String name) {
        return makeMatchedRecon(qid, name, new String[0]);
    }

    public static Cell makeNewItemCell(long judgementId, String name) {
        return new Cell(name, makeNewItemRecon(judgementId));
    }

    public static Cell makeMatchedCell(String qid, String name) {
        return new Cell(name, makeMatchedRecon(qid, name));
    }

    public static ReconItemIdValue makeNewItemIdValue(long judgementId, String name) {
        return new ReconItemIdValue(makeNewItemRecon(judgementId), name);
    }

    public static ReconItemIdValue makeMatchedItemIdValue(String qid, String name) {
        return new ReconItemIdValue(makeMatchedRecon(qid, name), name);
    }

    public static ReconPropertyIdValue makeNewPropertyIdValue(long judgmentId, String name) {
        return new ReconPropertyIdValue(makeNewItemRecon(judgmentId), name);
    }

    public static ReconPropertyIdValue makeMatchedPropertyIdValue(String pid, String name) {
        return new ReconPropertyIdValue(makeMatchedRecon(pid, name), name);
    }

    public static WbMonolingualExpr getTestMonolingualExpr(String langCode, String langLabel, String text) {
        return new WbMonolingualExpr(new WbLanguageConstant(langCode, langLabel), new WbStringConstant(text));
    }

    public static Statement generateStatement(ItemIdValue from, PropertyIdValue pid, ItemIdValue to) {
        Claim claim = Datamodel.makeClaim(from, Datamodel.makeValueSnak(pid, to), Collections.emptyList());
        return Datamodel.makeStatement(claim, Collections.emptyList(), StatementRank.NORMAL, "");
    }

    public static Statement generateStatement(ItemIdValue from, ItemIdValue to) {
        return generateStatement(from, pid, to);
    }

    public static void reconcileInceptionCells(Project project) {
        project.rows.get(0).cells.set(0, TestingData.makeMatchedCell("Q1377", "University of Ljubljana"));
        project.rows.get(1).cells.set(0, TestingData.makeMatchedCell("Q865528", "University of Warwick"));
        project.rows.get(2).cells.set(0, TestingData.makeNewItemCell(1234L, "new uni"));
    }
*/
}
