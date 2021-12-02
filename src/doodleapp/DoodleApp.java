/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package doodleapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class DoodleApp extends JFrame{

    private final Point lineStart= new Point(0,0);
    private final int size = 26; // erasing width
    private DrawPanel panel;
    
    DoodleApp(){
    setTitle("Doodle");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addMouseListener(new Down());
    addMouseMotionListener (new Drag());
    panel = new DrawPanel();
    panel.setBackground(Color.blue);
    getContentPane().add(panel);
}
    
    public static void main(String[] a) {
       JFrame jf= new DoodleApp();
       jf.setSize(1000,1000);
       jf.setVisible(true);
    }
    
    class DrawPanel extends JPanel{
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.black);
            g.drawString("Left button Draws", 10,20);
            g.drawString("Left button then Shift Drag Bold", 10, 35);
            g.drawString("Double click clears", 10, 50);
                    
        }
    }
    
    class Down extends MouseAdapter{
        
        @Override
        public void mousePressed(MouseEvent e){
            int x = e.getX(), y = e.getY();
            if(e.isShiftDown())
                setForeground(panel.getBackground());
            else
                setForeground(Color.black);
            if(e.getClickCount()== 2)// double click
                panel.repaint();
            else
                lineStart.move(x,y);
        }
    }
    
    class Drag extends MouseMotionAdapter{
        
        public void mouseDragged(MouseEvent e){
            int x = e.getX(), y = e.getY();
            Graphics g = panel.getGraphics();
            if(e.isShiftDown())
                g.fillOval(x-size/2, y-size/2, size, size);
            else{
                g.drawLine(lineStart.x, lineStart.y , x, y);
                lineStart.move(x, y);
            }
        }
    }
    
}