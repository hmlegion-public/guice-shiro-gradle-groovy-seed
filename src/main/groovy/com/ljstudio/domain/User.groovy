package com.ljstudio.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

import javax.persistence.*

@Entity
@Table(name="SHIROTALK_USER")
class User {
	
	@Id
	@Column(name="USER_ID")
	@SequenceGenerator(name="USER_SEQUENCE", sequenceName="USER_SEQUENCE", initialValue=1, allocationSize=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQUENCE")
	Long userId
	
	@Column(name="USERNAME", length=12, unique=true, nullable=false)
	String username
	
	@Column(name="EMAIL", length=100, unique=true, nullable=false)
	String email
	
	@JsonIgnore
	@Column(name="PASSWORD", length=100, nullable=false)
	String password
	
	@JsonIgnore
	@Column(name="SALT", length=100, nullable=false)
	String salt
	
	@Column(name="FIRST_NAME", length=50)
	String firstName
	
	@Column(name="LAST_NAME", length=50)
	String lastName
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable
	(
		name="USER_ROLE_JOIN",
		joinColumns=[ @JoinColumn(name="USER_ID", referencedColumnName="USER_ID") ],
		inverseJoinColumns=[ @JoinColumn(name="ROLE_ID", referencedColumnName="ROLE_ID") ]
	)
	List<Role> roles
	
	@JsonProperty
	public List<String> getUserRoles() {
		roles?.collect {role ->
			role.code
		}
	}
	
}
