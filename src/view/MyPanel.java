package view;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener{
	int x, y ;
	JButton onOff ;
	Timer time ;
	boolean flag ;
	boolean color ;
	File file = new File("src/resouce/music.wav") ;
	URL url = null;
	URI uri = null ;
	AudioClip clip = null;
	MyPanel() {
		setLayout(null);
		ImageIcon icon = new ImageIcon("src/resouce/OFF.png");
		icon.setImage(icon.getImage().getScaledInstance(50,50, 0)) ;
		onOff = new JButton() ;
		onOff.addActionListener(this) ;
		onOff.setIcon(icon) ;
		onOff.setBorder(null) ;
		onOff.setContentAreaFilled(false) ;
		onOff.setBounds(0, 0, 50, 50) ;
		add(onOff) ;
		flag = true ;
		color = true ;
		time = new Timer(300,this) ;
		time.stop() ;
		try {
			uri=file.toURI();	
			url = uri.toURL() ;
		} 
		catch (MalformedURLException e1) {}
		clip=Applet.newAudioClip(url);
	}
	public void paintComponent(Graphics g) {
		x = 380 ;
		y = 100 ;
		if(color) {
			ImageIcon image1 = new ImageIcon("src/resouce/2.png") ;
			g.drawImage(image1.getImage(), x-3, y-25, 28, 26, null) ;
		}
		else {
			ImageIcon image1 = new ImageIcon("src/resouce/1.png") ;
			g.drawImage(image1.getImage(), x-3, y-25, 25, 25, null) ;
		}
		Color red = new Color(255, 0, 0) ;
		Color yellow = new Color(255, 241, 0) ;		
		drawTree(1, 4, g) ;
		if(color) {
			drawDecoration(x+22, y-44, 6, yellow, g);
			drawDecoration(x, y-22, 8, red, g);
		}
		else {
			drawDecoration(x+22, y-44, 6, red, g);
			drawDecoration(x, y-22, 8, yellow, g);
		}
		x = 380-2*22;
		drawTree(3, 6, g) ;
		if(color) {
			drawDecoration(x+22, y-44, 10, yellow, g);
			drawDecoration(x, y-22, 12, red, g);
		}
		else {
			drawDecoration(x+22, y-44, 10, red, g);
			drawDecoration(x, y-22, 12, yellow, g);
		}
		x = 380-4*22;
		drawTree(5, 8, g) ;
		if(color) {
			drawDecoration(x+22, y-44, 14, yellow, g);
			drawDecoration(x, y-22, 16, red, g);
		}
		else {
			drawDecoration(x+22, y-44, 14, red, g);
			drawDecoration(x, y-22, 16, yellow, g);
		}
		x = 380-1*22 ;
		drwaRoot(g) ;
	} 
	void drawTree(int from, int to, Graphics g)	{
		Color c = new Color(9, 124, 37) ;
		g.setColor(c) ;
		for(int i=from; i<=to; i++) {
			for(int j=0; j<(i*2-1); j++) {
				g.fillRect(x, y, 20, 20);
				x += 22 ;
			}
			x = 380-i*22 ;
			y += 22 ;
		}
	}
	void drawDecoration(int tx, int ty, int num, Color c, Graphics g) {
		g.setColor(c) ;
		g.fillRoundRect(tx, ty, 18, 18, 18, 18) ;
		g.fillRoundRect(tx+num*22, ty, 18, 18, 18, 18) ;
	}
	void drwaRoot(Graphics g) {
		Color c = new Color(131, 78, 0) ;
		g.setColor(c);
		for(int i=0; i<4; i++) {	
			for(int j=0; j<3; j++) {
				g.fillRect(x, y, 20, 20);
				x += 22 ;
			}
			x = 380-1*22 ;
			y += 22 ;
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == onOff) {
			if(flag) {
				ImageIcon icon = new ImageIcon("src/resouce/ON.png");
				icon.setImage(icon.getImage().getScaledInstance(50,50, 0)) ;
				onOff.setIcon(icon) ;
				flag = false ;
				clip.loop();
				time.restart() ;
			}
			else {
				ImageIcon icon = new ImageIcon("src/resouce/OFF.png");
				icon.setImage(icon.getImage().getScaledInstance(50,50, 0)) ;
				onOff.setIcon(icon) ;
				flag = true ;
				time.stop() ;
				clip.stop() ;
			}
		}
		else if(e.getSource() == time) {
			repaint() ;
			color = !color ;
		}
	}
}
