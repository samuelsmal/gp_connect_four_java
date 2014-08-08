package org.sam.Random;

import java.util.Random;

/**
 * Created by samuel on 08/08/14.
 */
public enum GPRandom {
    INSTANCE(new Random(System.currentTimeMillis() + 4568321548855l));

    private Random rand;

    private GPRandom(Random rand) {
        this.rand = rand;
    }

    public int nextInt(int n) {
        return rand.nextInt(n);
    }

    public Random getRand() {
        return rand;
    }
}
