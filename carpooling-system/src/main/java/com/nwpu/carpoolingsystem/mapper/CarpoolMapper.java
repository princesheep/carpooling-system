package com.nwpu.carpoolingsystem.mapper;

import com.nwpu.carpoolingsystem.entity.Carpool;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Mapper
public interface CarpoolMapper {

    int insertCarpool(Carpool carpool);

    List<Carpool> getCurrentUserCarpool(Carpool carpool);

    List<Carpool> getCurrentUserCarpoolByState(Carpool carpool);

    List<Carpool> getCurrentUserCarpoolByStateAndTime(Carpool carpool);

    List<Carpool> getCurrentUserCarpoolByTime(Carpool carpool);

    int getStateById(Carpool carpool);

    Long getOwnerIdById(Carpool carpool);

    void updateCarpoolPoint(Carpool carpool);

    void updateCarpoolSeat(Carpool carpool);

    void updateCarpoolTime(Carpool carpool);

    void compeleteCarpool(Carpool carpool);

    List<Carpool> getCarpool();

    List<Carpool> getCarpoolByTime(Carpool carpool);

    List<Carpool> getCarpoolByOwner(Carpool carpool);

    List<Carpool> getCarpoolByOwnerAndTime(Carpool carpool);
}
