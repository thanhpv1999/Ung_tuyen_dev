package com.nhom20.cimeemappserver.service;

import com.nhom20.cimeemappserver.entity.CustomerPassword;
import com.nhom20.cimeemappserver.entity.Member;
import com.nhom20.cimeemappserver.entity.MemberUser;

public interface MemberService {
	public void addMember(Member member);

	public Member getMemberById(String groupId);
}
