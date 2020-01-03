package ru.levchenko.servletsApp.dao;


import ru.levchenko.servletsApp.dao.models.User;
import ru.levchenko.servletsApp.dao.models.UserRole;
import ru.levchenko.servletsApp.dao.models.UserRolesENUM;

import java.util.List;

public interface UserCrudDao extends CrudDao<User> {
    List<UserRole> roles(int id);

    List<UserRole> findAllRole();

    List<User> findUsersByRole(UserRolesENUM role);

    void saveRole(UserRole role);

    UserRole findRoleAndUsersByUserRole(UserRolesENUM userRolesENUM);

}
