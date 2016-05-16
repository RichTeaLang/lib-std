package uk.co.richwalton.richtea.stdlib;

import richTea.core.attribute.Attribute;
import richTea.core.execution.AbstractFunction;
import richTea.core.factory.bindings.Binding;
import richTea.core.node.TreeNode;

public class Man extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		Binding binding = getTarget();
		
		String doc = binding.getName() + " (" + binding.getFunctionClassName() + ")";
		
		doc += "\n\tImplicit attribute name: " + binding.getImplicitAttributeName();
		doc += "\n\tImplicit branch name: " + binding.getImplicitBranchName();
		
		Attribute[] attributes = binding.getDefaultAttributes().getAttributes();
		
		if (attributes.length > 0) {
			doc += "\n\tAttributes:";
			
			for(Attribute attribute : binding.getDefaultAttributes().getAttributes()) {
				doc += "\n\t\t" + attribute.getName() + " (Default value: " + attribute.getValue(context) + ")";
			}
		}
		
		context.setLastReturnValue(doc);
	}
	
	public Binding getTarget() {
		TreeNode node = (TreeNode) context.getValue("target");
		
		return node instanceof Binding ? (Binding) node : node.getBinding();
	}
}
