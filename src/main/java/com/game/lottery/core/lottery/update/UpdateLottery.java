package com.game.lottery.core.lottery.update;

import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UpdateLottery {

    Mono<Ticket> amendTicket(String id, List<Line> additionalLines);

    Mono<Ticket> checkTicketStatus(String id);
}
