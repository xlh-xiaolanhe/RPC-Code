package com.xiaolanhe.serializar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 0:14
 */
@Data
@ToString
@NoArgsConstructor
public class User implements Serializable {
    public User(String name) {
        this.name = name;
    }

    private String name;

}

