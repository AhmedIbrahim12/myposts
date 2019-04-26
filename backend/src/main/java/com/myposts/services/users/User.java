package com.myposts.services.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myposts.services.roles.Role;

@Entity
@Table(name = "system_users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    @JsonIgnore
    private String userPassword;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String userName, String userPassword) {
	this.userName = userName;
	this.userPassword = userPassword;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserPassword() {
	return userPassword;
    }

    public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
    }

    public List<Role> getRoles() {
	return roles;
    }

    @JsonIgnore
    public String[] getUserRoles() {
	List<String> rolesArray = roles.stream()
		.map(role -> role.getRoleName())
		.collect(Collectors.toList());
	return rolesArray.toArray(new String[rolesArray.size()]);
    }

    public void setRoles(List<Role> roles) {
	this.roles = roles;
    }

    public Date getCreateDate() {
	return createDate;
    }
}
