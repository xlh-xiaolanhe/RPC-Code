package com.xiaolanhe.netty;

import com.xiaolanhe.serializar.Serializar;
import com.xiaolanhe.serializar.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/1/30 22:55
 */
@Slf4j
public class RPCMessageToMessageCodec extends MessageToMessageCodec<ByteBuf, User> {

    private Serializar serializar;

    public RPCMessageToMessageCodec(Serializar serializar) {
        this.serializar = serializar;
    }
    
    /**
     * desciption
      * @param ctx Netty 的核心组件之一。它用于在 Netty 的 ChannelPipeline 中传播操作，如读取、写入、连接、断开等。它还允许你动态修改 pipeline（例如添加或删除处理器），并且有助于获取关于底层 channel 的各种状态信息。
     * @param user  要被编码的消息
     * @param list 这是一个输出列表，应该将编码后的数据（通常是 ByteBuf 类型）添加到这个列表中。Netty 会自动处理这个列表中的所有对象，将它们发送到 pipeline 中的下一个处理器。
     * @return void
     * @Date 2024/1/30 23:00
    */
    @Override
    /**
     java object ---------------> 网络传输的格式
     serializaor

     1. java object msg参数已经获取
     2. 序列化 自己创建
     3. 如何把序列化后的byte[] 交给netty进行传输
     byte[] ---> ByteBuf ---> out.add();
     */
    protected void encode(ChannelHandlerContext ctx, User user, List<Object> list) {
        log.debug("编码器运行了");
        ByteBufAllocator alloc = ctx.alloc();
        ByteBuf buffer = alloc.buffer();

        byte[] bytes = new byte[0];
        try {
            bytes = serializar.serializar(user);
        } catch (Exception e) {
            log.error("编码器出现了异常", e);
        }

         /*封帧的解码器*/
        // 写入长度
        buffer.writeInt(bytes.length);
        // 写入内容
        buffer.writeBytes(bytes);

        list.add(buffer);
    }

    /**
     * desciption
      * @param ctx
     * @param byteBuf 需要被解码的字节数据
     * @param list 用于存放解码后的消息。从 ByteBuf 中解析出 User 对象后，应将它添加到这个列表中。Netty 随后会将这个列表中的对象传递给 pipeline 中的下一个处理器。
     * @return void
     * @Date 2024/1/30 22:59
    */
    @Override
    /**
        网络传输的格式 ---------->  JavaObject
                      反序列化
        1. 网络传输的格式：
             ByteBuf
     */
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.debug("解码器运行了");
        int contentLength = byteBuf.readInt();
        byte[] bytes = new byte[contentLength];
        byteBuf.readBytes(bytes);

        // 反序列化操作
        User user = (User) serializar.deserializar(bytes);

        //3 解码器如何把封装转换的对象 交给后面的操作呢？
        list.add(user);
    }
}
