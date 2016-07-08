package richTea.std.exports;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import richTea.runtime.execution.AbstractFunction;

public class ReadFile extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		Path path = getPath();
		byte[] contents = Files.readAllBytes(path);
		Object returnValue = asString() ? new String(contents) : contents;
		
		context.setLastReturnValue(returnValue);
	}
	
	protected Path getPath() {
		return new File(context.getString("path")).toPath().toAbsolutePath().normalize();
	}
	
	protected boolean asString() {
		return context.getBooleanOrDefault("asString", true);
	}
}
