package day0327;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MemberViewEvt implements ActionListener{

	private MemberView frame;

	public MemberViewEvt(MemberView frame) {
		this.frame = frame;
	} //MemberViewEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frame.getJbtnSerch()) {
			new SearchZipcodeView(frame);
		} //end if
	} //actionPerformed
	
} //class
