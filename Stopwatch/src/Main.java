import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	boolean key = true;
	boolean isPaused = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Stopwatch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null); // setting location to the middle
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		JLabel hour = new JLabel("00 :");
		hour.setFont(new Font("Tahoma", Font.BOLD, 45));
		hour.setBounds(10, 11, 94, 71);
		contentPane.add(hour);
		
		JLabel minute = new JLabel("00 :");
		minute.setFont(new Font("Tahoma", Font.BOLD, 45));
		minute.setBounds(114, 11, 94, 71);
		contentPane.add(minute);
		
		JLabel second = new JLabel("00 ");
		second.setFont(new Font("Tahoma", Font.BOLD, 45));
		second.setBounds(218, 11, 94, 71);
		contentPane.add(second);
		
		JButton startBtn = new JButton("START");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key = true;
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						if(isPaused == false) {
							isPaused = true;
						}
						else if(isPaused == true) {
							isPaused = false;
						}
						if(isPaused) {
							startBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
							startBtn.setForeground(Color.BLACK);
							startBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
							startBtn.setBackground(new Color(44, 166, 211));
							startBtn.setText("PAUSE");
						}
						else if(!isPaused) {
							key = false;
							startBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
							startBtn.setForeground(Color.BLACK);
							startBtn.setBackground(new Color(255, 128, 64));
							startBtn.setText("START");
						}
						while(key) {
							
							int hours = Integer.parseInt(hour.getText().replaceAll(":","").trim());
							int minutes = Integer.parseInt(minute.getText().replaceAll(":","").trim());
							int seconds = Integer.parseInt(second.getText().replaceAll(":","").trim());
							try {
								Thread.sleep(1000);
								seconds++;
								if(seconds == 60) {
									minutes++;
									seconds = 0;
								}
								if(minutes == 60) {
									hours++;
									minutes = 0;
								}
								if(!key) {
									break; 
									// if user pauses suddenly
								}
								
								// formatting numbers to start with 0
								hour.setText(String.format("%02d",hours)+" :");
								minute.setText(String.format("%02d",minutes)+" :");
								second.setText(String.format("%02d",seconds));
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					}
				});
				thread.start();
			}
		});
		startBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		startBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		startBtn.setForeground(Color.BLACK);
		startBtn.setFocusPainted(false);
		startBtn.setBackground(new Color(255, 128, 64));
		startBtn.setBounds(61, 84, 73, 23);
		contentPane.add(startBtn);
		
		JButton resetBtn = new JButton("RESET");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPaused = false;
				hour.setText("00 :");
				minute.setText("00 :");
				second.setText("00 ");
				key = false;
				startBtn.setEnabled(true);
				startBtn.setBackground(new Color(255, 128, 64));
				startBtn.setText("START");
			}
		});
		resetBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		resetBtn.setActionCommand("");
		resetBtn.setForeground(Color.BLACK);
		resetBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		resetBtn.setFocusPainted(false);
		resetBtn.setBackground(new Color(20, 235, 143));
		resetBtn.setBounds(164, 84, 73, 23);
		contentPane.add(resetBtn);
	}
}