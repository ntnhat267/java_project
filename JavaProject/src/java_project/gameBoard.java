package java_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class gameBoard extends JFrame 
{
    private final int WIDTH;
    private final int HEIGHT;
    private final int MAX_INCORRECT;
    private final int MAX_PASSWORD_LENGTH;
    private final String HANGMAN_IMAGE_DIRECTORY;
    private final String HANGMAN_IMAGE_TYPE;
    private final String HANGMAN_IMAGE_BASE_NAME;
    private final String LETTER_IMAGE_DIRECTORY;
    private final String LETTER_IMAGE_TYPE;
    private letterRack gameRack;
    private loadImageHangman gameHangman;
    private int numIncorrect;
    private JLabel correct;
    private JLabel incorrect;
    private JLabel topic;
    private String password;
    private StringBuilder passwordHidden;
    public gameBoard()
    {
        WIDTH = 900;
        HEIGHT = 450;
        MAX_INCORRECT = 6;
        MAX_PASSWORD_LENGTH = 10;
        
        HANGMAN_IMAGE_DIRECTORY = LETTER_IMAGE_DIRECTORY = "images/";
        HANGMAN_IMAGE_TYPE = LETTER_IMAGE_TYPE = ".png";
        HANGMAN_IMAGE_BASE_NAME = "hangman";
        
        setTitle("Hangman");
        //setIconImage
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        addCloseWindowListener();
        
        initialize();
    }
   
    private void initialize()
    {        
        numIncorrect = 0;
        
        correct = new JLabel("Word: ");
        topic = new JLabel("Topic: ");
        incorrect = new JLabel("Incorrect: " + numIncorrect);
        password = new String();
        passwordHidden = new StringBuilder();
        
        getPassword();
        addTextPanel();
        addLetterRack();
        addButton();
        addHangman();
        
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(0 +50,
                0+40 );
        setVisible(true);
    }
    
    private void addCloseWindowListener()
    {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
            	
            	
                int prompt = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to quit?",
                        "Quit?", 
                        JOptionPane.YES_NO_OPTION);
                
                if (prompt == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }
    
    private void addTextPanel()
    {
    	Font font = new Font("Verdana", Font.BOLD, 30);
    	Font font1 = new Font("Verdana", Font.BOLD, 15);
    	
        JPanel textPanel = new JPanel();
        
        
        
        textPanel.setBorder(BorderFactory.createLineBorder(Color.black,4));
        textPanel.setLayout(new GridLayout(1,3));
        //textPanel.setPreferredSize(new Dimension(60,60) );
        textPanel.add(correct).setFont(font);
        Component textInc = textPanel.add(incorrect);
      textInc.setFont(font);
      textInc.setForeground(Color.RED);
      //AbstractBorder.getInteriorRectangle(textInc, null,5, 5,5, 5);
      
    Component texttopic = textPanel.add(topic);
    texttopic.setForeground(Color.gray);
    texttopic.setFont(font1);
    texttopic.setLocation(20, 20);
        
//        
//        JPanel textPanel1 = new JPanel();
//        textPanel1.setForeground(Color.red);
//        textPanel1.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(5, 5, 5, 5)));
//        //textPanel1.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
//        textPanel1.add(incorrect).setFont(font);
        //textPanel.setBackground(Color.gray);
       // textPanel.setFont(font);
        //Word
//        textPanel.add(correct).setFont(font);
        
        //incorrect
//        Component textInc = textPanel.add(incorrect);
//        textInc.setFont(font);
//        textInc.setForeground(Color.RED);
        //hint
//        Component texttopic = textPanel.add(topic);
//        texttopic.setForeground(Color.gray);
//        texttopic.setFont(font);
//        texttopic.setLocation(20, 20);
        
        add(textPanel, BorderLayout.NORTH);
       // add();
//        add(textPanel1, BorderLayout.NORTH);
    }
    
    private void addLetterRack()
    {
        gameRack = new letterRack(password, 
                LETTER_IMAGE_DIRECTORY, 
                LETTER_IMAGE_TYPE);
        
        gameRack.attachListeners(new TileListener());
        //gameRack.setBorder((Border) Color.black);
        add(gameRack, BorderLayout.SOUTH);
    }
    
    private void addButton()
    {
    	JPanel buttonPanel  = new JPanel(); 
    	buttonPanel.setLayout(new GridLayout(5,1));
    	buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));
    	JButton buttonNG = new JButton("New game || Quit");
    	JButton buttonQ = new JButton("Quit");
    	buttonPanel.add(buttonNG);
    	
    	//buttonPanel.add(buttonQ);
    	add(buttonPanel,BorderLayout.EAST);
    	
    	
    	buttonNG.addActionListener(new ActionListener(){
    		@Override
            public void actionPerformed(ActionEvent arg0) {
    			newGameDialog();
                
    	}
    	});
    	
    	
    	
    }
    
    private void addHangman()
    {
        JPanel hangmanPanel = new JPanel();
        gameHangman = new loadImageHangman(HANGMAN_IMAGE_BASE_NAME,
                HANGMAN_IMAGE_DIRECTORY,
                HANGMAN_IMAGE_TYPE);
        hangmanPanel.add(gameHangman);
        add(hangmanPanel, BorderLayout.CENTER);
        
    }
    
    private void getPassword()
    {
        String[] options = {"Let's Play", "Quit","Help","Languages"};
        String[] language = {"Back","Submit"};
        String[] languagess = {"English","Tiếng Việt"};
        JPanel passwordPanel = new JPanel();
        JLabel topicLabel =  new JLabel("Hint");
        JTextField topicText =  new JTextField(10);
        JLabel passwordLabel = new JLabel("Keyword: ");
        JTextField passwordText = new JTextField(MAX_PASSWORD_LENGTH);
        passwordPanel.setLayout(new GridLayout(2,1));
        passwordPanel.add(topicLabel);
        passwordPanel.add(topicText);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordText);
        
        JPanel languagesPanel = new JPanel();
        JLabel languagesLabel =  new JLabel("Language : ");
        JComboBox languagesText =  new JComboBox(languagess);
        
