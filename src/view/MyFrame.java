package view;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

    MyPanel p;

    MyFrame() {
        p = new MyPanel();
        add(p);
        setBounds(400, 200, 800, 800);
        setVisible(true);
        validate();
        setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);
    }
}
