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


public class newstageTwoFinal extends BasicGameState{
    Image character,boss, meteor1, meteor2, meteor3, meteor4, laser;
    Image background=null;
    boolean win=false;
    Random r = new Random();
    double cx=5, cy=320, m1x=105, m1y=-150, m2x=265, m2y=-150, m3x=425, m3y=-150,m4x=585, m4y=-150, lx, ly;
    int bx=800, by=135;
    int count=0, mcount=0;
    int mhp=100, bhp=100;
    boolean bdefeated=false;
    boolean sdirection=true, kdirection=false, shdirection=true, bdirection=true;
    boolean lappear=false, m1appear=false, m2appear=false, m3appear=false, m4appear=false;
    boolean up=true;
    boolean bappear=true;
    boolean transfer=false;
    int points=Transition.points, tries=0;
    int health=Transition.health;
    
   
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        

        background = new Image("newstageTwofinal.png");
        boss = new Image ("wiggler.png");
        meteor1 = new Image("meteor1.png");
        meteor2 = new Image("meteor2.png");
        meteor3 = new Image("meteor3.png");
        meteor4 = new Image("meteor4.png");
        laser = new Image("laser.png");
        character = new Image("rocket.png");
        
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.drawImage(background,0,0);
        
        g.drawImage(character, (float)cx, (float)cy);
        if (bappear==true) {
             g.drawImage(boss,bx,by);
             g.setColor(Color.black);
             g.drawString("Player HP", 700, 25);
             g.drawString("Boss HP", 850, 25);
             g.drawRect(700, 50, 100, 30);
             g.drawRect(850, 50, 100, 30);
             g.setColor(Color.green);
             g.fillRect(700, 50, mhp, 30); 
             g.fillRect(850, 50, bhp, 30);
        }
        if (m1appear==true) {
            g.drawImage(meteor1, (float)m1x, (float)m1y);
        }
        if (m2appear==true) {
            g.drawImage(meteor2, (float)m2x, (float)m2y);
        }
        if (m3appear==true) {
            g.drawImage(meteor3, (float)m3x, (float)m3y);
        }
        if (m4appear==true) {
            g.drawImage(meteor4, (float)m4x, (float)m4y);
        }
        
        if (lappear==true) {
            g.drawImage(laser, (float)lx, (float)ly);
        }
       
        g.drawString("Score: "+points, 10, 10);
        g.drawString("Health: "+health, 10, 20);
        
        
        
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if (input.isKeyDown(Input.KEY_A) && cx>0) {
            cx--;
        }
        else if (input.isKeyDown(Input.KEY_D) && cx+200<=960) {
            if (bdefeated==false) {
                if (cx+200<800) {
                    cx++;
                }
            }
            if (bdefeated==true) {
                cx++;
            }
        }
        if (input.isKeyDown(Input.KEY_W) && cy>=135) {
            cy--;
        }
        else if (input.isKeyDown(Input.KEY_S) && cy+100<=670) {
            cy++;
        }
        

        //weapon shot
            if (input.isKeyDown(Input.KEY_O) && lappear==false) {
                lx=cx+200;
                ly=cy+50;
                lappear=true;
            }
            if (lappear==true) {
                lx++;
            }
            if (bdefeated==false && lx+50>=800) {
                lappear=false;
                bhp-=2;
                lx=-1;
                ly=-1;
            }
            
