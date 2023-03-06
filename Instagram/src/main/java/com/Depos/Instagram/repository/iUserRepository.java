package com.Depos.Instagram.repository;

import com.Depos.Instagram.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iUserRepository extends JpaRepository<User,Integer> {
}
