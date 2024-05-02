package com.example.entity;


import com.example.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter @Setter
@ToString
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Long profileId;
    @Column(name = "from_where_region")
    private String fromWhereRegion;
    @Column(name = "from_where_district")
    private String fromWhereDistrict;
    @Column(name = "to_where_region")
    private String toWhereRegion;
    @Column(name = "to_where_district")
    private String toWhereDistrict;
    @Column(name = "price")
    private Double price =  0.0;
    @Column(name = "how_many_people")
    private Integer howManyPeople = 0;
    @Column(name = "how_many_people_taxi")
    private Integer howManyPeopleTaxi = 0;
    @Column(name = "additional_info")
    private String additionalInfo;
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "create_date")
    private LocalDateTime createDate = LocalDateTime.now();
}
