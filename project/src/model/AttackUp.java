package model;

public class AttackUp extends Buff
{
	private static final int ATTACKUP_VALUE = 5;
	public AttackUp()
	{
		// placeholder valuse
		super(1, "Attack Up", "attack up description", null);
	}

	@Override
	public boolean useItem(Character ch)
	{
		// use ch's setAttack method
		return ch.addStrength(ATTACKUP_VALUE);
	}
	
	
}
