package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;
import net.projet.ws.service.entities.Worker.Author;
import net.projet.ws.service.entities.Collection.CollectionPicture;
import net.projet.ws.service.entities.Collection.CollectionWork;

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

public class MuseumData{
	private static List<Museum> museums = new ArrayList<Museum>();
	
	private static final Logger LOG = Logger.getLogger(MuseumData.class);

	static {

		// --------------- Start data to test with the front-end ----------------

		Museum museum1 = new Museum();
		museum1.setName("Museum");
		museum1.setTheme("Hazard");
		museum1.setAdress("NY");

		museum1.setPictures(PictureData.initPictures());
		museum1.setPaints(WorkData.initPaints());
		museum1.setAuthors(AuthorData.initAuthors());

		museum1.setCollectionsWorks(CollectionData.initCollectionWork());
		museum1.setCollectionsPictures(CollectionData.initCollectionPicture());

		List<Paint> works = museum1.getPaints();
		List<Author> authors = museum1.getAuthors();
		for(int i=0;i<works.size();++i){
			works.get(i).setAuthor(authors.get(0));
		}

		List<Picture> pictures = museum1.getPictures();
		for(int i=0;i<pictures.size();++i){
			pictures.get(i).setPaint(works.get(0));
		}

		for(int i=0;i<works.size();++i){
			works.get(i).setPictures(pictures);
		}

		museum1.setPaints(works);
		museum1.setPictures(pictures);

		List<CollectionPicture> cPictures = museum1.getCollectionsPictures();
		for(int i=0;i<cPictures.size();++i){
			cPictures.get(i).setRefPicture(museum1.getPictures());
		}
		museum1.setCollectionsPictures(cPictures);

		List<CollectionWork> cWorks = museum1.getCollectionsWorks();
		for(int i=0;i<cWorks.size();++i){
			cWorks.get(i).setRefPaint(museum1.getPaints());
		}
		museum1.setCollectionsWorks(cWorks);

		for(int i=0;i<authors.size();++i){
			authors.get(i).setMuseum(museum1);
			authors.get(i).setPaints(works);
		}

		for(int i=0;i<pictures.size();++i){
			pictures.get(i).setMuseum(museum1);
		}

		for(int i=0;i<cWorks.size();++i){
			cWorks.get(i).setMuseum(museum1);
		}

		for(int i=0;i<cPictures.size();++i){
			cPictures.get(i).setMuseum(museum1);
		}

		for(int i=0;i<works.size();++i){
			works.get(i).setMuseum(museum1);
		}

		museums.add(museum1);
		
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			for(int i=0;i<museums.size();++i){
				em.persist(museums.get(i));
			}
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}

 	}

	public static List<Museum> getMuseums(){
		LOG.info("getMuseums");
		List<Museum> museums = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Museum> cq = cb.createQuery(Museum.class);
        	Root<Museum> rootEntry = cq.from(Museum.class);
        	CriteriaQuery<Museum> all = cq.select(rootEntry);
        	TypedQuery<Museum> allQuery = em.createQuery(all);
        	museums = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		
		return museums;
	}

	public static Museum getMuseum(int id){
		LOG.info("getMuseum");
		Museum museum = null;
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
	}

	public static void deleteMuseum(Museum museum){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(museum);
			LOG.debug("delete a museum ");
		} catch (RuntimeException re) {
			LOG.error("delete museum failed", re);
		}finally{
			tx.commit();
		}
	}
}
