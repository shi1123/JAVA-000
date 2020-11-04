package io.github.kimmking.gateway.outbound.okhttp;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler {
    String serverUrl;

    public OkhttpOutboundHandler(String proxyServer) {
        serverUrl = proxyServer;
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) throws IOException {
        OkHttpClient client = new OkHttpClient();
        try {
            String uri = fullRequest.uri();
            uri = uri.startsWith("/") ? uri : "/" + uri;
            String requestUrl = serverUrl + uri;
//            Request request = new Request.Builder().url(uri).build();
            Request request = new Request.Builder().url("http://localhost:8088/api/hello").build();
            Response response = client.newCall(request).execute();
//            ctx.writeAndFlush(response.body());
//            ctx.writeAndFlush(response.newBuilder());

            FullHttpResponse fullHttpResponse = null;
            byte[] body = response.body().string().getBytes();
            fullHttpResponse = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Type", "application/json");
            fullHttpResponse.headers().setInt("Content-Length", Integer.parseInt(response.header("Content-Length")));
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(fullHttpResponse);
                }
            }
            ctx.flush();

        } finally {
//            client.clone();
//            ctx.flush();
        }
    }
}
