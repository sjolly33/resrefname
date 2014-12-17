package net.projet.ws.service.entities.Collection;

import net.projet.ws.service.entities.Work.Work;

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

@XmlRootElement(name = "collectionWork")
@Entity
@DiscriminatorValue("Type")
@Table(name="COLLECTIONWORK")
public class CollectionWork extends CollectionMuseum{

	@OneToMany(mappedBy="_collection", cascade=CascadeType.ALL) //Collection is a set of existing works... 
	private List<Work> _works;

 	@XmlElement
 	public List<Work> getRefWork() {
 		return new ArrayList(_works);
 	}

 	public void setRefWork(List<Work> works) {
 		this._works = new ArrayList(works);
 	}
}
