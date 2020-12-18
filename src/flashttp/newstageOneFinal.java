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


public class newstageOneFinal extends BasicGameState{
    Image character,shroob,boss, glove, koopa, shadow, sonicring, greenb;
    Image background=null;
    
    double cx=0, cy=320, sx, sy, gx, gy, kx, ky, shx, shy;
    int bx=50, by=10, rx=140, ry=320, gbx, gby, bcount=1, edrop=0, pedrop=0;
    int count=0, gcount=0, kcount=0;
    int mhp=100, bhp=100;
    int tries=0;
    boolean win=true;
    boolean one=false, two=false, three=false;
    boolean direction=false;
    boolean bdefeated=false;
    boolean sdirection=true, kdirection=false, shdirection=true, bdirection=true;
    boolean gappear=false, sappear=false, kappear=false, rappear=true, gbappear=false, shappear=false;
    boolean up=true;
    boolean bappear=false;
    boolean transfer=false;
    int health=Transition.health;
    int points=Transition.health;
   
    /*public newstageOneFinal(int health)
    {
        this.health = health;
    }*/
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
        

        background = new Image("newstageOnefinal.png");
        boss = new Image ("metroid.png");
        shroob = new Image("shroob.png");
        koopa = new Image("koopa.png");
        shadow = new Image("shadow.png");
        glove = new Image("glove.png");
        sonicring = new Image("sonicring.png");
        character = new Image("brawler.png");
        greenb = new Image("greenattack.png");
    }


    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        g.drawImage(background,0,0);
        
        if (bappear==true) {
             g.drawImage(boss,bx,by);
             g.setColor(Color.black);
             g.drawString("Player HP", 50, 50);
             g.drawString("Boss HP", 50, 150);
             g.drawRect(50, 100, 100, 30);
             g.drawRect(50, 200, 100, 30);
             g.setColor(Color.green);
             g.fillRect(50, 100, mhp, 30); 
             g.fillRect(50, 200, bhp, 30);
        }
       
        if (gbappear==true) {
            g.drawImage(greenb, gbx, gby);
        }
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
        if (rappear==true) {
            g.drawImage(sonicring, rx, ry);
        }
       
            if (sdirection==true) {
                if (sappear==true) {
                    g.drawImage(shroob,(float)sx,(float)sy);
                  }
            }
            else if (sdirection==false) {
                if (sappear==true) {
                    shroob.getFlippedCopy(true,false).draw( (float)sx,(float) sy);
                }  
            } 
            if (kdirection==true) {
                if (kappear==true) {
                    g.drawImage(koopa,(float)kx,(float)ky);
                  }
            }
            else if (kdirection==false) {
                if (kappear==true) {
                    koopa.getFlippedCopy(true,false).draw( (float)kx,(float) ky);
                }  
            } 
            if (shdirection==true) {
                if (shappear==true) {
                    g.drawImage(shadow,(float)shx,(float)shy);
                  }
            }
            else if (shdirection==false) {
                if (shappear==true) {
                    shadow.getFlippedCopy(true,false).draw( (float)shx,(float) shy);
                }  
            } 
        
        g.setColor(Color.black);
        g.drawString("Score: "+points, 800, 10);
        g.drawString("Health: "+health, 800, 20);
        
        glove.setRotation(90);
       
    }


    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        if (cx+50>=210 && cx+50<=215) {
            cx=215;
            cy=590;
            rx=215;
            ry=590;
            bappear=true;
            rappear=false;
        }
        //up and down movement
        if (cx>=215 && cx+50<=795) {
            if (input.isKeyDown(Input.KEY_SPACE) && up==true) {
                up=false;
            }
        }
        
        if (up==false && count<120) {
            cy--;
            count++;
        }
        if (count==120 && cy<=590) {
            up=true;
            cy++;
        }
        if (cy==590) {
            count=0;
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
                gy=-1;
            }   
            if ((gy+25>sy && gy<sy+60) && (gx+25>sx && gx<sx+25) && sappear==true) {
                gappear=false;
                sappear=false;
                sx=-1;
                sy=-1;
                gx=-1;
                gy=-1;
                gcount=0;
                points+=10;
            }
            if ((gy+25>ky && gy<ky+60) && (gx+25>kx && gx<kx+25) && kappear==true) {
                gappear=false;
                kappear=false;
                kx=-1;
                ky=-1;
                gx=-1;
                gy=-1;
                gcount=0;
                 points+=10;
            }
            if ((gy+25>shy && gy<shy+60) && (gx+25>shx && gx<shx+25) && shappear==true) {
                gappear=false;
                shappear=false;
                shx=-1;
                shy=-1;
                gx=-1;
                gy=-1;
                gcount=0;
                 points+=10;
            }

        
        //boss movement
        if (bappear==true) {
            if (bx<=215)bdirection=true;
            else if(bx+150>=795)bdirection=false;
            if (bdirection)bx++;
            else if(bdirection==false)bx--;
            if (cx+65>=795) {
                cx--;
            }
            // boss drop enemies
           
            if (bcount%500==0 && edrop!=3) {
                if ((bx>=390 && bx+90<= 490) || (bx>=555 && bx+90<= 650)) {
                    edrop++;
                    if (edrop==1 && sappear==false) {
                        sx=bx+20;
                        sy=by+100;
                        sappear=true;
                    }
                    
                    if (edrop==2 && kappear==false) {
                        kx=bx+20;
                        ky=by+100;
                        kappear=true;
                    }
                    
                    if (edrop==3 && shappear==false) {
                        shx=bx+20;
                        shy=by+100;
                        shappear=true;
                    }
                    bcount++;
                }
            }
            else if(pedrop+1!=edrop && bcount%500!=0){
              if (edrop!=3) {
                 bcount++;
              }  
               
            }
            if ((bcount%500)==1) {
              if (edrop!=3) {
                 pedrop++;
              } 
            }
            
            //transfer
            if (edrop==3) {
                transfer=true;
            }
            //
            if (transfer==true) {
               if (bcount%500==0 && (bx>=390 && bx+90<= 490) || (bx>=555 && bx+90<= 650)) {

                    if (one) {
                        sx=bx+20;
                        sy=by+100;
                        sappear=true;
                        one=false;
                    }
                    else if (two) {
                        kx=bx+20;
                        ky=by+100;
                        kappear=true;
                        two=false;
                    }
                    else if (three) {
                        shx=bx+20;
                        shy=by+100;
                        shappear=true;
                        three=false;
                    }
                    bcount++;
                }


                else if(bcount%500!=0){

                     bcount++;


                } 
            }
            
            if (sappear==true && sy<=594)sy++;
            if (kappear==true && ky<=594)ky++;
            if (shappear==true && shy<=594)shy++;   
            //when minions die
            if (transfer) {
               if (sappear==false && one==false) {
                    one=true;
                }
                if (kappear==false && two==false) {
                    two=true;
                }
                if (shappear==false && three==false) {
                    three=true;
                } 
            }
                
                
            // minion movements
            if (sappear==true && sy==595) {
                if (sdirection==true)sx+=0.3;
                else if(sdirection==false)sx-=0.3;
                if(sx+50>=795)sdirection=false;
                else if(sx<=215)sdirection=true;
            }
            if (kappear==true && ky==595) {
                if (kdirection==true)kx+=0.3;
                else if(kdirection==false)kx-=0.3;
                if(kx+50>=795)kdirection=false;
                else if(kx<=215)kdirection=true;
            }
            if (shappear==true && shy==595) {
                if (shdirection==true)shx+=0.3;
                else if(shdirection==false)shx-=0.3;
                if(shx+50>=795)shdirection=false;
                else if(shx<=215)shdirection=true;
            }
           
            //hit block attacks
            if (cy<=490 && ((cx+50>=325 && cx<= 390) || (cx+50>=490 && cx<= 555) || (cx+50>=650 && cx<= 720))&& gbappear==false) {
                
                gby=383;
                if ((cx+50>=325 && cx<= 390))gbx=330;

                else if ((cx+50>=490 && cx<= 555))gbx=495;
                else{gbx=655;}
                gbappear=true;
            }
            if (gbappear==true) {
                gby-=2;
                if (gbx+75>=bx && gbx<=bx+150 && gby<=by+100) {
                    bhp-=5;
                    
                    gbappear=false;
                    gbx=0;
                    gby=0;
                }
                
                else if(gby<=0){
                    gbappear=false;
                    gbx=0;
                    gby=0;
                }
            }
            
            if (bhp<=0) {
                bdefeated=true;
                bappear=false;
                
            }
        }
        
        
        //shroob or koopa contact
        if (sappear==true) {
            if (((cy+75>sy && cy<sy+60) && (cx+35>sx && cx<sx+35))) {
                if (direction==true && sdirection==false) {
                    cx-=5;
                }
                else if (direction==false && sdirection==true) {
                    cx+=5;
                }
                if (cy+75>sy && cy<sy+60) {
                    sx+=30;
                }
                mhp-=5;
                if (sdirection)sdirection=false;
                else if(sdirection==false)sdirection=true;
            }
        }
        if (kappear==true) {
            if ((cy+75>ky && cy<ky+65) && (cx+35>kx && cx<kx+35)) {
                if (direction==true && kdirection==false) {
                    cx-=5;
                }
                else if (direction==false && kdirection==true) {
                    cx+=5;
                }
                if (cy+75>ky && cy<ky+65) {
                    kx+=30;
                }
                if (kdirection)kdirection=false;
                else if(kdirection==false)kdirection=true;
                mhp-=5;
            }
        }
        if (shappear==true) {
            if (((cy+75>shy && cy<shy+60) && (cx+25>shx && cx<shx+25))) {
                if (direction==true && shdirection==false) {
                    cx-=5;
                }
                else if (direction==false && shdirection==true) {
                    cx+=5;
                }
                if (cy+75>shy && cy<shy+60) {
                    shx+=30;
                }
                mhp-=5;
                if (shdirection)shdirection=false;
                else if(shdirection==false)shdirection=true;
            }
        }
        // you die
        if (mhp<=0) {
            cx=0; cy=320; sx=-1; sy=-1; gx=-1; gy=-1; kx=-1; ky=-1; shx=-1; shy=-1;
            bx=50; by=10; rx=140; ry=320; gbx=-1; gby=-1; bcount=1; edrop=0; pedrop=0;
            count=0; gcount=0; kcount=0;
            mhp=100; bhp=100;
            direction=false;
            bdefeated=false;
            one=false; two=false; three=false;
            sdirection=true; kdirection=false; shdirection=true; bdirection=true;
            gappear=false; sappear=false; kappear=false; rappear=true; gbappear=false; shappear=false;
            up=true;
            bappear=false;
            tries++;
            health--;
            points=tries*(-5);
            Transition.health=health;
            Transition.points+=points;
            if (health==0) {
                win=false;
                sbg.enterState(10, new FadeOutTransition(), new FadeInTransition());
            }
            else{
                sbg.enterState(4, new FadeOutTransition(), new FadeInTransition());
            }
            
        }
        
        //after defeating boss
        
        if (bdefeated==true) {
                
                rx=720;
                ry=590;
                rappear=true;
                sappear=false;sx=-1;sy=-1;
                kappear=false;kx=-1;ky=-1;
                shappear=false;shx=-1;shy=-1;
                if (cx+75>=790) {
                    cx=800;
                    cy=100;
                    rx=800;
                    ry=100;
                    bdefeated=false;
                }
                
        }
        if (cx+75>=790 && bdefeated==false) {
            if (cx+75>=955) {
                    points+=100;
                    Transition.health=health;
                    Transition.points+=points;
                   sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
               }
        }
     
    }
    
    public int getID() {
        return 5;
    }
}
