package components.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import components.CheckBox;
import components.Panel;
import oop.abstracts.Decoration;

public class Row extends Panel implements MouseListener, ItemListener{
	private static final long serialVersionUID = 2506138846020942141L;
	private Decoration normal, hover, highlight;
	private CheckBox check_box;
	private JPanel row_pane;
	private boolean isSelectionEnabled=true;

	public Row(Cell cell[]) {
		setArc(5);
		setLayout(new BorderLayout());
		
		check_box = new CheckBox();
		check_box.setOpaque(false);
		check_box.addItemListener(this);
		add(check_box, BorderLayout.WEST);
		
		row_pane = new JPanel();
		row_pane.setOpaque(false);
		row_pane.setLayout(new GridLayout(0, cell.length));
		for(int i=0; i<cell.length; i++) {
			row_pane.add(cell[i]);
		}
		add(row_pane, BorderLayout.CENTER);

		(normal = new Decoration.Basic(font[0], main_color[2], main_color[2])).decorate(this);
		hover = new Decoration.Basic(font[0], main_color[3], main_color[2]);
		highlight = new Decoration.Basic(font[0], new Color(0,0,0,0), main_color[3]);
		
		addMouseListener(this);
		
		setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(isSelectionEnabled && !check_box.isSelected()) {
			setSelected(true);
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(isSelectionEnabled && !check_box.isSelected()) {
			hover.decorate(this);
			repaint();
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(isSelectionEnabled && !check_box.isSelected()) {
			normal.decorate(this);
			repaint();
		}
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(isSelectionEnabled) {
			switch(e.getStateChange()) {
			case ItemEvent.SELECTED: highlight.decorate(this);
				break;
			case ItemEvent.DESELECTED: normal.decorate(this);
				break;
			}
			repaint();
		}
	}
	public boolean isSelected() {
		return check_box.isSelected();
	}
	public void setSelected(boolean isSelected) {
		check_box.setSelected(isSelected);
	}
	public void setSelectionEnabled(boolean enable) {
		isSelectionEnabled = enable;
	}
	public CheckBox getCheckBox() {
		return check_box;
	}
	public void setCheckBox(CheckBox check_box) {
		this.check_box = check_box;
	}
	public Cell getCell(int n) {
		return (Cell)row_pane.getComponent(n);
	}
	public void setCell(Cell new_cell,int n) {
		row_pane.remove(n);
		row_pane.add(new_cell, n);
	}
}
