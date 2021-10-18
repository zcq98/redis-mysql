package com.zcq.pojo;

import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlCharsetConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlEngineConstant;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @description: 测试mybatis-enhance-actable
 * @author: congqi.zhao
 * @date: Created in 2021/9/9 14:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@TableCharset(MySqlCharsetConstant.UTF8)
@TableEngine(MySqlEngineConstant.InnoDB)
public class User {
    @IsKey
    private int id;
    @Column
    private String name;
    @Column
    private String pwd;
    @Column
    @ColumnType(value = MySqlTypeConstant.TIMESTAMP)
    @DefaultValue(value = "CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP")
    private Timestamp time;
}
