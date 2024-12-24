package com.nwpu.carpoolingsystem.controller;

import com.nwpu.carpoolingsystem.entity.Carpool;
import com.nwpu.carpoolingsystem.entity.Result;
import com.nwpu.carpoolingsystem.service.CarpoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@RestController
@RequestMapping("/carpool")
public class CarpoolController {
    @Autowired
    private CarpoolService carpoolService;

    // 创建拼车信息
//    @PostMapping("/create")
//    public Result<Carpool> createCarpool(@RequestParam Long ownerId, @RequestBody Carpool carpool) {
//        Carpool newCarpool = carpoolService.createCarpool(ownerId, carpool);
//        return Result.success(newCarpool);
//    }
//
//    // 根据车主ID获取拼车信息
//    @GetMapping("/owner/{ownerId}")
//    public Result<List<Carpool>> getCarpoolsByOwner(@PathVariable Long ownerId) {
//        List<Carpool> carpools = carpoolService.getCarpoolsByOwner(ownerId);
//        return Result.success(carpools);
//    }
//
//    // 更新拼车信息
//    @PutMapping("/{carpoolId}")
//    public Result<Carpool> updateCarpool(@PathVariable Long carpoolId, @RequestBody Carpool carpool) {
//        Carpool updatedCarpool = carpoolService.updateCarpool(carpoolId, carpool);
//        return Result.success(updatedCarpool);
//    }
}
