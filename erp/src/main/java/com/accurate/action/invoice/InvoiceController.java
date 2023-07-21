package com.accurate.action.invoice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accurate.model.invoice.CustomerDO;
import com.accurate.service.invoice.InvoiceService;

@RestController
public class InvoiceController {
	private final static Logger LOGGER=Logger.getLogger(InvoiceController.class);
	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(value="/customer")
	public ResponseEntity<?> getCustomerDetails(){
		List<CustomerDO> custList=invoiceService.getCustometList();
		return new ResponseEntity<List<CustomerDO>>(custList,HttpStatus.OK);
	}
}
