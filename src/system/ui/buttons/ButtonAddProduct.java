package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import oop.Product;
import oop.enums.ProductCondition;
import res.Resource;
import system.ui.Window;
import system.ui.action_panels.ActionPanelAddProduct;

public abstract class ButtonAddProduct extends Button{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddProduct() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Product");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelAddProduct() {
			private static final long serialVersionUID = -2025771841420314051L;
			@Override
			public void onAddProductOk(Product products[]) {
				ProductCondition condition = ProductCondition.STORED;
				
				for(Product product: products) {
					addProduct(product, condition);
					condition = ProductCondition.ARCHIVED;
				}
			}
		}, 20, 20, 226);
	}
	public void addProduct(Product product, ProductCondition condition) {
		onAddProduct(product, condition);
	}
	public abstract void onAddProduct(Product product, ProductCondition condition);
}
