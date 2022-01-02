package me.luraframework.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.luraframework.commons.exception.AppException;
import me.luraframework.gateway.config.AuthProperties;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static com.google.common.collect.ImmutableMap.of;
import static me.luraframework.gateway.exception.GatewayErrorCode.INVALID_TOKEN;
import static me.luraframework.gateway.exception.GatewayErrorCode.UNAUTHORIZED;

@Slf4j
@RequiredArgsConstructor
public class TokenFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;
    private final WebClient.Builder builder;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (antPathMatcher.match("/**/login", path) || antPathMatcher.match("/**/register", path)) {
            return chain.filter(exchange);
        }
        if (Strings.isBlank(token)) {
            return Mono.error(new AppException(UNAUTHORIZED, of()));
        }
        return builder.build().post()
                      .uri(authProperties.getCheckUrl())
                      .header("Token", token)
                      .contentType(MediaType.APPLICATION_JSON)
                      .retrieve()
                      .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new AppException(INVALID_TOKEN, of())))
                      .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new AppException(INVALID_TOKEN, of())))
                      .bodyToMono(String.class)
                      .doOnNext(log::info)
                      .doOnNext(userInfo -> exchange.getRequest().mutate().header("userInfo", userInfo))
                      .then(chain.filter(exchange));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
