package flashttp;

import static jdk.nashorn.tools.ShellFunctions.input;
import org.lwjgl.input.Mouse;

import org.lwjgl.input.Mouse;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Map extends BasicGameState{
    
    public String mouse = "";
    Image character;
    Image menu, clouds;
    int cx=80, cy=520;
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.menu=new Image("Map.png");
        character = new Image("brawler.png");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        mouse = "Mouse position X: "+posX+"y:"+posY;
        
        
        if (input.isKeyDown(Input.KEY_A) && cx>=0)cx--;
        else if (input.isKeyDown(Input.KEY_D) && cx+75<=960)cx++;
        if (input.isKeyDown(Input.KEY_W) && cy>=0) cy--;
        else if (input.isKeyDown(Input.KEY_S) && cy+75<=600)cy++;
        
        if ((cx<560 && cx+50>495) && (cy<=90)) {
            cy+=20;
            sbg.enterState(9, new FadeOutTransition(), new FadeInTransition());
        }
        if((cx>700 && cx+50<800) && (cy==250)){
            
                sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
           
        }
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        menu.draw(0,0);
        character.draw(cx,cy);
        g.drawString(mouse,50,50);
    }

    public int getID() {
        return 6;
    }
    
}
