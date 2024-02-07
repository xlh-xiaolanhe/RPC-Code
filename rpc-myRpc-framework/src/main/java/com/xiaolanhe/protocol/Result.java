package com.xiaolanhe.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Result implements Protocol{
    private Object resultValue;
    private Exception exception;
}
