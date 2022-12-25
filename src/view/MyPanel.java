package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class MyPanel extends JPanel implements ActionListener {

    // 图片、音乐路径
    static final String MUSIC = "src/resouce/music.wav";
    static final String STAR_SHINE = "src/resouce/STAR_SHINE.png";
    static final String STAR_NOT_SHINE = "src/resouce/STAR_NOT_SHINE.png";
    static final String ON = "src/resouce/ON.png";
    static final String OFF = "src/resouce/OFF.png";

    int x, y;
    JButton onOff;
    Timer time;
    boolean flag;
    boolean color;
    File file = new File(MUSIC);
    URL url = null;
    URI uri = null;
    //   since jdk9 : Clip (jdk9 before : AudioClip)
    Clip clip = null;
    AudioInputStream ais = null;

    MyPanel() throws Exception {
        setLayout(null);
        ImageIcon icon = new ImageIcon(OFF);
        icon.setImage(icon.getImage().getScaledInstance(50, 50, 0));
        onOff = new JButton();
        onOff.addActionListener(this);
        onOff.setIcon(icon);
        onOff.setBorder(null);
        onOff.setContentAreaFilled(false);
        onOff.setBounds(0, 0, 50, 50);
        add(onOff);
        flag = true;
        color = true;
        time = new Timer(300, this);
        time.stop();
        try {
            uri = file.toURI();
            url = uri.toURL();
        } catch (MalformedURLException e1) {
            System.out.println(e1);
        }
        clip = AudioSystem.getClip();
        ais = AudioSystem.getAudioInputStream(file);


    }

    public void paintComponent(Graphics g) {
        x = 380;
        y = 100;
        if (color) {
            ImageIcon image1 = new ImageIcon(STAR_NOT_SHINE);
            g.drawImage(image1.getImage(), x - 3, y - 25, 28, 26, null);
        } else {
            ImageIcon image1 = new ImageIcon(STAR_SHINE);
            g.drawImage(image1.getImage(), x - 3, y - 25, 25, 25, null);
        }
        Color red = new Color(255, 0, 0);
        Color yellow = new Color(255, 241, 0);
        drawTree(1, 4, g);
        if (color) {
            drawDecoration(x + 22, y - 44, 6, yellow, g);
            drawDecoration(x, y - 22, 8, red, g);
        } else {
            drawDecoration(x + 22, y - 44, 6, red, g);
            drawDecoration(x, y - 22, 8, yellow, g);
        }
        x = 380 - 2 * 22;
        drawTree(3, 6, g);
        if (color) {
            drawDecoration(x + 22, y - 44, 10, yellow, g);
            drawDecoration(x, y - 22, 12, red, g);
        } else {
            drawDecoration(x + 22, y - 44, 10, red, g);
            drawDecoration(x, y - 22, 12, yellow, g);
        }
        x = 380 - 4 * 22;
        drawTree(5, 8, g);
        if (color) {
            drawDecoration(x + 22, y - 44, 14, yellow, g);
            drawDecoration(x, y - 22, 16, red, g);
        } else {
            drawDecoration(x + 22, y - 44, 14, red, g);
            drawDecoration(x, y - 22, 16, yellow, g);
        }
        x = 380 - 1 * 22;
        drwaRoot(g);
    }

    void drawTree(int from, int to, Graphics g) {
        Color c = new Color(9, 124, 37);
        g.setColor(c);
        for (int i = from; i <= to; i++) {
            for (int j = 0; j < (i * 2 - 1); j++) {
                g.fillRect(x, y, 20, 20);
                x += 22;
            }
            x = 380 - i * 22;
            y += 22;
        }
    }

    void drawDecoration(int tx, int ty, int num, Color c, Graphics g) {
        g.setColor(c);
        g.fillRoundRect(tx, ty, 18, 18, 18, 18);
        g.fillRoundRect(tx + num * 22, ty, 18, 18, 18, 18);
    }

    void drwaRoot(Graphics g) {
        Color c = new Color(131, 78, 0);
        g.setColor(c);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                g.fillRect(x, y, 20, 20);
                x += 22;
            }
            x = 380 - 1 * 22;
            y += 22;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == onOff) {
            if (flag) {
                ImageIcon icon = new ImageIcon(ON);
                icon.setImage(icon.getImage().getScaledInstance(50, 50, 0));
                onOff.setIcon(icon);
                try {
                    clip.open(ais);
                    clip.start();
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
                flag = false;
                clip.setLoopPoints(0, -1);
                time.restart();
            } else {
                ImageIcon icon = new ImageIcon(OFF);
                icon.setImage(icon.getImage().getScaledInstance(50, 50, 0));
                onOff.setIcon(icon);
                flag = true;
                time.stop();
                clip.stop();
            }
        } else if (e.getSource() == time) {
            repaint();
            color = !color;
        }
    }
}
