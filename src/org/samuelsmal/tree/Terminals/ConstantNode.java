package org.samuelsmal.tree.Terminals;

import org.samuelsmal.game.Game;
import org.samuelsmal.tree.INode;
import org.samuelsmal.tree.Leaf;

import java.util.List;

/**
 * Terminal Node
 * Created by samuel on 27/07/14.
 */
public class ConstantNode implements INode {
    /*
     * Should be in [0,6].
     */
    private long constant;

    public ConstantNode() {}

    public ConstantNode(long constant) {
        this.constant = constant;
    }

    public long getConstant() {
        return constant;
    }

    public void setConstant(long constant) {
        this.constant = constant;
    }

    @Override
    public INode getCopy() {
        return new ConstantNode(constant);
    }

    @Override
    public String print(List<Leaf> children) { return Long.toString(constant); }

    @Override
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children) {
        return constant;
    }
}
