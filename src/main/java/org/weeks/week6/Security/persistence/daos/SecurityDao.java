package org.weeks.week6.Security.persistence.daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.weeks.week6.Security.persistence.HibernateConfig;
import org.weeks.week6.Security.exceptions.ValidationException;
import org.weeks.week6.Security.model.Role;
import org.weeks.week6.Security.model.User;

public class SecurityDao {


    private EntityManagerFactory emf;

    public SecurityDao() {

        this.emf = HibernateConfig.getEntityManagerFactoryConfig("securitydb", false);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public User getVerifiedUser(String username, String password) throws ValidationException {

        try (EntityManager em = getEntityManager()) {

            User user = em.find(User.class, username);

            if (user == null) {
                throw new EntityNotFoundException("No user found with username: " + username);
            }
            user.getRoles().size();

            if (!user.verifyPassword(password)) {
                throw new ValidationException("Wrong password");
            }

            return user;
        }
    }

    public User createUser(String username, String password) {

        try (EntityManager em = emf.createEntityManager()) {

            em.getTransaction().begin();

            User user = new User(username, password);

            Role userRole = em.find(Role.class, "user");

            if (userRole == null) {
                userRole = new Role("user");
                em.persist(userRole);
            }
            user.addRole(userRole);

            em.persist(user);

            em.getTransaction().commit();

            return user;
        }
    }
}
