package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import res.Resource;
import system.enumerators.ProductCondition;
import system.objects.Product;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelAddProduct;

public abstract class ButtonAddProduct extends Button implements ActionListener{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddProduct() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Product");
		
		addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Window.load(() -> {
			Window.getStackPanel().pushPanel(new ActionPanelAddProduct() {
				private static final long serialVersionUID = -2025771841420314051L;
				@Override
				public void onAddProductOk(Product product, ProductCondition condition) {
					addProduct(product, condition);
				}
			}, 20, 20, 226);
		}, "");
	}
	public void addProduct(Product product, ProductCondition condition) {
		onAddProduct(product, condition);
	}
	
	public abstract void onAddProduct(Product product, ProductCondition condition);
	
}
