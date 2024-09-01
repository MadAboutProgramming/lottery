package com.game.lottery.core.lottery.create;

import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Mono;

import java.util.List;

public class CreateLotteryUseCases {

    private final CreateLottery createLottery;

    public CreateLotteryUseCases(CreateLottery createLottery) {
        this.createLottery = createLottery;
    }

    public Mono<Ticket> createTicket(List<Line> lines) {
        return createLottery.createTicket(lines);
    }
}
