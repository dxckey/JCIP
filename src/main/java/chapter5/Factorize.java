package chapter5;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 在因式分解servlet中使用结果缓存
 */
public class Factorize extends GenericServlet implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c = this::factor;
    private final Computable<BigInteger, BigInteger[]> cache = new Memorize<>(c);

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(res, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(res, "factorization interrupted");
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    void encodeError(ServletResponse resp, String errorString) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        //进行因式分解
        return new BigInteger[]{i};
    }
}
