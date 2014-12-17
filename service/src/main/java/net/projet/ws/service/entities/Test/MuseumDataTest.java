package net.projet.ws.service.entities.Test;

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

public class MuseumDataTest{
	private static List<MuseumTest> museums = new ArrayList<MuseumTest>();
	
	private static final Logger LOG = Logger.getLogger(MuseumDataTest.class);

	static {

 	MuseumTest museum1 = new MuseumTest();
 	museum1.setID(1);
 	museum1.setName("museum1");
 	museum1.setTheme("HipHop");
 	museum1.setAdress("quelque_part");
 	museum1.setInformation("dedeideidiednediez");
 	List<ElementTest> elements = new ArrayList<ElementTest>();
 	ElementTest element1 = new ElementTest();
 	element1.setName("titi");
 	elements.add(element1);
 	museum1.setElements(elements);

 	/*List<IMuseum> elements = new ArrayList<IMuseum>();
 	ElementTest element1 = new ElementTest();
	element1.setName("element1");
	elements.add((IMuseum)element1);
	ElementTest element2 = new ElementTest();
	element2.setName("element2");
	elements.add((IMuseum)element2);
	museum1.setElements(elements);*/
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

	public static List<MuseumTest> getMuseums(){
		LOG.info("getMuseums");
		return new ArrayList<MuseumTest>(museums);
	}

	public static MuseumTest getMuseum(int idMuseum){
		LOG.info("getMuseum");
		for(int i=0;i<museums.size();++i){
			if(museums.get(i).getID() == idMuseum)
				return museums.get(i);
		}
		return museums.get(0);
	}

	public static List<ElementTest> getListElements(int idMuseum){
		LOG.info("getMuseumElements");
		List<ElementTest> elements = new ArrayList<ElementTest>();
		for(int i=0; i<museums.size();++i){
			if(museums.get(i).getID() == idMuseum){
				elements = new ArrayList<ElementTest>(museums.get(i).getElements());
				break;
			}
		}
		return elements;
	}
}
