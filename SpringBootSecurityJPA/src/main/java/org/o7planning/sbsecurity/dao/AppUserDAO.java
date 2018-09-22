package org.o7planning.sbsecurity.dao;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
 
import org.o7planning.sbsecurity.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class AppUserDAO {
 
    @Autowired
    private EntityManager entityManager;
 
    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";
 
            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);
 
            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public void save(AppUser appUser) {
    	int year = appUser.getYear();
    	int money = appUser.getMoney();
    	int intelligence = appUser.getIntelligence();
    	int happiness = appUser.getHappiness();
    	int work_level = appUser.getWorkLevel();
    	String sql = "Update " + AppUser.class.getName() 
    			+ " set year = :year, money = :money, intelligence = :intelligence, happiness = :happiness, work_level = :work_level Where User_Id = :user_id";
    	Query query = entityManager.createQuery(sql);
    	query.setParameter("year", year);
    	query.setParameter("money", money);
    	query.setParameter("intelligence", intelligence);
    	query.setParameter("happiness", happiness);
    	query.setParameter("work_level", work_level);
    	query.setParameter("user_id", appUser.getUserId());
    	query.executeUpdate();
    	return;
    }
 
    public void create(AppUser appUser) {
    	String userName = appUser.getUserName();
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	appUser.setEncrytedPassword(encoder.encode(appUser.getEncrytedPassword()));
    	String encryptedPassword = appUser.getEncrytedPassword();
    	int year = appUser.getYear();
    	int money = appUser.getMoney();
    	int intelligence = appUser.getIntelligence();
    	int happiness = appUser.getHappiness();
    	int work_level = appUser.getWorkLevel();
    	String sql = "Insert into app_user"
    			+ " (user_name, Encryted_Password, enabled, year, money, intelligence, happiness, work_level) "
    			+ "VALUES (:user_name, :encrypted_password, 1, :year, :money, :intelligence, :happiness, :work_level)";
    	Query query = entityManager.createNativeQuery(sql);
    	query.setParameter("user_name", userName);
    	query.setParameter("encrypted_password", encryptedPassword);
    	query.setParameter("year", year);
    	query.setParameter("money", money);
    	query.setParameter("intelligence", intelligence);
    	query.setParameter("happiness", happiness);
    	query.setParameter("work_level", work_level);
    	query.executeUpdate();
    	appUser = findUserAccount(userName);
    	sql = "insert into user_role (user_id, role_id) values (:user_id,2)";
    	query = entityManager.createNativeQuery(sql);
    	query.setParameter("user_id", appUser.getUserId());
    	query.executeUpdate();
    }
}