package model;

public class Air extends Block
{
	/**
	 * Constructor for Air block.
	 * Air block is set as transparent block which is solid, thus
	 * characters will not be able to move onto air block.
	 */
	public Air()
	{
		// TODO: put correct image
		super(true, false, null, "Air");
	}
}
