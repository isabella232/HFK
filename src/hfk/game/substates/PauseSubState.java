/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hfk.game.substates;

import hfk.game.GameController;
import hfk.game.GameRenderer;
import hfk.game.InputMap;
import hfk.menu.MenuBox;
import hfk.menu.SimpleMenuBox;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author LostMekka
 */
public class PauseSubState extends GameSubState{

	private MenuBox mb = null;
	
	public PauseSubState(InputMap inputMap) {
		super(inputMap);
		inputMap.addKey(Input.KEY_Q, InputMap.A_QUIT);
		inputMap.addKey(Input.KEY_ESCAPE, InputMap.A_RESUMEGAME);
	}

	@Override
	public void initAfterLoading(GameController ctrl, GameContainer gc) {
		mb = new SimpleMenuBox(gc);
	}

	@Override
	public void update(GameController ctrl, GameContainer gc, StateBasedGame sbg, int time) throws SlickException {
		if(getInputMap().isKeyPressed(InputMap.A_QUIT)) gc.exit();
		if(getInputMap().isKeyPressed(InputMap.A_RESUMEGAME)){
			GameController.get().setCurrSubState(GameController.get().gameplaySubState);
		}
	}

	@Override
	public void render(GameController ctrl, GameRenderer r, GameContainer gc) throws SlickException {
		mb.render();
		int w = gc.getWidth(), h = gc.getHeight();
		String s = "pause";
		int scale = 4;
		int tw = r.getStringWidth(s);
		int th = r.getStringHeight(s) + 4;
		r.drawStringOnScreen(s, (w-tw*scale)/2, (h-th*scale)/2-th*scale, Color.yellow, scale);
		s = "esc : resume";
		scale = 2;
		tw = r.getStringWidth(s);
		r.drawStringOnScreen(s, (w-tw*scale)/2, (h-th*scale)/2, Color.yellow, scale);
		s = "q : quit";
		tw = r.getStringWidth(s);
		r.drawStringOnScreen(s, (w-tw*scale)/2, (h-th*scale)/2+th*scale, Color.yellow, scale);
	}
	
}