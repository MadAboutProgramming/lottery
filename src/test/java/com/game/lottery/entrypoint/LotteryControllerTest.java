package com.game.lottery.entrypoint;

import com.game.lottery.core.model.Line;
import com.game.lottery.core.model.Ticket;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LotteryControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void createTicket() {
        Line line1 = new Line();
        line1.setNumbers(new int[]{0,1,1});
        Line line2 = new Line();
        line2.setNumbers(new int[]{0,2,2});
        List<Line> request = new ArrayList<>();
        request.add(line1);
        request.add(line2);

        Ticket ticket = new Ticket("1", request);

        webTestClient.post()
                .uri("/v1/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(ticket);
    }

    @Order(2)
    @Test
    void getAllTickets() {
        Line line1 = new Line();
        line1.setNumbers(new int[]{0,1,1});
        Line line2 = new Line();
        line2.setNumbers(new int[]{0,2,2});

        Ticket ticket = new Ticket("1", List.of(
                line1, line2));

        webTestClient.get()
                .uri("/v1/ticket")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Ticket.class)
                .hasSize(1)
                .contains(ticket);
    }

    @Order(3)
    @Test
    void getTicket() {
        Line line1 = new Line();
        line1.setNumbers(new int[]{0,1,1});
        Line line2 = new Line();
        line2.setNumbers(new int[]{0,2,2});
        Ticket ticket = new Ticket("1", List.of(
                line1, line2));

        webTestClient.get()
                .uri("/v1/ticket/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(ticket);
    }

    @Order(4)
    @Test
    void amendTicket() {
        Line line1 = new Line();
        line1.setNumbers(new int[]{0,1,1});
        Line line2 = new Line();
        line2.setNumbers(new int[]{0,2,2});
        Line line3 = new Line();
        line3.setNumbers(new int[]{1,1,0});

        List<Line> request = List.of(
                line1,
                line3);
        Ticket amendedTicket = new Ticket("1", List.of(
                line1,
                line2,
                line1,
                line3));

        webTestClient.put()
                .uri("/v1/ticket/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .isEqualTo(amendedTicket);
    }

    @Order(5)
    @Test
    void checkTicketStatus() {
        webTestClient.put()
                .uri("/v1/ticket/status/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Ticket.class)
                .consumeWith(response -> {
                    Assertions.assertTrue(response.getResponseBody()
                            .isChecked());
                });
    }
}