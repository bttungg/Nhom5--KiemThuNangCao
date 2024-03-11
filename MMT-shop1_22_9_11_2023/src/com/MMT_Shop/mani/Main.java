package com.MMT_Shop.mani;

import com.MMT_Shop.Chien.Form_Hoa_Don;
import com.MMT_Shop.Dao.LoginDAO;
import com.MMT_Shop.Lam.Form_Voucher;
import com.MMT_Shop.Tung.Form_Khach_Hang;
import com.MMT_Shop.event.EventMenuSelect;
import com.MMT_Shop.form.Form_Ban_Hang;
import com.MMT_Shop.form.Form_San_Pham;
import com.MMT_Shop.ngan.Form_Nhan_Vien;
import com.MMT_Shop.thongKe.Form_Thong_Ke;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        menul.initMoving(Main.this);
        menul.addEventMenuSelect(new EventMenuSelect() {

            @Override
            public void selected(int index) {
                System.out.println("selcted index: " + index);
                if (index == 0) {
                    setForm(new Form_Ban_Hang());
                } else if (index == 1) {
                    setForm(new Form_Hoa_Don(Main.this));
                } else if (index == 2) {
                    setForm(new Form_San_Pham());
                } else if (index == 3) {
                    setForm(new Form_Voucher());
                } else if (index == 4) {
                    setForm(new Form_Thong_Ke());
                } else if (index == 5) {
                    setForm(new Form_Nhan_Vien());
                } else if (index == 6) {
                    setForm(new Form_Khach_Hang());
                } else if (index == 7) {
                    int lc = JOptionPane.showConfirmDialog(null, "bạn có muốn đăng xuất", "THông báo", JOptionPane.YES_NO_OPTION);
                    if (lc != JOptionPane.YES_OPTION) {
                        return;
                    }
                    LoginDAO.lg = null;
                    Main.this.dispose();
                    new Login(null, true).setVisible(true);

                }

            }
        });
        //set form mặc định
        setForm(new Form_Ban_Hang());

    }

    public void setForm(JComponent com) {
        mainPle.removeAll();
        mainPle.add(com);
        mainPle.repaint();
        mainPle.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.MMT_Shop.swing.PanelBorder();
        menul = new com.MMT_Shop.component.Menu();
        mainPle = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1320, 760));

        panelBorder1.setPreferredSize(new java.awt.Dimension(1500, 850));

        menul.setPreferredSize(new java.awt.Dimension(180, 380));

        mainPle.setOpaque(false);
        mainPle.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menul, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPle, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menul, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addComponent(mainPle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPle;
    private com.MMT_Shop.component.Menu menul;
    private com.MMT_Shop.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
