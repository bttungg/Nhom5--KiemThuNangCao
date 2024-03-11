package com.MMT_Shop.thongKe;

import com.MMT_Shop.thongKe.ThongKeService;
import com.MMT_Shop.EnTiTy.SanPhamCT;
import com.MMT_Shop.model.Model_Card_Thong_Tin;
import com.MMT_Shop.swing.ScrollBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Form_Thong_Ke extends javax.swing.JPanel {

    ThongKeService sv = new ThongKeService();
    int KH = 0;
    float DT = 0;
    int TC = 0;
    int DH = 0;
    public static String luaChon;
    private DefaultTableModel model = new DefaultTableModel();
    private ArrayList<SanPhamCT> data = new ArrayList<>();

    public Form_Thong_Ke() {
        initComponents();
        byMonth();
        card();
        addRowTableSPCT();

    }

    private void card() {
        card1.setData(new Model_Card_Thong_Tin(new ImageIcon(getClass().getResource("/com/MMT_Shop/icon/EuroMoney.png")), "Doanh thu", String.valueOf(DT), "VND"));
        card2.setData(new Model_Card_Thong_Tin(new ImageIcon(getClass().getResource("/com/MMT_Shop/icon/Receipt.png")), "Số hóa đơn", String.valueOf(TC), ""));
      //  card3.setData(new Model_Card_Thong_Tin(new ImageIcon(getClass().getResource("/com/MMT_Shop/icon/WriteOff Invoice.png")), "Số hóa đơn hủy", String.valueOf(DH), ""));
        card4.setData(new Model_Card_Thong_Tin(new ImageIcon(getClass().getResource("/com/MMT_Shop/icon/Minus1Year.png")), "Khách hàng", String.valueOf(KH), ""));

    }

    public void byMonth() {
        int selectedMonth = mcThang.getMonth();
      //  DH = sv.countHuybm(selectedMonth + 1);
        KH = sv.countKHbm(selectedMonth + 1);
        TC = sv.countTCbm(selectedMonth + 1);
        DT = sv.countDTbm(selectedMonth + 1);

        ArrayList<TK> tke = sv.BarChartTest2(selectedMonth + 1);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TK ke : tke) {
            dataset.setValue(ke.getDoanhthu(), "Số tiền", String.valueOf("Ngày " + ke.getThang()));
        }

        JFreeChart chart = ChartFactory.createBarChart("Doanh thu của Shop", "Tháng" + " " + String.valueOf(selectedMonth + 1), "Số tiền",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(243, 176, 245);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        barchart.removeAll();
        barchart.add(barpChartPanel, BorderLayout.CENTER);
        barchart.validate();

    }

    public void byYear() {

        int selectedYear = ycNam.getYear();
       // DH = sv.countHuyby(selectedYear);
        KH = sv.countKHby(selectedYear);
        TC = sv.countTCby(selectedYear);
        DT = sv.countDTby(selectedYear);

        ArrayList<TK> tke = sv.BarChartTest(selectedYear);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TK ke : tke) {
            dataset.setValue(ke.getDoanhthu(), "Số tiền", String.valueOf("Tháng " + ke.getThang()));
        }

        JFreeChart chart = ChartFactory.createBarChart("Doanh thu của Shop", "Năm" + " " + String.valueOf(selectedYear), "Số tiền",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(243, 176, 245);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        barchart.removeAll();
        barchart.add(barpChartPanel, BorderLayout.CENTER);
        barchart.validate();

    }

    public void byTime() {
        Date selectedDate1 = dcTG1.getDate();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf1.format(selectedDate1);
        System.out.println(formattedDate);

        Date selectedDate2 = dcTG2.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate2 = sdf2.format(selectedDate2);
        System.out.println(formattedDate2);

        ArrayList<TK> tke = sv.BarChartTest3(formattedDate, formattedDate2);

       // DH = sv.countHuybt(formattedDate, formattedDate2);
        KH = sv.countKHbt(formattedDate, formattedDate2);
        TC = sv.countTCbt(formattedDate, formattedDate2);
        DT = sv.countDTbt(formattedDate, formattedDate2);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (TK ke : tke) {
            dataset.setValue(ke.getDoanhthu(), "Số tiền", String.valueOf(ke.getThang()));
        }
        JFreeChart chart = ChartFactory.createBarChart("Doanh thu của Shop", "Từ" + " " + String.valueOf(formattedDate) + " đến " + String.valueOf(formattedDate2), "Số tiền",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(243, 176, 245);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        barchart.removeAll();
        barchart.add(barpChartPanel, BorderLayout.CENTER);
        barchart.validate();
    }

    private void addRowTableSPCT() {

        model = (DefaultTableModel) tblTopSP.getModel();
        model.setRowCount(0);
        srcSPCT.setVerticalScrollBar(new ScrollBar());
        data = sv.selectSanPhamCT();
        for (int i = data.size() - 1; i >= 0; i--) {
            SanPhamCT sp = data.get(i);
            tblTopSP.addRow(new Object[]{tblTopSP.getRowCount() + 1, sp.getTenSP(),
                sp.getThuongHieu(), sp.getKichThuoc(), sp.getChatLieu(), sp.getMauSac(), sp.getSoLuong(), sp.getGia()});

        }

    }

    

   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.MMT_Shop.component.Card_Thong_Tin();
        card2 = new com.MMT_Shop.component.Card_Thong_Tin();
        card4 = new com.MMT_Shop.component.Card_Thong_Tin();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        ycNam = new com.toedter.calendar.JYearChooser();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        mcThang = new com.toedter.calendar.JMonthChooser();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        dcTG1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dcTG2 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        panelBorder3 = new com.MMT_Shop.swing.PanelBorder();
        barchart = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        panelBorder1 = new com.MMT_Shop.swing.PanelBorder();
        tblSPCT = new javax.swing.JPanel();
        srcSPCT = new javax.swing.JScrollPane();
        tblTopSP = new com.MMT_Shop.swing.Table();

        setBackground(new java.awt.Color(255, 255, 255));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(78, 84, 200));
        card1.setColor2(new java.awt.Color(143, 148, 251));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(28, 146, 210));
        card2.setColor2(new java.awt.Color(212, 211, 221));
        panel.add(card2);

        card4.setColor1(new java.awt.Color(239, 239, 187));
        card4.setColor2(new java.awt.Color(135, 250, 165));
        panel.add(card4);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Lọc tneo năm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 60));

        ycNam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ycNamPropertyChange(evt);
            }
        });

        jButton5.setText("OK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(ycNam, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addComponent(ycNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.getAccessibleContext().setAccessibleName("Lọc theo năm ");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Lọc tneo tháng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(200, 60));

        mcThang.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                mcThangPropertyChange(evt);
            }
        });

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mcThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(mcThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel6);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Lọc theo khoảng thời gian", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(400, 60));

        dcTG1.setDateFormatString("yyyy MM dd");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Từ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Đến");

        dcTG2.setDateFormatString("yyyy-MM-dd");
        dcTG2.setMaxSelectableDate(new java.util.Date(253370743265000L));
        dcTG2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcTG2MouseClicked(evt);
            }
        });
        dcTG2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcTG2PropertyChange(evt);
            }
        });

        jButton4.setText("OK");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dcTG1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dcTG2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addComponent(jLabel2)
                    .addComponent(dcTG2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(dcTG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setPreferredSize(new java.awt.Dimension(90, 50));
        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 3));
        jPanel1.add(jPanel7);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        barchart.setBackground(new java.awt.Color(255, 255, 255));
        barchart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        barchart.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barchart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barchart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel2);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        tblSPCT.setBackground(new java.awt.Color(255, 255, 255));

        srcSPCT.setBackground(new java.awt.Color(255, 255, 255));
        srcSPCT.setBorder(null);
        srcSPCT.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        tblTopSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên ", "Thương hiệu ", "Kích thước", "Chất liệu", "Màu sắc", "Số lượng", "Đơn giá "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        srcSPCT.setViewportView(tblTopSP);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tblSPCT, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE))
                    .addComponent(srcSPCT))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(tblSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(srcSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Hàng bán được ", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jTabbedPane1)
            .addComponent(panel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dcTG2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcTG2MouseClicked

    }//GEN-LAST:event_dcTG2MouseClicked

    private void ycNamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ycNamPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_ycNamPropertyChange

    private void mcThangPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_mcThangPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_mcThangPropertyChange

    private void dcTG2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcTG2PropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_dcTG2PropertyChange

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        byYear();
        card();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        byMonth();
        card();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        byTime();
        card();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barchart;
    private com.MMT_Shop.component.Card_Thong_Tin card1;
    private com.MMT_Shop.component.Card_Thong_Tin card2;
    private com.MMT_Shop.component.Card_Thong_Tin card4;
    private com.toedter.calendar.JDateChooser dcTG1;
    private com.toedter.calendar.JDateChooser dcTG2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JMonthChooser mcThang;
    private javax.swing.JLayeredPane panel;
    private com.MMT_Shop.swing.PanelBorder panelBorder1;
    private com.MMT_Shop.swing.PanelBorder panelBorder3;
    private javax.swing.JScrollPane srcSPCT;
    private javax.swing.JPanel tblSPCT;
    private com.MMT_Shop.swing.Table tblTopSP;
    private com.toedter.calendar.JYearChooser ycNam;
    // End of variables declaration//GEN-END:variables
}
