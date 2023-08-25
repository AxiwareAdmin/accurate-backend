package com.accurate.dao.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accurate.action.invoice.InvoiceController;
import com.accurate.model.invoice.CustomerDO;
import com.accurate.model.invoice.InvoiceDO;
import com.accurate.model.invoice.InvoiceProductDO;
@Repository
public class InvoiceDao {
	
	private final static Logger LOGGER=Logger.getLogger(InvoiceController.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("deprecation")
	@Transactional
	public List<CustomerDO> getCustometList(){
		LOGGER.info("InvoiceDao :: getCustomerList :: Start ");
		List<CustomerDO> custList = new ArrayList<CustomerDO>();
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(CustomerDO.class);
			custList=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: GetCustomerList ");
		}
		LOGGER.info("InvoiceDao :: getCustomerList method end");
		return custList;
	}
	
	public CustomerDO getCustomerById(Integer custId){
		LOGGER.info("InvoiceDao :: getCustomerById :: Start ");
		CustomerDO customerDO=null;
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(CustomerDO.class);
			criteria.add(Restrictions.eq("customerId",custId));
			customerDO=(CustomerDO)criteria.uniqueResult();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getCustomerById ");
		}
		LOGGER.info("InvoiceDao :: getCustomerById method end");
		return customerDO;
	}
	
	public InvoiceProductDO getProductById(Integer prodId){
		LOGGER.info("InvoiceDao :: getProductById :: Start ");
		InvoiceProductDO productDO=null;
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceProductDO.class);
			criteria.add(Restrictions.eq("invoiceProductId",prodId));
			productDO=(InvoiceProductDO)criteria.uniqueResult();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getProductById ");
		}
		LOGGER.info("InvoiceDao :: getProductById method end");
		return productDO;
	}
	
	

	public List<InvoiceDO> getInvoiceList(){
		LOGGER.info("InvoiceDao :: getInvoiceList :: Start ");
		List<InvoiceDO> invoiceList = new ArrayList<InvoiceDO>();
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceDO.class);
			invoiceList=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvoiceList ");
		}
		LOGGER.info("InvoiceDao :: getInvoiceList method end");
		return invoiceList;
	}
	
	
	public List<InvoiceDO> getInvoiceListByMonth(String month){
		LOGGER.info("InvoiceDao :: getInvoiceListByMonth :: Start ");
		List<InvoiceDO> invoiceList = new ArrayList<InvoiceDO>();
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("month",month));
			invoiceList=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvoiceListByMonth ");
		}
		LOGGER.info("InvoiceDao :: getInvoiceListByMonth method end");
		return invoiceList;
	}
	
	public List<InvoiceProductDO> getInvoiceProductList(){
		LOGGER.info("InvoiceDao :: getInvoiceProductList :: Start ");
		List<InvoiceProductDO> invoiceProductList = new ArrayList<InvoiceProductDO>();
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceProductDO.class);
			invoiceProductList=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvoiceProductList ");
		}
		LOGGER.info("InvoiceDao :: getInvoiceProductList method end");
		return invoiceProductList;
	}
	
	public String saveInvoice(InvoiceDO invoiceDO) {
		LOGGER.info("InvoiceDao::saveInvoice::start");
		
		try {
			invoiceDO.setInvoiceProductId(1);
			invoiceDO.setMonth("Dec");
			invoiceDO.setCity("Mum");
			invoiceDO.setIgstValue(new BigDecimal(1));
			Session session=getSession();
			Transaction tx=session.beginTransaction();
			session.save(invoiceDO);
			session.flush();
			tx.commit();
			
		}catch(Exception e) {
			LOGGER.info("Exception occured in invoiceDao::saveInvoice::"+e);
			return "failure";
		}
		
		
		LOGGER.info("InvoiceDao::saveInvoice::end");
		return "success";
	}
	
	
	@Transactional
	public InvoiceDO getInvoiceDetails(String invNo){
		LOGGER.info("InvoiceDao :: getInvoiceDetails :: Start ");
		InvoiceDO invDO=null;
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("invoiceNo",invNo));
			invDO=(InvoiceDO)criteria.uniqueResult();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvoiceDetails ");
		}
		LOGGER.info("InvoiceDao :: getInvoiceDetails method end");
		return invDO;
	}
	
	@Transactional
	public boolean DeleteInvoice(String invNo){
		LOGGER.info("InvoiceDao :: DeleteInvoice :: Start ");
		boolean flag=false;
		InvoiceDO invDo = null;
		try {
			
			Session session=getSession();
			Query query= session.createSQLQuery("delete from Invoice where Invoice_No ='"+invNo+"'");
			/*Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("invoiceNo",invNo));
			invDo = (InvoiceDO)criteria.uniqueResult();
			session.delete(invDo);*/
			query.executeUpdate();
			flag = true;
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: DeleteInvoice ");
			flag = false;
		}
		LOGGER.info("InvoiceDao :: DeleteInvoice method end");
		return flag;
	}
	
	
	@Transactional
	public boolean cloneInvoice(String invNo){
		LOGGER.info("InvoiceDao :: cloneInvoice :: Start ");
		boolean flag=false;
		InvoiceDO invDO=null;
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("invoiceNo",invNo));
			invDO = (InvoiceDO)criteria.uniqueResult();
			String [] invnoarr  = invDO.getInvoiceNo().split("/");
			Integer num = Integer.parseInt(invnoarr[2])+1;
			num.toString().length();
			String fInv = "";
			for(int i=0;i<invnoarr[2].length()-num.toString().length();i++) {
				fInv = fInv+"0";
			}
			String fInvNo =fInv+num.toString();
			invDO.setInvoiceNo(fInvNo);
			invDO.setInvoiceId(10);
			closeSession(session);
			
			flag = saveCloneInv(invDO);
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: cloneInvoice ");
			flag = false;
		}
		LOGGER.info("InvoiceDao :: cloneInvoice method end");
		return flag;
	}
	
	@Transactional
	public boolean saveCloneInv(InvoiceDO invdo) {
	
        LOGGER.info("InvoiceDao::saveCloneInv::start");
		try {
			
			Session session=getSession();
			Transaction tx=session.beginTransaction();
			session.save(invdo);
			session.flush();
			tx.commit();
			
		}catch(Exception e) {
			LOGGER.info("Exception occured in invoiceDao::saveCloneInv::"+e);
			return false;
		}
		
		LOGGER.info("InvoiceDao::saveCloneInv::end");
		return true;
		
	}

	
	
	
	
	public Session getSession() {
		Session session=null;
		try {
		session=sessionFactory.getCurrentSession();
		}catch(Exception e) {
			session=sessionFactory.openSession();
		}
		return session;
	}
	
	public void closeSession(Session session) {
		if(session!=null && session.isOpen()) {
			session.close();
		}
	}
	
	
}
