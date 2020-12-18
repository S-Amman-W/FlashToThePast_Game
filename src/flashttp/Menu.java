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



public class Menu extends BasicGameState{
    public static boolean pauser=true;
    public String mouse = "";
    
Image menu;
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.menu=new Image("Title Page.jpg");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        pauser=true;
        
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        
        
        if((posX>815 && posX<935) && (posY>200 && posY<230)){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if((posX>795 && posX<935) && (posY>140 && posY<170)){
            if(Mouse.isButtonDown(0)){
                pauser=false;
                sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
                
            }
        }
        if((posX>850 && posX<935) && (posY>80 && posY<110)){
            if(Mouse.isButtonDown(0)){
                pauser=false;
                sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
                
            }
        }
        
      
        if((posX>815 && posX<935) && (posY>20 && posY<55)){
            if(Mouse.isButtonDown(0)){
                gc.exit();
            }
        }
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        menu.draw(0,0);
        g.drawString(mouse,50,50);
    }

    public int getID() {
        return 0;
    }
    
}
