/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hfk.items.weapons;

import hfk.PointF;
import hfk.Shot;
import hfk.game.GameController;
import hfk.game.slickstates.GameplayState;
import hfk.game.Resources;
import hfk.stats.Damage;
import hfk.stats.DamageCard;
import hfk.stats.WeaponStatsCard;

/**
 *
 * @author LostMekka
 */
public class Shotgun extends Weapon {

	public Shotgun(float angle, PointF position) {
		super(angle, position);
		shotSound = Resources.getSound("w_sg_s.wav");
		img = Resources.getImage("shotgun.png");
		flippedImg = Resources.getImage("shotgun.png", true);
	}

	@Override
	public float getScreenRecoilAmount() {
		return 0.5f;
	}

	@Override
	public Shot initShot(Shot s) {
		s.lifetime = GameController.random.nextInt(500) + 400;
		s.friction = 0.9f - 0.8f * GameController.random.nextFloat();
		s.size = 0.09f;
		s.img = Resources.getImage("shot.png");
		s.hit = Resources.getSound("hit1.wav");
		return s;
	}

	@Override
	public WeaponStatsCard getDefaultWeaponStats() {
		WeaponStatsCard s = new WeaponStatsCard();
		int shells = Weapon.AmmoType.shell.ordinal();
		s.clipSize[shells] = 2;
		s.reloadTimes[shells] = 1800;
		s.ammoPerShot[shells] = 1;
		s.shotsPerBurst = 1;
		s.projectilesPerShot = 8;
		s.burstInterval = 500;
		s.minScatter = 35f;
		s.maxScatter = s.minScatter;
		s.shotVel = 8f;
		s.weaponZoom = 0.2f;
		s.isAutomatic = false;
		return s;
	}

	@Override
	public DamageCard getDefaultDamageCard() {
		DamageCard d = new DamageCard(1);
		int physical = Damage.DamageType.physical.ordinal();
		d.setDieCount(physical, 3);
		d.setEyeCount(physical, 4);
		return d;
	}

	@Override
	public String getWeaponName() {
		return "Shotgun";
	}

	@Override
	public long getRarityScore() {
		return 7000;
	}
	
}