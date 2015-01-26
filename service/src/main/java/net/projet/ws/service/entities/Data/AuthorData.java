package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Worker.Author;


import net.projet.ws.service.filters.JpaUtil;

import javax.ws.rs.core.Response;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.persistence.criteria.*;

import javax.ws.rs.core.Response;

public class AuthorData{
	private static 	List<Author> authors = new ArrayList<Author>();
	
	private static final Logger LOG = Logger.getLogger(AuthorData.class);

	public static List<Author> initAuthors(){

		Author author1 = new Author();
		author1.setName("AL");
		author1.setAdress("Pessac");
		authors.add(author1);

		Author author2 = new Author();
		author2.setName("Victor");
		author2.setAdress("Bar");
		authors.add(author2);
		
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			for(int i=0;i<authors.size();++i){
				em.persist(authors.get(i));
			}
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}

		return authors;
 	}

	public static List<Author> getAuthors(){
		LOG.info("getAuthors");
		List<Author> authors = new ArrayList<Author>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        	Root<Author> rootEntry = cq.from(Author.class);
        	CriteriaQuery<Author> all = cq.select(rootEntry);
        	TypedQuery<Author> allQuery = em.createQuery(all);
        	authors = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		
		return authors;
	}

	public static Author getAuthor(List<Author> authors, int id){
		LOG.info("getAuthor");
		int index = 0;
		Author author = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			author = em.find(Author.class, id);
			index = authors.indexOf(author);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return author;
		else
			return null;
	}

	public static Author getAuthor(int id){
		LOG.info("getAuthor");
		Author author = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			author = em.find(Author.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return author;
	}

	public static Response addAuthor(Author author){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(author);
			LOG.debug("Add a new author ");
			return Response.ok(author).build();
		} catch (RuntimeException re) {
			LOG.error("add author failed", re);
			return Response.status(400).entity("author create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static Author updateAuthor(Author author){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(author);
			LOG.debug("merge a museum ");
		} catch (RuntimeException re) {
			LOG.error("merge museum failed", re);
		}finally{
			tx.commit();
		}
		return author;
	}

	public static void deleteAuthor(Author author){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(author);
			LOG.debug("delete a author ");
		} catch (RuntimeException re) {
			LOG.error("delete author failed", re);
		}finally{
			tx.commit();
		}
	}
}
