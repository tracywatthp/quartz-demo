package com.tinxi.quartz.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tinxi
 * @since 2019-04-11
 */
@Data
@Accessors(chain = true)
@TableName("qrtz_scheduler_state")
public class QrtzSchedulerState implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SCHED_NAME")
	private String schedName;		// 
	@TableField("INSTANCE_NAME")
	private String instanceName;		// 
	@TableField("LAST_CHECKIN_TIME")
	private Long lastCheckinTime;		// 
	@TableField("CHECKIN_INTERVAL")
	private Long checkinInterval;		// 

}










