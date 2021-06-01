import java.awt.Color;
import java.util.Random;





public class board {
    block[][] grid;
    block[][] gen2;
    block sand; 
    block wall;
    block air;
    block water;
    block wood;
    block generator;
    block fire;
    block smoke;
    Random ran;

    board(int width,int height){
        grid = new block[width][height];
        gen2 = new block[width][height];
        air = new block(0,"air", false, Color.BLACK);
        wall = new block(1, "wall", false, Color.GRAY);
        sand = new block(2, "sand", true, Color.YELLOW);
        water = new block(3, "water", true, Color.CYAN);
        wood = new block(4, "wood", true, new Color(122, 87, 60));
        generator = new block(5,"generator", false, Color.MAGENTA);
        fire = new block(6, "fire", false, new Color(216, 66, 30));
        smoke = new block(7, "smoke", false, new Color(68, 66, 63));

        ran = new Random();

        for (int i=0;i< grid.length;i++){//makes everythig air at the start
            for(int j=0;j<grid[i].length;j++){
                grid[i][j]=air;
            }
        }
        for (int i=0;i< grid.length;i++){//makes everythig air at the start
            for(int j=0;j<grid[i].length;j++){
                grid[0][j]=wall;
                grid[width-1][j]=wall;
                
            }
            grid[i][0]=wall;
            grid[i][height-1]=wall;
        }
        
    }

