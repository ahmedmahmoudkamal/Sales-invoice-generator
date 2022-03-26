package com.company.UI;
import com.company.CSVHandler;
import com.company.models.Invoice;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class LeftPanel extends JPanel implements ActionListener {
//    Declaration
    JButton createNewInvoice;
    JButton deleteInvoice;
    JLabel forInvoicesTable;
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable invoiceTable;
    JPanel bottomPanel;
    private String[] cols = {"No.","Date","Customer","Total"};
    List<Invoice> invoices;
    String[][] data;
    public CSVHandler csvHandler;

    public void getDataOfCSV(){

    }

    void projectData(List<Invoice> invoices,String[][] data){
        for (int i = 0 ; i < invoices.size() ; i++ ) {
            String[] row = new String[4];
            row[0] = String.valueOf(invoices.get(i).getId());
            row[1] = invoices.get(i).getDate();
            row[2] = invoices.get(i).getCustomer();
            row[3] = String.valueOf(invoices.get(i).getTotalPrice());
    tableModel.addRow(row);

        }

    }

    public LeftPanel(){

        createNewInvoice = new JButton("Create New Invoice");
        createNewInvoice.addActionListener(this);
        createNewInvoice.setActionCommand("createInvoice");
        deleteInvoice = new JButton("Delete Invoice");
        deleteInvoice.addActionListener(this);
        deleteInvoice.setActionCommand("deleteInvoice");
        bottomPanel = new JPanel();
        tableModel.setColumnIdentifiers(cols);

//      End Of INITIALIZING
//        project Data to table


         csvHandler = new CSVHandler();

        invoices = csvHandler.getInvoices();
        data = new String[invoices.size()+1][4];
//        End Fetch Data
//        initializing Components
        forInvoicesTable = new JLabel("Invoices Table");
        invoiceTable = new JTable(data,cols);
        invoiceTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        invoiceTable.setModel(tableModel);
        projectData(invoices,data);



//        End projecting Data
//        set layout
        setLayout(new BorderLayout(2,2));
        add(forInvoicesTable,BorderLayout.NORTH);
        add(new JScrollPane(invoiceTable),BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        bottomPanel.add(createNewInvoice);
        bottomPanel.add(deleteInvoice);

//        end layout



//        row selection event
//        invoiceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//            public void valueChanged(ListSelectionEvent event) {
//                int id = Integer.parseInt(invoiceTable.getValueAt(invoiceTable.getSelectedRow(), 0).toString());
//                Invoice invoice = csvHandler.getInvoiceWithItems(id);
//
//
//
//            }
//
//        });




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "createInvoice"){

        System.out.println(invoices.size());
        if(data[invoices.size()][0] == null ) {
//            int id = Integer.parseInt(data[invoices.size()][0]);
//            Invoice invoice = new Invoice();
//            invoice.setId(id);
//            invoices.add(invoice);
            String[] row = new String[4];
            row[0] = "";
            row[1] = "";
            row[2] = "";

            row[3] = "";
            tableModel.addRow(row);
            System.out.println(invoices.size());




           invoiceTable.repaint();


        }} else if (e.getActionCommand() == "deleteInvoice") {
            if(invoiceTable.getSelectedRow() != -1) {
                // remove selected row from the model
                tableModel.removeRow(invoiceTable.getSelectedRow());
                JOptionPane.showMessageDialog(this, "Selected row deleted successfully");
            }      }
        }
        }



