package com.nwpu.carpoolingsystem.service;

import com.nwpu.carpoolingsystem.entity.Carpool;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nwpu.carpoolingsystem.entity.Result;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
public interface CarpoolService {
    Result createCarpool(Carpool carpool);

    Result getCurrentUserCarpool();

    Result getCurrentUserCarpoolByParam(Carpool carpool);
//    Carpool createCarpool(Long ownerId, Carpool carpool);
//
//    List<Carpool> getCarpoolsByOwner(Long ownerId);
//
//    Carpool updateCarpool(Long carpoolId, Carpool carpool);
}
