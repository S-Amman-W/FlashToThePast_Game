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


public class newstageTwo extends BasicGameState{
    Image character,gongon,platform, glove, koopa, redg, blueg, button;
    Image background=null;
    
    double cx=0, cy=145, ggx=865, ggy=580, gx, gy, kx=221, ky=290;
    double rgx=650, rgy=305, bgx=885, bgy=305;
    int rgd=1;
    int bgd=3;
    int tries=0;
    int count=0, gcount=0, ggcount=0;
    boolean buttonappear=true;
    boolean direction=true;
    boolean kdirection=true;
    boolean gappear=false, rgappear=true, bgappear=true, kappear=true, ggappear=true;
    boolean up=true;
    int health=Transition.health;
    int points=Transition.points;
    
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        

        background = new Image("newstageTwo.png");
        platform = new Image ("platform.png");
        character = new Image("brawler.png");
        gongon = new Image("gongon.png");
        koopa = new Image("koopa.png");
        glove = new Image("glove.png");
        redg = new Image("redghost.png");
        blueg = new Image("blueghost.png");
        button = new Image("pow button.png");
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.drawImage(background,0,0);
        g.drawString("Score: "+points, 800, 10);
        g.drawString("Health: "+health, 800, 20);
        if (direction==true) {
            g.drawImage(character,(float) cx,(float)cy);
            if (gappear==true) {
                glove.setRotation(90);
                g.drawImage(glove, (float)gx,(float) gy);
            }
    
        }
        else if (direction==false) {
            character.getFlippedCopy(true,false).draw( (float)cx,(float) cy);
            if (gappear==true) {
                glove.setRotation(180);
                glove.getFlippedCopy(true,false).draw((float)gx, (float)gy);
            }
        }
        
        if (buttonappear==true) {
            g.drawImage(button, 260, 525);
        }
            
        if (ggappear==true) {
            gongon.getFlippedCopy(true,false).draw( (float)ggx,(float) ggy);
          }
              
        if (kappear==true) {
            
            if (kdirection==false) {
                g.drawImage(koopa,(float)kx,(float)ky);
              }
            else if (kdirection==true) {
                koopa.getFlippedCopy(true,false).draw( (float)kx,(float) ky);
            }  
        } 
        if (rgappear==true) {
            g.drawImage(redg, (float)rgx,(float) rgy);
        }
        if (bgappear==true) {
            g.drawImage(blueg, (float)bgx,(float) bgy);
        }
       
        
        if (buttonappear==false) {
            g.fillRect(0, 575, 25, 90);
            g.setColor(Color.white);
            g.drawString(" Third hit's a charm!", 265, 525);
        }
        
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        
        if(cx+75>950 && ggappear==false){
                cx=0;cy=145;
                kx=221; ky=290;
                gx=-1; gy=-80;
                rgd=1; bgd=3;
                rgx=650; rgy=305; bgx=885; bgy=305; ggx=865; ggy=580;
                count=0; gcount=0; ggcount=0;
                direction=false; kdirection=true;
                gappear=false; rgappear=true; bgappear=true; kappear=true; ggappear=true; buttonappear=true;
                up=true;
                Transition.health=health;
                Transition.points+=points;
                sbg.enterState(8, new FadeOutTransition(), new FadeInTransition());
            
        }
        //weapon shot
            if (input.isKeyDown(Input.KEY_O) && gcount==0) {
                gx=cx+50;
                gy=cy+25;
                gappear=true;
            }
            if (gappear==true) {
                if (direction==true) {
                    gx++;
                    gcount++;
                }
                else if(direction==false){
                    gx--;
                    gcount++;
                }
            }
            
            if(gcount==150){
                gappear=false;
                gcount=0;
                gx=-1;
                gy=-1;
            }   
            if ((gy+25>rgy && gy<rgy+25) && (gx+25>rgx && gx<rgx+25)) {
                gappear=false;
                rgappear=false;
                gx=-100;
                gy=-100;
                rgx=-200;
                rgy=-200;
                gcount=0;
                points+=15;
            }
            
