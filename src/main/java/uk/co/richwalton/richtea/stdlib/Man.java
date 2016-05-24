package uk.co.richwalton.richtea.stdlib;

import richTea.compiler.bootstrap.BindingNode;
import richTea.runtime.attribute.Attribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.node.TreeNode;

public class Man extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		BindingNode binding = getTarget();
		
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
	
	public BindingNode getTarget() {
		TreeNode node = (TreeNode) context.getValue("target");
		
		return node instanceof BindingNode ? (BindingNode) node : node.getBinding();
	}
}
