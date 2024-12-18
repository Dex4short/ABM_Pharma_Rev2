package system.ui.cells;


import javax.swing.BorderFactory;

import components.Button;
import components.table.Cell;

public class CellButton extends Cell{
	private static final long serialVersionUID = -752158914266118531L;
	private Button button;

	public CellButton(String str) {
		super(new Button(str));
		setButton((Button)getComponent(0));
		setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
	public void setButtonText(String txt) {
		button.setText(txt);
	}
	public String getButtonText() {
		return button.getText();
	}
}
