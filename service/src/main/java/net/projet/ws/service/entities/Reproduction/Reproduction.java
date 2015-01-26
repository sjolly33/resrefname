package net.projet.ws.service.entities.Reproduction;
import net.projet.ws.service.entities.IMuseum;

import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public abstract class Reproduction extends IMuseum{

	@Id 
	@Column(name="REPRODUCTION_ID", nullable=false, unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int _reproductionID;

	@Column(name="price")	
	private Float _price;

	@Column(name="quantityLeft")
	private int _quantity;

	@XmlElement
 	public int getID() {
 		return _reproductionID;
 	}

 	public void setID(int id){
 		_reproductionID = id;
 	}

	@XmlElement
	public Float getPrice(){
		return _price;
	}

	public void setPrice(Float price){
		_price = price;
	}	

	@XmlElement
	public int getQuantity(){
		return _quantity;
	}

	public void setQuantity(int quantity){
		_quantity = quantity;
	}

}