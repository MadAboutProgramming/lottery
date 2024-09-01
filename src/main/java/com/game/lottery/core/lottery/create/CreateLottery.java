package com.game.lottery.core.lottery.create;

import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CreateLottery {

    Mono<Ticket> createTicket(List<Line> lines);
}
