/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author DANIEL FAILADONA
 */
public class Session { 
    
    private static Session instance;
    private int uid;
    private String fname;
    private String lname;
    private String uname;
    private String type;
    private String phone;

    
    private Session(){
    //Private cons. prevents instance
    }

    public static synchronized Session getInstance() {
        if(instance == null)
        {
            instance = new Session();
        }
        return instance;
    }

    public static boolean isInstanceEmpty() {
        return instance == null;
    }
    
        public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
        public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
