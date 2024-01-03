package com.xiaolanhe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/3 23:38
 */

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private String name;
    private String password;
}
