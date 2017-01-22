package com.zhang.model;

import java.io.Serializable;

public class UserEmail implements Serializable {
    private Integer id;

    private String email;

    private Integer userId;

    private String createtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }

	@Override
	public String toString() {
		return "UserEmail [id=" + id + ", email=" + email + ", userId="
				+ userId + ", createtime=" + createtime + "]";
	}
    
}