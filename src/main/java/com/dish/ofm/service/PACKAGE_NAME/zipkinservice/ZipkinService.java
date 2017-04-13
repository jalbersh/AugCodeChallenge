package com.dish.ofm.service.PACKAGE_NAME.zipkinservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanAccessor;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ZipkinService {
    private final static Logger logger = LoggerFactory.getLogger(ZipkinService.class);

    private final SpanAccessor spanAccessor;
    private final Tracer tracer;
    private final ZipkinSpanListener listener;

    @Autowired(required = false)
    public ZipkinService(
        SpanAccessor spanAccessor,
        Tracer tracer,
        ZipkinSpanListener zipkinSpanListener
    ) {
        this.spanAccessor = spanAccessor;
        this.tracer = tracer;
        this.listener = zipkinSpanListener;
    }

    // Necessary for when zipkin is not enabled
    private ZipkinService() {
        this.spanAccessor = null;
        this.tracer = null;
        this.listener = null;
    }

    public SpanAccessor getSpanAccessor() {
        return spanAccessor;
    }

    public boolean isEnabled() {
        return spanAccessor != null && tracer != null;
    }

    public String getParentId(Span span) {
        return span != null && span.getParents() != null && !span.getParents().isEmpty() ? String.valueOf(span.getParents().get(0)) : "";
    }

    public Span publishClientSentEvent(String identifier) {
        if (isEnabled() && hasSpan()) {
            logger.info("Publish Client Sent Event: {}", identifier);

            Span span = tracer.createSpan(identifier);
            spanAccessor.getCurrentSpan().tag(String.format("SEND %s", Instant.now().toString()), identifier);

            listener.report(spanAccessor.getCurrentSpan());
            return span;
        }
        return null;
    }

    public void publishClientReceivedEvent(String identifier, Span span) {
        if (isEnabled() && hasSpan()) {
            logger.info("Publish Client Received Event: {}", identifier);

            spanAccessor.getCurrentSpan().tag(String.format("RECV %s", Instant.now().toString()), identifier);

            listener.report(spanAccessor.getCurrentSpan());

            tracer.close(span);
        }
    }

    private boolean hasSpan() {
        return spanAccessor.getCurrentSpan() != null;
    }
}
