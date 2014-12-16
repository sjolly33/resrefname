package net.projet.ws.service.entities.Sample;

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

@Path("/museum")

public class MuseumSample{
	
	@GET
	@Path("/json/museums")
	@Produces("application/json")
	public List<MuseumTest> listMuseums(){
		return MuseumData.getMuseums();
	}

	@GET
	@Path("/json/museum/{id}")
	@Produces("application/json")
	public MuseumTest getMuseum(@PathParam("id") int museumID){
		return MuseumData.getMuseum(museumID);
	}

}
