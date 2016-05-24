package uk.co.richwalton.richtea.stdlib;

import richTea.runtime.attribute.PrimativeAttribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.execution.VariableScope;

public class Let extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		VariableScope scope = context.getCurrentScope().getParent();
		
		if(scope != null) {
			String variableName = getVariableName();
			Object value = getVariableValue();
			
			scope.setAttribute(new PrimativeAttribute(variableName, value));
			
			context.setLastReturnValue(value);
		} else {
			throw new IllegalArgumentException("No scope to create variable");
		}
	}
	
	protected String getVariableName() {
		return context.getString("var");
	}

	protected Object getVariableValue() {
		return context.getValue("be");
	}
}
