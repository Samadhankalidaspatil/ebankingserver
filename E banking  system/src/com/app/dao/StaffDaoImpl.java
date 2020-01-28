
package com.app.dao;

import java.time.LocalDate;
import java.util.Date;
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
public class StaffDaoImpl implements IStaffDao
{
	@Autowired
	private SessionFactory sf;
	
    public Details staffDetail(String email,String password)
    {
    	String jpql = "select d from Details d where d.email=:em and d.password=:pw and d.roletype=:rl";
    	
       return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("em",email).setParameter("pw",password).
    		   setParameter("rl", Role.STAFF).
    		   getSingleResult();
       
    }
    
    public List<Details> getAllCustomers()
    {
    	String jpql = "select d from Details d  where d.roletype=:rl";
    	
    	return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("rl",Role.CUSTOMER).getResultList();
    	
    }
    public void newCustomers(Details det,Address add,Account acc)
    {
    	System.out.println("Inside the method of the add new customers!!!");
    	Integer Id=(Integer) sf.getCurrentSession().save(det);
    	det.addAddress(add);
    	det.addAccount(acc);
    	
    }
    

    	public List<Loan> getLoanList()
   {
	   String jpql = "select l from Loan l";
	   
	   return sf.getCurrentSession().createQuery(jpql,Loan.class).getResultList();
   }
    	

  public Loan loanProcess(int lid)
   {
	   System.out.println("inside the staff dao loan process method..");
	   String jpql = "select l from Loan l where l.lid=:id";
	   
	   return sf.getCurrentSession().createQuery(jpql,Loan.class).setParameter("id",lid).getSingleResult();
   }
   
   public void changeStatus(Loan  ln)
   {
	   System.out.println("inside the staus update method ");
	   sf.getCurrentSession().saveOrUpdate(ln);
   }

   
   public List<Account> allAccountList()
   {
	   String jpql = "select a from Account a";
	   System.out.println("inside the staff dao metthod of account list");
	   return sf.getCurrentSession().createQuery(jpql,Account.class).getResultList();
   }

public Account addAccountBalance(int account_no)
{
	   return sf.getCurrentSession().get(Account.class,account_no);
}

public void balanceOfAccount(Account acc)
{
	   sf.getCurrentSession().saveOrUpdate(acc);
}

public Account reduceAccountBalance(int account_no)
{
	   return sf.getCurrentSession().get(Account.class,account_no);
}

public void deleteCustomer(int id)
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


public Details updateCustomer(String email)
{
	String jpql = "select d from Details d where d.email=:em";

	return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("em",email).getSingleResult();

}

  public void setChangesOfUpdate(Details dt)
{
	   sf.getCurrentSession().saveOrUpdate(dt);
	  
}

public Details updateDetailOfCustomer(int id)
{
	   return sf.getCurrentSession().get(Details.class, id);
	   
}

public void startAccount(int id,Account acc)
{
	   Details det = sf.getCurrentSession().get(Details.class, id);

	   det.addAccount(acc);
}

	public void newCustomers(Details det,Address add)
 {
 	System.out.println("Inside the method of the add new customers!!!");
 	Integer Id=(Integer) sf.getCurrentSession().save(det);
 	det.addAddress(add);
 	
 	
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
