package sba.sms.services;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sba.sms.models.Course;
import sba.sms.models.Student;
import sba.sms.utils.HibernateUtil;

public class StudentService {

	public void createStudent(Student student) {

		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			tx = session.beginTransaction();
			session.merge(student);
			tx.commit();

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}

	}

	public void registerStudentToCourse(String email, int courseId) {
		

		Transaction tx = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Student s = session.get(Student.class, email);
			Course c = session.get(Course.class, courseId);
			tx = session.beginTransaction();
			s.addCourse(c);
			tx.commit();

		} catch (HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		} finally {
			session.close();
		}
			
	}

	public Student getStudentByEmail(String email) {
		Student s = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			 s = session.get(Student.class, email);

		} catch (HibernateException ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}

		return s;
	}

	public boolean validateStudent(String email, String password) {

		boolean result = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Student s = getStudentByEmail(email);
			if(s!=null) {
			if (s.getPassword().equals(password) && s.getEmail().equals(email)) {
				result = true;
			} }else
				result = false;

		} catch (HibernateException ex) {
	result = false;

		} finally {
			session.close();
		}
		return result;
	}

	public List<Student> getAllStudents() {
		List<Student> result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Student> q = session.createQuery("from  Student", Student.class);

			result = q.getResultList();
		} catch (HibernateException ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
		return result;
	}

	public Set<Course> getStudentCourses(String email) {
		Set<Course> result = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Query<Student> q = session.createQuery("from  Student s where s.email = :emailId", Student.class)
					.setParameter("emailId", email);
			Student s = q.getSingleResult();
			result= s.getCourse();
			
		} catch (HibernateException ex) {
			ex.printStackTrace();

		} finally {
			session.close();
		}
		return result;
	}

}
