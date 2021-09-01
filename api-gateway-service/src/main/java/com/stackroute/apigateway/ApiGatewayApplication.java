package com.stackroute.apigateway;

//import com.stackroute.apigateway.filters.ErrorFilter;
//import com.stackroute.apigateway.filters.PostFilter;
//import com.stackroute.apigateway.filters.PreFilter;
//import com.stackroute.apigateway.filters.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


//@EnableZuulProxy
@SpringBootApplication
/**
 *  Add annotation to make this Class as Zuul Proxy
 */
@EnableEurekaClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}

