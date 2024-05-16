import java.awt.*;

public class Mob extends Rectangle{
	
	  public int xC, yC; //  x,y kordinatları for mob
	  public int health ;
	  public int healthSpace = 3, healthHeight = 6;
	  public int mobSize = 52;
	  public int mobWalk = 0;
	  public int upward = 0, downward = 1, right = 2, left = 3;
	  public int direction = right;
	  public int mobID =Value.mobAir;
	  public boolean inGame = false;
	  public boolean hasUpward = false;
	  public boolean hasDownward =  false;
	  public boolean hasLeft =  false;
	  public boolean hasRight =  false;
	  
	  public Mob() {
		  
			
		  
	  	}
	
    
	   public void spawnMob(int mobID) { // 0,0 da başlıyacağını belirliyor
		   //determines that the mob will start at coordinate 0 a 0
		   for(int y= 0; y<Screen.room.block.length;y++ ) {
			    if(Screen.room.block[y][0].groundID == Value.groundRoad) {
			    	 setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
			    	 xC = 0;
			    	 yC = y;
			    	 
			    }
		  }
	  
		   
		   
		   
		   this.mobID = mobID;
		   this.health = mobSize;
		   
		   inGame= true;
		   
		 
	   }
	   
	   public void deleteMob() {
		   inGame = false;
		   direction = right ;
		   mobWalk = 0;
		   
		   
		   Screen.room.block[0][0].getMoney(mobID); // mobun öldükten sonra para vermsini sağlıyor
		   
	   }
	   
	   public void looseHealth() {
		   Screen.health-=1;   
	   }
	   // mob geçince kaç canın gider
	   
	   
	   public int walkFrame = 0, walkSpeed = 20;  // mobun hızı
	   public void physic() {
		   if(walkFrame>=walkSpeed) {
			   if(direction==right) {
			   x+=1;
			   
			   }else if(direction ==upward){
				   y-=1;
			   }
			   else if(direction == downward) {
				   y+=1;
			   }
			   else if(direction == left) {
				   x-=1;
			   }
			   
			   
			  mobWalk +=1;
			  
			  if(mobWalk == Screen.room.blockSize) { // sağ yönüne gitmesini sağlıyor
				  if(direction==right) {
					   xC+=1;
					   hasRight = true;
					   
					   }else if(direction ==upward){
						   yC-=1;
						   hasUpward = true;
					   }
					   else if(direction == downward) {
						   yC+=1;
						   hasDownward = true;
					   }else if(direction ==left) {
						   
						   xC -=1;
						   hasLeft = true;
					   }
				  
				  if(!hasUpward) {  // yolu izlemesini sağlıyor
					  
				
				  try {
				        if(Screen.room.block[yC+1][xC].groundID == Value.groundRoad) {
					      direction = downward;
				          }
				        }catch(Exception e) {}
				  }  
				  
				  
				  if(!hasDownward) {
					  
					  try {
						  if(Screen.room.block[yC-1][xC].groundID == Value.groundRoad) {
							  direction = upward;
						  }
					  }catch(Exception e) {}
				  }
				 
				if(!hasLeft) {
					 try {
	                	   if(Screen.room.block[yC][xC+1].groundID == Value.groundRoad) {
	     					  direction = right;
	                	   }
					  }catch(Exception e) {}
					
				}
				if(!hasRight) {
					 try {
	                	   if(Screen.room.block[yC][xC-1].groundID == Value.groundRoad) {
	     					  direction = left;
	                	   }
					  }catch(Exception e) {}
					
				}
				  
				if(Screen.room.block[yC][xC].airID == Value.airblackHole) {// eğer black hole ile mobun kordinatları aynı ise mobu siler ve canını götürür
					deleteMob();
					looseHealth();
				}
				
				  
				   hasUpward= false;
				   hasDownward = false;
				  hasLeft = false;
				  hasRight = false;
				  mobWalk = 0;
				  
				  
			  }
			  
			  
			   
			   
			   walkFrame=0;
		   }else {
			   walkFrame+=1;
		   }
		   
		   
		   
	   }
	   
	   public void loseHealth(int amo) {
		     health -= amo;
		     
		     checkDeath();
	   }
	   
	   public void checkDeath() {
		   
		       if(health <= 0) {
		    	   deleteMob();
		       }
	   }
	   
	   
	   public boolean isDead() {
		           if(inGame) {
		        	   return false;
		           }else {
		        	   return true;
		           }
	   }
	   
	   
	  public void draw(Graphics g) {
		  
		    	
		    	g.drawImage(Screen.tileset_mob[mobID], x, y, width, height, null); // mobun boyutu
		    	
		    	
		    	// Health bar, yeşil
		    	g.setColor(new Color(80,50,50));
		    	g.fillRect(x,y- (healthSpace + healthHeight),  width, healthHeight);
		    	
		    	
		    	g.setColor(new Color(50,180,50));// health bar , kırmızı
		    	g.fillRect(x,y- (healthSpace + healthHeight),  health, healthHeight);
		    	
		    	
		    	g.setColor(new Color(0,0,0));// health bar // health barın çerçevesi
		    	g.drawRect(x,y- (healthSpace + healthHeight),  health-1, healthHeight-1);
		 
	  }
	  
	  
	  
}









