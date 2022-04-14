package com.pug.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "kss_user")
public class KssUsers implements java.io.Serializable{
    @Column(name="create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
    private Integer status;
    private String username;
    @Id
    private Long userid;
    private Integer usermale;

}