//        JButton languagesSubmit=  new JButton("Submit");
        languagesPanel.add(languagesLabel);
        languagesPanel.add(languagesText);
//        languagesPanel.add(languagesSubmit);
        int confirm = -1;
        
        while (password.isEmpty())
        {
            confirm = JOptionPane.showOptionDialog(null, 
                    passwordPanel, 
                    "Enter Keyword", 
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);

            if (confirm == 0)
            {
                password = passwordText.getText();
                
                if (!password.matches("[a-zA-Z]+") || 
                    password.length() > MAX_PASSWORD_LENGTH)
                {
                    JOptionPane.showMessageDialog(null, 
                            "Keyword must be less than 10 characters and " +
                            "only contain letters A-Z.", 
                            "Invalid Keyword", 
                            JOptionPane.ERROR_MESSAGE);
                    password = ""; 
                }
            }
            else if(confirm == 2)
            {
            	JOptionPane.showMessageDialog(null, 
                        "The first player give the keyword and the others guess the password", 
                        "Help", 
                        JOptionPane.INFORMATION_MESSAGE);
            }
            else if(confirm == 3) {
            	int languages = -1;
            	
            	languages = JOptionPane.showOptionDialog(null, 
                         languagesPanel,
                        "Languages", 
                        JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, 
                        null, 
                        language, 
                        language[0]);
            	System.out.println(languages);
            			
            }
                    
            else if (confirm == 1)
                System.exit(0);
        }
        
        passwordHidden.append(password.replaceAll(".", "*"));
        correct.setText(correct.getText() + passwordHidden.toString());
        if( topicText.getText().matches("") )
        {
        	topic.setText("Hint: no hint");
        }
        else
        topic.setText("Hint: " + topicText.getText() );
    }
    
   private void newGameDialog()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null, 
                "The keyword was: " + password +
                "\nWould You Like to Start a New Game?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION)
            initialize(); 
        else
            System.exit(0);
    }
    
    private class TileListener implements MouseListener 
    {
        @Override
        public void mousePressed(MouseEvent e) 
        {
            Object source = e.getSource();
            if(source instanceof lettterTile)
            {
            	 char c = ' ';
                 int index = 0;
                 boolean updated = false;
                
                 lettterTile tilePressed = (lettterTile) source;
                 c = tilePressed.guess();
                 
                 while ((index = password.toLowerCase().indexOf(c, index)) != -1)
                 {
                     passwordHidden.setCharAt(index, password.charAt(index));
                     index++;
                     updated = true;
                 }
                 
                 if (updated)
                 {
                     correct.setText("Word: " + passwordHidden.toString());
                     
                     if (passwordHidden.toString().equals(password))
                     {
                         gameRack.removeListeners();
                         gameHangman.winImage();
                         newGameDialog();
                     }
                 }
                 
                 else
                 {
                     incorrect.setText("Incorrect: " + ++numIncorrect);
                     
                     if (numIncorrect >= MAX_INCORRECT)
                     {
                         gameHangman.loseImage();
                         gameRack.removeListeners();
                         newGameDialog();
                     }
                     
                     else
                         gameHangman.nextImage(numIncorrect);
                 }
             }
         }
         
         @Override
         public void mouseClicked(MouseEvent e) {}  

         @Override
         public void mouseReleased(MouseEvent e) {}

         @Override
         public void mouseEntered(MouseEvent e) {}
         
         @Override
         public void mouseExited(MouseEvent e) {}
     }
 }