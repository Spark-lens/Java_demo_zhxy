package com.atguigu.zhxy.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//使用了 MyBatis-Plus 提供的注解表示实体类的表名
@TableName("tb_admin")
public class Admin {
    //表示实体类的主键，自动判断主键的数据类型
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private Character gender;
    private String password;
    private String email;
    private String telephone;
    private String address;
    private String portraitPath;    //头像的图片路径
}
