package ru.levchenko.servletsApp.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.levchenko.servletsApp.dao.UserCrudDao;
import ru.levchenko.servletsApp.dao.UserCrudDaoHibernateImpl;
import ru.levchenko.servletsApp.dao.models.User;
import ru.levchenko.servletsApp.dao.models.UserRole;
import ru.levchenko.servletsApp.dao.models.UserRolesENUM;

import java.util.List;

public class UserWithRolesRepo {

    private static UserWithRolesRepo userWithRolesRepo;
    private SessionFactory sessionFactory;
    private UserCrudDao userCrudDao;

    public static UserWithRolesRepo getInstance() {
        return userWithRolesRepo;
    }

    static {
        userWithRolesRepo = new UserWithRolesRepo();
    }

    private UserWithRolesRepo() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            userCrudDao = new UserCrudDaoHibernateImpl(sessionFactory);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> userList() {
        return userCrudDao.findAll();
    }

    public void save(User user) {
        userCrudDao.save(user);
    }


    public void saveRole(UserRole role) {
        userCrudDao.saveRole(role);
    }

    public void deleteUser(int id) {
        userCrudDao.delete(id);
    }

    public UserRole userRole(UserRolesENUM userRolesENUM) {
        return userCrudDao.findRoleAndUsersByUserRole(userRolesENUM);
    }


}
