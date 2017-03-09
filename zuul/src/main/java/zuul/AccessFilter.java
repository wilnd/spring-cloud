package zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by THINK on 2017/3/9.
 */

public class AccessFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext rcx = RequestContext.getCurrentContext();
        HttpServletRequest request = rcx.getRequest();
        log.info(String.format("%s request to %s",request.getMethod(),request.getRequestURL().toString()));
        log.info(request.getRequestURI());
        String accessToken = request.getParameter("accessToken");
        if (accessToken==null){
            log.warn("access token is empty");
            rcx.setSendZuulResponse(false);
            rcx.setResponseStatusCode(401);
            return  null;
        }
        log.info("accessTocken ok");
        return null;
    }
}
