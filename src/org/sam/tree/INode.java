package org.sam.tree;

import org.sam.game.Game;

import java.util.List;

/**
 * Created by samuel on 27/07/14.
 */
public interface INode {
    //public INode getDeepCopy();
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children);
    public String print(List<Leaf> children);
}
