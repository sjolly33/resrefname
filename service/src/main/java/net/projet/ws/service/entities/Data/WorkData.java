package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
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
	private static List<Work> works = new ArrayList<Work>();
	
	private static final Logger LOG = Logger.getLogger(WorkData.class);

	public static List<Work> initWorks(){
		LOG.info("initWork");
		Work work1 = new Paint();
		work1.setTitle("THE paint");
		work1.setDescription("Work en souvenir de qqchose");
		works.add(work1);
		Work work2 = new Paint();
		works.add(work2);
		return new ArrayList<Work>(works);
	}

	public static List<Work> getWorks(){
		LOG.info("getWorks");
		List<Work> works = new ArrayList<Work>();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Work> cq = cb.createQuery(Work.class);
        	Root<Work> rootEntry = cq.from(Work.class);
        	CriteriaQuery<Work> all = cq.select(rootEntry);
        	TypedQuery<Work> allQuery = em.createQuery(all);
        	works = allQuery.getResultList();
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		return works;
	}

	public static Work getWork(List<Work> works, int id){
		LOG.info("getWork");
		int index = 0;
		Work work = new Work();
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = null;
		try{
			tx = em.getTransaction();
			tx.begin();
			work = em.find(Work.class, id);
			index = works.indexOf(work);
			tx.commit();
		}catch(Exception re)
		{
			if(tx!=null)
				LOG.error("Something went wrong; Discard all partial changes");
			tx.rollback();
		}finally{
		}
		if(index != -1)
			return work;
		else
			return new Work();
	}
}
