package com.example.digitaltwinhealthcareserver.Dao;

import com.example.digitaltwinhealthcareserver.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByName(String name);

    @Query("select u from User u where u.id <= ?1")
    Page<User> findMore(Long maxId, Pageable pageable);

    @Modifying
    // 为什么要添加@Transactional和@Modifying https://blog.csdn.net/weixin_44476553/article/details/108537716
    @Transactional
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int updateById(String name, Long id);
}
