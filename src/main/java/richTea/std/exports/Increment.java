package richTea.std.exports;

import richTea.runtime.attribute.Attribute;
import richTea.runtime.attribute.modifier.IncrementModifier;
import richTea.runtime.execution.AbstractFunction;

public class Increment extends AbstractFunction {

	@Override
	protected void run() {
		Attribute attribute = getAttribute();
		
		Object newValue = attribute.modify(context, new IncrementModifier(getBy().doubleValue()));
			
		context.setLastReturnValue(newValue);
	}
	
	protected Attribute getAttribute() {
		return context.getCurrentNode().getAttribute("attribute");
	}
	
	protected Number getBy() {
		return context.getNumber("by");
	}
}