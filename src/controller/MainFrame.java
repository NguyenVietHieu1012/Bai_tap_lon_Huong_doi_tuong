package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame implements ActionListener, Runnable{
    private Button graphicsPanel;
    private JPanel mainpanel;

    private JPanel createGraphicsPanel(){
        graphicsPanel = new Button(this,8,8);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLUE);
        panel.add(graphicsPanel);
        return panel;
    }
    private JPanel createMainPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createGraphicsPanel(), BorderLayout.CENTER);
        panel.add(createControlPanel(),BorderLayout.EAST);
        return panel;
    }
    public MainFrame(){
        add(mainpanel = createMainPanel());
        setTitle("Pikachu");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnNewGame) {
            showDialogNewGame("Bạn có muốn tạo game mới?","Question",0);
        }

    }
    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            progressTime.setValue((int) ((double)time / max_time * 100));
        }
    }

    private int max_time = 150;
    public int time = max_time;
    public JLabel lbScore;
    private JProgressBar progressTime;
    private JButton btnNewGame;

    private JButton createButton(String buttonName){
        JButton btn = new JButton(buttonName);
        btn.addActionListener(this);
        return btn;
    }

    private JPanel createControlPanel(){
        // tạo JPanel lblScore với giá trị ban đầu là 0
        lbScore = new JLabel("0");
        progressTime = new JProgressBar(0,100);
        progressTime.setValue(100);

        // tạo Panel chứa Score và Time
        JPanel panelLeft = new JPanel(new GridLayout(2,1,5,5));
        panelLeft.add(new JLabel("Score: "));
        panelLeft.add(new JLabel("Time: " ));

        JPanel panelCenter = new JPanel(new GridLayout(2,1,5,5));
        panelCenter.add(lbScore);
        panelCenter.add(progressTime);

        JPanel panelScoreAndTime = new JPanel(new BorderLayout(5,0));
        panelScoreAndTime.add(panelLeft,BorderLayout.WEST);
        panelScoreAndTime.add(panelCenter,BorderLayout.CENTER);

        // tạo Panel mới chứa PanelScoreAndTime và nút New Game
        JPanel panelControl = new JPanel(new BorderLayout(10,10));
        panelControl.setBorder(new EmptyBorder(10,3,5,3));
        panelControl.add(panelScoreAndTime, BorderLayout.CENTER);
        panelControl.add(btnNewGame = createButton("New Game"), BorderLayout.PAGE_END);

        // Set BorderLayout để panelControl xuất hiện ở đầu trang
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Good luck!!!"));
        panel.add(panelControl, BorderLayout.PAGE_START);

        return panel;
    }

    public void newGame(){
        time = max_time;
        graphicsPanel.removeAll();
        mainpanel.add(createGraphicsPanel(),BorderLayout.CENTER);
        mainpanel.validate();
        mainpanel.setVisible(true);
        lbScore.setText("0");
    }

    public void showDialogNewGame(String message, String title, int t) {
        pause = true;
        resume = false;
        int select = JOptionPane.showOptionDialog(null, message,title,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(select == 0){
            pause = false;
            newGame();
        }
        else {
            if(t == 1){
                System.exit(0);
            }
            else {
                resume = true;
            }
        }
    }

    private boolean pause = false;
    private boolean resume = false;

    public boolean getResume(){
        return resume;
    }

    public void setResume(boolean resume){
        this.resume = resume;
    }

    public boolean getPause(){
        return pause;
    }

    public void setPause(boolean pause){
        this.pause = pause;
    }


}
