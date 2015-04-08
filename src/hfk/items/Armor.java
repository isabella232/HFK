/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hfk.items;

import hfk.PointF;
import hfk.game.Resources;
import hfk.mobs.Mob;
import org.newdawn.slick.Color;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author LostMekka
 */
public class Armor extends InventoryItem{

	public static enum ArmorType{torso, head, legs, feet, arms, ring }

	public Armor(PointF pos) {
		super(pos);
		SpriteSheet sprites = Resources.getSpriteSheet("armor.png");
		assert(sprites != null);
		image = sprites.getSprite(0, 0);
	}
	
	@Override
	public String getDisplayName() {
		return "Armor";
	}

	@Override
	public long getRarityScore() {
		return 1;
	}

	@Override
	public Color getDisplayColor() {
		return Color.yellow;
	}

	@Override
	public boolean use(Mob m, boolean fromInventory) {
		//m.inventory.equipArmorFromInventory(this);
		return false;
	}
	
	
	
}
