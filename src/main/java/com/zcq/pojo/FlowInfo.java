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
public class FlowInfo {
    /* Map中的key,本字段是为了创建数据库 */
    @Column(isKey = true)
    private String flowid;
    private String mac;
    private String ip;
    private int flag;

    /* Redis解析填充 */
    private int protocol;
    private int dstPort;
    private int averageFlowRate;
    private int srcPort;
    private int maxflow;
    /* 若为空,则为Mysql数据插入时间 */
    @ColumnType(value = MySqlTypeConstant.TIMESTAMP)
    @DefaultValue(value = "CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP")
    private Timestamp time;
    private int visitCnt;
    private int life;
}