            if ((gy+25>bgy && gy<bgy+25) && (gx+25>bgx && gx<bgx+25)) {
                gappear=false;
                bgappear=false;
                gx=-100;
                gy=-100;
                bgx=-200;
                bgy=-200;
                gcount=0;
                points+=15;
            }
            if (buttonappear==false) {
                if ((gy+25>ggy && gy<ggy+80) && (gx+25>ggx && gx<ggx+80)) {
                    gappear=false;
                    gx=-100;
                    gy=-100;
                    if (ggcount==3) {
                        ggappear=false;
                         bgx=-200;
                         bgy=-200;
                         points+=30;
                    }
                    ggcount++;
                    gcount=0;
                }
            }
            
        // ghost movement
        
        if (rgd==1)rgy--;
        else if (rgd==2)rgx++;
        else if (rgd==3)rgy++;
        else if (rgd==4)rgx--;
        if (bgd==1)bgy--;
        else if (bgd==2)bgx++;
        else if (bgd==3)bgy++;
        else if(bgd==4)bgx--;
        
        if (rgd==1 && rgy<=165){
            rgy=166; 
            rgd++;
        }
        else if(rgd==2 && rgx>=885){
            rgx=884;
            rgd++;
        }
        else if(rgd==3 && rgy+50>=445){
            rgy=444-50; 
            rgd++;
        }
        else if(rgd==4 && rgx<=650){
            rgx=651;
            rgd=1;
        }
        
        if (bgd==1 && bgy<=165){
            bgy=166;
            bgd++;
        }
        else if(bgd==2 && bgx>=885){
            bgx=884;
            bgd++;
        }
        else if(bgd==3 && bgy+50>=445){
            bgy=444-50;
            bgd++;
        }
        else if(bgd==4 && bgx<=650){
            bgx=651;
            bgd=1;
        }
       
        // gongon movement
        ggx-=2;
        if (ggx<=0) {
            ggx=960;
        }
        
        if (buttonappear==false && ggx<=24) {
            ggx+=2;
        }
        //button pressed
            if (buttonappear) {
            if (cx+50>=295 && cx+50<=300 && (cy+60>525 && cy<575)){
               buttonappear=false;
           }
        }
       
        //koopa movement
        if (kappear==true) {
            if (kx+25>=610)kdirection=false;
            else if(kx<=210)kdirection=true;
            if(kdirection==true)kx+=0.5;
            else if(kdirection==false)kx-=0.5;
            
               
            if ((gy+25>ky && gy<ky+65) && (gx+25>kx && gx<kx+25)) {
                gappear=false;
                gcount=0;
                kappear=false;
                kx=-100;
                ky=-100;
                gx=-200;
                gy=-200;
                points+=10;
            }
            
            
        }
        
        //left and right movement
        
           if(input.isKeyDown(Input.KEY_D)){
                cx++;
                direction=true;
           } 
           if(input.isKeyDown(Input.KEY_A) && cx>-50){
                cx--;
                direction=false;
           }
           
        //flying platform stop
           if (cx+50>=730 && cx+50<=855 && (cy+60>295 && cy<340)){
               if (direction==false) {
                   cx++;
               }
               else{
                   cx--;
               }
           }
           if (cx+50>=0 && cx+50<=265 && (cy+60>525 && cy<575)){
               cx++;
           }
           
        //platform stop
        if (cx+50>=220 && cx+50<=221 && (cy+60>365 && cy<405)){
               if (direction==true) {
                   cx--;
               }
               
        if (cx+50>=609 && cx+50<=610 && (cy+60>365 && cy<405)) {
            if (direction==false) {
               cx++;
           }
        }
                
           }
        if (cx+50>=0 && cx+50<=570 && (cy+60>220 && cy<265)){
               cx++;
           }
        if (cx>=0 && cx+50<=570 && (cy+75>220 && cy+75<222)) {
            cy--;
        }
        
        
        //up and down movement
        
        if (input.isKeyDown(Input.KEY_SPACE) && up==true) {
            up=false;
        }
        if (up==false && count<120) {
            cy--;
            count++;
        }
        if (cx+50>=220 && cx+50<=610 && cy<=405 && cy>=403){
                count=120;
                cy++;
        }
        else if (cx>=0 && cx+50<=570 && cy<=265 && cy>=263){
                count=120;
                cy++;
        }
        else if (cx>=0 && cx+50<=265 && cy<=570 && cy>=568){
                count=120;
                cy++;
        }
        
