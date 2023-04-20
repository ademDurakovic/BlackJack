package blackjack.view;

import java.awt.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

/* Jframe and screen for the loan shark that is to be implemented later */
public class LoanShark {
    public JFrame mainFrame;
    public JPanel mainPanel;

    public LoanShark(){
        mainFrame = new JFrame("LoanShark");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);  //top down
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));

        JLayeredPane layeredPane = new JLayeredPane();   
        layeredPane.setPreferredSize(new Dimension(1000, 625));

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon sharkIcon = new ImageIcon(classLoader.getResource("LoanShark.png"));
        ImageResizer.resize(0.35, sharkIcon);
        JLabel sharkLabel = new JLabel(sharkIcon);
        sharkLabel.setBounds(360, 320, sharkIcon.getIconWidth(), sharkIcon.getIconHeight());

        ImageIcon officeIcon = new ImageIcon(classLoader.getResource("Office.png"));
        JLabel officeLabel = new JLabel(officeIcon);
        officeLabel.setBounds(0, 0, officeIcon.getIconWidth(), officeIcon.getIconHeight());

        JLabel getLoan = new JLabel("Enter Loan Amount:");
        getLoan.setForeground(Color.RED);
        getLoan.setBounds(600,50,300,100);
        getLoan.setFont(new Font("serif", Font.CENTER_BASELINE, 24));

        JTextField loanEnter = new JTextField();
        loanEnter.setBounds(820,90,100,35);

        JButton goBack = new JButton("Go Back");
        goBack.setBounds(850, 550, 75, 25);

        JLabel payBack = new JLabel("Enter Amount to Payback: ");
        payBack.setForeground(Color.RED);
        payBack.setBounds(545,100,300,100);
        payBack.setFont(new Font("serif", Font.CENTER_BASELINE, 24));

        JTextField payBackAmount = new JTextField();
        payBackAmount.setBounds(825,135,100,35);

        JLabel currentDebt = new JLabel("Current Debt: $5000");
        currentDebt.setForeground(Color.RED);
        currentDebt.setFont(new Font("serif", Font.CENTER_BASELINE, 18));
        currentDebt.setBounds(25, 25, 200, 50);

        layeredPane.add(sharkLabel,0);
        layeredPane.add(officeLabel,1);
        layeredPane.add(goBack,0);
        layeredPane.add(getLoan,0);
        layeredPane.add(payBack, 0);
        layeredPane.add(payBackAmount,0);
        layeredPane.add(loanEnter, 0);
        layeredPane.add(currentDebt,0);

        mainPanel.add(layeredPane);
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);


    }
}
