/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hfk;

import hfk.game.GameController;
import org.newdawn.slick.Image;

/**
 *
 * @author LostMekka
 */
public class Particle {
	
	public Image img;
	public PointF pos, vel;
	public float size, rotation;
	public int lifeTime = -1;
	private final float acc = -0.1f;

	public Particle(Image img, int borderX, int borderY, PointF pos, float r) {
		this(img, borderX, borderY, pos, GameController.random.nextFloat() * 2f * (float)Math.PI, r);
	}

	public Particle(Image img, int borderX, int borderY, PointF pos, float dir, float r) {
		setImg(img, borderX, borderY);
		this.pos = pos.clone();
		rotation = GameController.random.nextFloat() * 2f * (float)Math.PI;
		r *= GameController.random.nextFloat();
		pos.x += r * Math.cos(dir);
		pos.y += r * Math.sin(dir);
		vel = new PointF(dir);
		vel.multiply(GameController.random.nextFloat() * 6f + 0.5f);
		size = Math.max(img.getWidth(), img.getHeight()) / GameController.SPRITE_SIZE;
	}

	public Particle(Image img, int borderX, int borderY, PointF pos, PointF vel, float rotation) {
		setImg(img, borderX, borderY);
		this.pos = pos.clone();
		this.vel = vel.clone();
		this.rotation = rotation;
		size = Math.max(img.getWidth(), img.getHeight()) / GameController.SPRITE_SIZE;
	}
	
	private void setImg(Image origImg, int borderX, int borderY){
		int w = origImg.getWidth() - 2*borderX;
		int h = origImg.getHeight() - 2*borderY;
		int iw = Math.min(w, GameController.random.nextInt(6)+2);
		int ih = Math.min(h, GameController.random.nextInt(6)+2);
		int x = GameController.random.nextInt(w-iw+1);
		int y = GameController.random.nextInt(h-ih+1);
		img = origImg.getSubImage(x+borderX, y+borderY, iw, ih);
	}
	
	public void draw(){
		GameController.get().renderer.drawImage(img, pos, 1f, rotation, true);
//		float s = GameController.get().transformTilesToScreen(size);
//		PointF p = GameController.get().transformTilesToScreen(pos);
//		GameController.get().renderer.getGraphics().drawOval(p.x-s/2f, p.y-s/2f, s, s);
	}
	
	public boolean update(int time){
		// move
		GameController.get().moveThing(pos, vel.x, vel.y, size, time, false);
		PointF corr = GameController.get().level.doCollision(pos, size).corr;
		if(!corr.isZero()){
			pos.add(corr);
			vel.bounce(corr, 0.5f);
		}
		// apply acceleration
		PointF a = new PointF(vel.angle());
		a.multiply(acc);
		if(Math.signum(vel.x + a.x) != Math.signum(vel.x) || Math.signum(vel.y + a.y) != Math.signum(vel.y)){
			vel.x = 0;
			vel.y = 0;
		} else {
			vel.add(a);
		}
		// calculate life time
		if(lifeTime >= 0){
			lifeTime -= time;
			return lifeTime <= 0;
		}
		return false;
	}
	
}
