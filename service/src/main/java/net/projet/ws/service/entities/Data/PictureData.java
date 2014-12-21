package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Museum;
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

public class PictureData{
	private static List<Picture> pictures = new ArrayList<Picture>();
	
	private static final Logger LOG = Logger.getLogger(PictureData.class);

	public static List<Picture> initPictures(){
		LOG.info("initPicture");
		Picture picture1 = new Picture();
		picture1.setTitle("THE photo");
		picture1.setDescription("Photo en souvenir de qqchose");
		picture1.setResume("Une super photo representant rien, blabla");
		pictures.add(picture1);
		Picture picture2 = new Picture();
		pictures.add(picture2);
		return new ArrayList<Picture>(pictures);
	}

	public static List<Picture> getPictures(){
		LOG.info("getPictures");
		List<Picture> pictures = new ArrayList<Picture>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Picture> cq = cb.createQuery(Picture.class);
        	Root<Picture> rootEntry = cq.from(Picture.class);
        	CriteriaQuery<Picture> all = cq.select(rootEntry);
        	TypedQuery<Picture> allQuery = em.createQuery(all);
        	pictures = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return pictures;
	}

	public static Picture getPicture(List<Picture> pictures, int id){
		LOG.info("getPicture");
		int index = 0;
		Picture picture = new Picture();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			picture = em.find(Picture.class, id);
			index = pictures.indexOf(picture);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return picture;
		else
			return new Picture();
	}
	
	public static Response addPicture(Picture picture){
		LOG.info("addPicturePersist");
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(picture);
			LOG.debug("Add a new picture ");
			return Response.ok(picture).build();
		} catch (RuntimeException re) {
			LOG.error("add picture failed", re);
			return Response.status(400).entity("picture create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static Picture getPicture(int id){
		LOG.info("getPicture");
		int index = 0;
		Picture picture = new Picture();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			picture = em.find(Picture.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return picture;
	}

	public static Picture updatePicture(Picture picture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(picture);
			LOG.debug("merge a picture ");
		} catch (RuntimeException re) {
			LOG.error("merge picture failed", re);
		}finally{
			tx.commit();
		}
		return picture;
	}

	public static void deletePicture(Picture picture){
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
}
