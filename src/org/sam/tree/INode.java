package org.sam.tree;

import org.sam.game.Game;

/**
 * Created by samuel on 27/07/14.
 */
public interface INode {
    public INode getDeepCopy();
    public long evaluate(char playerColour, char enemyColour, Game game);

    public void accept(INodeVisitor visitor);

    @Override
    public boolean equals(Object o);
}
