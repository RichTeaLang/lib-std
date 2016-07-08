package richTea.std.exports;

import richTea.compiler.bootstrap.Binding;
import richTea.compiler.bootstrap.BindingDefinition;
import richTea.runtime.attribute.Attribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.node.TreeNode;

public class Man extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		Binding binding = getTarget();
		BindingDefinition definition = binding.getDefinition();
		
		String doc = binding.getName() + " (" + binding.getFunctionClass().getName() + ")";
		
		doc += "\n\tImplicit attribute name: " + definition.getImplicitAttributeName();
		doc += "\n\tImplicit branch name: " + definition.getImplicitBranchName();
		
		Attribute[] attributes = definition.getDefaultAttributes().getAttributes();
		
		if (attributes.length > 0) {
			doc += "\n\tAttributes:";
			
			for(Attribute attribute : definition.getDefaultAttributes().getAttributes()) {
				doc += "\n\t\t" + attribute.getName() + " (Default value: " + attribute.getValue(context) + ")";
			}
		}
		
		context.setLastReturnValue(doc);
	}
	
	public Binding getTarget() {
		TreeNode node = (TreeNode) context.getValue("target");
		
		return node.getBinding();
	}
}
