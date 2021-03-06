package net.projet.ws.service.entities.Collection;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "collectionWork")
@Entity
@Table(name="COLLECTIONWORK")
public class CollectionWork extends Collection{

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Collection is a set of existing works... 
	@JoinColumn(name="COLLECTION_PAINT_REF")
	private List<Paint> _paints;

 	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Collection is a set of existing works... 
	@JoinColumn(name="COLLECTION_SCULPTURE_REF")	
	private List<Sculpture> _sculptures;

	@Column(name="type")
	private final String type="collectionWork";

 	@XmlElement
 	public List<Paint> getRefPaint() {
 		return new ArrayList(_paints);
 	}

 	public void setRefPaint(List<Paint> works) {
 		this._paints = new ArrayList(works);
 	}

 	@XmlElement
 	public List<Sculpture> getRefSculpture() {
 		return new ArrayList(_sculptures);
 	}

 	public void setRefSculpture(List<Sculpture> works) {
 		this._sculptures = new ArrayList(works);
 	}

 	@XmlElement
	public String getType(){
		return this.type;
	}

}
