package com.game.lottery.configuration;

import com.game.lottery.core.lottery.create.CreateLottery;
import com.game.lottery.core.lottery.create.CreateLotteryUseCases;
import com.game.lottery.core.lottery.get.GetLottery;
import com.game.lottery.core.lottery.get.GetLotteryUseCases;
import com.game.lottery.core.lottery.update.UpdateLottery;
import com.game.lottery.core.lottery.update.UpdateLotteryUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LotteryUseCasesConfiguration {

    @Bean
    public CreateLotteryUseCases createLotteryUseCases(CreateLottery createLottery) {
        return new CreateLotteryUseCases(createLottery);
    }

    @Bean
    public UpdateLotteryUseCases updateLotteryUseCases(UpdateLottery updateLottery) {
        return new UpdateLotteryUseCases(updateLottery);
    }

    @Bean
    public GetLotteryUseCases getLotteryUseCases(GetLottery getLottery) {
        return new GetLotteryUseCases(getLottery);
    }
}
