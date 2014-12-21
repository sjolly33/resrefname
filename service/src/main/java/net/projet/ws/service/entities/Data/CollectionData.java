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
/*
	public static Museum getMuseum(int id){
		LOG.info("getMuseum");
		Museum museum = new Museum();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			museum = em.find(Museum.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return museum;
	}

	public static Response addMuseum(Museum museum){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(museum);
			LOG.debug("Add a new museum ");
			return Response.ok(museum).build();
		} catch (RuntimeException re) {
			LOG.error("add museum failed", re);
			return Response.status(400).entity("museum create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static Museum updateMuseum(Museum museum){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(museum);
			LOG.debug("merge a museum ");
		} catch (RuntimeException re) {
			LOG.error("merge museum failed", re);
		}finally{
			tx.commit();
		}
		return museum;
	}*/
}
