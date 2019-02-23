package com.itheima.dao;

import com.itheima.domain.Member;

public interface MemberDao {
    Member findMemberById(String id);
}
