package richTea.std.exports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import richTea.compiler.bootstrap.Binding;
import richTea.runtime.attribute.Attribute;
import richTea.runtime.attribute.PrimativeAttribute;
import richTea.runtime.execution.AbstractFunction;
import richTea.runtime.execution.ReturnException;
import richTea.runtime.execution.VariableScope;
import richTea.runtime.functions.Scope;
import richTea.runtime.node.Branch;
import richTea.runtime.node.TreeNode;

public class Let extends AbstractFunction {

	@Override
	protected void run() throws Exception {
		VariableScope scope = context.getParentScope(Scope.class);
		
		if(scope != null) {
			TreeNode currentNode = context.getCurrentNode();
			Map<String, Attribute> attributes = new HashMap<>();

			// Execute branches and take the last returned value
			for(Branch branch : currentNode.getBranches()) {
				String branchName = branch.getName();
				try {
					context.executeBranch(branchName);
				} catch(ReturnException e) {
					context.unRollScopeTo(this);
				}
				
				attributes.put(branchName, new PrimativeAttribute(branchName, context.getLastReturnValue()));
			}
			
			Binding binding = currentNode.getBinding();
			Attribute[] defaultAttributes = binding.getDefinition().getDefaultAttributes().getAttributes();
			
			for(Attribute attribute : currentNode.getAttributes()) {
				String attributeName = attribute.getName();
				Attribute existingAttribute = attributes.put(attribute.getName(), attribute);
				
				if (existingAttribute != null) {
					String functionName = binding.getName();
					String message =  "cannot contain attribute and branch with duplicate name";
					throw new IllegalArgumentException(functionName + " " + message + ": " + attributeName);
				}
			}
			
			// Binding default attributes will be set only if an attribute/branch was not present with same name
			for(Attribute attribute : defaultAttributes) {
				String attributeName = attribute.getName();
				
				if (!attributes.containsKey(attributeName)) {
					attributes.put(attributeName, attribute);
				}
			}
			
			List<Attribute> lets = new ArrayList<>(attributes.size());
			
			for(Attribute attribute : attributes.values()) {
				attribute = new PrimativeAttribute(attribute.getName(), attribute.getValue(context));
				
				lets.add(attribute);
				scope.setAttribute(attribute);
			}
			
			context.setLastReturnValue(lets);
		} else {
			throw new IllegalArgumentException("No scope to create variable");
		}
	}
}
