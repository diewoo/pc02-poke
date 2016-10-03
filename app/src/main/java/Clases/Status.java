package Clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Diego Renteria on 02/10/2016.
 */

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Status {

    @Generated("org.jsonschema2pojo")


        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("msg")
        @Expose
        private String msg;

        /**
         *
         * @return
         * The code
         */


        public Status() {
        }


        public Status(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }

        /**
         *
         * @param code
         * The code
         */
        public void setCode(Integer code) {
            this.code = code;
        }

        public Status withCode(Integer code) {
            this.code = code;
            return this;
        }

        /**
         *
         * @return
         * The msg
         */
        public String getMsg() {
            return msg;
        }

        /**
         *
         * @param msg
         * The msg
         */
        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Status withMsg(String msg) {
            this.msg = msg;
            return this;
        }

    }

