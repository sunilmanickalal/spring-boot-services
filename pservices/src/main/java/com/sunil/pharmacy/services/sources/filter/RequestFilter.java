package com.sunil.pharmacy.services.sources.filter;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String mid = UUID.randomUUID().toString();
        MDC.put("mid", mid);
        chain.doFilter(request, response);
    }

}
