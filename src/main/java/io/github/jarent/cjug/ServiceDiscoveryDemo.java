package io.github.jarent.cjug;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableDiscoveryClient
@RestController
@EnableAutoConfiguration
public class ServiceDiscoveryDemo {


    @LoadBalanced
    @Bean
    public RestTemplate loadbalancedRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/demo")
    String demo() {
        return this.restTemplate.getForObject("http://restaurant-us-11001/test", String.class);
        //return discoveryClient.getInstances("restaurant_us_11001").toString();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServiceDiscoveryDemo.class, args);
    }

}
