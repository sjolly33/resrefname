package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Collection.CollectionMuseum;
import net.projet.ws.service.entities.Collection.CollectionWork;
import net.projet.ws.service.entities.Collection.CollectionPicture;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Work.Work;
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

public class CollectionData{
	private static List<CollectionWork> collectionsWork = new ArrayList<CollectionWork>();
	private static List<CollectionPicture> collectionsPicture = new ArrayList<CollectionPicture>();

	private static final Logger LOG = Logger.getLogger(CollectionMuseum.class);

	public static List<CollectionPicture> initCollectionPicture() {

		CollectionPicture cMuseum1 = new CollectionPicture();
		cMuseum1.setTitle("CPicture");
		collectionsPicture.add(cMuseum1);

		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			for(int i=0;i<collectionsPicture.size();++i){
				em.persist(collectionsPicture.get(i));
			}
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}

		return collectionsPicture;

 	}

 	public static List<CollectionWork> initCollectionWork() {

		CollectionWork cMuseum2 = new CollectionWork();
		cMuseum2.setTitle("cWork");
		collectionsWork.add(cMuseum2);

		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			for(int i=0;i<collectionsWork.size();++i){
				em.persist(collectionsWork.get(i));
			}
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}

		return collectionsWork;

 	}

	public static List<CollectionPicture> getCollectionsPictures(){
		LOG.info("getcPicture");
		List<CollectionPicture> collections = new ArrayList<CollectionPicture>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<CollectionPicture> cq = cb.createQuery(CollectionPicture.class);
        	Root<CollectionPicture> rootEntry = cq.from(CollectionPicture.class);
        	CriteriaQuery<CollectionPicture> all = cq.select(rootEntry);
        	TypedQuery<CollectionPicture> allQuery = em.createQuery(all);
        	collections = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		
		return collections;
	}

	public static List<CollectionWork> getCollectionsWorks(){
		LOG.info("getcWork");
		List<CollectionWork> collections = new ArrayList<CollectionWork>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<CollectionWork> cq = cb.createQuery(CollectionWork.class);
        	Root<CollectionWork> rootEntry = cq.from(CollectionWork.class);
        	CriteriaQuery<CollectionWork> all = cq.select(rootEntry);
        	TypedQuery<CollectionWork> allQuery = em.createQuery(all);
        	collections = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		
		return collections;
	}

	public static CollectionPicture getCollectionPicture(int id){
		LOG.info("getcPicture");
		int index = 0;
		CollectionPicture cPicture = new CollectionPicture();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			cPicture = em.find(CollectionPicture.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return cPicture;
	}

	public static CollectionWork getCollectionWork(int id){
		LOG.info("getcPicture");
		int index = 0;
		CollectionWork cWork = new CollectionWork();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			cWork = em.find(CollectionWork.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return cWork;
	}

	public static CollectionPicture getCollectionPicture(List<CollectionPicture> cPictures, int id){
		LOG.info("getcPicture");
		int index = 0;
		CollectionPicture cPicture = new CollectionPicture();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			cPicture = em.find(CollectionPicture.class, id);
			index = cPictures.indexOf(cPicture);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return cPicture;
		else
			return new CollectionPicture();
	}

	public static CollectionWork getCollectionWork(List<CollectionWork> cWorks, int id){
		LOG.info("getcWork");
		int index = 0;
		CollectionWork cWork = new CollectionWork();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			cWork = em.find(CollectionWork.class, id);
			index = cWorks.indexOf(cWork);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return cWork;
		else
			return new CollectionWork();
	}

	public static Response addCollectionPicture(CollectionPicture cPicture){
		LOG.info("addCollectionPicture");
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			em.persist(cPicture);
			LOG.debug("Add a new CollectionPicture ");
			return Response.ok(cPicture).build();
		} catch (RuntimeException re) {
			LOG.error("add collectionPicture failed", re);
			return Response.status(400).entity("collectionPicture create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static Response addCollectionWork(CollectionWork cWork){
		LOG.info("addCollectionWork");
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			em.persist(cWork);
			LOG.debug("Add a new collectionWork ");
			return Response.ok(cWork).build();
		} catch (RuntimeException re) {
			LOG.error("add collectionWork failed", re);
			return Response.status(400).entity("collectionWork create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static CollectionPicture updateCollectionPicture(CollectionPicture cPicture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(cPicture);
			LOG.debug("merge a collectionPicture ");
		} catch (RuntimeException re) {
			LOG.error("merge collectionPicture failed", re);
		}finally{
			tx.commit();
		}
		return cPicture;
	}

	public static CollectionWork updateCollectionWork(CollectionWork cWork){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(cWork);
			LOG.debug("merge a collectionWork ");
		} catch (RuntimeException re) {
			LOG.error("merge collectionWork failed", re);
		}finally{
			tx.commit();
		}
		return cWork;
	}

	public static void deleteCollectionPicture(CollectionPicture picture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(picture);
			LOG.debug("delete a picture ");
		} catch (RuntimeException re) {
			LOG.error("delete picture failed", re);
		}finally{
			tx.commit();
		}
	}

	public static void deleteCollectionWork(CollectionWork work){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(work);
			LOG.debug("delete a work ");
		} catch (RuntimeException re) {
			LOG.error("delete work failed", re);
		}finally{
			tx.commit();
		}
	}
}
