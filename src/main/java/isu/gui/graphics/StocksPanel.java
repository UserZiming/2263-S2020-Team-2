package isu.gui.graphics;

import isu.engine.GameEngine;
import isu.engine.HotelChain;
import isu.gui.uis.StockCardUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StocksPanel extends JPanel {


    public final static int WIDTH = 40;
    public final static int HEIGHT = 60;
    public final static int Y = 25;
    public final static int X = 5;
    public final static int SPACE_RECT = 55;
    public final static int SPACE_STRING = 10;
    public final static int SPACE = 8;
    GameEngine gameEngine;
    private StockCardUI[] stockCardUIS;

    public StocksPanel(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        stockCardUIS = new StockCardUI[GameEngine.MAX_STOCK_PURCHASE_COUNT];
        int w;
        for (int i = 0; i < stockCardUIS.length; i++){
            w = SPACE_RECT * i;
            stockCardUIS[i] = new StockCardUI(X + w, Y, WIDTH, HEIGHT, null);
        }

        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tryRemoveStock(e.getX(), e.getY());
            }
        });

    }

    public void addChainToCard(HotelChain chain){
        for (StockCardUI stockCardUI: stockCardUIS){
            if (stockCardUI.getStockCardChain() == null){
                stockCardUI.setStockCardChain(chain);
                System.out.println(chain.getColor());
                break;
            }
        }
        this.repaint();
    }

    public void tryRemoveStock(int x, int y){
        for (StockCardUI stockCardUI: stockCardUIS){
            if (stockCardUI.isOverlapping(x, y)){
                stockCardUI.setStockCardChain(null);
                break;
            }
        }
        this.repaint();
    }

    public void disableAll(){}

    protected void paintComponent(Graphics g){
        for (int i = 0; i < stockCardUIS.length; i++){
            stockCardUIS[i].paintComponent(g);
        }

        g.setColor(Color.BLUE);
        g.setFont(getFont().deriveFont(12.0f));
        g.drawString("+", getWidth()/3 - SPACE, getHeight()/2 + SPACE_STRING);
        g.drawString("+", getWidth()/3 + getWidth()/3 - SPACE, getHeight()/2 + SPACE_STRING);
        g.drawString("=", getWidth() - SPACE_STRING, getHeight()/2 + SPACE_STRING);
    }
}