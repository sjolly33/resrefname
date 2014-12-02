package net.projet.ws.service.entities.Work;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;	

@XmlRootElement(name = "sculpture")
@Entity
@DiscriminatorValue("WorkType")
@Table(name="SCULPTURE")
public class Sculpture extends Work{

	public void addParticularity(ParticularTech tech){
		return; //TODO Error
	}

	public void addParticularity(ParticularSupport support){
		_particularities.addParticularity(support);
	}
}
