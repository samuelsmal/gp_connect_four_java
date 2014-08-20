package org.samuelsmal.tree.Terminals;

import org.samuelsmal.tree.INode;

/**
 * Created by samuel on 27/07/14.
 */
abstract class ComputationalNode implements INode {
    protected int x; // 0 <= x < 7
    protected int y; // 0 <= y < 7

    public ComputationalNode() {}

    public ComputationalNode(int x, int y) { this.x = x; this.y = y; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }
}
