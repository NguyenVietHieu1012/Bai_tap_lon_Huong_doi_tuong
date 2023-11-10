package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Button extends JPanel implements ActionListener {
    private int hang, cot,  bound = 2, size = 50;
    private JButton[][] btn;
    private Controller controller;
    private Color backGroundColor = Color.RED;
    private MainFrame frame;

    private JButton createButton(String action){
        JButton btn = new JButton();
        btn.setActionCommand(action);
        btn.setBorder(null);
        btn.addActionListener(this);
        return btn;
    }
    private Icon getIcon(int index) {
        Image image = new ImageIcon(getClass().getResource("/image/" + index + ".png")).getImage();
        Icon icon = new ImageIcon(image.getScaledInstance(48,48,image.SCALE_SMOOTH));
        return icon;
    }

    private void addArrayButton(){
        btn = new JButton[hang][cot];
        for(int i = 0; i < hang; i++){
            for(int j = 0; j < cot; j++) {
                btn[i][j] = createButton(i + "," + j);
                Icon icon = getIcon(controller.getMatrix()[i][j]);
                btn[i][j].setIcon(icon);
                add(btn[i][j]);
            }
        }
    }
    public void newGame(){
        controller = new Controller(this.hang, this.cot);
        addArrayButton();
    }

    private Point p1 = null;
    private Point p2 = null;
    private PointLine line;
    private int score = 0;
    private int item;
    public Button(MainFrame frame, int hang, int cot){
        this.frame = frame;
        this.hang = hang;
        this.cot = cot;

        item = hang * cot / 2; // Tổng số cặp icon

        setLayout(new GridLayout(hang,cot,bound,bound));
        setBackground(backGroundColor);
        setPreferredSize(new Dimension((size + bound) * cot, (size + bound) * hang));
        setBorder(new EmptyBorder(10,10,10,10));
        setAlignmentY(JPanel.CENTER_ALIGNMENT);

        newGame();
    }

    private void setDisable (JButton btn){
        btn.setIcon(null);
        btn.setBackground(backGroundColor);
        btn.setEnabled(false);
    }

    private void execute(Point p1, Point p2){
        System.out.println("Delete");
        setDisable(btn[p1.x][p1.y]);
        setDisable(btn[p2.x][p2.y]);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String btnIndex = e.getActionCommand();
        int indexDot = btnIndex.lastIndexOf(",");
        int x = Integer.parseInt(btnIndex.substring(0,indexDot));
        int y = Integer.parseInt(btnIndex.substring(indexDot + 1,btnIndex.length()));
        if(p1 == null){
            p1 = new Point(x,y);
            btn[p1.x][p1.y].setBorder(new LineBorder(Color.YELLOW));
        }
        else {
            p2 = new Point(x,y);
            System.out.println("(" + p1.x + "," + p1.y + ")" + " --> " + "(" + p2.x + "," + p2.y + ")");
            line = controller.checkTwoPoint(p1,p2);
            if (line != null){
                System.out.println("Line != null");
                controller.getMatrix()[p1.x][p1.y] = 0;
                controller.getMatrix()[p2.x][p2.y] = 0;
                controller.showMatrix();
                execute(p1,p2);
                line = null;
                score += 10;
                item --;
                frame.time++;
                frame.lbScore.setText(score + "");
            }
            btn[p1.x][p1.y].setBorder(null);
            p1 = null;
            p2 = null;
            System.out.println("Done");
            if(item == 0){
                frame.showDialogNewGame("Bạn đã thắng ! \n Bạn có muốn chơi lại ?","WIN",1);
            }
        }
    }
}
