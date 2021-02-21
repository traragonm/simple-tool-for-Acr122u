/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Frame.MainFrame;

/**
 *
 * @author minh
 */
public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            MainFrame mf = new MainFrame();
            mf.setLocationRelativeTo(null);
            mf.setVisible(true);
        });
        
    }
}
