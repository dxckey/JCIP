package chapter2;

import net.jcip.annotations.GuardedBy;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

public class SynchronizedFactorize extends GenericServlet implements Servlet {
    @GuardedBy(value = "this")
    private BigInteger lastNumber;
    @GuardedBy(value = "this")
    private BigInteger[] lastFactors;

    @Override
    public synchronized void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber)) {
            encodeIntoResponse(res, lastFactors);
        } else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(res, factors);
        }
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
