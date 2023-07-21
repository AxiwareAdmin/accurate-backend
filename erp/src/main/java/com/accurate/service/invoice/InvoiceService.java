package com.accurate.service.invoice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accurate.dao.invoice.InvoiceDao;
import com.accurate.model.invoice.CustomerDO;

@Service
public class InvoiceService {

	@Autowired
	InvoiceDao invoiceDao;
	
	public List<CustomerDO> getCustometList() {
		return invoiceDao.getCustometList();
	}
	
}
