package net.projet.ws.service;

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

import net.projet.ws.service.filters.JpaUtil;


public class MuseumTest
{
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
		//seedData();
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
	

	/*
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
	*/

	/*
	@Test
	public final void testFindAll() 
	{
		LOG.info("testFindAll");
		try{
			tx.begin();
			List<User> list = em.createQuery("select u from User u").getResultList();
			LOG.debug("find by example successful, result size: "+ list.size());
			//assertEquals("did not get expected number of entities ", 5, list.size());
		} catch (RuntimeException re) {
			LOG.error("find by example failed", re);
			throw re;
		}finally{
			tx.commit();
		}
	}
	*/

}