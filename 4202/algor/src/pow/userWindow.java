package pow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class userWindow extends JFrame implements ActionListener  {
    //draw the interface
    JFrame frame = new JFrame();
    JPanel jPanel=new JPanel();
    JTextField x = new JTextField(5);
    JTextField y = new JTextField(5);
    JTextField startx = new JTextField(5);
    JTextField starty = new JTextField(5);
    JTextField endx = new JTextField(5);
    JTextField endy = new JTextField(5);
    JButton add = new JButton("add");
    JButton runalgor = new JButton("run algorithm");
    JButton save = new JButton("save");
    public userWindow(){
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init(){
        frame.setTitle("surthland-hogeman Algorithm");
        frame.setSize(500,500); // default size is 0,0
        frame.setLocation(10,200); // default is 0,0 (top left corner)
        jPanel.add(new JLabel("Please insert points of polygon"));
        jPanel.add(new JLabel("x: "));
        jPanel.add(x);
        jPanel.add(new JLabel("y: "));
        jPanel.add(y);
        jPanel.add(add);
        add.addActionListener(this);
        jPanel.add(new JLabel("Set the area you want to cut"));
        jPanel.add(new JLabel("from point x: "));
        jPanel.add(startx);
        jPanel.add(new JLabel("y: "));
        jPanel.add(starty);
        jPanel.add(new JLabel("to point x: "));
        jPanel.add(endx);
        jPanel.add(new JLabel("y: "));
        jPanel.add(endy);
        jPanel.add(save);
        jPanel.add(runalgor);
        jPanel.add(new Graphs());
        frame.add(jPanel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e) {

    }
    class Graphs extends JComponent{
        private static final long serialVersionUID = 1L;

        Graphs() {
            setPreferredSize(new Dimension(500, 100));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.fillRect(200, 62, 30, 10);
        }
    }
}
