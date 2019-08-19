/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogototal;

/**
 *
 * @author ediberto
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

/**
 *
 * @author ediberto
 */
public class peca extends JButton {

    private static int tamanho = 64;
    private Estado estado;

    public peca() {
        super();
        estado = Estado.VAZIO;
    }

 

   

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tamanho, tamanho);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        if (estado != Estado.VAZIO) {
            {
                if (estado == Estado.BRANCO) {
                    g2d.setColor(Color.WHITE);
                } else if (estado == Estado.PRETO) {
                    g2d.setColor(Color.BLACK);
                }
                g2d.fillOval(6, 6, getWidth() - 12, getHeight() - 12);
            }
            //Pintar a borda da peça
            g2d.setColor(Color.GRAY);
            g2d.drawOval(6, 6, getWidth() - 12, getHeight() - 12);
        }
    }
}