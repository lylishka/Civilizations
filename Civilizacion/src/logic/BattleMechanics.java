package logic;

import java.util.ArrayList;

import game.MilitaryUnit;
import game.Variables;

public class BattleMechanics extends Battle implements Variables {
	public BattleMechanics(ArrayList<MilitaryUnit> civilizationArmy, ArrayList<MilitaryUnit> enemyArmy) {
		super(civilizationArmy, enemyArmy);
	}
}
