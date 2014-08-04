package org.sam.tree;

import org.sam.game.Game;

/**
 * Terminal Node
 * Created by samuel on 27/07/14.
 */
public class EnemyStoneAtNode extends ComputationalNode {
    public EnemyStoneAtNode() {
    }

    public EnemyStoneAtNode(ConstantNode x, ConstantNode y) {
        super(x, y);
    }

    public String toString() {
        return "enemyColour == game.getColourOfStone(\n" +
                "                (int)x.evaluate(playerColour, enemyColour, game),\n" +
                "                (int)y.evaluate(playerColour, enemyColour, game)) ? 1 : 0";
    }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game) {
        return enemyColour == game.getColourOfStone(
                (int)x.evaluate(playerColour, enemyColour, game),
                (int)y.evaluate(playerColour, enemyColour, game)) ? 1 : 0;
    }

    @Override
    public INode getDeepCopy() {
        return new EnemyStoneAtNode((ConstantNode) x.getDeepCopy(),(ConstantNode) y.getDeepCopy());
    }
}
