package com.Elrearning.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
	@Column(unique=true)
    private String username;


	@Column(name = "email",unique=true)
	private String email;

	@Column(name= "password")
    private String password;

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	@Column(name= "usertype")
	private String usertype;


	public User(String username, String email, String password, String usertype, Set<Role> authorities) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.usertype = usertype;
		this.authorities = authorities;
	}

	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="user_role_junction",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;
	@OneToMany(mappedBy = "idCourse")
	private List<Course> courses ;

    public User() {
		super();
		authorities = new HashSet<>();
	}
	

	public User(Integer userId, String username, String password, String email, String usertype, Set<Role> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.authorities = authorities;
		this.email=email ;
	}

    public Integer getUserId() {
		return this.userId;
	}
	
	public void setId(Integer userId) {
		this.userId = userId;
	}
	
	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	/* If you want account locking capabilities create variables and ways to set them for the methods below */
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
    
}
