package com.fq.config.interceptor;


import com.fq.utils.TraceIdUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class TraceIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        //如果有上层调用就用上层的ID
        String traceId = request.getHeader(TraceIdUtil.TRACE_ID);
        if (traceId == null || traceId.isEmpty()) {
            traceId = TraceIdUtil.generateTraceId();
        }
        TraceIdUtil.setTraceId(traceId);

        //配置traceId到响应header中
        response.setHeader(TraceIdUtil.TRACE_ID, TraceIdUtil.getTraceId());

        return true;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        //调用结束后删除
        TraceIdUtil.remove();
    }
}

