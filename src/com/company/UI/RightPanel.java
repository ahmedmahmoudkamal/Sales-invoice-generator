package com.company.UI;

import com.company.CSVHandler;
import com.company.models.Invoice;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel implements ActionListener {
    DefaultTableModel tableModel2 ;
    CSVHandler csvHandler = new CSVHandler();
    float totalPrice ;
    String[] emptyRow = {"","","","",""};

    // Declarations
JPanel invoiceDetails;
    JPanel invoiceNumberPanel;
            JLabel invoiceNumberLabel;JLabel invoiceNumber;
    JPanel invoiceDatePanel;
            JLabel invoiceDateLabel;JTextField invoiceDate;
    JPanel customerNamePanel;
            JLabel customerNameLabel;JTextField customerName;
    JPanel invoiceTotalPanel;
            JLabel invoiceTotalLabel;JLabel invoiceTotal;


     JTable items;
    private String[] headers = {"invoiceNumber","itemName","itemPrice","Count","itemTotal"};
    String[][] data;

    JPanel bottomPanel;
        JButton saveEdit;JButton cancelEdit;



    public RightPanel() {

        setLayout(new BorderLayout(2, 2));


        //Declarations
        invoiceDetails = new JPanel();
//        invoice number panel
        invoiceNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceNumberLabel = new JLabel("invoice Number     ");
        invoiceNumber = new JLabel("0");
        invoiceNumberPanel.add(invoiceNumberLabel);
        invoiceNumberPanel.add(invoiceNumber);
        invoiceDetails.add(invoiceNumberPanel);


//            invoiceDatePanel
        invoiceDatePanel = new JPanel();
        invoiceDateLabel = new JLabel("invoice Date        ");
        invoiceDate = new JTextField("");
        invoiceDate.setColumns(33);
        invoiceDatePanel.add(invoiceDateLabel);
        invoiceDatePanel.add(invoiceDate);
        invoiceDetails.add(invoiceDatePanel);

//Customer Name
        customerNamePanel = new JPanel();
        customerNameLabel = new JLabel("customer name    ");
        customerName = new JTextField("");
        customerName.setColumns(33);
        customerNamePanel.add(customerNameLabel);
        customerNamePanel.add(customerName);
        invoiceDetails.add(customerNamePanel);


//        Invoice Total
        invoiceTotalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoiceTotalLabel = new JLabel("invoice Total :      ");
        invoiceTotal = new JLabel("0");
        invoiceTotalPanel.add(invoiceTotalLabel);
        invoiceTotalPanel.add(invoiceTotal);
        invoiceDetails.add(invoiceTotalPanel);


        invoiceDetails.setLayout(new GridLayout(5, 1));
        add(invoiceDetails, BorderLayout.NORTH);


//table

        data = new String[5][headers.length];
        items = new JTable(data,headers);
        tableModel2 = new DefaultTableModel(data,headers);
        add(items);
        items.setModel(tableModel2);
        items.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableModel2.setColumnIdentifiers(headers);




//Bottom Buttons

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        saveEdit = new JButton("Save");
        saveEdit.addActionListener(this);
        saveEdit.setActionCommand("save_items");

        cancelEdit = new JButton("Cancel");
        bottomPanel.add(saveEdit);
        bottomPanel.add(cancelEdit);
        add(bottomPanel, BorderLayout.SOUTH);
  /*
        invoiceDetails.add(invoiceNumberLabel);
        invoiceDetails.add(invoiceNumber);
        invoiceDetails.add(invoiceDateLabel);
        invoiceDetails.add(invoiceDate);
        invoiceDetails.add(customerNameLabel);
        invoiceDetails.add(customerName);
        invoiceDetails.add(invoiceTotalLabel);
        invoiceDetails.add(invoiceTotal);
*/

        //        invoicesDetails

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "save_items") {
            if (tableModel2.getValueAt(tableModel2.getRowCount() -1,0).toString() != "" &&
                    tableModel2.getValueAt(tableModel2.getRowCount()-1,1).toString() != "" &&
                    tableModel2.getValueAt(tableModel2.getRowCount()-1,2).toString() != "" &&
                    tableModel2.getValueAt(tableModel2.getRowCount()-1,3).toString() != "" &&
                    tableModel2.getValueAt(tableModel2.getRowCount()-1,4).toString() != ""

            ) {
//                tableModel2.removeRow(tableModel2.getRowCount() - 1);
                csvHandler.setItems(tableModel2);
                tableModel2.addRow(emptyRow);
            }
        }
    }



        public void modifyPanel(Invoice invoice) {

            tableModel2.setRowCount(0);
            totalPrice = 0;

            invoiceNumber.setText(Integer.toString(invoice.getId()));
            invoiceDate.setText(invoice.getDate());
            customerName.setText(invoice.getCustomer());
            invoiceTotal.setText(Float.toString(invoice.getTotalPrice()));
            invoice.setTotalPrice(0);
            for (int i = 0 ; i < invoice.getItems().size()  ; i++ ) {
                try {
                    String[] row = new String[5];
                    row[0] = Integer.toString(invoice.getId());
                    row[1] = invoice.getItems().get(i).getName();
                    row[2] = Float.toString(invoice.getItems().get(i).getPrice());
                    row[3] = String.valueOf(invoice.getItems().get(i).getTotalCount());
                    row[4] = Float.toString(invoice.getItems().get(i).getPrice() * invoice.getItems().get(i).getTotalCount());
                    totalPrice = totalPrice + Float.parseFloat(row[4]);

                    tableModel2.addRow(row);
                }catch(Exception e){

                }
            }

            tableModel2.addRow(emptyRow);
             invoiceTotal.setText(Float.toString(totalPrice));



        }







    }


