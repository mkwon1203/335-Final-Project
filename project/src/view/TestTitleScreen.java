package view;

import model.*;

import javax.swing.*;

import java.awt.*;
import java.util.Random;

public class TestTitleScreen extends JPanel
{
	public static void main(String[]args)
	{
		Random rand = new Random();
		int str = 10;
		int raw = rand.nextInt((str+5)-(str-5)+1) + str -5;
		System.out.println(raw);

		double percent = 80 / 100.0;
		int actualAttack = (int) (raw * percent);
		System.out.println(actualAttack);
	}
}
