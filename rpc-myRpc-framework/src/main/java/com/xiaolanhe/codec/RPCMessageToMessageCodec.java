package com.xiaolanhe.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import lombok.extern.slf4j.Slf4j;
import com.xiaolanhe.protocol.Protocol;
import com.xiaolanhe.serializar.Serializar;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *@author: xiaolanhe
 *@createDate: 2024/2/7 10:37
 */
@Slf4j
public class RPCMessageToMessageCodec extends MessageToMessageCodec<ByteBuf, Protocol> {

    private Serializar serializar;

    public RPCMessageToMessageCodec(Serializar serializar)
    {
        this.serializar = serializar;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Protocol protocol, List<Object> list) throws Exception {
        log.debug("编码器开始运行");
        ByteBufAllocator bufAllocator = ctx.alloc();
        ByteBuf buffer = bufAllocator.buffer();

        try{
            byte[] contentBytes = this.serializar.serializar(protocol);

            // 设置魔数  5字节
            buffer.writeBytes(Protocol.MAGIC_NUMBER.getBytes(StandardCharsets.UTF_8));

            // 设置协议版本号 1字节
            buffer.writeByte(Protocol.PROTOCOL_VERSION);

            // 告诉封帧解码器 内容数据的长度
            buffer.writeInt(contentBytes.length);
            // 发送内容数据
            buffer.writeBytes(contentBytes);

            list.add(buffer);
        }catch (Exception e){
            log.error("编码器出现了异常", e);
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> list) throws Exception {
        // 先进行魔数的比较
        CharSequence charSequence = msg.readCharSequence(5, StandardCharsets.UTF_8);
        if(!Protocol.MAGIC_NUMBER.equals(charSequence.toString())){
            throw new RuntimeException("Invalid magic number");
        }

        //协议版本号的比较
        byte protocolVersion = msg.readByte();
        if(Protocol.PROTOCOL_VERSION != protocolVersion){
            throw new RuntimeException("Invalid protocol version");
        }

        // 读取内容的长度
        int contentLength = msg.readInt();

        byte[] bytes = new byte[contentLength];
        msg.readBytes(bytes);

        // 反序列化
        Protocol protocol = serializar.deserializar(bytes);

        // 交给后面handler操作
        list.add(protocol);
    }
}
