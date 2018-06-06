package architecture;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.persister.walking.spi.AttributeDefinition;
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
	
	ApplicationContext context;
	SessionFactory sessionFactory;
	Session session;
	

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
	public void test() {
		Map metadata = sessionFactory.getAllClassMetadata();

		for (Iterator it = metadata.values().iterator(); it.hasNext();) {

		EntityPersister persister = (EntityPersister) it.next();
		
		List<String> attrs = new ArrayList<String>();
		
		for (Iterator it2 = persister.getAttributes().iterator();it2.hasNext();) {
			AttributeDefinition attr = (AttributeDefinition) it2.next();
			if(attr.getName() != "rents" && attr.getName() != "creator" && attr.getName() != "reservations" && attr.getName() != "score") {
				System.out.println(persister.getEntityName() + "." +attr.getName());

				attrs.add("c." + attr.getName());
			}
			
		}
		
		String columns = String.join(",", attrs);
		
		Query q = session.createQuery(

		"select " + columns + " from " + persister.getEntityName() + " c");
		
		System.out.println(q.toString());

		q.iterate();

		}

			
	}

}
