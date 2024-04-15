package com.example.repository;

import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {
    List<ProfileEntity> findAllByRole(ProfileRole role);

    ProfileEntity findByUserId(Long id);

    @Transactional
    @Modifying
    @Query("update  ProfileEntity set name = :name, surname = :surname, role = :role, status = :status, step = :step, phoneNumber = :phone,carNum = :carNum, lastMessageId = :messageId,visible = :visible where userId = :userId")
    Integer updateByUserId(@Param("name") String name,
                           @Param("surname") String surname,
                           @Param("role") ProfileRole role,
                           @Param("status") ProfileStatus staus,
                           @Param("step") ProfileStep step,
                           @Param("phone") String phone,
                           @Param("carNum") String carNum,
                           @Param("messageId") Integer lastMessageId,
                           @Param("visible") Boolean visible,
                           @Param("userId") Long userId);

}
