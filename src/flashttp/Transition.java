package flashttp;

import java.awt.Rectangle;
import org.lwjgl.input.Mouse;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class Transition extends BasicGameState{
    private Rectangle rect = null;
    public String mouse = "";
    boolean characterSelect=false;
    Image trans, brawler, whitemage, megaman;
    public static int health=5;
    public static int points=0;
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        rect = new Rectangle(960,720,0,0);
        trans=new Image("Transition.jpg");
        
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
              
        
        int posX = Mouse.getX();
        int posY = Mouse.getY();
       
        
        if((posX>185 && posX<780) && (posY>405 && posY<615)){
            
            if(Mouse.isButtonDown(0)){
                sbg.enterState(4, new FadeOutTransition(), new FadeInTransition()); 
            }
        }
        if((posX>185 && posX<780) && (posY>150 && posY<360) && characterSelect==false){
            if(Mouse.isButtonDown(0)){
                sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
            }
        }
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        trans.draw(0,0);
        
        
    }

    public int getID() {
        return 1;
    }
    
}
