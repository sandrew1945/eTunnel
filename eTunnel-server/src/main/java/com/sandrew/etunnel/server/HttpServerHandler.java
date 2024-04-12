package com.sandrew.etunnel.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

/**
 * Created by summer on 2019/8/19.
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest>
{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception
    {
        System.out.println("read html request.....");
        HttpRequest httpRequest = (HttpRequest)fullHttpRequest;
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(httpRequest.uri());
        queryStringDecoder.parameters().entrySet().stream().forEach(entry -> {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        });
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer("<html><body>Hello!</body></html>".getBytes()));
        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=UTF-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        channelHandlerContext.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("read complete .....");
        super.channelReadComplete(ctx);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        if (null != cause)
        {
            cause.printStackTrace();
        }
        ctx.close();
    }
}
