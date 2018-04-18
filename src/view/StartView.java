import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartView extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JLabel lblNewLabel_1, lblNewLabel;
	private JButton btnNewButton;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartView frame = new StartView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StartView() {
		addLayout();
		eventProc();
	}
	
	
	void eventProc(){
//		MyMouseListener mc = new MyMouseListener();
		
//		lblNewLabel_1.addMouseListener( mc );
		lblNewLabel_1.addMouseListener( new MouseAdapter() { 
			public void mouseClicked(MouseEvent e) {
						JOptionPane.showMessageDialog(null, "다음으로 넘어갑니다.");
			}
		});
		
	}
	
//	class MyMouseListener extends MouseAdapter{
//		
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			Object evt = e.getSource();
//			if( evt == lblNewLabel_1 ){
//				JOptionPane.showMessageDialog(null, "다음으로 넘어갑니다.");
//			}
//		}
//	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
		
	}
	
	
	public void addLayout(){
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\student\\Desktop\\무인발권기\\p4.PNG"));
		lblNewLabel.setBounds(36, 32, 730, 507);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("티켓구매");
		btnNewButton.setForeground(Color.GRAY);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		btnNewButton.setBounds(74, 280, 207, 153);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(496, 280, 199, 153);
		contentPane.add(lblNewLabel_1);
	}
	
	
}
