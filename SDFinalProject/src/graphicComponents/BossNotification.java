
package graphicComponents;
import java.awt.Color;
import java.awt.Font;
import start.Game;

public class BossNotification extends javax.swing.JFrame
{
	
	private static final long serialVersionUID = 5692047168506816121L;
	private javax.swing.JLabel label;
	private javax.swing.JButton readyButton;

    public BossNotification() {
        initComponents();
    }
                        
    private void initComponents() 
    {

        label = new javax.swing.JLabel();
        readyButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        label.setText("I'm Ready");
        label.setFont(new Font("Arial", Font.BOLD,30));
        label.setForeground(Color.RED.brighter());

        label.setText("THE BOSS IS APPROACHING!");
      
        readyButton.setText("I'm Ready");
        readyButton.setFont(new Font("Arial", Font.BOLD,20));
        readyButton.setForeground(Color.ORANGE);
        readyButton.setBackground(Color.DARK_GRAY.brighter());
        readyButton.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                readyButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(readyButton)))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(readyButton)
                .addGap(10, 10, 10))
        );

        pack();
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.DARK_GRAY.brighter());
        this.setTitle("DANGER");
    }                      
                                        

    private void readyButtonMouseClicked(java.awt.event.MouseEvent evt) 
    {          
    	
    	Game.paused = false;
        this.dispose();
        
    }                                        
                
}
