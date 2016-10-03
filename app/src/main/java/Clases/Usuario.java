package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego Renteria on 01/10/2016.
 */

public class Usuario {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("password")
    @Expose
    private String password;


    public Usuario() {
    }


    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }


    public String getUsername() {
        return user;
    }

    public void setUsername(String user) {
        this.user = user;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
}
