package blackjack.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import blackjack.BlackJackObserver;
import blackjack.ControllerInterface;
import blackjack.model.*;

/* Jframe and screen for the loan shark that is to be implemented later */
public class LoanShark implements ActionListener, BlackJackObserver {
    public JFrame mainFrame;
    public JPanel mainPanel;
    private ControllerInterface controller;
    private User user;
    private JLabel currentDebt;
    private JLabel getLoan;
    private JButton goBack;
    private JTextField loanEnter;
    private JTextField payBackAmount;
    private JLabel payBack;
    private JLayeredPane layeredPane;
    private Gustavo gustavo;

    public LoanShark(ControllerInterface controller, User user, Gustavo gustavo){
        this.controller = controller;
        this.user = user;
        this.gustavo = gustavo;
        this.gustavo.register(this);


        mainFrame = new JFrame("LoanShark");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);  //top down
        mainPanel.setLayout(layout);
        mainPanel.setBackground(new Color(151,42,39));

        layeredPane = new JLayeredPane();   
        layeredPane.setPreferredSize(new Dimension(1000, 625));

        ClassLoader classLoader = getClass().getClassLoader();
        ImageIcon sharkIcon = new ImageIcon(classLoader.getResource("LoanShark.png"));
        ImageResizer.resize(0.35, sharkIcon);
        JLabel sharkLabel = new JLabel(sharkIcon);
        sharkLabel.setBounds(360, 320, sharkIcon.getIconWidth(), sharkIcon.getIconHeight());

        ImageIcon officeIcon = new ImageIcon(classLoader.getResource("Office.png"));
        JLabel officeLabel = new JLabel(officeIcon);
        officeLabel.setBounds(0, 0, officeIcon.getIconWidth(), officeIcon.getIconHeight());

        this.getLoan = new JLabel("Enter Loan Amount:");
        this.getLoan.setForeground(Color.RED);
        this.getLoan.setBounds(600,50,300,100);
        this.getLoan.setFont(new Font("serif", Font.CENTER_BASELINE, 24));

        this.loanEnter = new JTextField();
        this.loanEnter.setBounds(820,90,100,35);

        this.goBack = new JButton("Go Back");
        this.goBack.setBounds(850, 550, 75, 25);

        this.payBack = new JLabel("Enter Amount to Payback: ");
        this.payBack.setForeground(Color.RED);
        this.payBack.setBounds(545,100,300,100);
        this.payBack.setFont(new Font("serif", Font.CENTER_BASELINE, 24));

        this.payBackAmount = new JTextField();
        this.payBackAmount.setBounds(825,135,100,35);

        this.currentDebt = new JLabel("Current Debt: " + user.getDebt()); //changed it will construct upon user debt.
        this.currentDebt.setForeground(Color.RED);
        this.currentDebt.setFont(new Font("serif", Font.CENTER_BASELINE, 18));
        this.currentDebt.setBounds(25, 25, 200, 50);

        this.layeredPane.add(sharkLabel,0);
        this.layeredPane.add(officeLabel,1);
        this.layeredPane.add(goBack,0);
        this.layeredPane.add(getLoan,0);
        this.layeredPane.add(payBack, 0);
        this.layeredPane.add(payBackAmount,0);
        this.layeredPane.add(loanEnter, 0);
        this.layeredPane.add(currentDebt,0);

        this.mainPanel.add(layeredPane);
        this.mainFrame.add(mainPanel);
        this. mainFrame.pack();
        this.mainFrame.setVisible(true);  //maybe set to false as initial?
    }

    public void update(){
        currentDebt.setText("Current Debt: " + user.getDebt());
    }

    @Override
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();

        if(source == loanEnter){
            //controller will do get loan.
        }
        else if(source == payBackAmount){
            //controller will tell gustavo hes getting AP TMOO out here bud.
        }
        else if(source == goBack){
            //go back to main table. probably setVisible(false)?
        }
    }



}
