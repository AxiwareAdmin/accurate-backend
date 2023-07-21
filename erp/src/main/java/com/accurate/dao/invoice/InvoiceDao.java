package com.accurate.dao.invoice;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accurate.action.invoice.InvoiceController;
import com.accurate.model.invoice.CustomerDO;
import com.accurate.model.invoice.LsmDO;
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
//			Session session = hibernateUtl.createSession();
//			SQLQuery query = session.createSQLQuery("select Customer_Id as customerId , Customer_Name as customerName from customer");
//			query.addScalar("customerId",StandardBasicTypes.INTEGER);
//			query.addScalar("customerName",StandardBasicTypes.STRING);
//			
//			query.setResultTransformer(Transformers.aliasToBean(CustomerDO.class));
//			custList=query.list();
			
			Session session=getSession();
			Criteria criteria=session.createCriteria(CustomerDO.class);
			custList=criteria.list();
			
			
		}catch(Exception e) {
			LOGGER.error("Exception occured in InvoiceDao :: GetCustomerList ");
		}
		LOGGER.info("InvoiceDao :: getCustomerList method end");
		return custList;
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
	
	public static void main(String[] args) {
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		Session session=(Session)ctx.getBean("hibernateSessionFactory");
		Criteria criteria=session.createCriteria(LsmDO.class);
		
		System.out.println(criteria.list());
	}
	
}