        else if (!input.isKeyDown(Input.KEY_SPACE)){
            
            if (cx+50>=730 && cx+50<=855 && cy+75==730)cy--;
            
            if (cx+50>=221 && cx+50<=609 && cy+75==365)cy--;
            if (cx+50>=221 && cx+50<=609 && cy+75<365 && count==120){
                if (cx+50>=221 && cx+50<=570 && cy>265) {
                    cy++;
                }
                else if (cx+50>570 && cx+50<=609) {
                    cy++;
                }
            }
             if (cx>=0 && cx<=265 && cy<520 && cy>266) {
                cy++;
            }
             
                if (count==120)cy++;
                else if (cx>855 && up==true)cy++;
                else if (cx>610 && up==true &&cx+50<730  && cy+75<665)cy++;
                else if (cx>730 && up==true &&cx+50<855  && cy>340)cy++;
                
               
                
            
            
        }
       if (cx+50>=220 && cx+50<=610 && (cy+75==365 || cy+75==364)){
           up=true;
           count=0;
       }
       else if ((cx+50>=730 && cx+50<=855) && (cy+75==295 ||  cy+75==294)){
               up=true;
               count=0;
        }
       else if ((cx+50>=640 && cx+50<=710) && (cy+75==470 ||cy+75==469)) {
               up=true;
               count=0;
       }
       else if ((cx>=0 && cx+50<=265) && (cy+75==525 ||cy+75==524)) {
               up=true;
               count=0;
               cy--;
       }
       else if ((cx>=0 && cx+50<=570) && (cy+75==220 ||cy+75==219)) {
               up=true;
               count=0;
       }
       if (cy+75==665) {
            up=true;
            count=0;
            cy--;
        }
       
   //retry action
        if (kappear==true) {
            if (((cy+75>ky && cy<ky+65) && (cx+30>kx && cx<kx+30))) {
                cx=0;cy=145;
                kx=221; ky=290;
                gx=-1; gy=-80;
                rgd=1; bgd=3;
                rgx=650; rgy=305; bgx=885; bgy=305; ggx=865; ggy=580;
                count=0; gcount=0; ggcount=0;
                direction=false; kdirection=true;
                gappear=false; rgappear=true; bgappear=true; kappear=true; ggappear=true; buttonappear=true;
                up=true;
                tries++;
                points=tries*-5;
                sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
            }
        }
        
        if (rgappear==true) {
            if (((cy+75>rgy && cy<rgy+50) && (cx+30>rgx && cx<rgx+50))) {
                cx=0;cy=145;
                kx=221; ky=290;
                gx=-1; gy=-80;
                rgd=1; bgd=3;
                rgx=650; rgy=305; bgx=885; bgy=305; ggx=865; ggy=580;
                count=0; gcount=0; ggcount=0;
                direction=false; kdirection=true;
                gappear=false; rgappear=true; bgappear=true; kappear=true; ggappear=true; buttonappear=true;
                up=true;
                tries++;
                points=tries*(-5);
                sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if (bgappear==true) {
            if (((cy+75>bgy && cy<bgy+50) && (cx+30>bgx && cx<bgx+50))) {
                cx=0;cy=145;
                kx=221; ky=290;
                gx=-1; gy=-80;
                rgd=1; bgd=3;
                rgx=650; rgy=305; bgx=885; bgy=305; ggx=865; ggy=580;
                count=0; gcount=0; ggcount=0;
                direction=false; kdirection=true;
                gappear=false; rgappear=true; bgappear=true; kappear=true; ggappear=true; buttonappear=true;
                up=true;
                tries++;
                points=tries*(-5);
                sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if (ggappear==true) {
            if (((cy+75>ggy && cy<ggy+80) && (cx+30>ggx && cx<ggx+80))) {
                cx=0;cy=145;
                kx=221; ky=290;
                gx=-1; gy=-80;
                rgx=650; rgy=305; bgx=885; bgy=305; ggx=865; ggy=580;
                count=0; gcount=0; ggcount=0;
                direction=false; kdirection=true;
                gappear=false; rgappear=true; bgappear=true; kappear=true; ggappear=true; buttonappear=true;
                up=true;
                tries++;
                points=tries*(-5);
                sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
            }
        }
        
      
    }
    
    public int getID() {
        return 7;
    }
}
