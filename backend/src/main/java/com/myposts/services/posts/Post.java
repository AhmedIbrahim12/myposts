package com.myposts.services.posts;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.myposts.services.users.User;

@Entity(name = "posts")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private boolean isPrivate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
    }

    public Post(String content, boolean isPrivate, User user) {
	this.content = content;
	this.isPrivate = isPrivate;
	this.user = user;
	this.createDate = new Date();
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public boolean isPrivate() {
	return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
	this.isPrivate = isPrivate;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Long getId() {
	return id;
    }

    public Date getCreateDate() {
	return createDate;
    }
}
