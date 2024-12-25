package com.nwpu.carpoolingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Getter
@Setter
@ApiModel(value = "Carpool对象", description = "")
public class Carpool implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;              // 拼车ID
    private Long ownerId;         // 车主ID
    private String startPoint;    // 起点
    private String endPoint;      // 终点
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date departureTime;   // 发车时间
    private Integer availableSeats;  // 可用座位数
    private String route;         // 行驶路线
    private String plateNumber;   // 车牌号
    private Integer totalSeats;   // 总座位数
    private Date createTime;      // 创建时间
    private Date updateTime;      // 更新时间
    private Integer state;

    @TableField(exist = false)
    private Boolean useTime;
    @TableField(exist = false)
    private Boolean useState;
    @TableField(exist = false)
    private Boolean useOwner;




}
