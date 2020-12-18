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



public class Instructions extends BasicGameState{
    
    public String mouse = "No Input";
    
    Image instruct;
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.instruct=new Image("Instructions.jpg");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
              
        
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        System.out.println(Menu.pauser+" "+Pause.pauses);
        
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
        instruct.draw(0,0);
        g.drawString(mouse,50,50);
    }

    public int getID() {
        return 2;
    }
    
}
