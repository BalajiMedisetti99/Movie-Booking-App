package com.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();

        long startTime = System.nanoTime();

        String correlationId =
                request.getHeaders().getFirst(CorrelationIdFilter.CORRELATION_ID);

        log.info(
                "Incoming Request | CorrelationId={} | Method={} | Path={} | ClientIP={}",
                correlationId,
                request.getMethod(),
                request.getURI().getPath(),
                request.getRemoteAddress()
        );

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {

                    long duration =
                            (System.nanoTime() - startTime) / 1_000_000;

                    log.info(
                            "Outgoing Response | CorrelationId={} | Status={} | Duration={} ms",
                            correlationId,
                            exchange.getResponse().getStatusCode(),
                            duration
                    );

                }));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE +1;
    }
}