/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hfk.level;

import hfk.PointI;
import hfk.game.GameController;
import hfk.game.slickstates.GameplayState;
import hfk.game.Resources;
import hfk.mobs.Player;

/**
 *
 * @author LostMekka
 */
public class Stairs extends UsableLevelItem {

	public Stairs(PointI pos) {
		super(pos);
		img = Resources.getImage("stairs.png");
		hp = -1;
	}

	@Override
	public String getDisplayName() {
		return "Stairs";
	}

	@Override
	public boolean use(Player p) {
		GameController.get().nextLevel();
		return true;
	}
	
}