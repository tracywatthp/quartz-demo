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
@TableName("qrtz_simple_triggers")
public class QrtzSimpleTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SCHED_NAME")
	private String schedName;		// 
	@TableField("TRIGGER_NAME")
	private String triggerName;		// 
	@TableField("TRIGGER_GROUP")
	private String triggerGroup;		// 
	@TableField("REPEAT_COUNT")
	private Long repeatCount;		// 
	@TableField("REPEAT_INTERVAL")
	private Long repeatInterval;		// 
	@TableField("TIMES_TRIGGERED")
	private Long timesTriggered;		// 

}














