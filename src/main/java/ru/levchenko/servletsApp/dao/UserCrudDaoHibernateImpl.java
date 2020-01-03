package ru.levchenko.servletsApp.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.levchenko.servletsApp.dao.models.User;
import ru.levchenko.servletsApp.dao.models.UserRole;
import ru.levchenko.servletsApp.dao.models.UserRolesENUM;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class UserCrudDaoHibernateImpl implements UserCrudDao {

    private SessionFactory sessionFactory;

    @Override
    public void saveRole(UserRole role) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(role);
        session.getTransaction().commit();
        session.close();

    }

    public UserCrudDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserRole> roles(int id) {
        List<UserRole> roleList;

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        roleList = session.createQuery(" from User where id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult()
                .getRoles();

        session.getTransaction().commit();
        session.close();

        return roleList;
    }


    @Override
    public List<User> findUsersByRole(UserRolesENUM role) {
        List<User> userList;

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("select distinct u.users FROM UserRole u JOIN u.users us WHERE u.role= :role");
        query.setParameter("role", role);
        userList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return userList;
    }

    @Override
    public Optional<User> find(int id) {
        Optional<User> candidate;

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("from User as u where u.id = :id", User.class);
        query.setParameter("id", id);
        candidate = Optional.of((User) query.getSingleResult());
        session.getTransaction().commit();

        session.close();


        return candidate;
    }

    @Override
    public List<User> findAll() {
        List<User> userList;

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query query = session.createQuery(" from User", User.class);

        userList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return userList;
    }

    @Override
    public List<UserRole> findAllRole() {
        List<UserRole> userRolesList;

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query query = session.createQuery(" from UserRole ", UserRole.class);

        userRolesList = query.getResultList();
        session.getTransaction().commit();
        session.close();

        return userRolesList;
    }


    @Override
    public void delete(int id) {

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        Query query = session.createQuery("delete from User where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void save(User model) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.saveOrUpdate(model);
        session.getTransaction().commit();
        session.close();

    }

    //CriteriaQuery
    @Override
    public void update(User model) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.update(model);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public UserRole findRoleAndUsersByUserRole(UserRolesENUM userRolesENUM) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.createQuery("from UserRole where role= ?1 ", UserRole.class);
        query.setParameter(1, userRolesENUM);
        UserRole userRole = (UserRole) query.getSingleResult();
        session.getTransaction().commit();
        session.close();
        return userRole;
    }
}




