package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Museum;
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

public class MuseumData{
	private static List<Museum> museums = new ArrayList<Museum>();
	
	private static final Logger LOG = Logger.getLogger(MuseumData.class);

	static {

		Museum museum1 = new Museum();
		museum1.setName("Museum");
		museum1.setTheme("Hazard");
		museum1.setAdress("NY");

		museum1.setPictures(PictureData.initPictures());
		museum1.setWorks(WorkData.initWorks());

		List<Work> works = museum1.getWorks();
		List<Picture> pictures = museum1.getPictures();
		for(int i=0;i<pictures.size();++i){
			pictures.get(i).setWork(works.get(0));
		}
		/*for(int i=0;i<works.size();++i){
			works.get(i).setPicture(pictures);
		}*/
		museum1.setWorks(works);
		museum1.setPictures(pictures);

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
		List<Museum> museums = new ArrayList<Museum>();
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
}
