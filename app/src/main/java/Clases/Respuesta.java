package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

public class Respuesta {
    @Generated("org.jsonschema2pojo")

        @SerializedName("msg")
        @Expose
        private String msg;
        @SerializedName("status")
        @Expose
        private Status status;
        @SerializedName("usuario")
        @Expose
        private Usu usuario;


        public Respuesta() {
        }


        public Respuesta(String msg, Status status, Usu usuario) {
            this.msg = msg;
            this.status = status;
            this.usuario = usuario;
        }

        public Respuesta(String msg, Status status) {
            this.msg = msg;
            this.status = status;
        }

        public Respuesta(Status status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }


        public void setMsg(String msg) {
            this.msg = msg;
        }


        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Usu getUsuario() {
            return usuario;
        }

        public void setUsuario(Usu usuario) {
            this.usuario = usuario;
        }

}