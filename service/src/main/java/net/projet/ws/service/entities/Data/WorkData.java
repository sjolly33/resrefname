package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;

import net.projet.ws.service.entities.Reproduction.ReproductionPaint;
import net.projet.ws.service.entities.Reproduction.ReproductionSculpture;

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


public class WorkData{
	private static List<Paint> paints = new ArrayList<Paint>();
	private static List<Sculpture> sculptures = new ArrayList<Sculpture>();

	private static final Logger LOG = Logger.getLogger(WorkData.class);

	public static List<Paint> initPaints(){
		LOG.info("initPaint");
		Paint paint1 = new Paint();
		paint1.setReproductions(ReproductionData.initReproductionPaints());
		paint1.setTitle("Miku painting. Kawaii desu ne !");
		//work1.setTitle("THE paint");
		//work1.setDescription("Work en souvenir de qqchose");
		paints.add(paint1);
		Paint paint2 = new Paint();
		paints.add(paint2);
		paint2.setTitle("Anata !");
		return new ArrayList<Paint>(paints);
	}

	public static List<Paint> getPaints(){
		LOG.info("getPaints");
		List<Paint> paints = new ArrayList<Paint>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Paint> cq = cb.createQuery(Paint.class);
        	Root<Paint> rootEntry = cq.from(Paint.class);
        	CriteriaQuery<Paint> all = cq.select(rootEntry);
        	TypedQuery<Paint> allQuery = em.createQuery(all);
        	paints = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return paints;
	}

	public static Paint getPaint(int id){
		LOG.info("getPaint");
		int index = 0;
		Paint paint = new Paint();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			paint = em.find(Paint.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return paint;
	}

	public static Paint getPaint(List<Paint> paints, int id){
		LOG.info("getPaint");
		int index = 0;
		Paint paint = new Paint();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			paint = em.find(Paint.class, id);
			index = paints.indexOf(paint);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return paint;
		else
			return new Paint();
	}

	public static Response addPaint(Paint paint){
		LOG.info("addPaintPersist");
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(paint);
			LOG.debug("Add a new paint ");
			return Response.ok(paint).build();
		} catch (RuntimeException re) {
			LOG.error("add paint failed", re);
			return Response.status(400).entity("paint create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static Paint updatePaint(Paint paint){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(paint);
			LOG.debug("merge a paint ");
		} catch (RuntimeException re) {
			LOG.error("merge paint failed", re);
		}finally{
			tx.commit();
		}
		return paint;
	}

	public static void deletePaint(Paint paint){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(paint);
			LOG.debug("delete a paint ");
		} catch (RuntimeException re) {
			LOG.error("delete paint failed", re);
		}finally{
			tx.commit();
		}
	}

	public static List<Sculpture> initSculptures(){
		LOG.info("initSculptures");
		Sculpture sculpture1 = new Sculpture();
		//work1.setTitle("THE paint");
		//work1.setDescription("Work en souvenir de qqchose");
		sculptures.add(sculpture1);
		Sculpture sculpture2 = new Sculpture();
		sculptures.add(sculpture2);
		return new ArrayList<Sculpture>(sculptures);
	}

	public static List<Sculpture> getSculptures(){
		LOG.info("getSculptures");
		List<Sculpture> sculptures = new ArrayList<Sculpture>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Sculpture> cq = cb.createQuery(Sculpture.class);
        	Root<Sculpture> rootEntry = cq.from(Sculpture.class);
        	CriteriaQuery<Sculpture> all = cq.select(rootEntry);
        	TypedQuery<Sculpture> allQuery = em.createQuery(all);
        	sculptures = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return sculptures;
	}

	public static Sculpture getSculpture(int id){
		LOG.info("getSculpture");
		int index = 0;
		Sculpture sculpture = new Sculpture();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			sculpture = em.find(Sculpture.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return sculpture;
	}

	public static Sculpture getSculpture(List<Sculpture> sculptures, int id){
		LOG.info("getSculpture");
		int index = 0;
		Sculpture sculpture = new Sculpture();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			sculpture = em.find(Sculpture.class, id);
			index = sculptures.indexOf(sculpture);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return sculpture;
		else
			return new Sculpture();
	}

	public static Response addSculpture(Sculpture sculpture){
		LOG.info("addPaintPersist");
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(sculpture);
			LOG.debug("Add a new sculpture ");
			return Response.ok(sculpture).build();
		} catch (RuntimeException re) {
			LOG.error("add sculpture failed", re);
			return Response.status(400).entity("sculpture create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static Sculpture updateSculpture(Sculpture sculpture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(sculpture);
			LOG.debug("merge a sculpture ");
		} catch (RuntimeException re) {
			LOG.error("merge sculpture failed", re);
		}finally{
			tx.commit();
		}
		return sculpture;
	}

	public static void deleteSculpture(Sculpture sculpture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(sculpture);
			LOG.debug("delete a sculpture ");
		} catch (RuntimeException re) {
			LOG.error("delete sculpture failed", re);
		}finally{
			tx.commit();
		}
	}
	
}
