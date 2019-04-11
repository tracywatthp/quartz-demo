package com.tinxi.quartz.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
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
@TableName("qrtz_triggers")
public class QrtzTriggers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SCHED_NAME")
	private String schedName;		// 
	@TableField("TRIGGER_NAME")
	private String triggerName;		// 
	@TableField("TRIGGER_GROUP")
	private String triggerGroup;		// 
	@TableField("JOB_NAME")
	private String jobName;		// 
	@TableField("JOB_GROUP")
	private String jobGroup;		// 
	@TableField("DESCRIPTION")
	private String description;		// 
	@TableField("NEXT_FIRE_TIME")
	private Long nextFireTime;		// 
	@TableField("PREV_FIRE_TIME")
	private Long prevFireTime;		// 
	@TableField("PRIORITY")
	private Integer priority;		// 
	@TableField("TRIGGER_STATE")
	private String triggerState;		// 
	@TableField("TRIGGER_TYPE")
	private String triggerType;		// 
	@TableField("START_TIME")
	private Long startTime;		// 
	@TableField("END_TIME")
	private Long endTime;		// 
	@TableField("CALENDAR_NAME")
	private String calendarName;		// 
	@TableField("MISFIRE_INSTR")
	private Integer misfireInstr;		// 
	@TableField("JOB_DATA")
	private Blob jobData;		// 

}


































