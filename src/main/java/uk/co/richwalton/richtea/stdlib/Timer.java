package uk.co.richwalton.richtea.stdlib;

import richTea.runtime.attribute.AbstractAttribute;
import richTea.runtime.attribute.PrimativeAttribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.execution.ExecutionContext;
import richTea.runtime.execution.VariableScope;

public class Timer extends AbstractFunction {
	
	@Override
	protected void run() throws Exception {
		long currentTime = System.currentTimeMillis();
		
		VariableScope scope = context.createScope(
			new PrimativeAttribute("timeStarted", currentTime),
			new PrimativeAttribute("elapsedTime", new ElapsedTimeAttribute("elapsedTime", currentTime)),
			new PrimativeAttribute("currentTime", currentTime)
		);
		
		context.executeBranch("block", scope);
		context.setLastReturnValue(scope);
	}
}

class ElapsedTimeAttribute extends AbstractAttribute {
	private long timeStarted;
	
	public ElapsedTimeAttribute(String name, long timeStarted) {
		super(name);
		
		this.timeStarted = timeStarted;
	}
	
	@Override
	public Object getValue(ExecutionContext context) {
		return System.currentTimeMillis() - this.timeStarted;
	}
}