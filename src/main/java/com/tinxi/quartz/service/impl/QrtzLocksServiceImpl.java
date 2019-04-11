package com.tinxi.quartz.service.impl;

import com.tinxi.quartz.bean.QrtzLocks;
import com.tinxi.quartz.mapper.QrtzLocksMapper;
import com.tinxi.quartz.service.IQrtzLocksService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tinxi
 * @since 2019-04-11
 */
@Service
public class QrtzLocksServiceImpl extends ServiceImpl<QrtzLocksMapper, QrtzLocks> implements IQrtzLocksService {
	
}
