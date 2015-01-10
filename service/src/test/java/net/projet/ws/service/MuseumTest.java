package net.projet.ws.service;

import net.projet.ws.service.filters.JpaUtil;
import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Work.Work;
//import net.projet.ws.service.entities.Work.Paint;

import javax.persistence.*;
import java.util.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.rules.TestName;
import org.junit.Rule;
import static org.junit.Assert.*;
import java.io.*; 
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.CriteriaDelete;

import org.dbunit.dataset.ITable;
import org.dbunit.util.TableFormatter;
import org.dbunit.Assertion;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.xml.FlatDtdDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.log4j.Logger;


public class MuseumTest
{	

	@Rule public TestName name = new TestName();
	
	private static EntityManager em;
	private static EntityTransaction tx;
	private static Logger LOG = Logger.getLogger(MuseumTest.class.getName());
	
	@BeforeClass
	public static void initEntityManager() throws Exception
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuseumTest");
		em = emf.createEntityManager();
	}

	@Before
	public void initTransaction() throws Exception 
	{
		tx = em.getTransaction();
		seedData();
	}

	protected void seedData() throws Exception 
	{
		tx.begin();
		Connection connection = em.unwrap(java.sql.Connection.class);
		try {
			IDatabaseConnection dbUnitCon = new DatabaseConnection(connection);
			dbUnitCon.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
			IDataSet dataset;
			FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
			flatXmlDataSetBuilder.setColumnSensing(true);
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/dataset.xml");
			if(in !=null){
				LOG.warn("DataSet found");
				dataset = flatXmlDataSetBuilder.build(in);
			} else {
				LOG.warn("DataSet not found");
				dataset= new DefaultDataSet();
			}
			DatabaseOperation.CLEAN_INSERT.execute(dbUnitCon, dataset);
		} finally {
			tx.commit();
		}
	}

	@After
	public void afterTests() throws Exception {
		Class driverClass = Class.forName("org.h2.Driver");
		Connection jdbcConnection = DriverManager.getConnection("jdbc:h2:mem://localhost:9101/dbunit", "sa", "");
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
		// full database export
		IDataSet fullDataSet = connection.createDataSet();
		FlatXmlDataSet.write(fullDataSet, new FileOutputStream("target/"+name.getMethodName()+".xml"));
		FlatDtdDataSet.write(connection.createDataSet(), new FileOutputStream("target/"+"test.dtd"));
	}

	@AfterClass
	public static void closeEntityManager() throws Exception
	{
		em.close();
	}

	@Test
	public final void testExecuted() 
	{
		LOG.info("testExecuted");
		assertTrue(true);
	}

	@Test
	public final void findMuseum() 
	{
		LOG.info("testFindMuseum");
		try{
			tx.begin();
			Museum museum = em.find(Museum.class, 1);
			assertEquals(museum.getID(), 1);
		} catch (RuntimeException re) {
			LOG.error("find all museums failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
	
	@Test
	public final void findAllMuseum() 
	{
		LOG.info("testFindAllMuseum");
		try{
			tx.begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
        	CriteriaQuery<Museum> cq = cb.createQuery(Museum.class);
        	Root<Museum> rootEntry = cq.from(Museum.class);
        	CriteriaQuery<Museum> all = cq.select(rootEntry);
        	TypedQuery<Museum> allQuery = em.createQuery(all);
        	List<Museum> museums = allQuery.getResultList();
        	assertEquals(museums.size(), 2);
			LOG.debug("find all museums successful, result size: "+ museums.size());
		} catch (RuntimeException re) {
			LOG.error("find all museums failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
	
	@Test
	public final void createMuseum() throws Exception
	{
		LOG.info("testCreateMuseum");
		try{
			tx.begin();
			Museum museum = new Museum();
			museum.setID(3);
			museum.setName("museumTest");
			museum.setTheme("lolCat");
			museum.setAdress("WWW");
			em.persist(museum);
			assertTrue(em.find(Museum.class, 3) != null);
		}catch (RuntimeException re) {
			LOG.error("testCreateMuseum failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}

	@Test
	public void removeOneMuseum() throws Exception {
		LOG.info("removeOneMuseum");
		try{
			tx.begin();
			Museum museum = em.find(Museum.class, 2);
			em.remove(museum);
			assertEquals((em.find(Museum.class, 2)), null);
		}catch (RuntimeException re) {
			LOG.error("removeOneMuseum failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}

	@Test
	public void updateOneMuseum() throws Exception {
		LOG.info("updateOneMuseum");
		try{
			tx.begin();
  			Museum museum = em.find(Museum.class, 2);
			museum.setAdress("Internet");
  			em.merge(museum);
  			assertEquals((em.find(Museum.class, 2)).getAdress(), "Internet");
		}catch (RuntimeException re) {
			LOG.error("updateOneMuseum failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}

	@Test
	public void findWork() throws Exception {
		LOG.info("findWork");
		try{
			tx.begin();
  			Work work = em.find(Work.class, 3);
  			assertEquals(work.getID(), 3);
		}catch (RuntimeException re) {
			LOG.error("findWork failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}

	@Test
	public void getWorksFromMuseum() throws Exception {
		LOG.info("getWorkFromMuseum");
		try{
			tx.begin();
  			Museum museum = em.find(Museum.class, 1);
  			List<Work> works = museum.getWorks();
  			assertEquals(museum.getWorks().size(), 2);
		}catch (RuntimeException re) {
			LOG.error("getWorkFromMuseum failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
/*
	@Test
	public void findPicture() throws Exception {
		LOG.info("findWork");
		try{
			tx.begin();
  			Work work = em.find(Work.class, 3);
  			assertEquals(work.getID(), 3);
		}catch (RuntimeException re) {
			LOG.error("findWork failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}*/
}