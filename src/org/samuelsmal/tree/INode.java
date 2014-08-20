package org.samuelsmal.tree;

import org.samuelsmal.game.Game;

import java.util.List;

/**
 * Created by samuel on 27/07/14.
 */
public interface INode {
    //public INode getDeepCopy();
    public long evaluate(char playerColour, char enemyColour, Game game, List<Leaf> children);
    public String print(List<Leaf> children);
    public INode getCopy();
}
