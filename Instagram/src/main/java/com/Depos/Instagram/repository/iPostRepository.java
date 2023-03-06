package com.Depos.Instagram.repository;

import com.Depos.Instagram.dao.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iPostRepository extends JpaRepository<Post,Integer> {
}
