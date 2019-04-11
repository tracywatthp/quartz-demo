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
@TableName("qrtz_job_details")
public class QrtzJobDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("SCHED_NAME")
	private String schedName;		// 
	@TableField("JOB_NAME")
	private String jobName;		// 
	@TableField("JOB_GROUP")
	private String jobGroup;		// 
	@TableField("DESCRIPTION")
	private String description;		// 
	@TableField("JOB_CLASS_NAME")
	private String jobClassName;		// 
	@TableField("IS_DURABLE")
	private String isDurable;		// 
	@TableField("IS_NONCONCURRENT")
	private String isNonconcurrent;		// 
	@TableField("IS_UPDATE_DATA")
	private String isUpdateData;		// 
	@TableField("REQUESTS_RECOVERY")
	private String requestsRecovery;		// 
	@TableField("JOB_DATA")
	private Blob jobData;		// 

}






















