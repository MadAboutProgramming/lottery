package com.game.lottery.core.lottery.get;

import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetLottery {

    Flux<Ticket> getAllTickets();

    Mono<Ticket> getTicketById(String id);
}
