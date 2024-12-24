package com.nwpu.carpoolingsystem.controller;

import com.nwpu.carpoolingsystem.entity.CarpoolOrder;
import com.nwpu.carpoolingsystem.entity.Result;
import com.nwpu.carpoolingsystem.service.CarpoolOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@RestController
@RequestMapping("/carpoolOrder")
public class CarpoolOrderController {
    @Autowired
    private CarpoolOrderService carpoolOrderService;

//    // 创建拼车订单
//    @PostMapping("/create")
//    public Result<CarpoolOrder> createOrder(@RequestParam Long carpoolId, @RequestParam Long userId, @RequestBody CarpoolOrder order) {
//        CarpoolOrder newOrder = carpoolOrderService.createOrder(carpoolId, userId, order);
//        return Result.success(newOrder);
//    }
//
//    // 确认拼车订单
//    @PostMapping("/confirm/{orderId}")
//    public Result<CarpoolOrder> confirmOrder(@PathVariable Long orderId) {
//        CarpoolOrder updatedOrder = carpoolOrderService.confirmOrder(orderId);
//        return updatedOrder != null ? Result.success(updatedOrder) : Result.error("订单确认失败");
//    }
//
//    // 取消拼车订单
//    @PostMapping("/cancel/{orderId}")
//    public Result<CarpoolOrder> cancelOrder(@PathVariable Long orderId) {
//        CarpoolOrder updatedOrder = carpoolOrderService.cancelOrder(orderId);
//        return updatedOrder != null ? Result.success(updatedOrder) : Result.error("订单取消失败");
//    }
}
