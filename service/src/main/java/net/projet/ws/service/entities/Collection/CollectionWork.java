package net.projet.ws.service.entities.Collection;

import net.projet.ws.service.entities.Work;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;

@XmlRootElement(name = "collectionWork")
@Entity
@DiscriminatorValue("Type")
@Table(name="COLLECTIONWORK")
public class CollectionWork extends CollectionMuseum{

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(mappedBy="WorkID",targetEntity=Order.class, fetch=FetchType.EAGER)
	@Column(name="WorkID", nullable=false)
	private List<Work> _works;

 	@XmlElement
 	public List<Work> getRefWork() {
 		return new ArrayList(_works);
 	}

 	public void setRefWork(List<Work> works) {
 		this._works = new ArrayList(works);
 	}
}