        //meteor attack sequence
        System.out.println(m1appear+" "+m2appear+" "+m3appear+"  "+m4appear);
        if (m1appear==false && m2appear==false && m3appear==false && m4appear==false && bdefeated==false && bappear==true) {
            mcount=(int) (Math.random() * 4 + 1);
             if (bhp>80) {
                 
                 
                 if (mcount==1) {
                     m1appear=true;
                 }
                 else if(mcount==2){
                     m2appear=true;
                 }
                 else if(mcount==3){
                     m3appear=true;
                 }
                 else if(mcount==4){
                     m4appear=true;
                 }
            }
            else if (bhp>60 && bhp<=80) {
                
                    
                    if (mcount==1) {
                        m1appear=true;
                    }
                    else if(mcount==2){
                        m2appear=true;
                    }
                    else if(mcount==3){
                        m3appear=true;
                    }
                    else if(mcount==4){
                        m4appear=true;
                    } 
                 
            }
            else if (bhp>40 && bhp<=60) {
                 
                
                    
                    if (mcount==1) {
                        m1appear=true;
                    }
                    else if(mcount==2){
                        m2appear=true;
                    }
                    else if(mcount==3){
                        m3appear=true;
                    }
                    else if(mcount==4){
                        m4appear=true;
                    } 
                 
                
            }
            else if (bhp>20 && bhp<=40) {
               
                    
                    if (mcount==1) {
                        m1appear=true;
                    }
                    else if(mcount==2){
                        m2appear=true;
                    }
                    else if(mcount==3){
                        m3appear=true;
                    }
                    else if(mcount==4){
                        m4appear=true;
                    } 
                 
                 
            }
            else if (bhp<=20) {
                
                    
                    if (mcount==1) {
                        m1appear=true;
                    }
                    else if(mcount==2){
                        m2appear=true;
                    }
                    else if(mcount==3){
                        m3appear=true;
                    }
                    else if(mcount==4){
                        m4appear=true;
                    } 
                 
            }
        }
            
        if (m1appear==true) {
            
           
            if (bhp>80) {
                
                if(m1appear==true){
                    m1y+=0.5;
                    if (m1y+150>=670) {
                        
                        m1appear=false;
                        m1x=105;
                        m1y=-150;
                    }
                   
                }
                
            }
            else if ((bhp>60 && bhp<=80)) {
                
                if(m1appear==true){
                    m1y++;
                    if (m1y+150>=670) {
                        m1appear=false;
                        m1x=105;
                        m1y=-150;
                    }
                }
                
            }
            else if ((bhp>40 && bhp<=60)) {
                
                if(m1appear==true){
                    m1y+=1.5;
                    if (m1y+150>=670) {
                        m1appear=false;
                        m1x=105;
                        m1y=-150;
                    }
                }
                
            }
            else if ((bhp>20 && bhp<=40)) {
                
                if(m1appear==true){
                    m1y+=2;
                    if (m1y+150>=670) {
                        m1appear=false;
                        m1x=105;
                        m1y=-150;
                    }
                }
                
            }
            else if (bhp<20) {
                
                if(m1appear==true){
                    m1y+=2.5;
                    if (m1y+150>=670) {
                        m1appear=false;
                        m1x=105;
                        m1y=-150;
                    }
                }
                
            }
            
        }

