package system.ui.buttons;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import components.Button;
import oop.Product;
import res.Resource;
import system.ui.Window;
import system.ui.panels.dialogs.DialogDisposeProduct;

public abstract class ButtonDisposeProduct extends Button{
	private static final long serialVersionUID = -211465351098739393L;

	public ButtonDisposeProduct() {
		setArc(30);
		setPreferredSize(new Dimension(30,30));
		setIcon(new ImageIcon(Resource.get("delete.png")));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		DialogDisposeProduct dialog_dispose_product = new DialogDisposeProduct(getSelectedProduct()) {
			private static final long serialVersionUID = 6203612887549457328L;
			@Override
			public void onDisposeProductOk(Product product) {
				disposeProduct(product);
			}
		};
		Window.getStackPanel().pushPanel(dialog_dispose_product, 200, 200);
	}
	public void disposeProduct(Product product) {
		onDisposeProduct(product);
	}
	
	public abstract void onDisposeProduct(Product product);
	public abstract Product getSelectedProduct();
	
}