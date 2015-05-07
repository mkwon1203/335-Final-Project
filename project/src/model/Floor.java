package model;

public class Floor extends Block
{
	/**
	 * Constructor for the floor block
	 * Floor blocks are nonsolid blocks that units can
	 * walk on and walk through
	 */
	public Floor()
	{
		super(false, false, "res/sprites/brickFloor.png", "Floor");
	}
}
