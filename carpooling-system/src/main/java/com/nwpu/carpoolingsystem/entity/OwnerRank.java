package com.nwpu.carpoolingsystem.entity;

import lombok.Data;

@Data
public class OwnerRank {
    private Long ownerId;           // 车主ID
    private String ownerName;       // 车主名称
    private Integer completedCount; // 成功拼车的次数

    // Getters and Setters
}
