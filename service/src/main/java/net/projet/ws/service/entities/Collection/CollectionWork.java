package net.projet.ws.service.entities.Collection;
import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;

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
@Table(name="COLLECTIONWORK")
public class CollectionWork extends IMuseum{

	@Id 
	@Column(name="CWORKID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _cWorkID;

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Collection is a set of existing works... 
	@JoinColumn(name="COLLECTION_PAINT_REF", referencedColumnName="CWORKID")
	private List<Paint> _paints;

 	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Collection is a set of existing works... 
	@JoinColumn(name="COLLECTION_SCULPTURE_REF", referencedColumnName="CWORKID")	
	private List<Sculpture> _sculptures;

	@XmlElement
 	public int getID() {
 		return _cWorkID;
 	}

 	public void setID(int id){
 		_cWorkID = id;
 	}

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
}
