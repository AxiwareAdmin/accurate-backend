package com.accurate.model.invoice;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

	/*@Column(name="Prodcut_Type")
	String productType;*/
	
	/*@Column(name="part_code")
	String partCode;*/
	
	@Column(name = "HSN_SAC")
	String hsnCode;

	@Column(name = "Unit")
	Integer unit;

	@Column(name = "Rate")
	BigDecimal rate;

	/*@Column(name = "category")
	String category;*/

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
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Invoice_id")
	private InvoiceDO invoiceId;

	public InvoiceDO getInvoiceId() {
		return invoiceId;
	}

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

	/*public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}*/

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

	/*public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}*/

	public BigDecimal getApplicableTax() {
		return applicableTax;
	}

	public void setApplicableTax(BigDecimal applicableTax) {
		this.applicableTax = applicableTax;
	}

	/*public Integer getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(Integer openingStock) {
		this.openingStock = openingStock;
	}*/
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

	/*public Date getAsOnDate() {
		return asOnDate;
	}

	public void setAsOnDate(Date asOnDate) {
		this.asOnDate = asOnDate;
	}*/

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	
}
