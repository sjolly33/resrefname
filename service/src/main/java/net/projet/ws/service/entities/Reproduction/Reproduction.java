package net.projet.ws.service.entities.Reproduction;
import net.projet.ws.service.entities.IMuseum;

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

@MappedSuperclass
public abstract class Reproduction extends IMuseum{

	@Column(name="price")	
	private Float _price;

	@Column(name="quantityLeft")
	private int _quantity;

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