package com.accurate.model.invoice;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_products")
public class InvoiceProductDO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Invoice_Product_Id")
	Integer invoiceProductId;

	@Column(name = "Product_Name")
	String productName;

	@Column(name = "Product_Description")
	String productDescription;

	@Column(name="Quantity")
	Integer quantity;
	
	@Column(name="Discount")
	Integer discount;
	
	@Column(name = "HSN_SAC")
	String hsnCode;

	@Column(name = "Unit")
	Integer unit;

	@Column(name = "Rate")
	BigDecimal rate;

	@Column(name = "Amount")
	Integer amount;

	@Column(name = "Tax")
	BigDecimal applicableTax;

	/*@Column(name = "Opening_Stock")
	Integer openingStock;*/

	
	/*@Column(name = "As_On_Date")
	Date asOnDate;*/

		
	@Column(name = "CreatedBy")
	String createdBy;
	
	@Column(name = "Created_Date")
	Date createdDate;
	
	@Column(name = "Register_Id")
	Integer registerId;
	
	@Column(name = "User_Id")
	Integer userId;
	
	
	@ManyToOne
	@JoinColumn(name = "Invoice_id" , nullable=false)
	private InvoiceDO invoiceId;

	/*public InvoiceDO getInvoiceId() {
		return invoiceId;
	}*/

	public void setInvoiceId(InvoiceDO invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Integer getInvoiceProductId() {
		return invoiceProductId;
	}

	public void setInvoiceProductId(Integer invoiceProductId) {
		this.invoiceProductId = invoiceProductId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}


	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	

	public BigDecimal getApplicableTax() {
		return applicableTax;
	}

	public void setApplicableTax(BigDecimal applicableTax) {
		this.applicableTax = applicableTax;
	}

	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public Integer getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Integer registerId) {
		this.registerId = registerId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	
}
