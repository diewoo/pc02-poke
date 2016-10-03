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



    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return user;
    }

    /**
     *
     * @param user
     * The username
     */
    public void setUsername(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
