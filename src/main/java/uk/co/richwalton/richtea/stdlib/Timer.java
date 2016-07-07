package uk.co.richwalton.richtea.stdlib;

import richTea.runtime.attribute.AbstractAttribute;
import richTea.runtime.attribute.AttributeSet;
import richTea.runtime.attribute.PrimativeAttribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.execution.ExecutionContext;

public class Timer extends AbstractFunction {
	
	@Override
	protected void run() throws Exception {
		long currentTime = System.currentTimeMillis();
		
		AttributeSet returns = new AttributeSet(
			new PrimativeAttribute("timeStarted", currentTime),
			new PrimativeAttribute("elapsedTime", new ElapsedTimeAttribute("elapsedTime", currentTime)),
			new PrimativeAttribute("currentTime", currentTime)
		);
		
		context.executeBranch("block", returns.getAttributes());
		context.setLastReturnValue(returns);
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