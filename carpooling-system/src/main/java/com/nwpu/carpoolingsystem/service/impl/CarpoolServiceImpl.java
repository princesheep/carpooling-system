package com.nwpu.carpoolingsystem.service.impl;

import com.nwpu.carpoolingsystem.entity.Carpool;
import com.nwpu.carpoolingsystem.mapper.CarpoolMapper;
import com.nwpu.carpoolingsystem.service.CarpoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wzy
 * @since 2024-12-20
 */
@Service
public class CarpoolServiceImpl implements CarpoolService {
    @Autowired
    private CarpoolMapper carpoolMapper;


}
