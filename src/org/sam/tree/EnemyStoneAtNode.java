package org.sam.tree;

import org.sam.game.Game;

/**
 * Created by samuel on 27/07/14.
 */
public class EnemyStoneAtNode extends ComputationalNode {
    public EnemyStoneAtNode() {
    }

    public EnemyStoneAtNode(ConstantNode x, ConstantNode y) {
        super(x, y);
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return enemyColour == game.getColourOfStone(
                (int)x.evaluate(playerColour, enemyColour, game),
                (int)y.evaluate(playerColour, enemyColour, game)) ? 1 : 0;
    }
}
