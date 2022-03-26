package com.company;

import com.company.UI.MainFrame;
import com.company.models.Invoice;
import com.company.models.Item;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class CSVHandler {

    BufferedReader reader = null;
    BufferedReader reader2 = null;
    String filePath1;
    String filePath2;
    String line;
    String line2;
    List<Invoice> invoices ;
    BufferedWriter bwr;
    BufferedWriter bwrItems;

    public CSVHandler(){
        filePath1 = "src/com/company/InvoiceHeader.csv";
        filePath2 = "src/com/company/InvoiceLine.csv";
        invoices = new ArrayList<>();
    }


    public List<Invoice> getInvoices(){

        try {
            reader = new BufferedReader(new FileReader(filePath1));
            invoices = new ArrayList<Invoice>();
            while((line = reader.readLine()) != null){
                    String[] row = line.split(",");
                    Invoice invoice = new Invoice();
                    invoice.setId(Integer.parseInt(row[0]));
                    invoice.setDate(row[1]);
                    invoice.setCustomer(row[2]);
                    invoices.add(invoice);

            }
            return invoices;


        } catch (Exception e) {
            e.printStackTrace();
        }
//FileNotFoundException

        return null;
    }



    public Invoice getInvoiceWithItems(int id){

        try {
            reader2 = new BufferedReader(new FileReader(filePath2));
            ArrayList<Item> items = new ArrayList<Item>() ;

            Invoice invoice = getInvoice(id);
            while((line2 = reader2.readLine()) != null) {
                String[] row = line2.split(",");
                if (Integer.parseInt(row[0]) == id) {
                    Item item = new Item();
                    item.setId(Integer.parseInt(row[0]));
                    item.setName(row[1]);
                    item.setPrice(Integer.parseInt(row[2]));
                    item.setTotalCount(Integer.parseInt(row[3]));
                    item.setInvoice(invoice);
                    items.add(item);
                 }
            }
            invoice.setItems(items);

            return invoice;


        } catch (Exception e) {
            e.printStackTrace();
        }
//FileNotFoundException

        return null;
    }


    public  Invoice getInvoice(int id){
        Invoice invoice = new Invoice();
        List<Invoice> invoices = getInvoices();
        for (int i = 0 ; i < invoices.size() ; i++ ) {
              invoice = invoices.stream().filter(a->a.getId() == id).collect(Collectors.toList()).get(0);
        }

        return invoice;
    }



    public void setInvoices(DefaultTableModel tableInvoices){
        try {

            FileWriter writer = new FileWriter(filePath1);
            bwr = new BufferedWriter(writer);
         for (int i = 0 ; i < tableInvoices.getRowCount() ; i++ ) {
             StringBuilder s = new StringBuilder();

             for (int z = 0 ; z < 4 ; z++) {
                 s = s.append(tableInvoices.getValueAt(i,z)).append(",");

             }
             bwr.write(s.toString());
               bwr.newLine();
            }



        }
        catch
        (Exception ioe) { ioe.printStackTrace();
        }finally {
            if (bwr != null){
                try {
                    bwr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public void setItems(DefaultTableModel invoiceItemsTable){

        try {

            FileWriter writer = new FileWriter(filePath2);
            bwrItems = new BufferedWriter(writer);
            for (int i = 0 ; i < invoiceItemsTable.getRowCount() ; i++ ) {
                StringBuilder s = new StringBuilder();
               for (int z = 0 ; z < 5 ; z++) {
                    s = s.append(invoiceItemsTable.getValueAt(i,z)).append(",");
                 }
                bwrItems.write(s.toString());
                bwrItems.newLine();

            }



        }
        catch
        (Exception ioe) { ioe.printStackTrace();
        }finally {
            if (bwrItems != null){
                try {
                    bwrItems.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }









}
