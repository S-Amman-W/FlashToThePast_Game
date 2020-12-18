package flashttp;

import org.lwjgl.input.Mouse;

import org.lwjgl.input.Mouse;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class Credit extends BasicGameState{
    
    public String mouse = "";
    
    Image credits;
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.credits=new Image("Credits.jpg");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
              
        
        int posX = Mouse.getX();
        int posY = Mouse.getY();
       
        
        if((posX>665 && posX<940) && (posY>40 && posY<110)){
            if(Mouse.isButtonDown(0)){
                if (Menu.pauser==false) {
                    sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
                }
                if (Pause.pauses==true) {
                     sbg.enterState(9, new FadeOutTransition(), new FadeInTransition());
                }
               
            }
        }
       
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        credits.draw(0,0);
       
    }

    public int getID() {
        return 3;
    }
    
}
