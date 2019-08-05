package com.son.migration.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.BorderLayout;

public class Redis2Redis {

	private JFrame frame;
	private JTextField txtHostRedisSource;
	private JTextField txtHostRedisTarget;
	private JTextField txtPortRedisSource;
	private JTextField txtPortRedisTarget;
	private JLabel lblSourceredis;
	private JLabel lblTargetredis;
	private JTextField txtRedisKey;
	private JButton btnExecute;
	private JButton btnCheckConnections;
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Redis2Redis window = new Redis2Redis();
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
	public Redis2Redis() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		textField.setColumns(10);
		frame = new JFrame("Redis Migration");
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		lblSourceredis = new JLabel("Source Redis");
		frame.getContentPane().add(lblSourceredis, "2, 2");
		
		txtHostRedisSource = new JTextField();
		txtHostRedisSource.setText("Host Redis Source");
		frame.getContentPane().add(txtHostRedisSource, "6, 2, fill, default");
		txtHostRedisSource.setColumns(10);
		
		txtPortRedisSource = new JTextField();
		txtPortRedisSource.setText("Port Redis Source");
		frame.getContentPane().add(txtPortRedisSource, "8, 2, fill, default");
		txtPortRedisSource.setColumns(10);
		
		lblTargetredis = new JLabel("Target Redis");
		frame.getContentPane().add(lblTargetredis, "2, 4");
		
		txtHostRedisTarget = new JTextField();
		txtHostRedisTarget.setText("Host Redis Target");
		frame.getContentPane().add(txtHostRedisTarget, "6, 4, fill, default");
		txtHostRedisTarget.setColumns(10);
		
		txtPortRedisTarget = new JTextField();
		txtPortRedisTarget.setText("Port Redis Target");
		frame.getContentPane().add(txtPortRedisTarget, "8, 4, fill, default");
		txtPortRedisTarget.setColumns(10);
		
		txtRedisKey = new JTextField();
		txtRedisKey.setText("Redis Key");
		frame.getContentPane().add(txtRedisKey, "6, 6, fill, default");
		txtRedisKey.setColumns(10);
		
		btnCheckConnections = new JButton("Check Connections");
		frame.getContentPane().add(btnCheckConnections, "2, 8");
		
		btnExecute = new JButton("Execute");
		frame.getContentPane().add(btnExecute, "6, 8");
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(10.0);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		panel_1 = new JPanel();
		panel.setMinimumSize(new Dimension(100, 100));
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		textField_1 = new JTextField();
		panel.add(textField_1, "10, 2, fill, default");
		textField_1.setColumns(10);
		
		textField = new JTextField();
		panel.add(textField, "12, 2, fill, default");
		textField.setColumns(10);
		
		textField_3 = new JTextField();
		panel.add(textField_3, "10, 4, fill, default");
		textField_3.setColumns(10);
		
		textField_2 = new JTextField();
		panel.add(textField_2, "12, 4, fill, default");
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		panel.add(textField_4, "10, 6, fill, default");
		textField_4.setColumns(10);
		splitPane.setRightComponent(panel_1);
		
		btnExecute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!"".equals(txtPortRedisSource.getText()) ){
					
				}
				
			}
		});
	}

}
