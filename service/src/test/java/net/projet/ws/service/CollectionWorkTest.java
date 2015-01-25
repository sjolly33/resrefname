package net.projet.ws.service;

import net.projet.ws.service.filters.JpaUtil;
import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Worker.Author;
import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Collection.CollectionPicture;
import net.projet.ws.service.entities.Collection.CollectionWork;
import net.projet.ws.service.entities.Reproduction.ReproductionPaint;
import net.projet.ws.service.entities.Reproduction.ReproductionSculpture;

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


// Class to test the application with some simple tests
public class CollectionWorkTest
{	

	@Rule public TestName name = new TestName();
	
	private static EntityManager em;
	private static EntityTransaction tx;
	private static Logger LOG = Logger.getLogger(CollectionWorkTest.class.getName());
	
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
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data/collectionWorkset.xml");
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
	public void findWork() throws Exception {
		LOG.info("findWork");
		try{
			tx.begin();
  			Paint work = em.find(Paint.class, 1);
  			assertEquals(work.getID(), 1);
		}catch (RuntimeException re) {
			LOG.error("findWork failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
/*
	@Test
	public void findCollectionWork() throws Exception {
		LOG.info("findcWork");
		try{
			tx.begin();
  			CollectionWork cWork = em.find(CollectionWork.class, 1);
  			assertEquals(cWork.getID(), 1);
		}catch (RuntimeException re) {
			LOG.error("findcWork failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}

	@Test
	public void findWorkByCollectionWork() throws Exception {
		LOG.info("findWorkByCollectionWork");
		try{
			tx.begin();
  			CollectionWork cWork = em.find(CollectionWork.class, 1);
  			Paint work = em.find(Paint.class, 1);
  			assertTrue(cWork.getRefPaint().indexOf(work) > -1);
		}catch (RuntimeException re) {
			LOG.error("findWorkByCollectionWork failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
	*/
}