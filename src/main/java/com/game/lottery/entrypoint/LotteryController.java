package com.game.lottery.entrypoint;

import com.game.lottery.core.lottery.create.CreateLotteryUseCases;
import com.game.lottery.core.lottery.get.GetLotteryUseCases;
import com.game.lottery.core.lottery.update.UpdateLotteryUseCases;
import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class LotteryController {

    private final CreateLotteryUseCases createTicketUseCases;

    private final GetLotteryUseCases getLotteryuseCases;

    private final UpdateLotteryUseCases updateLotteryUseCases;

    public LotteryController(CreateLotteryUseCases createTicketUseCases,
                             GetLotteryUseCases getLotteryuseCases,
                             UpdateLotteryUseCases updateLotteryUseCases) {
        this.createTicketUseCases = createTicketUseCases;
        this.getLotteryuseCases = getLotteryuseCases;
        this.updateLotteryUseCases = updateLotteryUseCases;
    }

    @PostMapping("/ticket")
    public Mono<Ticket> createTicket(@RequestBody List<Line> lines) {
        return createTicketUseCases.createTicket(lines);
    }

    @GetMapping("/ticket")
    public Flux<Ticket> getAllTickets() {
        return getLotteryuseCases.getAllTickets();
    }

    @GetMapping("/ticket/{id}")
    public Mono<Ticket> getTicket(@PathVariable String id) {
        return getLotteryuseCases.getTicketById(id);
    }

    @PutMapping("/ticket/{id}")
    public Mono<Ticket> amendTicket(@PathVariable String id,
                                    @RequestBody List<Line> additionalLines) {
        return updateLotteryUseCases.amendTicket(id, additionalLines);
    }

    @PutMapping("/ticket/status/{id}")
    public Mono<Ticket> checkTicketStatus(@PathVariable String id) {
        return updateLotteryUseCases.checkTicketStatus(id);
    }
}
