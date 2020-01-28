
package com.app.dao;

import java.util.Iterator;
import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Account;
import com.app.pojos.Address;
import com.app.pojos.Details;
import com.app.pojos.Loan;
import com.app.pojos.Role;

@Repository
@Transactional
public class AdminDaoImpl implements IAdminDao
{
	@Autowired
	private SessionFactory sf;
	
	public Details admin(String email,String password)
	{
        String jpql = "select d from Details d where d.email=:em and d.password=:pw";
		
		return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("em",email).setParameter("pw",password).getSingleResult();
	}
	
	
	    public List<Details> getAllCustomers()
	    {
	    	String jpql = "select d from Details d  where d.roletype=:rl";
	    	
	    	return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("rl",Role.CUSTOMER).getResultList();
	    	
	    }
	    
	    
	    public List<Details> getAllStaff()
	    {
	    	String jpql = "select d from Details d  where d.roletype=:rl";
	    	
	    	return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("rl",Role.STAFF).getResultList();
	    	
	    }
	    

	    public void addNewStaff(Details det)
	    {
	    	System.out.println("Inside the method of the add new staff!!!");
	    	sf.getCurrentSession().persist(det);
	    }

	    public void changeStatusByAdmin(Loan ln)
	    {
	    	System.out.println("inside the method of the update status by the admin");
	    	sf.getCurrentSession().saveOrUpdate(ln);
	    }
	    
	    public Account findAccount(int accid)
	    {
	    	String jpql = "select a from Account a where a.account_no=:aid";
	    	return sf.getCurrentSession().createQuery(jpql,Account.class).setParameter("aid",accid).getSingleResult();
	    	//acc.setAccount_bal(acc.getAccount_bal()+amount);
	    	//return sf.getCurrentSession().save(acc);
	    	
	    }
	    
	    public void setChangesAmount(Account acc)
	    {
	    	sf.getCurrentSession().saveOrUpdate(acc);
	    }


	    public Loan loanApproving(int lid)
	    {
	    	 System.out.println("inside the admin dao loan process method..");
	  	   String jpql = "select l from Loan l where l.lid=:id";
	  	   
	  	   return sf.getCurrentSession().createQuery(jpql,Loan.class).setParameter("id",lid).getSingleResult();
	    }
	    
	    public List<Loan> getLoanList()
	    {
	 	   String jpql = "select l from Loan l";
	 	   
	 	   return sf.getCurrentSession().createQuery(jpql,Loan.class).getResultList();
	    }
	    

public void deleteStaff(int id)
{
	  Details detail = sf.getCurrentSession().get(Details.class,id);
	  
	 List<Account> list = detail.getList();
	 Iterator<Account> ite = list.iterator();
	 while(ite.hasNext())
	 {
		Account acc = ite.next();
		ite.remove();
		
	 }
	
	 List<Loan> loan = detail.getLoan();
	 Iterator<Loan> iterate = loan.iterator();
	 while(iterate.hasNext())
	 {
		Loan ln= iterate.next();
		iterate.remove();
	 }
	 sf.getCurrentSession().delete(detail);
	  
}
public void newCustomers(Details det,Address add)
{
	System.out.println("Inside the method of the add new customers!!!");
	Integer Id=(Integer) sf.getCurrentSession().save(det);
	det.addAddress(add);
	
}


@Override
public Details updateDetailOfCustomer(int id) {
	   return sf.getCurrentSession().get(Details.class, id);
}


@Override
public Details saveChanges(Details d, Details det) {
	det.setLastName(d.getLastName());
	det.setFirstName(d.getFirstName());
	det.setEmail(d.getEmail());
	det.setMobileNo(d.getMobileNo());
	det.setAadharNo(d.getAadharNo());
	sf.getCurrentSession().update(det);;
	return det;
}


	    
}