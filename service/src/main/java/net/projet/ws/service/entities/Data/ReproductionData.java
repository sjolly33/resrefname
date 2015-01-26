package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Reproduction.ReproductionPaint;
import net.projet.ws.service.entities.Reproduction.ReproductionSculpture;
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

public class ReproductionData{
	private static List<ReproductionPaint> reproductionPaints = new ArrayList<ReproductionPaint>();
	private static List<ReproductionSculpture> reproductionSculptures = new ArrayList<ReproductionSculpture>();
	
	private static final Logger LOG = Logger.getLogger(ReproductionData.class);

	public static List<ReproductionPaint> initReproductionPaints(){
		LOG.info("initReproductionPaints");
		ReproductionPaint repro1 = new ReproductionPaint();
		repro1.setTitle("Chinese");
		repro1.setDescription("toc");
		reproductionPaints.add(repro1);
		ReproductionPaint repro2 = new ReproductionPaint();
		reproductionPaints.add(repro2);
		return new ArrayList<ReproductionPaint>(reproductionPaints);
	}

	public static List<ReproductionPaint> getReproductionPaints(){
		LOG.info("getReproductionPaints");
		List<ReproductionPaint> reproductionPaints = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<ReproductionPaint> cq = cb.createQuery(ReproductionPaint.class);
        	Root<ReproductionPaint> rootEntry = cq.from(ReproductionPaint.class);
        	CriteriaQuery<ReproductionPaint> all = cq.select(rootEntry);
        	TypedQuery<ReproductionPaint> allQuery = em.createQuery(all);
        	reproductionPaints = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return reproductionPaints;
	}

	
	public static Response addReproductionPaint(ReproductionPaint reproductionPaint){
		LOG.info("addReproductionPaintPersist");
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(reproductionPaint);
			LOG.debug("Add a new ReproductionPaint ");
			return Response.ok(reproductionPaint).build();
		} catch (RuntimeException re) {
			LOG.error("add ReproductionPaint failed", re);
			return Response.status(400).entity("ReproductionPaint create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static ReproductionPaint getReproductionPaint(int id){
		LOG.info("getReproductionPaint");
		int index = 0;
		ReproductionPaint reproductionPaint = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			reproductionPaint = em.find(ReproductionPaint.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return reproductionPaint;
	}

	public static ReproductionPaint updateReproductionPaint(ReproductionPaint reproductionPaint){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(reproductionPaint);
			LOG.debug("merge a ReproductionPaint ");
		} catch (RuntimeException re) {
			LOG.error("merge ReproductionPaint failed", re);
		}finally{
			tx.commit();
		}
		return reproductionPaint;
	}

	public static void deleteReproductionPaint(ReproductionPaint reproductionPaint){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(reproductionPaint);
			LOG.debug("delete a ReproductionPaint ");
		} catch (RuntimeException re) {
			LOG.error("delete ReproductionPaint failed", re);
		}finally{
			tx.commit();
		}
	}

	public static List<ReproductionSculpture> initReproductionSculptures(){
		LOG.info("initReproductionSculptures");
		ReproductionSculpture repro1 = new ReproductionSculpture();
		repro1.setTitle("THE photo");
		repro1.setDescription("Photo en souvenir de qqchose");
		reproductionSculptures.add(repro1);
		ReproductionSculpture repro2 = new ReproductionSculpture();
		reproductionSculptures.add(repro2);
		return new ArrayList<ReproductionSculpture>(reproductionSculptures);
	}

	public static List<ReproductionSculpture> getReproductionSculptures(){
		LOG.info("getReproductionSculptures");
		List<ReproductionSculpture> reproductionSculptures = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<ReproductionSculpture> cq = cb.createQuery(ReproductionSculpture.class);
        	Root<ReproductionSculpture> rootEntry = cq.from(ReproductionSculpture.class);
        	CriteriaQuery<ReproductionSculpture> all = cq.select(rootEntry);
        	TypedQuery<ReproductionSculpture> allQuery = em.createQuery(all);
        	reproductionSculptures = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return reproductionSculptures;
	}

	
	public static Response addReproductionSculpture(ReproductionSculpture reproductionSculpture){
		LOG.info("addReproductionSculpturePersist");
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.persist(reproductionSculpture);
			LOG.debug("Add a new ReproductionSculpture ");
			return Response.ok(reproductionSculpture).build();
		} catch (RuntimeException re) {
			LOG.error("add ReproductionSculpture failed", re);
			return Response.status(400).entity("ReproductionSculpture create failed!").build();
		}finally{
			tx.commit();
		}
	}

	public static ReproductionSculpture getReproductionSculpture(int id){
		LOG.info("getReproductionSculpture");
		int index = 0;
		ReproductionSculpture reproductionSculpture = null;
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			reproductionSculpture = em.find(ReproductionSculpture.class, id);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return reproductionSculpture;
	}

	public static ReproductionSculpture updateReproductionSculpture(ReproductionSculpture reproductionSculpture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.merge(reproductionSculpture);
			LOG.debug("merge a ReproductionSculpture ");
		} catch (RuntimeException re) {
			LOG.error("merge ReproductionSculpture failed", re);
		}finally{
			tx.commit();
		}
		return reproductionSculpture;
	}

	public static void deleteReproductionSculpture(ReproductionSculpture reproductionSculpture){
		EntityManager em= JpaUtil.getEntityManager();
		EntityTransaction tx=em.getTransaction();
		try{
			tx.begin();
			em.remove(reproductionSculpture);
			LOG.debug("delete a ReproductionSculpture ");
		} catch (RuntimeException re) {
			LOG.error("delete ReproductionSculpture failed", re);
		}finally{
			tx.commit();
		}
	}
}
