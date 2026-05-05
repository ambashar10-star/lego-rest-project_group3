package services;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import data.*;

@Path("/lego")
public class LegoService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("lego");	
	
	@Path("/getlego")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getLego() {
		return "Lego service Legorest2!";
	}
	
	@Path("/setvalues")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Lego setValues(Lego lego) {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(lego);
	    em.getTransaction().commit();		
		return lego;
	}
	
	@SuppressWarnings("unchecked")
	@Path("/getvalues")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getValues() {
	    EntityManager em=emf.createEntityManager();
	    em.getTransaction().begin();
		Query q=em.createQuery("select s from Lego s order by s.id desc").setMaxResults(1);
		List<Lego> list=q.getResultList();
		em.getTransaction().commit();
		Lego lego=list.get(0);
		return lego.getId()+"#"+lego.getRun()+"#"+lego.getSpeed()+"#"+lego.getTurn();
	}

	@Path("/setsensordata")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SensorData setSensorData(SensorData sensorData) {
    	EntityManager em = emf.createEntityManager();

    	em.getTransaction().begin();
    	em.persist(sensorData);
    	em.getTransaction().commit();

    	em.close();

    	return sensorData;
	}

	@SuppressWarnings("unchecked")
	@Path("/getsensordata")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SensorData> getSensorData() {
    	EntityManager em = emf.createEntityManager();

    	em.getTransaction().begin();

    	Query q = em.createQuery("select s from SensorData s order by s.id desc");
    	q.setMaxResults(20);

    	List<SensorData> list = q.getResultList();

    	em.getTransaction().commit();
    	em.close();

    	return list;
	}

	@Path("/getstats")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getStats() {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Double avgSpeed = (Double) em.createQuery(
			"select avg(l.speed) from Lego l"
		).getSingleResult();

		Long totalCommands = (Long) em.createQuery(
			"select count(l) from Lego l"
		).getSingleResult();

		Long totalSensorEvents = (Long) em.createQuery(
			"select count(s) from SensorData s"
		).getSingleResult();

		Long wallEvents = (Long) em.createQuery(
			"select count(s) from SensorData s where s.sensorType = 'wall'"
		).getSingleResult();

		em.getTransaction().commit();
		em.close();

		return "{"
			+ "\"avgSpeed\":" + (avgSpeed != null ? avgSpeed : 0) + ","
			+ "\"totalCommands\":" + totalCommands + ","
			+ "\"totalSensorEvents\":" + totalSensorEvents + ","
			+ "\"wallEvents\":" + wallEvents
			+ "}";
	}
}