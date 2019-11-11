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
package org.openrefine.snac.commands;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.openrefine.snac.testing.TestingData2;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import com.google.refine.commands.Command;
import com.google.refine.model.Project;
import com.google.refine.tests.RefineTest;

import org.apache.http.*;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.IOException;

public class CommandTest {

    /*
    * Test API calls for recently_published
    */

    @Test
    public void testRecentlyPublished() throws Exception{
      DefaultHttpClient client = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://api.snaccooperative.org");
      post.setEntity(new StringEntity("{\"command\":\"recently_published\"}", "UTF-8"));
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      //System.out.println(result);
      Assert.assertTrue(result.contains("success"));
    }

    /*
    * Test API calls for vocabulary
    */

    @Test
    public void testTermSearch() throws Exception{
      DefaultHttpClient client = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://api.snaccooperative.org");
      post.setEntity(new StringEntity("{\"command\": \"vocabulary\",\"query_string\": \"person\",\"type\": \"entity_type\",\"entity_type\": null}","UTF-8"));
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      Assert.assertTrue(result.contains("700"));
    }

    /*
    * Test API calls for browse
    */

    @Test
    public void testBrowsing() throws Exception{
      DefaultHttpClient client = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://api.snaccooperative.org");
      post.setEntity(new StringEntity("{\"command\": \"browse\",\"term\": \"Washington\",\"position\": \"middle\"}", "UTF-8"));
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      Assert.assertTrue(result.contains("name_entry"));
    }

    /*
    * Test API calls for read_resource
    */

    @Test
    public void TestExistingReadResource() throws Exception{
      DefaultHttpClient client = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://api.snaccooperative.org");
      post.setEntity(new StringEntity("{\"command\": \"read_resource\",\"resourceid\": \"7149468\"}", "UTF-8"));
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      Assert.assertTrue(result.contains("dataType\": \"Resource\""));
    }

    @Test
    public void TestNonexistantReadResource() throws Exception{
      DefaultHttpClient client = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://api.snaccooperative.org");
      post.setEntity(new StringEntity("{\"command\": \"read_resource\",\"resourceid\": \"100000000\"}", "UTF-8"));
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      Assert.assertTrue(!result.contains("dataType\": \"Resource\""));
    }

    /*
    * Test API calls for resource_search
    */

    @Test
    public void TestResourceSearch() throws Exception{
      DefaultHttpClient client = new DefaultHttpClient();
      HttpPost post = new HttpPost("http://api.snaccooperative.org");
      post.setEntity(new StringEntity("{\"command\": \"resource_search\",\"term\": \"Papers\"}", "UTF-8"));
      HttpResponse response = client.execute(post);
      String result = EntityUtils.toString(response.getEntity());
      Assert.assertTrue(result.contains("total\": ")); // we got a result
      Assert.assertTrue(!result.contains("total\": 0,")); // There were resources found
    }

  /*  @BeforeMethod(alwaysRun = true)
    public void setUpProject() {
        project = createCSVProject(TestingData.inceptionWithNewCsv);
        TestingData.reconcileInceptionCells(project);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);

        when(request.getParameter("project")).thenReturn(String.valueOf(project.id));

        try {
            when(response.getWriter()).thenReturn(printWriter);
        } catch (IOException e1) {
            Assert.fail();
        }
    }*/

}
