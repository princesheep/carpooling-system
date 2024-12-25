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
}
