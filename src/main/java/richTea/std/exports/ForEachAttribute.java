package richTea.std.exports;

import java.util.Arrays;

import richTea.runtime.attribute.Attribute;
import richTea.runtime.attribute.AttributeSet;

public class ForEachAttribute extends ForEach {
	
	@Override
	protected Iterable<?> inList() {
		Object target = context.getValue("in");
		
		if(target instanceof AttributeSet) {
			Attribute[] attributes = ((AttributeSet) target).getAttributes();
			
			return Arrays.asList(attributes);
		}
		
		throw new IllegalArgumentException("'in' attribute does not specify an AttributeSet");
	}
}
