package richTea.std.exports;

import richTea.runtime.attribute.Attribute;
import richTea.runtime.attribute.modifier.SetModifier;
import richTea.runtime.execution.AbstractFunction;

public class Set extends AbstractFunction {
	
	@Override
	protected void run() {
		Attribute attribute = getAttribute();
		
		if(attribute != null) {
			Object value = getAttributeValue();
			value = attribute.modify(context, new SetModifier(value));
			context.setLastReturnValue(value);
		}
	}
	
	protected Attribute getAttribute() {
		return context.getCurrentNode().getAttribute("attribute");
	}

	protected Object getAttributeValue() {
		return context.getValue("to");
	}
}