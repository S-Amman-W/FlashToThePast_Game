package flashttp;

import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
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


public class newstageOne extends BasicGameState {
    Image character,shroob,platform, glove, koopa, kglove;
    Image background=null;
    
    double cx=50, cy=490, sx=335, sy=505, gx, gy, kx=650, ky=400;
    int px=330, py=370;
    double kgx, kgy;
    int count=0, gcount=0, kcount=0;
    
    boolean direction=false;
    boolean sdirection=false, kdirection=true;
    boolean gappear=false, sappear=true, kappear=true, kgappear=false;
    boolean up=true;
    boolean pause=false;
    boolean instruct=false;
    boolean win=false;
    int tries=0;
    Random r= new Random();
    int y=1;
    int x=1;
    int p1=0,p2=0;
    int splash=0;
    int health=Transition.health;
    int points=Transition.points;
    private Rectangle rect=null;
    
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        

        background = new Image("newstageOne.png");
        platform = new Image ("platform.png");
        character = new Image("brawler.png");
        shroob = new Image("shroob.png");
        koopa = new Image("koopa.png");
        glove = new Image("glove.png");
        kglove = new Image("glove.png");
    }

    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
        
        g.drawImage(background,0,0);
        g.drawImage(platform,px,py);
        if (direction==false) {
            g.drawImage(character,(float) cx,(float)cy);
            if (gappear==true) {
                glove.setRotation(90);
                g.drawImage(glove, (float)gx,(float) gy);
            }
        }
        else if (direction==true) {
            character.getFlippedCopy(true,false).draw( (float)cx,(float) cy);
            if (gappear==true) {
                glove.setRotation(90);
                glove.getFlippedCopy(true,false).draw((float)gx, (float)gy);
            }
        }
         
            if (sdirection==false) {
                if (sappear==true) {
                    g.drawImage(shroob,(float)sx,(float)sy);
                  }
            }
            else if (sdirection==true) {
                if (sappear==true) {
                    shroob.getFlippedCopy(true,false).draw( (float)sx,(float) sy);
                }  
            }   
            if (kappear==true) {
                g.drawImage(koopa, (float) kx, (float) ky);
            }
        if (kgappear==true) {
            kglove.setRotation(270);
            kglove.getFlippedCopy(true,false).draw((float)kgx, (float)kgy);
        }
        g.setColor(Color.black);
        g.drawString("Score: "+points, 800, 10);
        g.drawString("Health: "+health, 800, 20);
        glove.setRotation(90);
        
    }

    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException{
        Input input = gc.getInput();
        
        if(cx+75>950){
                cx=50;
                cy=390;
                kx=650;
                ky=400;
                sx=335; 
                sy=505;
                gx=-1;
                gy=-80;
                kgx=-1;
                kgy=-1;
                count=0;
                gcount=0;
                kcount=0;
                sdirection=false; kdirection=true;
                gappear=false; sappear=true; kappear=true; kgappear=false;
                up=true;
                Transition.health=health;
                Transition.points+=points;
                sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
            
        }
        //weapon shot
            if (input.isKeyDown(Input.KEY_O) && gcount==0) {
                gx=cx+50;
                gy=cy+25;
                gappear=true;
            }
            if (gappear==true) {
                if (direction==false) {
                    gx++;
                    gcount++;
                }
                else if(direction==true){
                    gx--;
                    gcount++;
                }
            }
            
            if(gcount==150){
                gappear=false;
                gcount=0;
                gx=-1;
                gy=-80;
            }   
            if ((gy+25>sy && gy<sy+60) && (gx+25>sx && gx<sx+25)) {
                gappear=false;
                sappear=false;
                sx=-1;
                sy=-1;
                gcount=0;
                points+=10;
            }
           
            
        //koopa movement
        if (kappear==true) {
            if (ky>=400)kdirection=true;
            else if(ky<=200)kdirection=false;
            if(kdirection==true)ky--;
            else if(kdirection==false)ky++;
            
            if (ky>=299 && ky<=301 && kcount==0) {
                kgx=kx;
                kgy=ky+20;
                kgappear=true;
            }
            if (kgappear==true) {
                    kgx--;
                    kcount++;
            }
            
            if(kcount>=250){
                kgappear=false;
                kcount=0;
                kgx=-1;
                kgy=-80;
            }   
            if ((gy+25>ky && gy<ky+65) && (gx+25>kx && gx<kx+25)) {
                gappear=false;
                gcount=0;
                kappear=false;
                kgappear=false;
                kx=-1;
                ky=-1;
                kcount=0;
                points+=15;
            }
        }
        
        //left and right movement
        
           if(input.isKeyDown(Input.KEY_D)){
                cx++;
                direction=false;
           } 
           if(input.isKeyDown(Input.KEY_A) && cx>0){
                cx--;
                direction=true;
           }
           
        //pipes stop
           if (cx+50>=640 && cx+50<=710 && cy+75>470){
               if (direction==false) {
                   cx--;
               }
               else{
                   cx++;
               }
           }
           if (cx+50>=250 && cx+50<=320 && cy+75>470){
               if (direction==false) {
                   cx--;
               }
               else{
                   cx++;
               }
           }
        //platform stop
        if (cx+50>=px && cx+50<=px+1 && (cy+75>py && cy<py+50)){
               if (direction==false) {
                   cx--;
               }
               
           }
        if (cx+50>=px+249 && cx+50<=px+250 && (cy+75>py && cy<py+50)){
               if (direction==true) {
                   cx++;
               }
           }
           
        //pipes stop shroob
        if (sappear==true) {
            if (sdirection==false)sx++;
            else if(sdirection==true)sx--;
            if(sx+50>=640)sdirection=true;
            else if(sx+30<=320)sdirection=false;
        }
         
        
        
        //up and down movement
        
        if (input.isKeyDown(Input.KEY_SPACE) && up==true) {
            up=false;
        }
        if (up==false && count<120) {
            cy--;
            count++;
        }
        if (cx+50>=px && cx+50<=px+250 && cy<=py+50 && cy>=py+49){
                count=120;
                cy++;
        }
        else if (!input.isKeyDown(Input.KEY_SPACE)){
            
            if (cx+50>=250 && cx+50<=320 && cy+75==470)cy--;
            
            if (cx+50>=640 && cx+50<=710 && cy+75==470)cy--;
            if (cx+50>=px+1 && cx+50<=px+249 && cy+74==py)cy-=2;
            if (cx+50>=px+1 && cx+50<=px+249 && cy+75<py && count==120)cy++;
            
                if (count==120)cy++;
                else if (cx+50>710 && up==true && cy+75<565)cy++;
                else if (cx+50>320 && up==true &&cx+50<640  && cy+75<565)cy++;
                else if (cx+50<250  && up==true && cy+75<565)cy++;
            
            
        }
       if (cx+50>=px && cx+50<=px+250 && (cy+75==py || cy+75==py-1)){
           up=true;
           count=0;
       }
       if ((cx+50>=250 && cx+50<=320) && (cy+75==470 ||  cy+75==469)){
               up=true;
               count=0;
        }
        else if ((cx+50>=640 && cx+50<=710) && (cy+75==470 ||cy+75==469)) {
               up=true;
               count=0;
        }
       if (cy+75==565) {
            up=true;
            count=0;
        }
        //shroob or koopa contact
        if (sappear==true) {
            if (((cy+75>sy && cy<sy+60) && (cx+30>sx && cx<sx+30))) {
            cx=50;
            cy=490;
                if (kappear==false) {
                    kx=650;
                    ky=400;
                }
            sx=335; 
            sy=505;
            gx=-1;
            gy=-80;
            kgx=-1;
            kgy=-80;
            kappear=true;
            gcount=0;
            kcount=0;
            kgappear=false;
            up=true;count=0;
            tries++;
            points=tries*(-5);
            sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
           
            }
        }
        if (kappear==true) {
            if (((cy+75>ky && cy<ky+65) && (cx+30>kx && cx<kx+30)) || ((cy+75>kgy && cy<kgy+25) && (cx+30>kgx && cx<kgx+30))) {
                cx=50;
                cy=390;
                kx=650;
                ky=400;
                if (sappear==false) {
                    sx=335; 
                    sy=505;
                }
                gx=-1;
                gy=-80;
                kgx=-1;
                kgy=-80;
                gcount=0;
                kcount=0;
                sappear=true;
                kgappear=false;
                up=true;count=0;
                tries++;
                points=tries*(-5);
                sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
            }
        }
        
      
    }
    
    public int getID() {
        return 4;
    }
}
