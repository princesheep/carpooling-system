package com.nwpu.carpoolingsystem.controller;

import com.nwpu.carpoolingsystem.entity.CarpoolOrder;
import com.nwpu.carpoolingsystem.entity.OwnerRank;
import com.nwpu.carpoolingsystem.entity.Result;
import com.nwpu.carpoolingsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

//    // 导出拼车记录（根据时间段和车主筛选）
//    @GetMapping("/export")
//    public Result<List<CarpoolOrder>> exportCarpoolOrders(
//            @RequestParam(required = false) Long ownerId,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate) {
//
//        List<CarpoolOrder> orders = adminService.exportCarpoolOrders(ownerId, startDate, endDate);
//        return Result.success(orders);  // 返回导出的拼车订单记录
//    }
//
//    // 获取成功拼车次数最多的车主列表
//    @GetMapping("/top-owners")
//    public Result<List<OwnerRank>> getTopOwners() {
//        List<OwnerRank> topOwners = adminService.getTopOwnersByCompletedOrders();
//        return Result.success(topOwners);  // 返回按成功拼车次数排序的车主列表
//    }
}
