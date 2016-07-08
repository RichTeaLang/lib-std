package richTea.std.exports;

import java.util.ArrayList;
import java.util.List;

import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.execution.ReturnException;

public class Map extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		List<?> input = getInput();
		List<Object> mappedValues = new ArrayList<Object>();
		
		for(Object value : input) {
			context.setLastReturnValue(value);
			
			try {
				context.executeBranch("mapFunction");
			} catch(ReturnException e) {
				context.unRollScopeTo(this);
			}
			
			mappedValues.add(context.getLastReturnValue());
		}
		
		context.setLastReturnValue(mappedValues);	
	}
	
	protected List<?> getInput() {
		return (List<?>) context.getValue("input");
	}
}
