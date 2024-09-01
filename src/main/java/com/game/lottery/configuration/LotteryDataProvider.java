package com.game.lottery.configuration;

import com.game.lottery.dataprovider.lottery.LotteryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LotteryDataProvider {

    @Bean
    public LotteryService createLotteryServiceBean() {
        return new LotteryService();
    }
}
