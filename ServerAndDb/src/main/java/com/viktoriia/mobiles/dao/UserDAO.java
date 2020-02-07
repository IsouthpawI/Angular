package com.viktoriia.mobiles.dao;

import com.viktoriia.mobiles.entity.Mobile;
import com.viktoriia.mobiles.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Transactional
@Repository
public class UserDAO implements IUserDAO{
    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public boolean mobileExists(String firstName, String lastName, String address, long phoneNumberM, long phoneNumberH, Date added) {
//        String hql = "FROM Mobile as mob WHERE  mob.phoneNumberM = ?";
//        int countM = entityManager.createQuery(hql).setParameter(0, phoneNumberM).getResultList().size();
//        return countM > 0 ? true : false;
//    }








    @Override
    public User getUserById(String username) {
        return entityManager.find(User.class, username);
//        return null;
    }

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public boolean userExists(String username) {
        String hql = "FROM User as us WHERE  us.username = ?";
        int countM = entityManager.createQuery(hql).setParameter(0, username).getResultList().size();
        return countM > 0 ? true : false;
    }

    @Override
    public boolean checkUser(String username, String password) {
        System.out.println("USERNAME" + username);
        String hql = "FROM User as us WHERE  us.username = ? and us.password = ?";
        int count = entityManager.createQuery(hql).setParameter(0, username).setParameter(1, password)
                .getResultList().size();
        return count > 0 ? true : false;
    }
}
