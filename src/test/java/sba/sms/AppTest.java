package sba.sms;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import sba.sms.models.Student;

import sba.sms.utils.CommandLine;
import sba.sms.utils.HibernateUtil;

class AppTest {
	static Student s =null;
	static Student s1=null;
	
	//given
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		CommandLine.addData();
		  s = new Student ("reema@gmail.com", "reema brown", "password");
	}
	
	@Test @DisplayName("Testing email if user is valid")
	void testEmailIfItIsValid(){
    	 //when 
        String test = s.getEmail();
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Student> q = session.createQuery("from  Student s where s.email = :emailId", Student.class)
					.setParameter("emailId", test);
			s1 = q.getSingleResult();

		} catch (HibernateException ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
        //then
         assertThat(test).isEqualTo(s1.getEmail());      
    }

}
