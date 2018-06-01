package com.proiect.management.model;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;



public class ReportFactory {
	public Report getReport(String reportType) throws FileNotFoundException, DocumentException{
	      if(reportType == null){
	         return null;
	      }		
	      if(reportType.equalsIgnoreCase("PDF")){
	         return new PDF();
	         
	      } else if(reportType.equalsIgnoreCase("CSV")){
	         return new CSV();
	         
	      } 
	      
	      return null;
	   }
}
