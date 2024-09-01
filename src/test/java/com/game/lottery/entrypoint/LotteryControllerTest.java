package com.game.lottery.entrypoint;

import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import com.game.lottery.dataprovider.lottery.LotteryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest(LotteryController.class)
@Import(LotteryService.class)
class LotteryControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LotteryService lotteryService;

    @Test
    void createTicket() {
        List<Line> request = List.of(
                new Line(new int[]{0, 1, 1}),
                new Line(new int[]{0, 2, 2})
        );

        Ticket ticket = new Ticket("1", request);

        when(lotteryService.createTicket(request)).thenReturn(Mono.just(ticket));

        webTestClient.post()
                .uri("/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(ticket);
    }

    @Test
    void getAllTickets() {
        Ticket ticket1 = new Ticket("1", List.of(
                new Line(new int[]{0, 1, 1})));
        Ticket ticket2 = new Ticket("2", List.of(
                new Line(new int[]{2, 2, 2})));

        when(lotteryService.getAllTickets()).thenReturn(Flux.just(ticket1, ticket2));

        webTestClient.get()
                .uri("/ticket")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Ticket.class)
                .hasSize(2)
                .contains(ticket1, ticket2);
    }

    @Test
    void getTicket() {
        Ticket ticket = new Ticket("1", List.of(
                new Line(new int[]{0, 1, 1})));

        when(lotteryService.getTicketById("1")).thenReturn(Mono.just(ticket));

        webTestClient.get()
                .uri("/ticket/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(ticket);
    }

    @Test
    void amendTicket() {
        List<Line> request = List.of(
                new Line(new int[]{0, 1, 1}),
                new Line(new int[]{0, 2, 2})
        );
        Ticket amendedTicket = new Ticket("1", List.of(
                new Line(new int[]{0, 1, 1}),
                new Line(new int[]{1, 1, 0})));

        when(lotteryService.amendTicket("1", request)).thenReturn(Mono.just(amendedTicket));

        webTestClient.put()
                .uri("/ticket/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(amendedTicket);
    }

    @Test
    void checkTicketStatus() {
        Ticket status = new Ticket("1", List.of(
                new Line(new int[]{0, 1, 1})));

        when(lotteryService.checkTicketStatus("1")).thenReturn(Mono.just(status));

        webTestClient.put()
                .uri("/status/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(status);
    }
}