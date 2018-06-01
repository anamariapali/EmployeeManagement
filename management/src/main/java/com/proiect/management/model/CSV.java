package com.proiect.management.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;

public class CSV implements Report {
	private PrintWriter pw ;
    private StringBuilder sb = new StringBuilder();
    @Override
    public void generateReport(int nume,Date dueDate,String status) {
        String filepath = "C:\\Users\\User\\eclipse-workspace\\management\\Angajat.csv";
        try {
            pw = new PrintWriter(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
      
        
        sb.append("Nume:");
        sb.append(',');
        sb.append(nume);
        sb.append('\n');

        sb.append("DueDate:");
        sb.append(',');
        sb.append(dueDate.toString());
        sb.append('\n');
        
        sb.append("Staus:");
        sb.append(',');
        sb.append(status);
        sb.append('\n');
         
        pw.write(sb.toString());
        pw.flush();
        pw.close();
    }
}
