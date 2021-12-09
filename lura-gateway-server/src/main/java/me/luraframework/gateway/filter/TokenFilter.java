package me.luraframework.gateway.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.luraframework.gateway.config.AuthProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class TokenFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();
        String token = exchange.getRequest().getHeaders().getFirst("Token");
        ServerHttpResponse response = exchange.getResponse();
        if (path.equals(authProperties.getLoginUrl())) {
            return chain.filter(exchange);
        }
        WebClient webClient = WebClient.create();
        return webClient.post()
                        .uri(authProperties.getCheckUrl())
                        .header("Token", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Exception(clientResponse.toString())))
                        .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(Exception::new))
                        .bodyToMono(String.class)
                .doOnNext(log::info)
                        .then(chain.filter(exchange));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
