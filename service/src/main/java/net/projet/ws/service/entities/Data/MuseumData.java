package net.projet.ws.service.entities.Data;

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

public class MuseumData{
	private static List<Museum> museums = new ArrayList<Museum>();
	
	private static final Logger LOG = Logger.getLogger(MuseumData.class);

	static {

	
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
		return new ArrayList(museums);
	}
}
