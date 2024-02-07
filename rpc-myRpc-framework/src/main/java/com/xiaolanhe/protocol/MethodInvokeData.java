package com.xiaolanhe.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:39
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MethodInvokeData implements Protocol{
    /** 类名 */
    private Class targetInterface;

    /** 调用的方法名  */
    private String methodName;


    private Class<?>[] parametersTypes;

    /** 方法的参数(因为可能存在对一个方法的重载) */
    private Object[] parameters;
}
