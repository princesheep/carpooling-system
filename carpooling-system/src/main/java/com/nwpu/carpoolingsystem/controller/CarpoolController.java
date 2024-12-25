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



    /**
     * 车主创建拼车信息
     * @param carpool
     * @return
     */
    @PostMapping("/create")
    public Result createCarpool(@RequestBody Carpool carpool) {
        return carpoolService.createCarpool(carpool);
    }

    /**
     * 查看当前车主的所有拼车信息
     * @return
     */
    @GetMapping("/currentUserCarpool")
    public Result getCurrentUserCarpool() {
        return carpoolService.getCurrentUserCarpool();
    }

    @PostMapping("/currentUserCarpoolByParam")
    public Result getCurrentUserCarpoolByParam(@RequestBody Carpool carpool) {
        return carpoolService.getCurrentUserCarpoolByParam(carpool);
    }
}
