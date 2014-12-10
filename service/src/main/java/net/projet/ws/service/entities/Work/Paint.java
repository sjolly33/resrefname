package net.projet.ws.service.entities.Work;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;	

@XmlRootElement(name = "paint")
@Entity
@DiscriminatorValue("WorkType")
@Table(name="PAINT")
public class Paint extends Work{

	//TODO : Once
	//public void addParticularity(ParticularTech tech){
//		_particularities.addParticularity(tech);
	//}
	
	//TODO : Once
	//public void addParticularity(ParticularSupport support){
	//	_particularities.addParticularity(support);
	//}
}
