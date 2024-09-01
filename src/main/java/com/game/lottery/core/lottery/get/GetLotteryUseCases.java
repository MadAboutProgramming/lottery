package com.game.lottery.core.lottery.get;

import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class GetLotteryUseCases {

    private final GetLottery getLottery;

    public GetLotteryUseCases(GetLottery getLottery) {
        this.getLottery = getLottery;
    }

    public Flux<Ticket> getAllTickets() {
        return getLottery.getAllTickets();
    }

    public Mono<Ticket> getTicketById(String id) {
        return getLottery.getTicketById(id);
    }
}
