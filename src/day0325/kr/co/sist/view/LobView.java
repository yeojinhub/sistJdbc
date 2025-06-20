package day0325.kr.co.sist.view;

import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import day0325.kr.co.sist.evt.LobEvent;

public class LobView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField jtfNum;
	private JTextField jtfName;
	private JTextField jtfEmail;
	private JTextField jtfTel;
	
	private JButton jbtnAdd; 
	private JButton jbtnSerch; 
	private JButton jbtnExit; 
	private JButton jbtnSelect; 
	
	private JScrollPane jspIntroduction;
	private JTextArea jtaIntroduction;
	
	private File defaultImg;
	
	private JLabel jlblImage;

	public LobView() {
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			String defaultPath="";
			try {
				defaultPath=new File("").getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
			
			defaultImg=new File(defaultPath+"/src/day0325/kr/co/sist/view/default_img.png");
			ImageIcon icon = new ImageIcon(defaultImg.getAbsolutePath());
			
			jlblImage = new JLabel(icon);
			jlblImage.setBounds(256, 50, 114, 138);
			contentPane.add(jlblImage);
			
			jbtnSelect = new JButton("선택");
			jbtnSelect.setBounds(256, 198, 114, 31);
			contentPane.add(jbtnSelect);
			
			JLabel jlblTitle = new JLabel("회원관리");
			jlblTitle.setBounds(171, 10, 97, 37);
			contentPane.add(jlblTitle);
			
			JLabel jlblNum = new JLabel("번호");
			jlblNum.setBounds(12, 50, 57, 15);
			contentPane.add(jlblNum);
			
			JLabel jlblName = new JLabel("이름");
			jlblName.setBounds(12, 80, 57, 15);
			contentPane.add(jlblName);
			
			JLabel jlblEmail = new JLabel("이메일");
			jlblEmail.setBounds(12, 110, 57, 15);
			contentPane.add(jlblEmail);
			
			JLabel jlblTel = new JLabel("전화번호");
			jlblTel.setBounds(12, 140, 57, 15);
			contentPane.add(jlblTel);
			
			jtfNum = new JTextField();
			jtfNum.setBounds(68, 50, 116, 21);
			contentPane.add(jtfNum);
			jtfNum.setColumns(10);
			
			jtfName = new JTextField();
			jtfName.setColumns(10);
			jtfName.setBounds(68, 80, 116, 21);
			contentPane.add(jtfName);
			
			jtfEmail = new JTextField();
			jtfEmail.setColumns(10);
			jtfEmail.setBounds(68, 110, 116, 21);
			contentPane.add(jtfEmail);
			
			jtfTel = new JTextField();
			jtfTel.setColumns(10);
			jtfTel.setBounds(68, 140, 116, 21);
			contentPane.add(jtfTel);
			
			JLabel jlblSogae = new JLabel("소개");
			jlblSogae.setBounds(12, 247, 32, 15);
			contentPane.add(jlblSogae);
			
			jtaIntroduction = new JTextArea();
			jspIntroduction=new JScrollPane(jtaIntroduction);
			jspIntroduction.setBounds(51, 247, 340, 152);
			contentPane.add(jspIntroduction);
			
			jbtnAdd = new JButton("추가");
			jbtnAdd.setBounds(157, 409, 70, 25);
			contentPane.add(jbtnAdd);
			
			jbtnSerch = new JButton("검색");
			jbtnSerch.setBounds(239, 409, 70, 25);
			contentPane.add(jbtnSerch);
			
			jbtnExit = new JButton("종료");
			jbtnExit.setBounds(321, 409, 70, 25);
			contentPane.add(jbtnExit);
			
			LobEvent le=new LobEvent(this);
			addWindowListener(le);
			
			jbtnAdd.addActionListener(le);
			jbtnExit.addActionListener(le);
			jbtnSelect.addActionListener(le);
			jbtnSerch.addActionListener(le);
			
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 418, 486);
			setResizable(false);
			
			jtfName.requestFocus();
		}
	
	public JTextField getJtfNum() {
		return jtfNum;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public JTextField getJtfTel() {
		return jtfTel;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public JButton getJbtnSerch() {
		return jbtnSerch;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public JScrollPane getJspIntroduction() {
		return jspIntroduction;
	}

	public JTextArea getJtaIntroduction() {
		return jtaIntroduction;
	}

	public File getDefaultImg() {
		return defaultImg;
	}

	public JButton getJbtnSelect() {
		return jbtnSelect;
	}

	public JLabel getJlblImage() {
		return jlblImage;
	}
}
