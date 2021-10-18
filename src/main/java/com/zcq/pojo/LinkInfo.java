package com.zcq.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(isSimple = true)
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class LinkInfo {
    /* 创建数据库需要的字段,linkid和value存储在Map中,time默认为Mysql数据插入时间，由Mysql生成 */
    /* 取消主键，防止覆盖 */
    //@Column(isKey = true)
    private String linkid;
    @Column(type = MySqlTypeConstant.DECIMAL)
    private double value;
    @ColumnType(value = MySqlTypeConstant.TIMESTAMP)
    @DefaultValue(value = "CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP")
    private Timestamp time;
}
