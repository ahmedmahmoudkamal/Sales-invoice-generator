package com.company.UI;

import com.company.CSVHandler;
import com.company.models.Invoice;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem menuOpen;
    private JMenuItem menuSave;
    LeftPanel leftPanel;
    CSVHandler csv;
    public MainFrame(){

//        adding Menu Bar
        menuBar = new JMenuBar();
        fileMenu = new JMenu("file");
        menuOpen = new JMenuItem("load");
        menuSave = new JMenuItem("save");
        menuSave.addActionListener(this);
        fileMenu.add(menuOpen);
        fileMenu.add(menuSave);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
//         End Of Menu
//        Setting The Layout into 2 Columns
        setTitle("SalesInvoiceGenerator");
        setSize(1000,600);
        GridLayout layout = new GridLayout(1,2);
        layout.setVgap(5);
        layout.setHgap(5);
        setLayout(layout);
        leftPanel = new LeftPanel();
       RightPanel rightPanel = new RightPanel();

        leftPanel.invoiceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                try {
                    int id = Integer.parseInt(leftPanel.invoiceTable.getValueAt(leftPanel.invoiceTable.getSelectedRow(), 0).toString());
                    Invoice invoice = leftPanel.csvHandler.getInvoiceWithItems(id);
                    rightPanel.modifyPanel(invoice);
                }catch(Exception e) {
                    System.out.println("Save This State First to display it in the right side");
                }
    }





        });


        add(leftPanel);
        add(rightPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var x = leftPanel.tableModel;
        csv = new CSVHandler();
        csv.setInvoices(x);

    }
}
