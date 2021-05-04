package graphicComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import audio.AudioPlayer;
import bll.RecordHistoryBLL;

public class UsernameForm extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -1640129747306904302L;
	private JButton button;
	private JTextField textField;
	public String username;
	Menu menu;
	
	public UsernameForm(Menu menu)
	{
		
		this.menu = menu;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(350, 60));
		textField.setFont(new Font("Arial", Font.BOLD,50));
		textField.setForeground(Color.ORANGE);
		textField.setBackground(Color.DARK_GRAY.brighter());
		textField.setCaretColor(Color.ORANGE);
		textField.addKeyListener(new KeyListener()
		{
		    @Override
		    public void keyReleased(KeyEvent e) 
		    {
		        int pos = textField.getCaretPosition();
		        textField.setText(textField.getText().toUpperCase());
		        textField.setCaretPosition(pos);
		    }

			@Override
			public void keyPressed(KeyEvent arg0)
			{		
			}



			@Override
			public void keyTyped(KeyEvent arg0)
			{			
			}
		});
		
	    button = new JButton("Submit");
		button.addActionListener(this);
		button.setFont(new Font("Arial", Font.BOLD,20));
		button.setForeground(Color.ORANGE);
		button.setBackground(Color.DARK_GRAY.brighter());
		
		
		
		
		
		
		
		
		
		this.add(textField);
		this.add(button);
		this.setResizable(false);
		
		this.pack();
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		AudioPlayer.loadSounds();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == button)
		{
				
			RecordHistoryBLL recordHistoryBLL = new RecordHistoryBLL();
			menu.username = textField.getText().toUpperCase();
			menu.historyList = recordHistoryBLL.selectRecordHistory(textField.getText().toUpperCase());
			
			AudioPlayer.getSound("clickSound").play();
			this.dispose();
		}
		
	}
}
