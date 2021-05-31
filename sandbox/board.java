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
    Random ran;

    board(int width,int height){
        grid = new block[width][height];
        gen2 = new block[width][height];
        air = new block(0,"air", false, Color.BLACK);
        wall = new block(1, "wall", false, Color.GRAY);
        sand = new block(2, "sand", true, Color.YELLOW);
        water = new block(3, "water", true, Color.CYAN);
        wood = new block(4, "wood", true, new Color(122, 87, 60));
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
                        if(grid[i][j+1].id == air.id){//checks down to move
                            gen2[i][j+1]=water;
                            gen2[i][j]=air;
                        }
                        else if(i-1>=0 && aux==1){
                            if(grid[i-1][j+1].id == air.id ){//checks botton left to move
                                gen2[i-1][j+1]=water;
                                gen2[i][j]=air;
                                
                            }else if(gen2[i-1][j].id == air.id && grid[i-1][j].id == air.id ){
                                //checks left to move and that no other water block is not going to move there and gets deleted
                                    gen2[i-1][j]=water;
                                    gen2[i][j]=air;
                                
                            }

                        }
                        else if(i+1<grid.length){
                            if(grid[i+1][j+1].id == air.id ){//checks botton right to move
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
            }
        }

        for (int i=0;i< grid.length;i++){//copies new array
            for(int j=0;j<grid[i].length;j++){
                grid[i][j]=gen2[i][j];
            }
        }
    }
}
