package net.zuperz.amber_revival.entity.client;

import java.util.Arrays;
import java.util.Comparator;

public enum RaptorVariant {
    DEFAULT(0),
    ALBINO(1),
    BLUE(2);

    private static final RaptorVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(RaptorVariant::getId)).toArray(RaptorVariant[]::new);
    private final int id;

    RaptorVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static RaptorVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