    public void nextGen(){
 
        for (int i=0;i< grid.length;i++){//copies array before updating
            for(int j=0;j<grid[i].length;j++){
                gen2[i][j]=grid[i][j];
            }
        }

        for (int i=0;i< grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                //sand logic
                if(grid[i][j].id == sand.id){
                    
                    if(j+1<grid.length){//check if there is another array
                        int aux = ran.nextInt(2);
                        if(grid[i][j+1].id == air.id || grid[i][j+1].id == water.id){//checks down to move
                            block auxi = gen2[i][j+1];
                            gen2[i][j+1]=sand;
                            gen2[i][j]=auxi;
                        }
                        else if(i-1>=0 && aux==1){
                            if(grid[i-1][j+1].id == air.id || grid[i-1][j+1].id == air.id){//checks botton left to move
                                block auxi = gen2[i-1][j+1];
                                gen2[i-1][j+1]=sand;
                                gen2[i][j]=auxi;
                                
                            }
                        }
                        else if(i+1<grid.length && aux == 0){//checks botton right to move
                            if(grid[i+1][j+1].id == air.id || grid[i+1][j+1].id == water.id ){
                                block auxi = gen2[i+1][j+1];
                                gen2[i+1][j+1]=sand;
                                gen2[i][j]=auxi;
                            }
                        }
                    }
                }
                //water logic
                else if(grid[i][j].id == water.id){
                    
                    if(j+1<grid.length){//check if there is another array
                        int aux = ran.nextInt(2);
                        if(gen2[i][j+1].id == air.id && grid[i][j+1].id == air.id){//checks down to move
                            gen2[i][j+1]=water;
                            gen2[i][j]=air;
                        }
                        else if(i-1>=0 && aux==1){
                            if(gen2[i-1][j+1].id == air.id && grid[i-1][j+1].id == air.id ){//checks botton left to move
                                gen2[i-1][j+1]=water;
                                gen2[i][j]=air;
                                
                            }else if(gen2[i-1][j].id == air.id && grid[i-1][j].id == air.id ){
                                //checks left to move and that no other water block is not going to move there and gets deleted
                                    gen2[i-1][j]=water;
                                    gen2[i][j]=air;
                                
                            }

                        }
                        else if(i+1<grid.length){
                            if(gen2[i+1][j+1].id == air.id && grid[i+1][j+1].id == air.id){//checks botton right to move
                                gen2[i+1][j+1]=water;
                                gen2[i][j]=air;
                            }else if(gen2[i+1][j].id == air.id && grid[i+1][j].id == air.id){
                                //checks right to move and that no other water block is not going to move there and gets deleted
                                    gen2[i+1][j]=water;
                                    gen2[i][j]=air;
                                
                            }
                        }
                        
                    }
                }
                //generator logic clones top block to the bottom
                else if(grid[i][j].id == generator.id){
                    if(j-1>=0 && j+1<grid.length){//check if there is another array
                        if(grid[i][j-1].id != air.id){
                            gen2[i][j+1]=grid[i][j-1];}
                    }
                }

                //smoke logic
                else if(grid[i][j].id == smoke.id){
                    
                    if(j-1>=0){//check if there is another array up
                        int aux = ran.nextInt(2);
                        if(gen2[i][j-1].id == air.id && grid[i][j-1].id == air.id){//checks up to move
                            gen2[i][j-1]=smoke;
                            gen2[i][j]=air;
                        }
                        else if(i-1>=0 && aux==1){
                            if(gen2[i-1][j-1].id == air.id && grid[i-1][j-1].id == air.id ){//checks up left to move
                                gen2[i-1][j-1]=smoke;
                                gen2[i][j]=air;
                                
                            }else if(gen2[i-1][j].id == air.id && grid[i-1][j].id == air.id ){
                                //checks left to move and that no other smoke block is not going to move there and gets deleted
                                    gen2[i-1][j]=smoke;
                                    gen2[i][j]=air;
                                
                            }

                        }
                        else if(i+1<grid.length){
                            if(gen2[i+1][j-1].id == air.id && grid[i+1][j-1].id == air.id){//checks up right to move
                                gen2[i+1][j-1]=smoke;
                                gen2[i][j]=air;
                            }else if(gen2[i+1][j].id == air.id && grid[i+1][j].id == air.id){
                                //checks right to move and that no other smoke block is not going to move there and gets deleted
                                    gen2[i+1][j]=smoke;
                                    gen2[i][j]=air;
                                
                            }
                        }
                        
                    }
                }

                //fire logic
                else if(grid[i][j].id == fire.id){
                    
                    if(j+1<grid.length){//check if there is another array
                        
                        if(gen2[i][j+1].id == wood.id && grid[i][j+1].id == wood.id){//checks down to move
                            gen2[i][j+1]=fire;
                            
                        }
                        else if(i-1>=0){
                            if(gen2[i-1][j+1].id == wood.id && grid[i-1][j+1].id == wood.id ){//checks botton left to move
                                gen2[i-1][j+1]=fire;
                                
                                
                            }
                            if(j-1>=0){
                                if(gen2[i-1][j-1].id == wood.id && grid[i-1][j-1].id == wood.id ){//checks top left to move
                                    gen2[i-1][j-1]=fire;
                                    
                                }
                                if(gen2[i][j-1].id == wood.id && grid[i][j-1].id == wood.id ){//checks top left to move
                                    gen2[i][j-1]=fire;
                                    
                                }
                            }
                            if(gen2[i-1][j].id == wood.id && grid[i-1][j].id == wood.id ){
                                //checks left to move and that no other fire block is not going to move there and gets deleted
                                    gen2[i-1][j]=fire;
                                    
                                
                            }
                        else if(i+1<grid.length){
                            if(gen2[i+1][j+1].id == wood.id && grid[i+1][j+1].id == wood.id){//checks botton right to move
                                gen2[i+1][j+1]=fire;
                                
                            }
                            if(j-1>=0){
                                if(gen2[i+1][j-1].id == wood.id && grid[i+1][j-1].id == wood.id ){//checks top right to move
                                gen2[i+1][j-1]=fire;
                                
                                }
                            }
                            if(gen2[i+1][j].id == wood.id && grid[i+1][j].id == wood.id){
                                //checks right to move and that no other fire block is not going to move there and gets deleted
                                gen2[i+1][j]=fire;
                                
                                
                            }
                        }
                        
                    }
                }
                gen2[i][j]=smoke;//siempre se vuelve smoke
                
            }
        }
    }
        for (int i=0;i< grid.length;i++){//copies new array
            for(int j=0;j<grid[i].length;j++){
                grid[i][j]=gen2[i][j];
            }
        }
    }
}
