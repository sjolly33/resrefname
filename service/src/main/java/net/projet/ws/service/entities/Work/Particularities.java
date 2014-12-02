package net.projet.ws.service.entities.Work;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.persistence.*;	

@XmlRootElement(name = "particularities")
@Entity
@Table(name="PARTICULARITIES")
public abstract class Particularities{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int _particuliesID;

	@Column(name="particularity")
	private List<Particularity> _particularity = new ArrayList<Particularity>();

	public List<Particularity> getParticularities(){
		return new ArrayList<Particularity>(_particularity);
	}

	public void addParticularity(Particularity particularity){
		for(int i=0;i<_particularity.size();++i){
			if(_particularity.get(i).getName().equals(particularity.getName()))
				return;
		}
		_particularity.add(particularity);
	}
}