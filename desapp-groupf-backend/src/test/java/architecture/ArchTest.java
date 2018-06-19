package architecture;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.type.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring-persistence-context.xml"})
@TransactionConfiguration(transactionManager="persistence.transactionManager",defaultRollback=true)
public class ArchTest {
	
	private ApplicationContext context;
	private SessionFactory sessionFactory;
	private Session session;
	private final static Logger logger = Logger.getLogger("ArchTest");

	

	@Before
	public void setUp() throws Exception{
		context = new FileSystemXmlApplicationContext("classpath*:/META-INF/spring-persistence-context.xml");
		sessionFactory = (SessionFactory) context.getBean("persistence.sessionFactory");
		session = sessionFactory.openSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	@Test
	public void hibernateMappingTest() {
		
		boolean allOk = true;

		Map<String, ClassMetadata> metadata = sessionFactory.getAllClassMetadata();

		for (Iterator<ClassMetadata> it = metadata.values().iterator(); it.hasNext();) {

			EntityPersister persister = (EntityPersister) it.next();
		
			List<String> attrs = new ArrayList<String>();
			String attrsJoin = "";
			String[] properties = persister.getPropertyNames();
			Type[] types = persister.getPropertyTypes();
		
			for(int i =0 ; i<properties.length;i++){
				if(!types[i].isCollectionType()){
					attrs.add("c." + properties[i]);
				}
				else{
					attrs.add(properties[i] + "ATTR");
					attrsJoin += (" join c."+ properties[i]+ " " + properties[i] +"ATTR ");
				}
			}

				
		String columns = String.join(",", attrs);
		try {
			Query q = session.createQuery("select " + columns + " from " + persister.getEntityName() + " c" + attrsJoin);
			q.setMaxResults(1);
			q.uniqueResult();
		}
		catch(HibernateException e) {
		      logger.warn("ERROR probando el mapeo de la entidad " + persister.getEntityName(), e);
		      allOk = false;
		}
		

		}

		assertTrue(allOk);
	}

}
