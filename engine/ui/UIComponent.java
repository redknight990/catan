package ui;

import java.util.ArrayList;
import java.util.List;

public class UIComponent {
	
	final UIDimensions dimensions;
	private UIConstraints constraints = null;
	
	protected List<UIComponent> children = new ArrayList<UIComponent>();
	
	private boolean visible = true;
	
	public UIComponent(UIDimensions dimensions) {
		this.dimensions = dimensions;
	}
	
	public UIComponent() {
		this.dimensions = new UIDimensions();
	}
	
	void setConstraints(UIConstraints constraints) {
		this.constraints = constraints;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public UIConstraints getConstraints() {
		return this.constraints;
	}
	
	public UIDimensions getDimensions() {
		return new UIDimensions().set(dimensions);
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void computeChildrenDimensions() {
		
		for(UIComponent child : children) {

			int lastWidth = child.dimensions.getWidth();
			int lastHeight = child.dimensions.getHeight();
			
			if(child.getConstraints() != null) 
				child.getConstraints().computeDimensions(dimensions, child.dimensions);
			else {
				child.dimensions.set(dimensions);
				child.dimensions.setElevation(dimensions.getElevation() + 1);
			}

			if (child instanceof UIText && (lastWidth != child.dimensions.getWidth() || lastHeight != child.dimensions.getHeight()))
				((UIText) child).shouldUpdateImage = true;

			child.computeChildrenDimensions();
			
		}
		
	}
	
	public UIComponent add(UIComponent component, UIConstraints constraints) {
		component.setConstraints(constraints);
		children.add(component);
		return this;
	}
	
}
