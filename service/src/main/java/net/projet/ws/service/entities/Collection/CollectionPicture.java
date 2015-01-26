package net.projet.ws.service.entities.Collection;
import net.projet.ws.service.entities.IMuseum;
import net.projet.ws.service.entities.Museum;
import net.projet.ws.service.entities.Picture.Picture;

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
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "collectionPicture")
@Entity
@Table(name="COLLECTIONPICTURE")
public class CollectionPicture extends Collection{

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Collection is a set of existing pictures... 
	@JoinColumn(name="COLLECTION_PICTURE_REF", referencedColumnName="CPICTUREID")
	private List<Picture> _pictures;

	@Column(name="type")
	private final String type="collectionPicture";

 	@XmlElement
 	public List<Picture> getRefPicture() {
 		return new ArrayList(_pictures);
 	}

 	public void setRefPicture(List<Picture> pictures) {
 		this._pictures = new ArrayList(pictures);
 	}

	@XmlElement
	public String getType(){
		return this.type;
	}

}
