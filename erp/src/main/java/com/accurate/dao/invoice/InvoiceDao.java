package com.accurate.dao.invoice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accurate.action.invoice.InvoiceController;
import com.accurate.model.MnanageMaster.DocumentSeqMasterDO;
import com.accurate.model.invoice.CustomerDO;
import com.accurate.model.invoice.InvoiceDO;
import com.accurate.model.invoice.ProductDO;
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
	
	public ProductDO getProductById(Integer prodId){
		LOGGER.info("InvoiceDao :: getProductById :: Start ");
		ProductDO productDO=null;
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(ProductDO.class);
			criteria.add(Restrictions.eq("invoiceProductId",prodId));
			productDO=(ProductDO)criteria.uniqueResult();
			
			
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
	
	public String getInvNo(){
		LOGGER.info("InvoiceDao :: getInvNo :: Start ");
		String invNo="";
		DocumentSeqMasterDO documentSeqMasterDO = null;
		InvoiceDO invoiceDO = null;
		String tempInv = "";
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(DocumentSeqMasterDO.class);
			criteria.add(Restrictions.eq("documentName","Invoice"));
			documentSeqMasterDO=(DocumentSeqMasterDO) criteria.uniqueResult();
			
			if(documentSeqMasterDO != null) {
			String matchVal = documentSeqMasterDO.getPrefix1() +"/"+documentSeqMasterDO.getPrefix2()+"/";
			Session session1=getSession();
			Criteria criteria1=session1.createCriteria(InvoiceDO.class);
			criteria1.add(Restrictions.like("invoiceNo",matchVal,MatchMode.ANYWHERE));
			Projection p1 = Projections.max("invoiceNo");
			criteria1.setProjection(p1);
			 tempInv=(String) criteria1.uniqueResult();
			}
			System.out.println("max invoice no :"+tempInv);
			if(tempInv != null && !tempInv.equalsIgnoreCase("")) {
				int len = (tempInv.split("/")[2]).length();
				Integer nextInvNo = Integer.parseInt((tempInv.split("/")[2]))+1;
				invNo = documentSeqMasterDO.getPrefix1() +"/"+documentSeqMasterDO.getPrefix2()+"/"+String.format("%04d", nextInvNo);
			}else {
				invNo = documentSeqMasterDO.getPrefix1() +"/"+documentSeqMasterDO.getPrefix2()+"/"+documentSeqMasterDO.getSeries();
			}
			
			return invNo;
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvNo "+e);
		}
		LOGGER.info("InvoiceDao :: getInvNo method end");
		return invNo;
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
	public List<ProductDO> getInvoiceProductList(){
		LOGGER.info("InvoiceDao :: getInvoiceProductList :: Start ");
		List<ProductDO> invoiceProductList = new ArrayList<ProductDO>();
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(ProductDO.class);
			invoiceProductList=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvoiceProductList ");
		}
		LOGGER.info("InvoiceDao :: getInvoiceProductList method end");
		return invoiceProductList;
	}
	
	
	public static void main(String[] args) {
		try {
			
			InvoiceDao t = new InvoiceDao();
						
			/*ProductDO productDO=null;
			
			InvoiceDao invoiceDao=new InvoiceDao();
				
				Session session=invoiceDao.getSession();
				Transaction tx=session.getTransaction();
				tx.begin();
				Criteria criteria=session.createCriteria(ProductDO.class);
				criteria.add(Restrictions.eq("invoiceProductId",1));
				productDO=(ProductDO)criteria.uniqueResult();
				
				productDO.setProductDescription("nadimkhan");
				tx.commit();
				session.flush();
				session.close();
				*/
			
		}catch(Exception e) {
			System.out.println("Exception:"+e);
		}
	}
	
	
	public String saveInvoice(InvoiceDO invoiceDO) {
		LOGGER.info("InvoiceDao::saveInvoice::start");
		
		try {
			InvoiceDO tmp = invoiceDO;
			InvoiceDO invDO = null;
			invoiceDO.setInvoiceProductId(1);
			Calendar cal = Calendar.getInstance();
			invoiceDO.setMonth(new SimpleDateFormat("MMM").format(cal.getTime()));
			invoiceDO.setCity("Mum");
			invoiceDO.setIgstValue(new BigDecimal(1));
			Session session=getSession();
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(invoiceDO);
			session.flush();
			tx.commit();
			
		}catch(Exception e) {
			LOGGER.info("Exception occured in invoiceDao::saveInvoice::"+e);
			return "failure";
		}
		
		
		LOGGER.info("InvoiceDao::saveInvoice::end");
		return "success";
	}
	
	@SuppressWarnings("deprecation")
	@Transactional
	public InvoiceDO getInvoiceDetails(String invNo){
		LOGGER.info("InvoiceDao :: getInvoiceDetails :: Start ");
		InvoiceDO invDO=null;
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("invoiceId",Integer.parseInt(invNo)));
			invDO=(InvoiceDO)criteria.uniqueResult();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getInvoiceDetails "+e);
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
			Query query= session.createSQLQuery("delete from Invoice where Invoice_Id ='"+Integer.parseInt(invNo)+"'");
			/*Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("invoiceNo",invNo));
			invDo = (InvoiceDO)criteria.uniqueResult();
			session.delete(invDo);*/
			query.executeUpdate();
			flag = true;
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: DeleteInvoice "+e);
			flag = false;
		}
		LOGGER.info("InvoiceDao :: DeleteInvoice method end");
		return flag;
	}
	
	
	@Transactional
	public boolean cloneInvoice(String invNo){
		LOGGER.info("InvoiceDao :: cloneInvoice :: Start ");
		boolean flag=false;
		InvoiceDO invDO= new InvoiceDO();
		List<InvoiceDO> invtemp = new ArrayList<>();
		Integer gInvNo = 0;
		String fInvNo="";
		try {
			
			invtemp = getInvoiceList();
			gInvNo = invtemp.size();
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(InvoiceDO.class);
			criteria.add(Restrictions.eq("invoiceId",Integer.parseInt(invNo)));
			invDO = (InvoiceDO)criteria.list().get(0);
			
			fInvNo = getInvNo();
			invDO.setInvoiceNo(fInvNo);
					
			List<InvoiceProductDO> productdo = new ArrayList<InvoiceProductDO>();
			for(InvoiceProductDO temp : invDO.getInvoiceProductDO()) {
				InvoiceProductDO invprod = new InvoiceProductDO();
				invprod = temp;
				invprod.setInvoiceDO(invDO);
				invprod.setInvoiceProductId(null);
				productdo.add(invprod);
			}
			invDO.getInvoiceProductDO().clear();
			invDO.setInvoiceProductDO(productdo);
			
			session.clear();
			closeSession(session);
			
			
			flag  = saveCloneInv(invDO);

			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: cloneInvoice "+e);
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
	
	public String getCustomerEmail(String custName) {
		
        LOGGER.info("InvoiceDao::getCustomerEmail::start");
        String email = "";
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(CustomerDO.class);
			criteria.add(Restrictions.eq("customerName",custName));
			Projection p1 = Projections.property("email");
			criteria.setProjection(p1);
			email=(String) criteria.uniqueResult();
			
		}catch(Exception e) {
			LOGGER.info("Exception occured in invoiceDao::getCustomerEmail::"+e);
			return email;
		}
		
		LOGGER.info("InvoiceDao::getCustomerEmail::end");
		return email;
		
	}
	
//	public String getFinYear() {
//		LOGGER.info("InvoiceDao::getFinYear::start");
//		String fY = "";
//		try {
//			
//			int year = Calendar.getInstance().get(Calendar.YEAR);
//
//		    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
//	
//		    if (month < 3) {
//		        fY = (year - 1) + "-" + String.valueOf(year).substring(2);
//		    } else {
//		        fY =  year + "-" + String.valueOf((year + 1)).substring(2);
//		    }
//			
//		}catch(Exception e) {
//			LOGGER.info("Exception occured in invoiceDao::getFinYear::"+e);
//		}
//		return fY;
//	}
//	
	
	
	public List<DocumentSeqMasterDO> getDocMaster(){
		LOGGER.info("InvoiceDao :: getDocMaster :: Start ");
		
		List<DocumentSeqMasterDO> documentSeqMasterDO = new ArrayList<DocumentSeqMasterDO>();
		try {
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(DocumentSeqMasterDO.class);
			documentSeqMasterDO=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: getDocMaster ");
		}
		LOGGER.info("InvoiceDao :: getDocMaster method end");
		return documentSeqMasterDO;
	}
	
	public String saveDocMaster(DocumentSeqMasterDO docmentseqmasterdo) {
		LOGGER.info("InvoiceDao::saveDocMaster::start");
		
		try {
			
			Session session=getSession();
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(docmentseqmasterdo);
			session.flush();
			tx.commit();
			
		}catch(Exception e) {
			LOGGER.info("Exception occured in invoiceDao::saveDocMaster::"+e);
			return "failure";
		}
		return "success";
	}
	
	
	public boolean deleteDocMaster(String docId){
		LOGGER.info("InvoiceDao :: deleteDocMaster :: Start ");
		boolean flag=false;
		try {
			
			Session session=getSession();
			Query query= session.createSQLQuery("delete from document_seq_master where document_seq_Id ='"+docId+"'");
			query.executeUpdate();
			flag = true;
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: deleteDocMaster ");
			flag = false;
		}
		LOGGER.info("InvoiceDao :: deleteDocMaster method end");
		return flag;
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
