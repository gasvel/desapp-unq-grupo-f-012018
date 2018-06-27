package architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;



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
		
		try {
			Query q = session.createQuery(" from " + persister.getEntityName() + " c");
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
	
	@Test
	public void packageAccessArchTest(){
		
		JavaClasses importedClasses = new ClassFileImporter().importPackages("");
	    
		//We check that all classes in persistance package are only accesible for the service package or the persistence package itself
        ArchRule rule = classes()	
        		.that().resideInAPackage("persistence")
        	    .should().onlyBeAccessed().byClassesThat().resideInAnyPackage("service", "persistence");
        
        //We check that all classes in package service are only accesible for the package webservice and tests packages. Or the service package itself
        ArchRule rule2 = classes()	
        		.that().resideInAPackage("service")
        	    .should().onlyBeAccessed().byClassesThat().resideInAnyPackage("service", "webservice","services","architecture");
    
        ArchRule rule3 = classes()	
        		.that().resideInAPackage("webservice")
        	    .should().onlyBeAccessed().byClassesThat().resideInAPackage("webservice");
        
        rule.check(importedClasses);
        rule2.check(importedClasses);
        rule3.check(importedClasses);
		
	}
	
	

}
