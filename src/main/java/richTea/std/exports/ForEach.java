package richTea.std.exports;

import richTea.runtime.attribute.PrimativeAttribute;
import richTea.runtime.execution.AbstractFunction;

public class ForEach extends AbstractFunction {
	
	@Override
	protected void run() {
		Iterable<?> inList = inList();
		String as = as();
		
		PrimativeAttribute currentElement = new PrimativeAttribute(as, null);
		
		context.pushScope(context.createScope(currentElement));
		
		for(Object element : inList) {
			currentElement.setValue(element);	
			context.setLastReturnValue(element);
			context.executeBranch("do");
		}
		
		context.popScope();
	}
	
	protected Iterable<?> inList() {
		return (Iterable<?>) context.getValue("in");
	}
	
	protected String as() {
		return context.getString("as");
	}	
}