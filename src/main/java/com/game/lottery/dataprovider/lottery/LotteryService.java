package com.game.lottery.dataprovider.lottery;

import com.game.lottery.core.lottery.LotteryCRUD;
import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class LotteryService implements LotteryCRUD {

    private final List<Ticket> tickets = new ArrayList<>();

    public LotteryService() {
    }

    @Override
    public Mono<Ticket> createTicket(List<Line> lines) {
        Ticket ticket = new Ticket(UUID.randomUUID().toString(), lines);
        tickets.add(ticket);
        return Mono.just(ticket);
    }

    @Override
    public Flux<Ticket> getAllTickets() {
        return Flux.fromIterable(tickets);
    }

    @Override
    public Mono<Ticket> getTicketById(String id) {
        return Mono.just(Objects.requireNonNull(tickets.stream()
                .filter(ticket -> ticket.getId().equals(id))
                .findFirst()
                .orElse(null)));
    }

    @Override
    public Mono<Ticket> amendTicket(String id, List<Line> additionalLines) {
        return getTicketById(id)
                .map(ticket -> {
                    if (ticket != null && !ticket.isChecked()) {
                        ticket.addLines(additionalLines);
                    }
                    return ticket;
                });
    }

    @Override
    public Mono<Ticket> checkTicketStatus(String id) {
        return getTicketById(id)
                .map(ticket -> {
                    if (ticket != null && !ticket.isChecked()) {
                        List<Line> lines = ticket.getLines();
                        lines.sort(Comparator.comparingInt(this::calculateLineResult));
                        ticket.setChecked(true);
                    }
                    return ticket;
                });
    }

    private int calculateLineResult(Line line) {
        int[] numbers = line.getNumbers();
        int sum = numbers[0] + numbers[1] + numbers[2];

        if (sum == 2) {
            return 10;
        } else if (numbers[0] == numbers[1] && numbers[1] == numbers[2]) {
            return 5;
        } else if (numbers[0] != numbers[1] && numbers[0] != numbers[2]) {
            return 1;
        } else {
            return 0;
        }
    }
}
