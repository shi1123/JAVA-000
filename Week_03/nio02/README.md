# netty-gateway


在HttpInBoundHandler的构造器中初始化
OkhttpOutBoundHandler 和 HttpRequestFilterImpl
```$java
    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
//        handler = new HttpOutboundHandler(this.proxyServer);
        handler = new OkhttpOutboundHandler(this.proxyServer);
        filter = new HttpRequestFilterimpl();
    }
```
## 作业一

OkhttpOutBoundHandler

```$xslt
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
            Request request = new Request.Builder().url(uri).build();
//            Request request = new Request.Builder().url("http://localhost:8088/api/hello").build();
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
```
## 作业二

 HttpRequestFilterImpl
 ```$xslt
public class HttpRequestFilterimpl implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        System.out.println("start filter! " + fullRequest.toString());
    }
}
```

## 测试

不走代理，直接压测后台
```$xslt
wrk -c 100 -t 10 -d 60 http://localhost:8088/api/hello

Running 1m test @ http://localhost:8088/api/hello
  10 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    18.90ms   55.29ms 626.65ms   91.94%
    Req/Sec     4.74k     2.08k   12.45k    66.79%
  2678725 requests in 1.00m, 319.81MB read
Requests/sec:  44594.88
Transfer/sec:      5.32MB
```

走代理
```$xslt
wrk -c 100 -t 10 -d 60 http://localhost:8080/api/hello

Running 1m test @ http://localhost:8080/api/hello
  10 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    49.32ms  226.38ms   1.99s    95.90%
    Req/Sec     1.65k     1.24k    5.21k    66.88%
  513005 requests in 1.00m, 40.12MB read
  Socket errors: connect 0, read 0, write 0, timeout 178
Requests/sec:   8536.32
Transfer/sec:    683.57KB
```

性能下降太快

将Filter中的打印输出关闭再测试：
```$xslt
wrk -c 100 -t 10 -d 60 http://localhost:8080/api/hello

Running 1m test @ http://localhost:8080/api/hello
  10 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    31.48ms  164.54ms   1.66s    96.19%
    Req/Sec     3.67k     1.57k    8.52k    73.02%
  1029675 requests in 1.00m, 80.52MB read
  Socket errors: connect 0, read 0, write 0, timeout 203
Requests/sec:  17133.87
Transfer/sec:      1.34MB
```
走代理的吞吐还是性能有下降。