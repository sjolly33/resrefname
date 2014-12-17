package net.projet.ws.service.entities.Test;

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

	@GET
	@Path("/json/museums")
	@Produces("application/json")
	public List<MuseumTest> listMuseums(){
		return MuseumDataTest.getMuseums();
	}

	@GET
	@Path("/json/museum/{id}")
	@Produces("application/json")
	public MuseumTest getMuseum(@PathParam("id") int museumID){
		return MuseumDataTest.getMuseum(museumID);
	}

	@GET
	@Path("/json/museum/{id}/elements")
	@Produces("application/json")
	public List<ElementTest> listElements(@PathParam("id") int museumID){
		return MuseumDataTest.getListElements(museumID);
	}
}
