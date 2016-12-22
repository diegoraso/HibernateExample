package com.mkyong;

import java.util.Date;

import org.hibernate.Session;

import com.mkyong.entities.Stock;
import com.mkyong.entities.StockDailyRecord;
import com.mkyong.util.HibernateUtils;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtils.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
        stock.setStockCode("7052");
        stock.setStockName("PADINI");
       
        
        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setData(new Date());
        
        stock.getStockDailyRecords().add(stockDailyRecords);
        
        stockDailyRecords.setStock(stock);

        session.save(stock);

		session.getTransaction().commit();
		System.out.println("Done");
	}
}
