package views.menu.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class TImage extends JLabel  implements  ComponentListener{
    private ImageIcon srcIcon;
    private ImageIcon cloneIcon;
    public TImage(){
        this.setVerticalAlignment(JLabel.CENTER);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.addComponentListener(this);
    }
    public TImage(ImageIcon icon){
        this();
        setSrcIcon(icon);
    }

    public ImageIcon getSrcIcon() {
        return srcIcon;
    }

    public ImageIcon getCloneIcon() {
        return cloneIcon;
    }
    public void setSrcIcon(ImageIcon icon){
        if(icon==srcIcon)return;
        srcIcon = icon;
        cloneIcon = new ImageIcon(srcIcon.getImage());
        this.setIcon(null);
        new Thread(()->{
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	fitToJLabel();
        }).start();
    }
    private void fitToJLabel(){
        if(srcIcon==null)return;
        int wight1 = Math.max(1,getWidth());
        int height1 = Math.max(1,(int) (getWidth()/1.0/ srcIcon.getIconWidth()* srcIcon.getIconHeight()));
        int width2 = Math.max(1,(int) (getHeight()/1.0/ srcIcon.getIconHeight()* srcIcon.getIconWidth()));
        int height2 = Math.max(1,getHeight());
        cloneIcon.getImage().flush();
        cloneIcon.setImage(srcIcon.getImage().getScaledInstance(Math.min(wight1,width2),Math.min(height1,height2), Image.SCALE_DEFAULT));
        this.setIcon(cloneIcon);
        System.out.println("test");
    }
    @Override
    public void componentResized(ComponentEvent e) {
        //resize
        fitToJLabel();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

}