package flashttp;
        
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
     
public class FlashTTP extends StateBasedGame { 

    public FlashTTP(String name) {
        super(name);
    }

    
    public static void main(String[] args) throws SlickException{
         AppGameContainer app = new AppGameContainer(new FlashTTP("Zoom: A Flash to the Past"));
         app.setDisplayMode(960, 720, false);
         app.setAlwaysRender(true);
         app.setTargetFrameRate(400);
         app.start();
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
       this.addState(new Menu());
       this.addState(new Transition());
       this.addState(new Instructions());
       this.addState(new Credit());
       this.addState(new newstageOne());
       this.addState(new newstageOneFinal());
       this.addState(new Map());
       this.addState(new newstageTwo());
       this.addState(new newstageTwoFinal());
       this.addState(new Pause());
       this.addState(new winorlose());
       
    }
    
}
