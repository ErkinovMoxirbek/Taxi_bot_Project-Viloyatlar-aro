package com.example.repository;

import com.example.entity.AdminInfoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminInfoRepository extends CrudRepository<AdminInfoEntity, Integer> {
    AdminInfoEntity findByInfoId(Integer id);
    @Modifying
    @Query("update AdminInfoEntity set login = :login, password = :password, visible = :visible where id = :id")
    Integer updateByProfileId(@Param("login") String login,
                              @Param("password") String password,
                              @Param("visible") Boolean visible,
                              @Param("id") Integer id);

}
