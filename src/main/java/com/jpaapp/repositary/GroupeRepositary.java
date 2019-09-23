package com.jpaapp.repositary;

import com.jpaapp.entities.Group;
import com.jpaapp.entities.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author YBolshakova
 */
public class GroupeRepositary {

    public static final Logger LOGGER = Logger.getLogger(GroupeRepositary.class.getName());

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private EntityTransaction transaction = null;
    private StudentRepositary studentRepositary;

    public GroupeRepositary(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.entityManager = entityManagerFactory.createEntityManager();
        this.studentRepositary = new StudentRepositary(entityManagerFactory);
    }

    public void addGroup(Group group) {
        try {
            transaction = entityManager.getTransaction();
            Group isExistGroup = findByCode(group.getCode());
            if (isExistGroup == null) {
                transaction.begin();
                entityManager.persist(group);
                transaction.commit();
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning(ex.getMessage());
        }
    }

    public void updateGroup(Group group, String newData) {
        try {
            transaction = entityManager.getTransaction();
            if (group != null) {
                group.setCode(newData);
                transaction.begin();
                entityManager.persist(group);
                transaction.commit();
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning(ex.getMessage());
        }
    }

    public void deleteGroup(Group group) {
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(group);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning(ex.getMessage());
        }
    }

    public Group findByCode(String groupCode) {
        Group group = null;
        try {
            Query query = entityManager.createNamedQuery("find group by code");
            query.setParameter("code", groupCode);
            List<Group> groups = query.getResultList();
            if (groups.isEmpty()) {
                return group;
            } else {
                group = groups.get(0);
            }
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }
        return group;
    }

    public List<Group> selectAll() {
        List<Group> groups = new ArrayList<>();
        try {
            groups = entityManager.createQuery("SELECT g FROM Group AS g", Group.class).getResultList();
        } catch (Exception ex) {
            LOGGER.warning(ex.getMessage());
        }
        return groups;
    }

}
