package chapter2;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用AtomicLong类型的变量来统计已处理请求的数量
 */
@ThreadSafe
public class CountingFactorize extends GenericServlet implements Servlet {
    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(res, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        //并不是真的因式分解
        return new BigInteger[] { i };
    }
}
