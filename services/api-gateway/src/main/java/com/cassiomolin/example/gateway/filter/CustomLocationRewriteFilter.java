package com.cassiomolin.example.gateway.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.http.HttpStatus;

/**
 * Filter to re-write the {@code Location} header to point to the API gateway.
 *
 * @author cassiomolin
 */
public class CustomLocationRewriteFilter extends LocationRewriteFilter {

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        int statusCode = ctx.getResponseStatusCode();
        return HttpStatus.valueOf(statusCode).is3xxRedirection() || HttpStatus.valueOf(statusCode) == HttpStatus.CREATED;
    }
}
