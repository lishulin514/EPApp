package com.bl.ep.config;


import com.bl.ep.domain.User;
import com.bl.ep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口：ServletRequest 或者HttpServletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     * Spring 5.0 重新定义了服务请求和响应接口；
     * 请求接口: ServerRequest
     * 响应接口: ServerResponse
     * 即可以支持servlet规范，也可以支持自定义，比如Netty(Web Server)
     * 以本例
     * 定义GET请求，并且返回所有的用户对象URI:/person/find/all
     * FLUX是0-N个对象集合
     * Mono是0-1个对象集合
     * Reactive 中的Flux 或者 Mono它是y异步处理的（分阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux 或者Mono都是Publisher
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){

        /**
         * Reactive 提高吞吐量
         */
        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    Collection<User> users = userRepository.findAll();
                    Mono<ServerResponse> response = null;
                    Flux<User> userFlux = Flux.fromIterable(users);
                  return  ServerResponse.ok().body(userFlux, User.class);
        });

    }
}
