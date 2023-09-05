package com.accurate.action.invoice;

import java.awt.PageAttributes.MediaType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accurate.model.invoice.CustomerDO;
import com.accurate.model.invoice.InvoiceDO;
import com.accurate.model.invoice.ProductDO;
import com.accurate.service.invoice.InvoiceService;
import com.accurate.model.invoice.InvoiceProductDO;

@RestController
public class InvoiceController {
	private final static Logger LOGGER=Logger.getLogger(InvoiceController.class);
	private final static String [] month = {"Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","Jan","Feb","Mar"};
	
	@Autowired
	InvoiceService invoiceService;
	
	@GetMapping(value="/customers")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getCustomerList(){
		List<CustomerDO> custList=invoiceService.getCustometList();
		if(custList!=null && custList.size()>0) {
		return new ResponseEntity<List<CustomerDO>>(custList,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Customers are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/customer/{custId}")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getCustomer(@PathVariable Integer custId){
		CustomerDO customerDO=invoiceService.getCustomerById(custId);
		if(customerDO!=null) {
		return new ResponseEntity<CustomerDO>(customerDO,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Customers are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/allInvoices")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getAllInvoiceList(){
		List<InvoiceDO> invoiceList=invoiceService.getInvoiceList();
		if(invoiceList!=null && invoiceList.size()>0) {
		return new ResponseEntity<List<InvoiceDO>>(invoiceList,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Invoices are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/invoices/{month}")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getInvoiceList(@PathVariable String month){
		List<InvoiceDO> invoiceList=invoiceService.getInvoiceListByMonth(month);
		if(invoiceList!=null && invoiceList.size()>0) {
		return new ResponseEntity<List<InvoiceDO>>(invoiceList,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Invoices are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	
	
	@GetMapping(value="/invoiceproducts")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getInvoiceProducts(){
		List<ProductDO> invoiceProductList=invoiceService.getInvoiceProductList();
		if(invoiceProductList!=null && invoiceProductList.size()>0) {
		return new ResponseEntity<List<ProductDO>>(invoiceProductList,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Invoice products are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/invoiceproduct/{id}")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getInvoiceProducts(@PathVariable Integer id){
		ProductDO prodDO=invoiceService.getProductById(id);
		if(prodDO!=null) {
		return new ResponseEntity<ProductDO>(prodDO,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Invoice products are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}

	@PostMapping(value="/saveInvoice",consumes= {"application/json"})
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> saveInvoice(@RequestBody Map<String, Object> inputJson) throws ParseException{
		
		System.out.print(inputJson);
		
		String msg=invoiceService.saveInvoice(inputJson);
		
		JSONObject jsonObj=new JSONObject();
		if(msg.equals("success")) {
		jsonObj.put("res", "success");
		}else {
			jsonObj.put("res", "failure");
		}
		return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);

		
		
	}
	
	
	@GetMapping(value="/viewSalesReg")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getviewSalesReg(){
		List<InvoiceDO> invoiceList=invoiceService.getInvoiceList();
		if(invoiceList!=null && invoiceList.size()>0) {
			
			/*invoiceList.forEach((ele) ->{
				
			});*/
			List<String> salesobj = new ArrayList<>();
			for(String str  : month) {
				JSONObject jsonObj=new JSONObject();
				Integer totalInv = 0;
				BigDecimal closingBal = new BigDecimal(0);
				BigDecimal amount = new BigDecimal(0);
				for(InvoiceDO invdo : invoiceList) {
					if(str.equalsIgnoreCase(invdo.getMonth())) {
						totalInv = totalInv + 1;
						amount = amount.add(invdo.getInvoiceValue());
						closingBal = closingBal.add(new BigDecimal(1));
					}
				}
				jsonObj.put("month", str);
				jsonObj.put("totalInv", totalInv);
				jsonObj.put("amount", amount);
				jsonObj.put("closingBal", closingBal);
				jsonObj.put("progress", 30);
				salesobj.add(jsonObj.toString());
			}
			
		return new ResponseEntity<List<String>>(salesobj,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Invoices are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/viewInvoice")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getInvoiceDetails(@QueryParam("invNo") String invNo){
		InvoiceDO invoicedo=invoiceService.getInvoiceDetails(invNo);
		if(invoicedo!=null) {
		return new ResponseEntity<InvoiceDO>(invoicedo,HttpStatus.OK);
		}
		else {
			JSONObject jsonObj=new JSONObject();
			jsonObj.put("res", "Invoice Details are not found");
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(value="/deleteInv")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> getDeleteInvoice(@QueryParam("invNo") String invNo){
		boolean flag=invoiceService.DeleteInvoice(invNo);
		
		JSONObject jsonObj=new JSONObject();
		
		if(flag) {
			jsonObj.put("res", "sucess");
		}else {
			jsonObj.put("res", "failure");
		}
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
	}
	
	@GetMapping(value="/cloneInv")
	@CrossOrigin(origins={"*"})
	public ResponseEntity<?> cloneInvoice(@QueryParam("invNo") String invNo){
		boolean flag=false;
				flag = invoiceService.cloneInvoice(invNo);
		
		JSONObject jsonObj=new JSONObject();
		
		if(flag) {
			jsonObj.put("res", "sucess");
		}else {
			jsonObj.put("res", "failure");
		}
			return new ResponseEntity<String>(jsonObj.toString(),HttpStatus.OK);
	}
}
