package chapter2;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 在没有同步的情况下统计已处理请求数量的Servlet，不要这么做
 */
@NotThreadSafe
public class UnsafeCountingFactorize extends GenericServlet implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        ++count;
        encodeIntoResponse(res, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        ////并不是真的因式分解
        return new BigInteger[] { i };
    }
}
