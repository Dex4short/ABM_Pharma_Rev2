package system.ui.cells;


import javax.swing.BorderFactory;

import components.Label;
import components.table.Cell;

public class CellLabel extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Label label;

	public CellLabel(String str) {
		super(new Label(str));
		setLabel((Label)getComponent(0));
		setBorder(BorderFactory.createEmptyBorder(5,0,5,10));
	}
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public void setLabelText(String txt) {
		label.setText(txt);
	}
	public String getLabelText() {
		return label.getText();
	}
}
