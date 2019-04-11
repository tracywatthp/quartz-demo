package com.tinxi.quartz.service.impl;

import com.tinxi.quartz.bean.QrtzJobDetails;
import com.tinxi.quartz.mapper.QrtzJobDetailsMapper;
import com.tinxi.quartz.service.IQrtzJobDetailsService;
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
public class QrtzJobDetailsServiceImpl extends ServiceImpl<QrtzJobDetailsMapper, QrtzJobDetails> implements IQrtzJobDetailsService {
	
}
