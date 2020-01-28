package com.app.dao;

import java.util.List;

import org.apache.catalina.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Details;

@Repository
@Transactional
public class UserDao implements IUserDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public List<Details> getAllUser() {
		String jpql = "select d from Details d";
		return sf.getCurrentSession().createQuery(jpql, Details.class).getResultList();
	}

	@Override
	public Details validateUser(String email, String password) {
		String jpql = "select d from Details d where d.email=:em and d.password=:pass";
		return sf.getCurrentSession().createQuery(jpql, Details.class).setParameter("em", email)
				.setParameter("pass", password).getSingleResult();
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

	@Override
	public Details getUserWithAddress(int id)
	{
		String jpql = "select u from Details u join fetch u.address where u.id=:i_d";
		return sf.getCurrentSession().createQuery(jpql, Details.class).setParameter("i_d", id).getSingleResult();
		
	}
}
