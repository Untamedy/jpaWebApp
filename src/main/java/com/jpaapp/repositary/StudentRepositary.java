package com.jpaapp.repositary;

import com.jpaapp.entities.Student;
import com.jpaapp.services.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author YBolshakova
 */
public class StudentRepositary {

    public static final Logger LOGGER = Logger.getLogger(StudentRepositary.class.getName());

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private EntityTransaction transaction = null;

    public StudentRepositary(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void addStudent(Student student) {
        boolean isExists = false;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning(ex.getMessage());
        }
    }

    public void updateStudent(Student student) {
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning(ex.getMessage());
        }
    }

    public void deleteStudent(Student student) {
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning(ex.getMessage());

        }
    }

    public List<Student> findByLastname(String lastname) {
        List<Student> students = new ArrayList<>();
        try {
            students = entityManager.createQuery("SELECT s FROM Student s where s.lastname=" + "'" + lastname + "'").getResultList();
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }
        return students;
    }

    public List<Student> findByAge(int min, int max) {
        List<Student> students = new ArrayList<>();
        try {
            students = entityManager.createQuery("SELECT s FROM Student s WHERE s.age>" + min + " AND s.age<" + max).getResultList();
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }
        return students;
    }

    public List<Student> findByGroup(int id) {
        List<Student> students = new ArrayList<>();
        try {
            students = entityManager.createQuery("SELECT s FROM Student s WHERE s.group=" + id).getResultList();
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }
        return students;
    }

    public List<Student> getAllStudents() {
      List<Student> students = new ArrayList<>();
        try {
            students = entityManager.createQuery("SELECT s FROM Student AS s").getResultList();
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }
        return students;
    }

}
