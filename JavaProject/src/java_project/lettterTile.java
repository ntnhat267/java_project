package java_project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class lettterTile extends JLabel 
{

	private final char IMAGE_LETTER;		//ten cua anh (a||b||..)
	private final String IMAGE_DIRECTORY;	//thu muc cua anh
    private final String IMAGE_TYPE;		//png
    private final int PREFERRED_WIDTH; 		//chieu rong
    private final int PREFERRED_HEIGHT;    	//chieu cao
    private String path;					//duong dan
    private BufferedImage image;			//dung de load anh
    private MouseListener tileListener;		//dung de thay doi trang thai cua chuot
    
    public lettterTile() {
    	this('a', "images/", ".png");
    }
    
    public lettterTile(char imageLetter, String imageDirectory, String imageType)
    {
    	IMAGE_LETTER = imageLetter;
        IMAGE_DIRECTORY = imageDirectory;
        IMAGE_TYPE = imageType;
        
        PREFERRED_WIDTH = PREFERRED_HEIGHT = 50;
        
        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT)); //thiet lap kich thuoc rieng ben trong component
        path = IMAGE_DIRECTORY + IMAGE_LETTER + IMAGE_TYPE; 
        image = loadImage(path);
         
    }
    
    private BufferedImage loadImage(String imagePath){
    	BufferedImage img = null;
    	//tim file anh
        try 
        {
            img = ImageIO.read(new File(imagePath));
        } 

        catch (IOException ex) 
        {
            System.err.println("loadImage(): Error: Image at "
                    + imagePath + " could not be found");
            System.exit(1);
        }
        
        return img;
	
	    }
	    
	    public char guess() 
	    { 
        loadNewImage("guessed");
        removeTileListener();
        return IMAGE_LETTER;
    }
    
    private void loadNewImage(String guessed)
    {
        path = IMAGE_DIRECTORY + IMAGE_LETTER + "_" + guessed + IMAGE_TYPE; 	//images/a_guessed.png
        image = loadImage(path);
        repaint();   															//ve lai anh
    }
    //* dang ky su dung phuong thuc
    public void addTileListener(MouseListener l) 
    { 
        tileListener = l;
        addMouseListener(tileListener);
    }
    //* xoa dang ky su dung phuong thuc (k the nhap chuot)
    public void removeTileListener() 
    { 
    	removeMouseListener(tileListener); 
    }
    
    @Override
  
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.drawImage(image, 
                0, 
                0, 
                PREFERRED_WIDTH, 
                PREFERRED_HEIGHT, 
                null);
    }
    
    
    
    
	
}
