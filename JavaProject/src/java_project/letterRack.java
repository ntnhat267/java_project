package java_project;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class letterRack extends JPanel {
	private final int RACK_COLS;				//so cot
    private final int RACK_ROWS;				//so dong
    private final GridLayout LETTER_RACK_LAYOUT;//
    //private final int CAPACITY;					//so dong * sp cot = dien tich
    private final String IMAGE_DIRECTORY;		//thu muc cua anh
    private final String IMAGE_TYPE;			//file duoi anh
    private final String password;				//tu khoa
    private final ArrayList<lettterTile> rack;	//
    
    public letterRack()
    {
    	this("password" , " images/" , ".png");
    }
    
    public letterRack(String inPassword, String imageDirectory, String imageType)
    {
    	 RACK_COLS = 13;
         RACK_ROWS = 2;
         LETTER_RACK_LAYOUT = new GridLayout(RACK_ROWS, RACK_COLS); 	//tao 8 cot 2 hang
         LETTER_RACK_LAYOUT.setVgap(10);								//khoang cach giua 2 hang
        // CAPACITY = RACK_ROWS * RACK_COLS;
         
         IMAGE_DIRECTORY = imageDirectory;
         IMAGE_TYPE = imageType;
         
         rack = new ArrayList<>();										//*
         password = inPassword;
         
         
         setBorder(BorderFactory.createEmptyBorder(10, 17, 10, 10));	//*
         setLayout(LETTER_RACK_LAYOUT);									//*
         loadRack();													//tai tung anh len
    }
    
    private void loadRack()
    {
        buildRack();
        for (lettterTile tile : rack)
            add(tile);
    }
    
    private void buildRack()
    {
    	StringBuilder passwordBuilder =  new StringBuilder(password.toLowerCase());  
    	ArrayList<Character> tiles = new ArrayList<>(); 
        Random rand = new Random(); 
        int i = 0, j = 0;
        
        while (passwordBuilder.length() > 0)
        {
            if (!tiles.contains(passwordBuilder.charAt(0)))
            {
                tiles.add(passwordBuilder.charAt(0));
                i++;
            }
           
            passwordBuilder.deleteCharAt(0);
        }
      
        
        
        //i =4 
        // chọn 16-4=12 số ngẫu nhiên bất kì khác nhau
        for (; i <26; i++)
        {
            Character c = 'a'; // 'a' is just a default value
            do
            {
                c = (char) (rand.nextInt(26) + 'a');
            } while (tiles.contains(c));
            tiles.add(c);
            
        }
        // chọn ngẫu nhiên chữ cái vad chỗ bất kì
        
        for (i = 0; i <26; i++)
        {
            j = rand.nextInt(tiles.size());
            rack.add(new lettterTile(tiles.get(j), 
                    IMAGE_DIRECTORY, 
                    IMAGE_TYPE));
            tiles.remove(j);
        }
    }
    
    //*
    
    public void attachListeners(MouseListener l)
    {
        for (lettterTile tile : rack)
            tile.addTileListener(l);
    }
    
    public void removeListeners()
    {
        for (lettterTile tile : rack)
            tile.removeTileListener();
    }
    
}
