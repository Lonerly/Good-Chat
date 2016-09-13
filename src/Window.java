import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.JTextComponent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextArea;

public class Window extends JFrame implements WritingOn {
	private JTextArea textArea;
	private JTextField ipTextField;
	private JTextField targetPort;
	private JTextField messageTextField;
	private JTextField receivingPort;
	private JButton btnListen;

	public Window() {
		getContentPane().setBackground(new Color(0, 100, 0));

		setVisible(true);
		setSize(650, 400);

		ipTextField = new JTextField();
		ipTextField.setForeground(new Color(255, 255, 255));
		ipTextField.setBackground(new Color(0, 0, 0));
		ipTextField.setColumns(10);

		targetPort = new JTextField();
		targetPort.setBackground(new Color(0, 0, 0));
		targetPort.setForeground(new Color(255, 255, 255));
		targetPort.setColumns(10);

		messageTextField = new JTextField();
		messageTextField.setBackground(new Color(0, 0, 0));
		messageTextField.setForeground(new Color(255, 255, 255));
		messageTextField.setColumns(10);

		JButton buttonSend = new JButton("Send");
		buttonSend.setForeground(new Color(255, 255, 255));
		buttonSend.setBackground(new Color(50, 205, 50));
		buttonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

		receivingPort = new JTextField();
		receivingPort.setForeground(new Color(255, 255, 255));
		receivingPort.setBackground(new Color(0, 0, 0));
		receivingPort.setColumns(10);

		JLabel lblNewLabel = new JLabel("Target port:");
		lblNewLabel.setForeground(new Color(255, 255, 255));

		JLabel lblReceivingPort = new JLabel("Receiving port:");
		lblReceivingPort.setBackground(new Color(0, 0, 0));
		lblReceivingPort.setForeground(new Color(255, 255, 255));
	

		btnListen = new JButton();
		btnListen.setBackground(new Color(50, 205, 50));
		btnListen.setText("Listen");
		btnListen.setForeground(new Color(255, 255, 255));
		btnListen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listenButtonActionPerformed(evt);
            }
        });

		textArea = new JTextArea();
		textArea.setForeground(new Color(255, 255, 255));
		textArea.setBackground(new Color(0, 0, 0));
		
		lblIp = new JLabel("IP:");
		lblIp.setForeground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ipTextField, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(targetPort, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblReceivingPort)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(receivingPort, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnListen))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(messageTextField, GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(buttonSend, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnListen)
						.addComponent(receivingPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblReceivingPort)
						.addComponent(targetPort, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(ipTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIp))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(messageTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttonSend))
					.addGap(4))
		);
		getContentPane().setLayout(groupLayout);

	}
	
	Listener listener;
	private JLabel lblIp;

	private void listenButtonActionPerformed(java.awt.event.ActionEvent evt) {
		listener = new Listener(this, Integer.parseInt(receivingPort.getText()));
		listener.start();
	}

	private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Transmitter transmitter = new Transmitter(messageTextField.getText(), ipTextField.getText(),
				Integer.parseInt(targetPort.getText()));
		transmitter.start();
	}

	@Override
	public void write(String s) {

		textArea.append(s + System.lineSeparator());
	}
}
