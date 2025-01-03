package system.ui.buttons.picking;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import components.Button;
import components._misc_.abstracts.Decoration;
import components.pickers.DatePicker;
import system.enumerators.Quality;
import system.managers.QualityManager;
import system.objects.Date;
import system.ui.Window;

public class ButtonDatePicker extends Button.Notified implements ActionListener{
	private static final long serialVersionUID = -8947393395162386027L;
	private DatePicker date_picker;
	private Quality exp_state;
	private boolean auto_check_expiry=true;

	public ButtonDatePicker(String date) {
		super(date);
		
		date_picker = new DatePicker(date) {
			private static final long serialVersionUID = 7485068439816912681L;
			@Override
			public void onPickCalendarDate(int yyyy, String month, int mm, String day, int dd) {
				setDate(getDate());
				Window.getStackPanel().popPanel();
			}
			@Override
			public void onCloseDatePicker() {
				Window.getStackPanel().popPanel();
			}
		};
		
		addActionListener(this);
		setNotified(true);
		checkExpiry();
	}
	public ButtonDatePicker(Date date) {
		super((date!=null) ? date.toString() : "yyyy-mm-dd");
		
		date_picker = new DatePicker((date!=null) ? date.toString() : "yyyy-mm-dd") {
			private static final long serialVersionUID = 7485068439816912681L;
			@Override
			public void onPickCalendarDate(int yyyy, String month, int mm, String day, int dd) {
				setDate(getDate());
				Window.getStackPanel().popPanel();
			}
			@Override
			public void onCloseDatePicker() {
				Window.getStackPanel().popPanel();
			}
		};
		
		addActionListener(this);
		setNotified(true);
		checkExpiry();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(date_picker == null) return;
		Window.getStackPanel().pushPanel(date_picker, 300, 300);
	}
	public void setDatePicker(DatePicker date_picker) {
		this.date_picker = date_picker;
	}
	public void setDate(Date date) {
		setText(date.toString());
		checkExpiry();
	}
	public void setState(Quality exp_state) {
		setAccentColorDecoration(exp_state.getState());
		this.exp_state = exp_state;
		
		System.out.println(exp_state.getState());
		repaint();
	}
	public void setAutoCheckExpiry(boolean auto_check_expiry) {
		this.auto_check_expiry = auto_check_expiry;
		if(!auto_check_expiry) {
			setState(Quality.NEUTRAL);
		}
	}
	public DatePicker getDatePicker() {
		return date_picker;
	}
	public Date getDate() {
		return new Date(getText());
	}
	public Quality getState() {
		return exp_state;
	}
	public void checkExpiry() {
		if(!auto_check_expiry) return;
		
		if(getText().equals("yyyy-mm-dd")) {
			setState(Quality.UNSET);
		}
		else {
			Date expiry = new Date(getText());
			setState(QualityManager.checkQuality(expiry));
		}
	}
	public boolean isAutoCheckExpiry() {
		return auto_check_expiry;
	}

	private void setAccentColorDecoration(int accent) {
		Decoration decorations[] = new Decoration[4];
		(decorations[Normal_State] = new Decoration.Bordered(
				font[0], 
				accent_color[0][accent],
				accent_color[1][accent],
				new Color(0,0,0,0)
		)).decorate(this);
		decorations[Hovered_State] = new Decoration.Bordered(
				font[0], 
				accent_color[0][accent],
				accent_color[1][accent],
				accent_color[0][accent]
		);
		decorations[Pressed_State] = new Decoration.Bordered(
				font[0], 
				accent_color[0][accent],
				accent_color[1][accent].brighter(),
				accent_color[0][accent]
		);
		decorations[Disabled_State] = decorations[Normal_State];
		setDecorations(decorations);
		
		setNotificationColor(getForeground());
	}
}
