import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {
	//private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MinesweeperPanel myPanel = (MinesweeperPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//right mouse button
			Component r = e.getComponent();
			while (!(r instanceof JFrame)) {
				r = r.getParent();
				if (r == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame) r;
			MinesweeperPanel myPanel1 = (MinesweeperPanel) myFrame1.getContentPane().getComponent(0);
			Insets myInsets1 = myFrame1.getInsets();
			int x2 = myInsets1.left;
			int y2 = myInsets1.top;
			e.translatePoint(-x2, -y2);
			int x3 = e.getX();
			int y3 = e.getY();
			myPanel1.x = x3;
			myPanel1.y = y3;
			myPanel1.mouseDownGridXr = myPanel1.getGridX(x3, y3);
			myPanel1.mouseDownGridYr = myPanel1.getGridY(x3, y3);
			myPanel1.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MinesweeperPanel myPanel = (MinesweeperPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			
			
			if ((myPanel.mouseDownGridX >= 0) && (myPanel.mouseDownGridX < 9) && (myPanel.mouseDownGridY >= 0) && (myPanel.mouseDownGridY < 9) && (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == Color.GRAY)) {							
				myPanel.surroundingMines(myPanel.mouseDownGridX, myPanel.mouseDownGridY);
				System.out.println(myPanel.surroundingMines(myPanel.mouseDownGridX,myPanel.mouseDownGridY));
				//myPanel.showAdjacentPanels(myPanel.mouseDownGridX,myPanel.mouseDownGridY);
				myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.WHITE;
				if (myPanel.panelValue[myPanel.mouseDownGridX][myPanel.mouseDownGridY]== 1) {
					myPanel.showMines();
					JOptionPane.showMessageDialog(null, "Presionaste una mina");
				}
			}else if((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)){
				
			}
			
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == 0) || (gridY == 0)) {
							//On the left column and on the top row... do nothing
						} 
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		
			Component r = e.getComponent();
			while (!(r instanceof JFrame)) {
				r = r.getParent();
				if (r == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame)r;
			MinesweeperPanel myPanel1 = (MinesweeperPanel) myFrame1.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets1 = myFrame1.getInsets();
			int x2 = myInsets1.left;
			int y2 = myInsets1.top;
			e.translatePoint(-x2, -y2);
			int x3 = e.getX();
			int y3 = e.getY();
			myPanel1.x = x3;
			myPanel1.y = y3;
//			int gridX1 = myPanel1.getGridX(x3, y3);
//			int gridY1 = myPanel1.getGridY(x3, y3);
			if ((myPanel1.mouseDownGridXr >= 0) && (myPanel1.mouseDownGridYr < 9) && (myPanel1.mouseDownGridYr >= 0) && (myPanel1.mouseDownGridYr < 9)) {
				myPanel1.colorArray[myPanel1.mouseDownGridXr][myPanel1.mouseDownGridYr] = Color.RED;
				if(myPanel1.panelValue[myPanel1.mouseDownGridXr][myPanel1.mouseDownGridYr] == 1){
					myPanel1.panelValue[myPanel1.mouseDownGridXr][myPanel1.mouseDownGridYr] = 0;
				}
			}else{
				
			}
			myPanel1.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
		
	}
	
	

}
	