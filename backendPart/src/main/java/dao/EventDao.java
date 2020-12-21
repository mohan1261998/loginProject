package dao;

import java.io.*;  
import java.util.*; 
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.User;
import ninja.jpa.UnitOfWork;
import controllers.LoginLogoutController;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

public class EventDao {
   
    @Inject

    Provider<EntityManager> entitiyManagerProvider;
    LoginLogoutController newu=new LoginLogoutController();
    
    


        @UnitOfWork
        public User currentuser(String username) {
                
            EntityManager entityManager = entitiyManagerProvider.get();
                
            TypedQuery<User> q = entityManager.createQuery("SELECT x FROM User x WHERE x.username = :nameParam", User.class);
            User user = q.setParameter("nameParam", username).getSingleResult();        
                
            return user;
                
                
        }
        
 
    }


    
    

