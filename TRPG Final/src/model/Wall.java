package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Block
{
	public Wall()
	{
		// placeholder values
		super(true, false, false, "res/sprites/wall.png");
	}

	@Override
	public void draw()
	{
	}

}
