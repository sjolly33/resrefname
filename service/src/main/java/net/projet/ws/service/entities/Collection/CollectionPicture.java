package net.projet.ws.service.entities.Collection;
import net.projet.ws.service.entities.IMuseum;
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

@XmlRootElement(name = "collectionPicture")
@Entity
@Table(name="COLLECTIONPICTURE")
public class CollectionPicture extends IMuseum{

	@Id 
	@Column(name="CPICTUREID", nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _cPictureID; 

	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}) //Collection is a set of existing pictures... 
	@JoinColumn(name="COLLECTION_PICTURE_REF", referencedColumnName="CPICTUREID")
	private List<Picture> _pictures;

	@XmlElement
 	public int getID() {
 		return _cPictureID;
 	}

 	public void setID(int id){
 		_cPictureID = id;
 	}

 	@XmlElement
 	public List<Picture> getRefPicture() {
 		return new ArrayList(_pictures);
 	}

 	public void setRefPicture(List<Picture> pictures) {
 		this._pictures = new ArrayList(pictures);
 	}
}
