package com.example.mcarit.enq_enquiry.model;

/**
 * Created by lalit on 9/12/2016.
 */
public class User {

    private int id;
    private String fileno;
    private String msg;
    private String phone;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileno() {
        return fileno;
    }

    public void setFileno(String fileno) {
        this.fileno = fileno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) { this.msg = msg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
