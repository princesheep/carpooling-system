package com.nwpu.carpoolingsystem.service.impl;

import com.nwpu.carpoolingsystem.entity.CarpoolOrder;
import com.nwpu.carpoolingsystem.mapper.CarpoolOrderMapper;
import com.nwpu.carpoolingsystem.service.CarpoolOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Service
public class CarpoolOrderServiceImpl implements CarpoolOrderService {
    @Autowired
    private CarpoolOrderMapper carpoolOrderMapper;


}
