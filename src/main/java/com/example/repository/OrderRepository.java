package com.example.repository;

import com.example.entity.OrderEntity;
import com.example.enums.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer>  {

    List<OrderEntity> findAllByOrderStatus(OrderStatus status);
    OrderEntity findByProfileId(Long id);

    @Transactional
    @Modifying
    @Query("update  OrderEntity set fromWhereRegion = :fromWhereRegion, fromWhereDistrict = :fromWhereDistrict," +
            " toWhereRegion = :toWhereRegion, toWhereDistrict = :toWhereDistrict, price = :price, " +
            "peopleCount = :peopleCount,howManyPeopleTaxi = :howManyPeopleTaxi, additionalInfo = :additionalInfo," +
            "orderStatus = :orderStatus where profileId = :profileId")
    Integer updateByProfileId(@Param("fromWhereRegion") String fromWhereRegion,
                           @Param("fromWhereDistrict") String fromWhereDistrict,
                           @Param("toWhereRegion") String toWhereRegion,
                           @Param("toWhereDistrict") String toWhereDistrict,
                           @Param("price") Double price,
                           @Param("peopleCount") String howManyPeople,
                           @Param("howManyPeopleTaxi") Integer howManyPeopleTaxi,
                           @Param("additionalInfo") String additionalInfo,
                           @Param("orderStatus") OrderStatus orderStatus,
                           @Param("profileId") Long profileId);
}
