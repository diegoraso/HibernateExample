package com.mkyong.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stock_daily_record")
public class StockDailyRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name = "id_Sequence", sequenceName = "HIBERNATE_SEQUENCE_1")
	@Column(name = "RECORD_ID", unique = true, nullable = false)
	private Integer recordId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STOCK_ID")
	private Stock stock;
	
	@Column(name = "PRICE_OPEN", precision = 6)
	private Float priceOpen;
	
	@Column(name = "PRICE_CLOSE", precision = 6)
	private Float priceClose;
	
	@Column(name = "PRICE_CHANGE", precision = 6)
	private Float priceChange;
	
	@Column(name = "VOLUME")
	private Long volume;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DATA", unique = true, nullable = false, length = 10)
	private Date data;

	public StockDailyRecord() {
	}

	public StockDailyRecord(Stock stock, Date date) {
		this.stock = stock;
		this.data = date;
	}

	public StockDailyRecord(Stock stock, Float priceOpen, Float priceClose,
			Float priceChange, Long volume, Date date) {
		this.stock = stock;
		this.priceOpen = priceOpen;
		this.priceClose = priceClose;
		this.priceChange = priceChange;
		this.volume = volume;
		this.data = date;
	}

	
	public Integer getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	
	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	
	public Float getPriceOpen() {
		return this.priceOpen;
	}

	public void setPriceOpen(Float priceOpen) {
		this.priceOpen = priceOpen;
	}

	
	public Float getPriceClose() {
		return this.priceClose;
	}

	public void setPriceClose(Float priceClose) {
		this.priceClose = priceClose;
	}

	
	public Float getPriceChange() {
		return this.priceChange;
	}

	public void setPriceChange(Float priceChange) {
		this.priceChange = priceChange;
	}

	
	public Long getVolume() {
		return this.volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
