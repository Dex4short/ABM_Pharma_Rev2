package system.ui.buttons.customer_listing;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import components.Button;
import oop.Customer;
import oop.enums.CustomerState;
import res.Resource;
import system.ui.Window;
import system.ui.panels.actions.ActionPanelCustomerAdd;

public abstract class ButtonAddCustomer extends Button{
	private static final long serialVersionUID = -8319852584060410711L;

	public ButtonAddCustomer() {
		setArc(30);
		setPreferredSize(new Dimension(120,30));
		setBorder(BorderFactory.createEmptyBorder());
		
		setIcon(new ImageIcon(Resource.get("add.png")));
		setText("Add Customer");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Window.getStackPanel().pushPanel(new ActionPanelCustomerAdd() {
			private static final long serialVersionUID = 2483456328408759972L;

			@Override
			public void onCustomerOk(String customer_name, String address, String contact_no, String e_mail, String company) {
				onAddCustomerOk(new Customer(-1, customer_name, address, contact_no, e_mail, company, CustomerState.Listed));
			}
		}, 400, 300);
	}
	public void addCustomerOk(Customer customer) {
		onAddCustomerOk(customer);
	}
	
	public abstract void onAddCustomerOk(Customer customer);
	
}
