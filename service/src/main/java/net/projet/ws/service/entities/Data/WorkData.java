package net.projet.ws.service.entities.Data;

import net.projet.ws.service.entities.Work.Work;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.filters.JpaUtil;

import javax.ws.rs.core.Response;

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

public class WorkData{
	private static List<Work> works = new ArrayList<Work>();
	
	private static final Logger LOG = Logger.getLogger(WorkData.class);

	public static List<Work> initWorks(){
		LOG.info("initWork");
		Work work1 = new Paint();
		work1.setTitle("THE paint");
		work1.setDescription("Work en souvenir de qqchose");
		works.add(work1);
		Work work2 = new Paint();
		works.add(work2);
		return new ArrayList<Work>(works);
	}

	public static List<Work> getWorks(){
		LOG.info("getWorks");
		return new ArrayList<Work>(works);
	}

	public static Work getWork(int id){
		LOG.info("getWork");
		for(int i=0;i<works.size();++i){
			if(works.get(i).getID() == id)
				return works.get(i);
		}
		return works.get(0);
	}
}
