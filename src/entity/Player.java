package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            System.out.println("Before image lookup");
            up0 = ImageIO.read(getClass().getResourceAsStream("/player/up0.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            down0 = ImageIO.read(getClass().getResourceAsStream("/player/down0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            left0 = ImageIO.read(getClass().getResourceAsStream("/player/left0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            right0 = ImageIO.read(getClass().getResourceAsStream("/player/right0.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
        }
        catch (IOException e){
            System.out.println("Image not found");
        }
      }

    public void update(){
        // Player sprite won't change unless key is pressed.
        if(keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed){
            if(keyHandler.upPressed){
                direction = "up";
                y -= speed;
            }
            else if(keyHandler.downPressed){
                direction = "down";
                y += speed;
            }
            else if(keyHandler.leftPressed){
                direction = "left";
                x -= speed;
            }
            else if(keyHandler.rightPressed){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter > 20){
                if(spriteNumber == 1){
                    spriteNumber = 2;
                }
                else if(spriteNumber == 2){
                    spriteNumber = 1;
                }
                spriteCounter = 0;
        }

        }
    }
    public void draw(Graphics g2d){
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNumber == 1){
                    image = up0;
                }
                if(spriteNumber == 2){
                    image = up1;
                }
            break;
            case "down":
                if(spriteNumber == 1){
                    image = down0;
                }
                if(spriteNumber == 2){
                    image = down1;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left0;
                }
                if(spriteNumber == 2){
                    image = left1;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right0;
                }
                if(spriteNumber == 2){
                    image = right1;
                }
                break;
        };
        g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
