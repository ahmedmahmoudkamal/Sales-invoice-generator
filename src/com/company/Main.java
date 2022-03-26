package com.company;

import com.company.UI.MainFrame;
import com.company.models.Invoice;

import java.util.List;

public class Main {
    public static MainFrame mainFrame;
    public static void main(String[] args) {
        List<Invoice> invoices ;
	// write your code here
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);



    }
}
