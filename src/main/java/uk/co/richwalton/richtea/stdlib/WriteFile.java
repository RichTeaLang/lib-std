package uk.co.richwalton.richtea.stdlib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import richTea.runtime.execution.AbstractFunction;

public class WriteFile extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		String path = getPath();
		byte[] contents = getContents();
		
		FileOutputStream output = new FileOutputStream(new File(path));
		
		output.write(contents);
		output.close();
	}
	
	protected String getPath() {
		return context.getString("path");
	}
	
	protected byte[] getContents() {
		Object contents = context.getValue("contents");
		
		if (contents instanceof ByteArrayOutputStream) {
			return ((ByteArrayOutputStream) contents).toByteArray();
		} else if (contents instanceof String) {
			return ((String) contents).getBytes();
		} else {
			return null;
		}
	}
}
