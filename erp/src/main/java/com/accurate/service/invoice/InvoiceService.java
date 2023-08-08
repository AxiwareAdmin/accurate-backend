package com.accurate.service.invoice;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.accurate.dao.invoice.InvoiceDao;
import com.accurate.model.invoice.CustomerDO;
import com.accurate.model.invoice.InvoiceDO;
import com.accurate.model.invoice.InvoiceProductDO;

@Service
public class InvoiceService {
	private static final Logger LOGGER=Logger.getLogger(InvoiceService.class);

	@Autowired
	InvoiceDao invoiceDao;
	
	public List<CustomerDO> getCustometList() {
		LOGGER.info("InvoiceService::getCustometList()::start");
		return invoiceDao.getCustometList();
	}
	
	public List<InvoiceDO> getInvoiceList(){
		LOGGER.info("InvoiceService::getInvoiceList()::start");
		return invoiceDao.getInvoiceList();
	}
	
	public List<InvoiceProductDO> getInvoiceProductList(){
		LOGGER.info("InvoiceService::getInvoiceProductList()::start");
		return invoiceDao.getInvoiceProductList();
		
	}
	
	public CustomerDO getCustomerById(Integer custId){
		LOGGER.info("InvoiceService::getInvoiceProductList()::start");
		return invoiceDao.getCustomerById(custId);
	}
	
	public InvoiceProductDO getProductById(Integer prodId){
		LOGGER.info("InvoiceService::getProductById()::start");
		return invoiceDao.getProductById(prodId);
	}
	
	public String saveInvoice(Map<String, Object> inputJson) throws ParseException {
		
		SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy");
		
		InvoiceDO invoiceDO=new InvoiceDO();
		
		
		Object invoiceNo=inputJson.get("invoiceNo");
		
		Object sgstValue=inputJson.get("sgstValue");
		
		Object cgstValue=inputJson.get("cgstValue");
		
		Object taxableValue=inputJson.get("taxableValue");
		
		Object invoiceValue=inputJson.get("invoiceValue");
		
		Object transportCharges=inputJson.get("transportCharges");
		
		Object additionalCharges=inputJson.get("additionalCharges");
		
		Object discount=inputJson.get("discount");
		
		Object otherDiscount=inputJson.get("otherDiscount");
		
		Object shippingAddress=inputJson.get("shippingAddress");
		
		Object billingAddress=inputJson.get("billingAddress");
		
		Object poNumber=inputJson.get("poNumber");
		
		Object customerName=inputJson.get("customerName");
		
		Object invoiceDate=inputJson.get("invoiceDate");
		
		Object poDate=inputJson.get("poDate");
		
		Object challanNumber=inputJson.get("challanNumber");
		
		Object challanDate=inputJson.get("challanDate");
		
		Object paymentTerms=inputJson.get("paymentTerms");
		
		Object dueDate=inputJson.get("dueDate");
		
		Object transportMode=inputJson.get("transportMode");
		
		Object vehicleNumber=inputJson.get("vehicleNumber");
		
		if(invoiceNo!=null)
		invoiceDO.setInvoiceNo(invoiceNo.toString());
		
		if(sgstValue!=null) {
			invoiceDO.setSgstValue(new BigDecimal(sgstValue.toString()));
		}
		
		if(cgstValue!=null) {
			invoiceDO.setCgstValue(new BigDecimal(cgstValue.toString()));
		}
		
		if(taxableValue!=null) {
			invoiceDO.setTaxableValue(new BigDecimal(taxableValue.toString()));
		}
		
		if(invoiceValue!=null) {
			invoiceDO.setInvoiceValue(new BigDecimal(invoiceValue.toString()));
		}
				
		if(transportCharges!=null) {
			invoiceDO.setTransportCharges(transportCharges.toString());
		}
		
		if(additionalCharges!=null) {
			invoiceDO.setAdditionalCharges(additionalCharges.toString());
		}
		
		if(discount!=null) {
			invoiceDO.setDiscount(new BigDecimal(discount.toString()));
		}
		
		if(otherDiscount!=null) {
			invoiceDO.setOtherDiscount(new BigDecimal(otherDiscount.toString()));
		}
		
		if(shippingAddress!=null) {
			invoiceDO.setShippingAddress(shippingAddress.toString());
		}
		
		if(billingAddress!=null) {
			invoiceDO.setBillingAddress(billingAddress.toString());
		}
		
		if(poNumber!=null) {
			invoiceDO.setPoNumber(poNumber.toString());
		}
		
		if(customerName!=null) {
			invoiceDO.setCustomerName(customerName.toString());
		}
		
		if(invoiceDate!=null) {
			
			invoiceDO.setInvoiceDate(sdf.parse(invoiceDate.toString()));
		}
		
		if(poDate!=null) {
			invoiceDO.setPoDate(sdf.parse(poDate.toString()));
		}
		
		if(challanNumber!=null) {
			invoiceDO.setChallanNo(challanNumber.toString());
		}
		
		if(challanDate!=null) {
			invoiceDO.setChallanDate(sdf.parse(challanDate.toString()));
		}
		
		if(dueDate!=null) {
			invoiceDO.setDueDate(sdf.parse(dueDate.toString()));
		}
		
		if(paymentTerms!=null) {
			invoiceDO.setPaymentTerms(paymentTerms.toString());
		}
		
		if(transportMode!=null) {
			invoiceDO.setTransportMode(transportMode.toString());
		}
		
		if(vehicleNumber!=null) {
			invoiceDO.setVehicleNo(vehicleNumber.toString());
		}
		
		
		
		
		
		
		return invoiceDao.saveInvoice(invoiceDO);
		
	}
	
}
