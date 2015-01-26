package net.projet.ws.service.entities;

import net.projet.ws.service.entities.Data.MuseumData;
import net.projet.ws.service.entities.Data.PictureData;
import net.projet.ws.service.entities.Data.WorkData;
import net.projet.ws.service.entities.Data.AuthorData;
import net.projet.ws.service.entities.Data.CollectionData;
import net.projet.ws.service.entities.Data.ReproductionData;

import net.projet.ws.service.entities.Picture.Picture;
import net.projet.ws.service.entities.Work.Paint;
import net.projet.ws.service.entities.Work.Sculpture;
import net.projet.ws.service.entities.Worker.Author;
import net.projet.ws.service.entities.Collection.CollectionWork;
import net.projet.ws.service.entities.Collection.CollectionPicture;
import net.projet.ws.service.entities.Reproduction.ReproductionPaint;
import net.projet.ws.service.entities.Reproduction.ReproductionSculpture;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;

import javax.ws.rs.core.Response;


@Path("/museum")

public class MuseumRoot{


// ------------------------------ GET -----------------------------------

	
	@GET
	@Path("/museums")
	@Produces("application/json")
	public List<Museum> listMuseums(){
		return MuseumData.getMuseums();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Museum getMuseum(@PathParam("id") int museumID){
		return MuseumData.getMuseum(museumID);
	}

	@GET
	@Path("/picture/{id}")
	@Produces("application/json")
	public Picture getPicture(@PathParam("id") int pictureID){
		return PictureData.getPicture(pictureID);
	}

	@GET
	@Path("/{id}/pictures")
	@Produces("application/json")
	public List<Picture> getPictures(@PathParam("id") int museumID){
		return (MuseumData.getMuseum(museumID)).getPictures();
	}

	@GET
	@Path("/{id_museum}/picture/{id_picture}")
	@Produces("application/json")
	public Picture getPicture(@PathParam("id_museum") int museumID, @PathParam("id_picture") int pictureID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Picture> pictures = museum.getPictures();
		return PictureData.getPicture(pictures, pictureID);
	}

	@GET
	@Path("/paint/{id}")
	@Produces("application/json")
	public Paint getPaint(@PathParam("id") int paintID){
		return WorkData.getPaint(paintID);
	}

	@GET
	@Path("/{id}/paints")
	@Produces("application/json")
	public List<Paint> getPaints(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getPaints();
	}

	@GET
	@Path("/sculpture/{id}")
	@Produces("application/json")
	public Sculpture getSculpture(@PathParam("id") int sculptureID){
		return WorkData.getSculpture(sculptureID);
	}

	@GET
	@Path("/{id}/sculptures")
	@Produces("application/json")
	public List<Sculpture> getSculptures(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getSculptures();
	}


	@GET
	@Path("/author/{id}")
	@Produces("application/json")
	public Author getAuthor(@PathParam("id") int authorID){
		return AuthorData.getAuthor(authorID);
	}

	@GET
	@Path("/{id}/authors")
	@Produces("application/json")
	public List<Author> getAuthors(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getAuthors();
	}

	@GET
	@Path("/reproductionPaint/{id}")
	@Produces("application/json")
	public ReproductionPaint getReproductionPaint(@PathParam("id") int rPaintID){
		return ReproductionData.getReproductionPaint(rPaintID);
	}

	@GET
	@Path("/reproductionSculpture/{id}")
	@Produces("application/json")
	public ReproductionSculpture getReproductionSculpture(@PathParam("id") int rSculptureID){
		return ReproductionData.getReproductionSculpture(rSculptureID);
	}


	@GET
	@Path("/{id}/author/{id_author}")
	@Produces("application/json")
	public Author getAuthor(@PathParam("id") int museumID, @PathParam("id") int authorID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Author> authors = museum.getAuthors();
		return AuthorData.getAuthor(authors, authorID);
	}


	@GET
	@Path("/collectionWork/{id}")
	@Produces("application/json")
	public CollectionWork getCollectionWork(@PathParam("id") int cWorkID){
		return CollectionData.getCollectionWork(cWorkID);
	}

	@GET
	@Path("/{id}/collectionsWork")
	@Produces("application/json")
	public List<CollectionWork> getCollectionsWork(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getCollectionsWorks();
	}


	@GET
	@Path("/collectionPicture/{id}")
	@Produces("application/json")
	public CollectionPicture getCollectionPicture(@PathParam("id") int cPictureID){
		return CollectionData.getCollectionPicture(cPictureID);
	}

	@GET
	@Path("/{id}/collectionsPicture")
	@Produces("application/json")
	public List<CollectionPicture> getCollectionsPicture(@PathParam("id") int museumID){
		Museum museum = MuseumData.getMuseum(museumID);
		return museum.getCollectionsPictures();
	}

	@GET
	@Path("/{id_museum}/paint/{id_work}")
	@Produces("application/json")
	public Paint getPaint(@PathParam("id_museum") int museumID, @PathParam("id_work") int workID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Paint> works = museum.getPaints();
		return WorkData.getPaint(works, workID);
	}

	@GET
	@Path("/{id_museum}/sculpture/{id_work}")
	@Produces("application/json")
	public Sculpture getSculpture(@PathParam("id_museum") int museumID, @PathParam("id_work") int workID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<Sculpture> works = museum.getSculptures();
		return WorkData.getSculpture(works, workID);
	}

	@GET
	@Path("/{id}/collectionWork/{id_cWork}")
	@Produces("application/json")
	public CollectionWork getCollectionWork(@PathParam("id") int museumID, @PathParam("id_cWork") int cWorkID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<CollectionWork> cWorks = museum.getCollectionsWorks();
		return CollectionData.getCollectionWork(cWorks, cWorkID);
	}

	@GET
	@Path("/{id}/collectionPicture/{id_cPicture}")
	@Produces("application/json")
	public CollectionPicture getCollectionPicture(@PathParam("id") int museumID, @PathParam("id_cPicture") int cPictureID){
		Museum museum = MuseumData.getMuseum(museumID);
		List<CollectionPicture> cPictures = museum.getCollectionsPictures();
		return CollectionData.getCollectionPicture(cPictures, cPictureID);
	}



// ------------------------------ POST -----------------------------------



	@POST
	@Path("/new/museum")
	@Consumes("application/json")
	public Response addMuseum(Museum museum){
		return MuseumData.addMuseum(museum);
	}

	@POST
	@Path("/new/picture")
	@Consumes("application/json")
	public Response addPicture(Picture picture){
		return PictureData.addPicture(picture);
	}

	@POST
	@Path("/new/paint")
	@Consumes("application/json")
	public Response addPaint(Paint work){
		return WorkData.addPaint(work);
	}

	@POST
	@Path("/new/sculpture")
	@Consumes("application/json")
	public Response addSculpture(Sculpture work){
		return WorkData.addSculpture(work);
	}

	@POST
	@Path("/new/collectionPicture")
	@Consumes("application/json")
	public Response addCollectionPicture(CollectionPicture collectionPicture){
		return CollectionData.addCollectionPicture(collectionPicture);
	}

	@POST
	@Path("/new/collectionWork")
	@Consumes("application/json")
	public Response addCollectionWork(CollectionWork collectionWork){
		return CollectionData.addCollectionWork(collectionWork);
	}

	@POST
	@Path("/new/author")
	@Consumes("application/json")
	public Response addAuthor(Author author){
		return AuthorData.addAuthor(author);
	}

	@POST
	@Path("/new/reproductionPaint")
	@Consumes("application/json")
	public Response addReproductionPaint(ReproductionPaint reproduction){
		return ReproductionData.addReproductionPaint(reproduction);
	}

	@POST
	@Path("/new/reproductionSculpture")
	@Consumes("application/json")
	public Response addReproductionSculpture(ReproductionSculpture reproduction){
		return ReproductionData.addReproductionSculpture(reproduction);
	}

	@POST
	@Path("{id_museum}/new/author")
	@Consumes("application/json")
	public Response addAuthorToMuseum(@PathParam("id_museum") int museumID, Author author){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = AuthorData.addAuthor(author);
		List<Author> authors = museum.getAuthors();
		authors.add(author);
		museum.setAuthors(authors);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/picture")
	@Consumes("application/json")
	public Response addPictureToMuseum(@PathParam("id_museum") int museumID, Picture picture){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = PictureData.addPicture(picture);
		List<Picture> pictures = museum.getPictures();
		pictures.add(picture);
		museum.setPictures(pictures);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/paint")
	@Consumes("application/json")
	public Response addPaintToMuseum(@PathParam("id_museum") int museumID, Paint work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addPaint(work);
		List<Paint> works = museum.getPaints();
		works.add(work);
		museum.setPaints(works);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/sculpture")
	@Consumes("application/json")
	public Response addSculptureToMuseum(@PathParam("id_museum") int museumID, Sculpture work){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = WorkData.addSculpture(work);
		List<Sculpture> works = museum.getSculptures();
		works.add(work);
		museum.setSculptures(works);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/collectionPicture")
	@Consumes("application/json")
	public Response addCollectionPictureToMuseum(@PathParam("id_museum") int museumID, CollectionPicture collectionPicture){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = CollectionData.addCollectionPicture(collectionPicture);
		List<CollectionPicture> collectionPictures = museum.getCollectionsPictures();
		collectionPictures.add(collectionPicture);
		museum.setCollectionsPictures(collectionPictures);
		MuseumData.updateMuseum(museum);
		return res;
	}

	@POST
	@Path("/{id_museum}/new/collectionWork")
	@Consumes("application/json")
	public Response addCollectionWorkToMuseum(@PathParam("id_museum") int museumID, CollectionWork collectionWork){
		Museum museum = MuseumData.getMuseum(museumID);
		Response res = CollectionData.addCollectionWork(collectionWork);
		List<CollectionWork> collectionWorks = museum.getCollectionsWorks();
		collectionWorks.add(collectionWork);
		museum.setCollectionsWorks(collectionWorks);
		MuseumData.updateMuseum(museum);
		return res;
	}



// ------------------------------ PUT -----------------------------------



	@PUT
	@Path("/update/museum")
	@Produces("application/json")
	@Consumes("application/json")
	public Museum updateMuseum(Museum museum){
		return MuseumData.updateMuseum(museum);
	}

	@PUT
	@Path("/update/picture")
	@Produces("application/json")
	@Consumes("application/json")
	public Picture updatePicture(Picture picture){
		return PictureData.updatePicture(picture);
	}

	@PUT
	@Path("/update/paint")
	@Produces("application/json")
	@Consumes("application/json")
	public Paint updatePaint(Paint work){
		return WorkData.updatePaint(work);
	}

	@PUT
	@Path("/update/sculpture")
	@Produces("application/json")
	@Consumes("application/json")
	public Sculpture updateSculpture(Sculpture work){
		return WorkData.updateSculpture(work);
	}

	@PUT
	@Path("/update/collectionPicture")
	@Produces("application/json")
	@Consumes("application/json")
	public CollectionPicture updateCollectionPicture(CollectionPicture cPicture){
		return CollectionData.updateCollectionPicture(cPicture);
	}

	@PUT
	@Path("/update/collectionWork")
	@Produces("application/json")
	@Consumes("application/json")
	public CollectionWork updateCollectionWork(CollectionWork cWork){
		return CollectionData.updateCollectionWork(cWork);
	}

	@PUT
	@Path("/update/author")
	@Produces("application/json")
	@Consumes("application/json")
	public Author updateAuthor(Author author){
		return AuthorData.updateAuthor(author);
	}

	@PUT
	@Path("/update/reproductionPaint")
	@Produces("application/json")
	@Consumes("application/json")
	public ReproductionPaint updateReproductionPaint(ReproductionPaint reproductionPaint){
		return ReproductionData.updateReproductionPaint(reproductionPaint);
	}

	@PUT
	@Path("/update/reproductionSculpture")
	@Produces("application/json")
	@Consumes("application/json")
	public ReproductionSculpture updateReproductionSculpture(ReproductionSculpture reproductionSculpture){
		return ReproductionData.updateReproductionSculpture(reproductionSculpture);
	}



// ------------------------------ DELETE -----------------------------------



	@DELETE
	@Path("/delete/museum/{id}")
	@Consumes("application/json")
	public void deleteMuseum(@PathParam("id") int museumID){
		MuseumData.deleteMuseum(MuseumData.getMuseum(museumID));
	}

	@DELETE
	@Path("/delete/picture/{id}")
	@Consumes("application/json")
	public void deletePicture(@PathParam("id") int pictureID){
		Picture picture = PictureData.getPicture(pictureID);

		Paint paint = picture.getPaint();
		List<Picture> pictures = paint.getPictures();
		if(pictures.remove(picture)){
			paint.setPictures(pictures);
			this.updatePaint(paint);
		}
		
		Sculpture sculpture = picture.getSculpture();
		pictures = sculpture.getPictures();
		if(pictures.remove(picture)){
			sculpture.setPictures(pictures);
			this.updateSculpture(sculpture);
		}

		Museum museum = picture.getMuseum();
		List<Picture> picturesMuseum = museum.getPictures();
		if(picturesMuseum.remove(picture)){
			museum.setPictures(picturesMuseum);
			this.updateMuseum(museum);
		} 

		PictureData.deletePicture(picture);
	}

	@DELETE
	@Path("/delete/paint/{id}")
	@Consumes("application/json")
	public void deletePaint(@PathParam("id") int workID){
		Paint paint = WorkData.getPaint(workID);

		Author author = paint.getAuthor();
		List<Paint> authorPaints = author.getPaints();
		if(authorPaints.remove(paint)){
			author.setPaints(authorPaints);
			this.updateAuthor(author);
		}	

		List<Picture> pictures = paint.getPictures();
		for(int i=0;i<pictures.size();++i){
			pictures.get(i).setPaint(null);
			this.updatePicture(pictures.get(i));
		}
		
		Museum museum = paint.getMuseum();
		List<Paint> paintsMuseum = museum.getPaints();
		if(paintsMuseum.remove(paint)){
			museum.setPaints(paintsMuseum);
			this.updateMuseum(museum);
		} 

		WorkData.deletePaint(paint);
	}

	@DELETE
	@Path("/delete/sculpture/{id}")
	@Consumes("application/json")
	public void deleteSculpture(@PathParam("id") int workID){
		Sculpture sculpture = WorkData.getSculpture(workID);
		
		Author author = sculpture.getAuthor();
		List<Sculpture> authorSculptures = author.getSculptures();
		if(authorSculptures.remove(sculpture)){
			author.setSculptures(authorSculptures);
			this.updateAuthor(author);
		}	

		List<Picture> pictures = sculpture.getPictures();
		for(int i=0;i<pictures.size();++i){
			pictures.get(i).setSculpture(null);
			this.updatePicture(pictures.get(i));
		}
		
		Museum museum = sculpture.getMuseum();
		List<Sculpture> sculpturesMuseum = museum.getSculptures();
		if(sculpturesMuseum.remove(sculpture)){
			museum.setSculptures(sculpturesMuseum);
			this.updateMuseum(museum);
		} 

		WorkData.deleteSculpture(sculpture);
	}

	@DELETE
	@Path("/delete/collectionPicture/{id}")
	@Consumes("application/json")
	public void deleteCollectionPicture(@PathParam("id") int cPictureID){
		CollectionPicture cP = CollectionData.getCollectionPicture(cPictureID);
		Museum museum = cP.getMuseum();
		List<CollectionPicture> cPsMuseum = museum.getCollectionsPictures();
		if(cPsMuseum.remove(cP)){
			museum.setCollectionsPictures(cPsMuseum);
			this.updateMuseum(museum);
		} 
		CollectionData.deleteCollectionPicture(cP);
	}

	@DELETE
	@Path("/delete/collectionWork/{id}")
	@Consumes("application/json")
	public void deleteCollectionWork(@PathParam("id") int cWorkID){
		CollectionWork cW = CollectionData.getCollectionWork(cWorkID);
		Museum museum = cW.getMuseum();
		List<CollectionWork> cWsMuseum = museum.getCollectionsWorks();
		if(cWsMuseum.remove(cW)){
			museum.setCollectionsWorks(cWsMuseum);
			this.updateMuseum(museum);
		} 
		CollectionData.deleteCollectionWork(cW);
	}

	@DELETE
	@Path("/delete/author/{id}")
	@Consumes("application/json")
	public void deleteAuthor(@PathParam("id") int authorID){
		Author author = AuthorData.getAuthor(authorID);

		Museum museum = author.getMuseum();
		List<Author> authorsMuseum = museum.getAuthors();
		if(authorsMuseum.remove(author)){
			museum.setAuthors(authorsMuseum);
			this.updateMuseum(museum);
		} 

		AuthorData.deleteAuthor(author);
	}

	@DELETE
	@Path("/delete/reproductionPaint/{id}")
	@Consumes("application/json")
	public void deleteReproductionPaint(@PathParam("id") int reproductionPaintID){
		ReproductionData.deleteReproductionPaint(ReproductionData.getReproductionPaint(reproductionPaintID));
	}

	@DELETE
	@Path("/delete/reproductionSculpture/{id}")
	@Consumes("application/json")
	public void deleteReproductionSculpture(@PathParam("id") int reproductionSculptureID){
		ReproductionData.deleteReproductionSculpture(ReproductionData.getReproductionSculpture(reproductionSculptureID));
	}
}
