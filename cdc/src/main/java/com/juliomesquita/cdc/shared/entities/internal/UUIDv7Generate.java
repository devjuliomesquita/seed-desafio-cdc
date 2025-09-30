package com.juliomesquita.cdc.shared.entities.internal;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

public final class UUIDv7Generate {
    private UUIDv7Generate() {
    }

    private static final SecureRandom random = new SecureRandom();

    public static UUID generateV7() {
        long timestamp = Instant.now().toEpochMilli();

        long msb = (timestamp & 0xFFFFFFFFFFFFL) << 16;

        msb |= 0x7000;

        long lsb = random.nextLong();
        lsb &= ~(0xC000000000000000L);
        lsb |= 0x8000000000000000L;

        return new UUID(msb, lsb);
    }
}
