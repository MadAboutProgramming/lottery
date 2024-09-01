package com.game.lottery.core.lottery.update;

import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Mono;

import java.util.List;

public class UpdateLotteryUseCases {

    private final UpdateLottery updateLottery;

    public UpdateLotteryUseCases(UpdateLottery updateLottery) {
        this.updateLottery = updateLottery;
    }

    public Mono<Ticket> amendTicket(String id, List<Line> additionalLines) {
        return updateLottery.amendTicket(id, additionalLines);
    }

    public Mono<Ticket> checkTicketStatus(String id) {
        return updateLottery.checkTicketStatus(id);
    }
}
