package richTea.std.exports;

import richTea.runtime.execution.AbstractFunction;

public class SystemExit extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		System.exit(getExitCode());
	}
	
	protected int getExitCode() {
		return (int) context.getNumberOrDefault("code", -1);
	}
}