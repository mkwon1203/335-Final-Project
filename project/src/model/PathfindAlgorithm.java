package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PathfindAlgorithm
{
	private Map map;
	private Node[][] g; // graph of Nodes

	public PathfindAlgorithm(Map map)
	{
		this.map = map;
		g = new Node[map.getLevelRow()][map.getLevelCol()];
	}

	public List<Point> findPath(Point start, Point end)
	{
		// TODO: replace with PathNotFoundException ?
		if (!movablePositions(start, 6).contains(end))
			return null;

		List<Point> toReturn = new ArrayList<Point>();

		Point curr = end;
		while (!curr.equals(start))
		{
			toReturn.add(curr);
			curr = g[curr.x][curr.y].p.key;
		}

		Collections.reverse(toReturn);

		return toReturn;
	}

	private void populateGraph(Point p, int moveDistance)
	{
		int currentRow = p.x;
		int leftCol = p.y;
		int rightCol = p.y;

		while (moveDistance >= 0)
		{
			for (int row = currentRow - moveDistance; row <= currentRow
					+ moveDistance; row++)
			{
				if (map.verifyBounds(row, leftCol))
					g[row][leftCol] = new Node(new Point(row, leftCol));
				if (map.verifyBounds(row, rightCol) && leftCol != rightCol)
					g[row][rightCol] = new Node(new Point(row, rightCol));
			}

			leftCol--;
			rightCol++;
			moveDistance--;
		}
	}

	public List<Point> movablePositions(Point start, int moveDistance)
	{
		populateGraph(start, moveDistance);
		
		List<Point> toReturn = new ArrayList<Point>();

		// slightly modified BFS
		Node source = g[start.x][start.y];
		source.color = 1;
		source.d = 0;
		source.p = null;

		Queue<Node> q = new LinkedList<Node>();
		q.add(source);
		while (!q.isEmpty())
		{
			Node u = q.remove();
			if (u.d < moveDistance)
			{
				for (Node v : adj(u))
				{
					if (v.color == 0)
					{
						v.color = 1;
						v.d = u.d + 1;
						v.p = u;
						q.add(v);
						toReturn.add(v.key);
					}
				}
			}
		}

		return toReturn;
	}

	/*
	 * helper method, returns all adjacent nodes to the input node N
	 */
	private List<Node> adj(Node n)
	{
		Point p = n.getKey();
		Point top = new Point(p.x - 1, p.y);
		Point left = new Point(p.x, p.y - 1);
		Point right = new Point(p.x, p.y + 1);
		Point bot = new Point(p.x + 1, p.y);

		List<Node> toReturn = new ArrayList<Node>();

		if (map.canMoveTo(top))
			toReturn.add(g[top.x][top.y]);
		if (map.canMoveTo(left))
			toReturn.add(g[left.x][left.y]);
		if (map.canMoveTo(right))
			toReturn.add(g[right.x][right.y]);
		if (map.canMoveTo(bot))
			toReturn.add(g[bot.x][bot.y]);

		return toReturn;
	}

	private class Node
	{
		private Point key;
		private int d;
		private Node p;
		private int color;
		// white = 0
		// gray = 1

		public Node(Point k)
		{
			key = k;
			d = Integer.MAX_VALUE;
			p = null;
			color = 0;
		}

		public Point getKey()
		{
			return key;
		}
	}
}
