package com.accurate.service.invoice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
