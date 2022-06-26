package org.jxch.capital.stock.ds.three;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
public class StockDS3APP {
    public static void main(String[] args) {
        SpringApplication.run(StockDS3APP.class, args);
    }
}