        else if (m2appear==true) {
             if (bhp>80) {
                
                if(m2appear==true){
                    m2y+=0.5;
                    if (m2y+150>=670) {
                        
                        m2appear=false;
                        m2x=105;
                        m2y=-150;
                    }
                   
                }
                
            }
            else if ((bhp>60 && bhp<=80)) {
                
                if(m2appear==true){
                    m2y++;
                    if (m2y+150>=670) {
                        m2appear=false;
                        m2x=105;
                        m2y=-150;
                    }
                }
                
            }
            else if ((bhp>40 && bhp<=60)) {
                
                if(m2appear==true){
                    m2y+=1.5;
                    if (m2y+150>=670) {
                        m2appear=false;
                        m2x=105;
                        m2y=-150;
                    }
                }
                
            }
            else if ((bhp>20 && bhp<=40)) {
                
                if(m2appear==true){
                    m2y+=2;
                    if (m2y+150>=670) {
                        m2appear=false;
                        m2x=105;
                        m2y=-150;
                    }
                }
                
            }
            else if (bhp<20) {
                
                if(m2appear==true){
                    m2y+=2.5;
                    if (m2y+150>=670) {
                        m2appear=false;
                        m2x=105;
                        m2y=-150;
                    }
                }
                
            }
            
        }
        else if (m3appear==true) {
             if (bhp>80) {
                
                if(m3appear==true){
                    m3y+=0.5;
                    if (m3y+150>=670) {
                        
                        m3appear=false;
                        m3x=105;
                        m3y=-150;
                    }
                   
                }
                
            }
            else if ((bhp>60 && bhp<=80)) {
                
                if(m3appear==true){
                    m3y++;
                    if (m3y+150>=670) {
                        m3appear=false;
                        m3x=105;
                        m3y=-150;
                    }
                }
                
            }
            else if ((bhp>40 && bhp<=60)) {
                
                if(m3appear==true){
                    m3y+=1.5;
                    if (m3y+150>=670) {
                        m3appear=false;
                        m3x=105;
                        m3y=-150;
                    }
                }
                
            }
            else if ((bhp>20 && bhp<=40)) {
                
                if(m3appear==true){
                    m3y+=2;
                    if (m3y+150>=670) {
                        m3appear=false;
                        m3x=105;
                        m3y=-150;
                    }
                }
                
            }
            else if (bhp<20) {
                
                if(m3appear==true){
                    m3y+=2.5;
                    if (m3y+150>=670) {
                        m3appear=false;
                        m3x=105;
                        m3y=-150;
                    }
                }
                
            }
            
        }
        else if (m4appear==true) {
             if (bhp>80) {
                
                if(m4appear==true){
                    m4y+=0.5;
                    if (m4y+150>=670) {
                        
                        m4appear=false;
                        m4x=105;
                        m4y=-150;
                    }
                   
                }
                
            }
            else if ((bhp>60 && bhp<=80)) {
                
                if(m4appear==true){
                    m4y++;
                    if (m4y+150>=670) {
                        m4appear=false;
                        m4x=105;
                        m4y=-150;
                    }
                }
                
            }
            else if ((bhp>40 && bhp<=60)) {
                
                if(m4appear==true){
                    m4y+=1.5;
                    if (m4y+150>=670) {
                        m4appear=false;
                        m4x=105;
                        m4y=-150;
                    }
                }
                
            }
            else if ((bhp>20 && bhp<=40)) {
                
                if(m4appear==true){
                    m4y+=2;
                    if (m4y+150>=670) {
                        m4appear=false;
                        m4x=105;
                        m4y=-150;
                    }
                }
                
            }
            else if (bhp<20) {
                
                if(m4appear==true){
                    m4y+=2.5;
                    if (m4y+150>=670) {
                        m4appear=false;
                        m4x=105;
                        m4y=-150;
                    }
                }
                
            }
            
        }
        //contact with meteors
        if (((cy+75>m1y && cy<m1y+150) && (cx+30>m1x && cx<m1x+50)) || ((cy+75>m2y && cy<m2y+150) && (cx+30>m2x && cx<m2x+50)) || ((cy+75>m3y && cy<m3y+150) && (cx+30>m3x && cx<m3x+50)) || ((cy+75>m4y && cy<m4y+150) && (cx+30>m4x && cx<m4x+50))) {
            mhp-=5;
            cx=50;
            cy=320;
        }
        //lose
        if(mhp<=0){
            cx=5; cy=320; m1x=105; m1y=-150; m2x=265; m2y=-150; m3x=425; m3y=-150;m4x=585; m4y=-150;lx=-5;ly=-5;
            bx=800; by=135;
            count=0; mcount=0;
            mhp=100; bhp=100;
            bdefeated=false;
            sdirection=true; kdirection=false; shdirection=true; bdirection=true;
            lappear=false; m1appear=false; m2appear=false; m3appear=false; m4appear=false;
            up=true;
            bappear=true;
            transfer=false;
            health--;
            Transition.health=health;
            Transition.points+=points;
            if (health==0) {
                
                sbg.enterState(10, new FadeOutTransition(), new FadeInTransition());
            }
            else{
                sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
            }
            
        }
        //win
        if (bhp<=0) {
            bdefeated=true;
            bappear=false;
        }
        if (cx+200>=960 && bdefeated==true) {
            win=true;
            sbg.enterState(10, new FadeOutTransition(), new FadeInTransition());
        }
      
        
    }
    
    public int getID() {
        return 8;
    }
}
