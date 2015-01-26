package net.projet.ws.service.filters;
import org.apache.log4j.Logger;
 import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.FilterChain;
 import javax.servlet.Filter;
 import javax.servlet.ServletException;
 import javax.servlet.FilterConfig;
 import java.io.IOException;

 public class JpaFilter implements Filter {
	 private static final Logger LOG = Logger.getLogger(JpaFilter.class);
	 private static EntityManagerFactory emf = null;

	 public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		 LOG.info("INTERCEPT REQUEST");
		 EntityManager em = null;
		 try{
			 em = emf.createEntityManager();
			 JpaUtil.ENTITY_MANAGERS.set(em);
			 chain.doFilter(request,response);
			 JpaUtil.ENTITY_MANAGERS.remove();
		 }finally{
			 try{
				 if (em != null)
				 	em.close();
			 } 
			 catch (Throwable t) {
			 	LOG.error("While closing an EntityManager",t);}
			}
	 }

	 public void init(FilterConfig config) {
		 destroy();
		 emf =Persistence.createEntityManagerFactory("Museum");
	 }

	 public void destroy(){
		 if (emf != null)
		 	emf.close();
	 }
 }
