package com.nwpu.carpoolingsystem.service.impl;

import com.nwpu.carpoolingsystem.mapper.CarpoolMapper;
import com.nwpu.carpoolingsystem.mapper.CarpoolOrderMapper;
import com.nwpu.carpoolingsystem.service.AdminService;
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
public class AdminServiceImpl implements AdminService {
    @Autowired
    private CarpoolOrderMapper carpoolOrderMapper;
    @Autowired
    private CarpoolMapper carpoolMapper;


}
