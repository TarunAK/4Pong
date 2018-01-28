package singleplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import multiplayer.ClientPlayer;
import multiplayer.CreateServer;


public class PingPong extends JFrame{

	public static final int WINDOW_WIDTH = 500;
	public static final int WINDOW_HEIGHT = 500;
	private JFrame frame;
	private JTextField textField_1;
	public static boolean diff_level;
	public static int players=2;
	public final Dimension gameSize = new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PingPong window = new PingPong();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PingPong() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Ping-Pong");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		frame.setContentPane(new JLabel(new ImageIcon(getClass().getClassLoader().getResource("background/pingpong.jpg"))));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		 
		
		
		diff_level = false;
		
		
		JButton play=new JButton("Play Offline");
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PingPong1();
				frame.dispose();
			}
		});
		play.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_play = new GridBagConstraints();
		gbc_play.insets = new Insets(0, 0, 5, 5);
		gbc_play.gridx = 0;
		gbc_play.gridy = 5;
		frame.getContentPane().add(play, gbc_play);
		
		JButton quit=new JButton("Quit Game ");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				Game.stop();
			}
		});
		quit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_quit = new GridBagConstraints();
		gbc_quit.insets = new Insets(0, 0, 5, 5);
		gbc_quit.gridx = 0;
		gbc_quit.gridy = 6;
		frame.getContentPane().add(quit, gbc_quit);
		
		JButton host=new JButton("    Host      ");
		host.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("host the game");
				frame.dispose();
				if (ClientPlayer.connected==1){
				   new CreateServer();
				}
				else {
					new CreateServer();
				}
			}
		});
		host.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_host = new GridBagConstraints();
		gbc_host.insets = new Insets(0, 0, 5, 5);
		gbc_host.gridx = 0;
		gbc_host.gridy = 3;
		frame.getContentPane().add(host, gbc_host);
		
		JButton connect=new JButton("   Player2   ");
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

							System.out.println("Connect to the game");
							frame.dispose();
							try {
								new ClientPlayer();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				
			}
		});
		connect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_connect = new GridBagConstraints();
		gbc_connect.insets = new Insets(0, 0, 5, 5);
		gbc_connect.gridx = 0;
		gbc_connect.gridy = 4;
		frame.getContentPane().add(connect, gbc_connect);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setEnabled(true);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.BLACK);
		textField_1.setForeground(Color.RED);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_display1 = new GridBagConstraints();
		gbc_display1.fill = GridBagConstraints.HORIZONTAL;
		gbc_display1.gridx = 1;
		gbc_display1.gridy = 5;
		frame.getContentPane().add(textField_1, gbc_display1);
		textField_1.setColumns(20);
		
	}
	
	 public void PingPong1() {
		 	setTitle("Ping-Pong");
	        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	        setResizable(false);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
	        setVisible(true);
	        add(new Game());
//	        frame.add(this);
	    }
}
