package net.projet.ws.service.entities;

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

@Path("/museumTest")

public class MuseumSampleTest{
	private static List<MuseumTest> museums = new ArrayList<MuseumTest>();
	
	private static final Logger LOG = Logger.getLogger(MuseumSampleTest.class);

	static {

 	MuseumTest museum1 = new MuseumTest();
 	museum1.setID(1);
 	museum1.setName("museum1");
 	museum1.setTheme("HipHop");
 	museum1.setAdress("quelque_part");
 	museum1.setInformation("dedeideidiednediez");
 	museums.add(museum1);

 	MuseumTest museum2 = new MuseumTest();
 	museum2.setID(2);
 	museum2.setName("museum2");
 	museum2.setTheme("LoL");
 	museum2.setAdress("quelque_part");
 	museums.add(museum2);

	
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

	@GET
	@Path("/json/museums")
	@Produces("application/json")
	public List<MuseumTest> listMuseums(){
		LOG.info("getMuseums");
		return new ArrayList(museums);
	}
}
