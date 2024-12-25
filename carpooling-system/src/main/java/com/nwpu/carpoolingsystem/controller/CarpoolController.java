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

    /**
     * 获得所有拼车信息
     * @return
     */
    @GetMapping("/getCarpool")
    public Result getCarpool() {
        return carpoolService.getCarpool();
    }

    /**
     * 条件获得拼车信息
     * @param carpool
     * @return
     */
    @PostMapping("/getCarpoolByParam")
    public Result getCarpoolByParam(@RequestBody Carpool carpool) {
        return carpoolService.getCarpoolByParam(carpool);
    }

    /**
     * 条件查看当前车主的拼车信息
     * @param carpool
     * @return
     */
    @PostMapping("/currentUserCarpoolByParam")
    public Result getCurrentUserCarpoolByParam(@RequestBody Carpool carpool) {
        return carpoolService.getCurrentUserCarpoolByParam(carpool);
    }

    /**
     * 车主调整行程起始点
     * @param carpool
     * @return
     */
    @PostMapping("/updateCarpoolPoint")
    public Result updateCarpoolPoint(@RequestBody Carpool carpool) {
        return carpoolService.updateCarpoolPoint(carpool);
    }

    /**
     * 车主调整行程出发时间
     * @param carpool
     * @return
     */
    @PostMapping("/updateCarpoolTime")
    public Result updateCarpoolTime(@RequestBody Carpool carpool) {
        return carpoolService.updateCarpoolTime(carpool);
    }

    /**
     * 车主调整行程剩余座椅
     * @param carpool
     * @return
     */
    @PostMapping("/updateCarpoolSeat")
    public Result updateCarpoolSeat(@RequestBody Carpool carpool) {
        return carpoolService.updateCarpoolSeat(carpool);
    }

    /**
     * 车主确定行程完成
     * @param carpool
     * @return
     */
    @PostMapping("/compeleteCarpool")
    public Result compeleteCarpool(@RequestBody Carpool carpool) {
        return carpoolService.comleleteCarpool(carpool);
    }
}
