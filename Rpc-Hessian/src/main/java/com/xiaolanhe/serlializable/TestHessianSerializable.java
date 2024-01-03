package com.xiaolanhe.serlializable;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.xiaolanhe.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/3 23:59
 */

@Slf4j
public class TestHessianSerializable {
    public static void main(String[] args) throws IOException {

        OutputStream outputStream = new FileOutputStream("E:\\Study After Graduate\\RPC\\RPC-Code\\Rpc-Hessian\\test");
        Hessian2Output out = new Hessian2Output(outputStream);
        out.writeObject(new User("xiaolanhe", "123456"));
        out.flush();
        outputStream.close();

        //Hessian反序列化
        InputStream inputStream = new FileInputStream("E:\\Study After Graduate\\RPC\\RPC-Code\\Rpc-Hessian\\test");
        Hessian2Input hessian2Input = new Hessian2Input(inputStream);
        User user = (User) hessian2Input.readObject();

        log.debug("{}", user);

    }
}
