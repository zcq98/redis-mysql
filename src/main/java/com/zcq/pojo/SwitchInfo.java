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
public class SwitchInfo {
    /* Map中的key,本字段是为了创建数据库 */
    @Column(isKey = true)
    private String switchid;
    /* time默认为Mysql数据插入时间，由Mysql生成 */
    @ColumnType(value = MySqlTypeConstant.TIMESTAMP)
    @DefaultValue(value = "CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP")
    private Timestamp time;

    /* Redis解析填充 */
    private int averagepkts;
    private int maxpkts;
    private int averagebytes;
    private int maxbytes;
    private int totalbytes;
    private int totalflownums;
    private int totalflowrate;
}
