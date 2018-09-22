package org.o7planning.sbsecurity.entity;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Entity
@Table(name = "App_User", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser {
 
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    @Column(name = "User_Id", nullable = false)
    private Long userId;
 
    @Column(name = "User_Name", length = 36, nullable = false)
    private String userName;
 
    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;
 
    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;
    
    @Column(name = "year", nullable = false)
    private int year;
    
    @Column(name = "money", nullable = false)
    private int money;
 
    @Column(name = "intelligence", nullable = false)
    private int intelligence;
    
    @Column(name = "happiness", nullable = false)
    private int happiness;
    
    @Column(name = "work_level", nullable = false)
    private int work_level;
    
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getEncrytedPassword() {
        return encrytedPassword;
    }
 
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
 
    public boolean isEnabled() {
        return enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public int getYear() {
        return year;
    }
 
    public int getMoney() {
        return money;
    }
    
    public int getIntelligence() {
        return intelligence;
    }
    
    public int getHappiness() {
        return happiness;
    }
    
    public int getWorkLevel() {
        return work_level;
    }
    
    public void setYear(int v) {
        this.year = v;
    }
 
    public void setMoney(int v) {
    	this.money = v;
    }
    
    public void setIntelligence(int v) {
    	this.intelligence = v;
    }
    
    public void setHappiness(int v) {
    	this.happiness = v;
    }
    
    public void setWorkLevel(int v) {
    	this.work_level = v;
    }
}