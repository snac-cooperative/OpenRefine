package com.google.refine.commands.recon;

import com.google.refine.commands.CommandTestBase;
import java.io.IOException;

import javax.servlet.ServletException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PreviewExtendDataCommandTests extends CommandTestBase {
	
	@BeforeMethod
	public void setUpCommand() {
		command = new PreviewExtendDataCommand();
	}
	
	@Test
	public void testCSRFProtection() throws ServletException, IOException {
		command.doPost(request, response);
		assertCSRFCheckFailed();
	}
}
