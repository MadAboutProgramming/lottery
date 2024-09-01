package com.game.lottery.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Ticket {
    private final String id;
    private final List<Line> lines;
    @Setter
    private boolean checked;

    public Ticket(String id, List<Line> lines) {
        this.id = id;
        this.lines = lines;
        this.checked = false;
    }

    public void addLines(List<Line> additionalLines) {
        this.lines.addAll(additionalLines);
    }

}