package system.ui.panels.actions.remarks;

import system.objects.Date;
import system.objects.Time;
import system.objects.Transaction;
import system.ui.Window;

public abstract class ActionPanelRemarksReturning extends ActionPanelRemarks{
	private static final long serialVersionUID = 9160139962791406360L;
	private Transaction transaction;

	public ActionPanelRemarksReturning(Transaction transaction) {
		super("Returning Remarks");
		setTransaction(transaction);
	}
	@Override
	public void onRemarksOk(Time time, Date date, String details) {
		Window.load( () -> {
			returnRemarksOk(time, date, details);
		}, "Returning product(s)...");
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public void returnRemarksOk(Time time, Date date, String details) {
		onReturnRemarksOk(time, date, details);
	}
	
	public abstract void onReturnRemarksOk(Time time, Date date, String details);
	
}
