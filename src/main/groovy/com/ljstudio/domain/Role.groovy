package com.ljstudio.domain

import com.fasterxml.jackson.annotation.JsonIgnore

import javax.persistence.*

@Entity
@Table(name="ROLE")
class Role extends BaseBean{
	
	@JsonIgnore
	@Id
	@Column(name="ROLE_ID")
	@SequenceGenerator(name="ROLE_SEQUENCE", sequenceName="ROLE_SEQUENCE", initialValue=1, allocationSize=10)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_SEQUENCE")
	Long roleId
	
	@Column(name="CODE", length=12, unique=true, nullable=false)
	String code
	
	@JsonIgnore
	@Column(name="DESCRIPTION", length=100, nullable=false)
	String description

	@Override
	Object getPrimaryKey() {
		return roleId;
	}
}
