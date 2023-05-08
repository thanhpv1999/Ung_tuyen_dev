package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
@Embeddable
public class MemberUserPk implements Serializable{

	private String user;
	private String member;
	@Override
	public int hashCode() {
		return Objects.hash(member, user);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberUserPk other = (MemberUserPk) obj;
		return Objects.equals(member, other.member) && Objects.equals(user, other.user);
	}
	
}
