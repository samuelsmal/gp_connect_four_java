package org.samuelsmal.Random;

import java.util.Random;

/**
 * Created by samuel on 08/08/14.
 */
public enum GPRandom {
    // To provide reproducible outcomes.
    INSTANCE(new Random(4568321548855l));

    private Random rand;

    private GPRandom(Random rand) {
        this.rand = rand;
    }

    public synchronized int nextInt(int n) {
        return rand.nextInt(n);
    }

    public Random getRand() {
        return rand;
    }
}
