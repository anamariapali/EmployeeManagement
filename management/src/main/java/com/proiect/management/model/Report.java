package com.proiect.management.model;

import java.sql.Date;

public interface Report {
	public void generateReport(int nume,Date dueDate,String satusTask);
}
