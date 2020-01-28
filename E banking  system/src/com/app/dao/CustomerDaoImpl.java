package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.app.pojos.Account;
import com.app.pojos.Details;
import com.app.pojos.Loan;
import com.app.pojos.Transfer;


@Repository
@Transactional
public class CustomerDaoImpl implements ICustomerDao
{
	@Autowired
	private SessionFactory sf;
	
	public Details customer(String email,String password)
	{
		String jpql = "select d from Details d where d.email=:em and d.password=:pw";
		
		return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("em",email).setParameter("pw",password).getSingleResult();
		
	}
	
	public void transfer(int send_acc,int rec_acc,double amount,Transfer trans)
	{
	    String s_jpql = "select a from Account a where a.account_no=:sacc";
	    String r_jpql = "select a from Account a where a.account_no=:racc";
	    
		 Account s_acc=   sf.getCurrentSession().createQuery(s_jpql,Account.class).setParameter("sacc",send_acc).getSingleResult();
		 Account r_acc=   sf.getCurrentSession().createQuery(r_jpql,Account.class).setParameter("racc",rec_acc).getSingleResult();
		 
		 s_acc.setAccount_bal(s_acc.getAccount_bal()-amount);
		 r_acc.setAccount_bal(r_acc.getAccount_bal()+amount);
		 
		 sf.getCurrentSession().persist(s_acc);
		 sf.getCurrentSession().persist(r_acc);
		 
		 sf.getCurrentSession().persist(trans);
	 
		
	}
	
	public Details account(int id)
	{
		String jpql = "select d from Details d join fetch d.list=:did";
		return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("did", id).getSingleResult();
	}
	
	public Details cusAcc(int id)
	{
		String jpql = "select d from Details d join fetch d.list=:did";
		return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("did", id).getSingleResult();
	}

	public Details setPasswordNew(String email1)
	{
		String jpql = "select d from Details d where d.email=:em";
		
		return sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("em", email1).getSingleResult();
	}
	
	public void addPassword(Details d)
	{
	  sf.getCurrentSession().saveOrUpdate(d);
	}



	public void addLoanDetailEdu(Loan ln,int id)
    {
    	String jpql = "select d from Details d where d.id=:lid";
    	Details detail = sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("lid",id).getSingleResult();
    	detail.addLoan(ln);
    	
    }
    
    public void addLoanDetailHome(Loan ln,int id)
    {
    	String jpql = "select d from Details d where d.id=:lid";
    	Details detail = sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("lid",id).getSingleResult();
    	detail.addLoan(ln);
    	
    }

    public void addLoanDetailPersonal(Loan ln,int id)
    {
    	String jpql = "select d from Details d where d.id=:lid";
    	Details detail = sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("lid",id).getSingleResult();
    	detail.addLoan(ln);
    	
    }
    
    public List<Account> getAccountList(int id)
    {
    	String jpql = "select d from Details d left outer join fetch d.list where d.id=:aid";
    	Details detail = sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("aid",id).getSingleResult();
        List<Account> acc = detail.getList();
        return acc;
    }

    public List<Loan> getLoanList(int id)
    {
    	String jpql ="select d from Details d left outer join fetch d.loan where d.id=:lid";
    	Details detail = sf.getCurrentSession().createQuery(jpql,Details.class).setParameter("lid",id).getSingleResult();
        List<Loan> loan = detail.getLoan();
        return loan;
    }

}
