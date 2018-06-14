package GameComponents;

import javax.swing.*;
import java.awt.*;

abstract class Pacman implements Visited{
    private int _pacman_Anim_Count;
    private int _Pacman_Speed;
    private int pacman_Anim_Pos;
    private Image _pacman0up,_pacman0left,pacman0down,_pacman0right, _pacman1up, _pacman1left, _pacman1right, _pacman1down;
    private Image _pacman2up, _pacman2down, _pacman2left, _pacman2right;
    private Image _pacman3up, _pacman3down, _pacman3left, _pacman3right;
    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    // 1.is at live, 0.dying
    protected int lifestate;

    public Pacman(int selectedboard,String pacManType,int blockSize) {
        loadImage(pacManType);

        this._pacman_Anim_Count=3;
        this._Pacman_Speed=6;
        this.pacman_Anim_Pos=0;
        this.pacmand_x=0;
        this.pacmand_y=0;
        this.lifestate=1;
        //starting position depends on the board
        if(selectedboard==1)
        {
            this.pacman_x=15*blockSize;
            this.pacman_y=12*blockSize;
        }
        else if(selectedboard==2) {
            this.pacman_x=15*blockSize;
            this.pacman_y=15*blockSize;
        }
        else {
            this.pacman_x=15*blockSize;
            this.pacman_y=11*blockSize;
        }

    }

    private void loadImage(String Type)
    {
        if(Type.equals("Nice")) {
            this._pacman0up = new ImageIcon("src\\Resources\\up1.png").getImage();
            this.pacman0down=new ImageIcon("src\\Resources\\down1.png").getImage();
            this._pacman0left=new ImageIcon("src\\Resources\\left1.png").getImage();
            this._pacman0right=new ImageIcon("src\\Resources\\right1.png").getImage();
            this._pacman1up = new ImageIcon("src\\Resources\\up1.png").getImage();
            this._pacman1left = new ImageIcon("src\\Resources\\left1.png").getImage();
            this._pacman1right =new ImageIcon("src\\Resources\\right1.png").getImage();
            this._pacman1down =new ImageIcon("src\\Resources\\down1.png").getImage();
            this._pacman2up = new ImageIcon("src\\Resources\\up2.png").getImage();
            this._pacman2down = new ImageIcon("src\\Resources\\down2.png").getImage();
            this._pacman2left =  new ImageIcon("src\\Resources\\left2.png").getImage();
            this._pacman2right = new ImageIcon("src\\Resources\\right2.png").getImage();
            this._pacman3up =new ImageIcon("src\\Resources\\up3.png").getImage();
            this._pacman3down =new ImageIcon("src\\Resources\\down3.png").getImage();
            this._pacman3left =new ImageIcon("src\\Resources\\left3.png").getImage();
            this._pacman3right = new ImageIcon("src\\Resources\\right3.png").getImage();
        }
        else if (Type.equals("Defended"))
        {
            this._pacman0up = new ImageIcon("src\\Resources\\up1_defended.png").getImage();
            this.pacman0down=new ImageIcon("src\\Resources\\down1_defended.png").getImage();
            this._pacman0left=new ImageIcon("src\\Resources\\left1_defended.png").getImage();
            this._pacman0right=new ImageIcon("src\\Resources\\right1_defended.png").getImage();
            this._pacman1up = new ImageIcon("src\\Resources\\up1_defended.png").getImage();
            this._pacman1left = new ImageIcon("src\\Resources\\left1_defended.png").getImage();
            this._pacman1right =new ImageIcon("src\\Resources\\right1_defended.png").getImage();
            this._pacman1down =new ImageIcon("src\\Resources\\down1_defended.png").getImage();
            this._pacman2up = new ImageIcon("src\\Resources\\up2_defended.png").getImage();
            this._pacman2down = new ImageIcon("src\\Resources\\down2_defended.png").getImage();
            this._pacman2left =  new ImageIcon("src\\Resources\\left2_defended.png").getImage();
            this._pacman2right = new ImageIcon("src\\Resources\\right2_defended.png").getImage();
            this._pacman3up =new ImageIcon("src\\Resources\\up3_defended.png").getImage();
            this._pacman3down =new ImageIcon("src\\Resources\\down3_defended.png").getImage();
            this._pacman3left =new ImageIcon("src\\Resources\\left3_defended.png").getImage();
            this._pacman3right = new ImageIcon("src\\Resources\\right3_defended.png").getImage();
        }
        //type is angry
        else
        {
            this._pacman0up = new ImageIcon("src\\Resources\\up1_angry.png").getImage();
            this.pacman0down=new ImageIcon("src\\Resources\\down1_angry.png").getImage();
            this._pacman0left=new ImageIcon("src\\Resources\\left1_angry.png").getImage();
            this._pacman0right=new ImageIcon("src\\Resources\\right1_agngry.png").getImage();
            this._pacman1up = new ImageIcon("src\\Resources\\up1_angry.png").getImage();
            this._pacman1left = new ImageIcon("src\\Resources\\left1_angry.png").getImage();
            this._pacman1right =new ImageIcon("src\\Resources\\right1_agngry.png").getImage();
            this._pacman1down =new ImageIcon("src\\Resources\\down1_angry.png").getImage();
            this._pacman2up = new ImageIcon("src\\Resources\\up2_angry.png").getImage();
            this._pacman2down = new ImageIcon("src\\Resources\\down2_angry.png").getImage();
            this._pacman2left =  new ImageIcon("src\\Resources\\left2_angry.png").getImage();
            this._pacman2right = new ImageIcon("src\\Resources\\right2_angry.png").getImage();
            this._pacman3up =new ImageIcon("src\\Resources\\up3_angry.png").getImage();
            this._pacman3down =new ImageIcon("src\\Resources\\down3_angry.png").getImage();
            this._pacman3left =new ImageIcon("src\\Resources\\left3_angry.png").getImage();
            this._pacman3right = new ImageIcon("src\\Resources\\right3_angry.png").getImage();
        }
    }

    public int get_pacman_Anim_Count() {
        return _pacman_Anim_Count;
    }

    public void die() {
    lifestate=0;
    }
    public void live(){
        lifestate=1;
    }
    public int getLifestate(){return lifestate;}

    public void set_pacman_Anim_Count(int _pacman_Anim_Count) {
        this._pacman_Anim_Count = _pacman_Anim_Count;
    }

    public int get_Pacman_Speed() {
        return _Pacman_Speed;
    }

    public void set_Pacman_Speed(int _Pacman_Speed) {
        this._Pacman_Speed = _Pacman_Speed;
    }

    public int getPacman_Anim_Pos() {
        return pacman_Anim_Pos;
    }

    public void setPacman_Anim_Pos(int pacman_Anim_Pos) {
        this.pacman_Anim_Pos = pacman_Anim_Pos;
    }

    public Image get_pacman0up() {
        return _pacman0up;
    }

    public void set_pacman0up(Image _pacman0up) {
        this._pacman0up = _pacman0up;
    }

    public Image get_pacman0left() {
        return _pacman0left;
    }

    public void set_pacman0left(Image _pacman0left) {
        this._pacman0left = _pacman0left;
    }

    public Image getPacman0down() {
        return pacman0down;
    }

    public void setPacman0down(Image pacman0down) {
        this.pacman0down = pacman0down;
    }

    public Image get_pacman0right() {
        return _pacman0right;
    }

    public void set_pacman0right(Image _pacman0right) {
        this._pacman0right = _pacman0right;
    }

    public Image get_pacman1up() {
        return _pacman1up;
    }

    public void set_pacman1up(Image _pacman1up) {
        this._pacman1up = _pacman1up;
    }

    public Image get_pacman1left() {
        return _pacman1left;
    }

    public void set_pacman1left(Image _pacman1left) {
        this._pacman1left = _pacman1left;
    }

    public Image get_pacman1right() {
        return _pacman1right;
    }

    public void set_pacman1right(Image _pacman1right) {
        this._pacman1right = _pacman1right;
    }

    public Image get_pacman1down() {
        return _pacman1down;
    }

    public void set_pacman1down(Image _pacman1down) {
        this._pacman1down = _pacman1down;
    }

    public Image get_pacman2up() {
        return _pacman2up;
    }

    public void set_pacman2up(Image _pacman2up) {
        this._pacman2up = _pacman2up;
    }

    public Image get_pacman2down() {
        return _pacman2down;
    }

    public void set_pacman2down(Image _pacman2down) {
        this._pacman2down = _pacman2down;
    }

    public Image get_pacman2left() {
        return _pacman2left;
    }

    public void set_pacman2left(Image _pacman2left) {
        this._pacman2left = _pacman2left;
    }

    public Image get_pacman2right() {
        return _pacman2right;
    }

    public void set_pacman2right(Image _pacman2right) {
        this._pacman2right = _pacman2right;
    }

    public Image get_pacman3up() {
        return _pacman3up;
    }

    public void set_pacman3up(Image _pacman3up) {
        this._pacman3up = _pacman3up;
    }

    public Image get_pacman3down() {
        return _pacman3down;
    }

    public void set_pacman3down(Image _pacman3down) {
        this._pacman3down = _pacman3down;
    }

    public Image get_pacman3left() {
        return _pacman3left;
    }

    public void set_pacman3left(Image _pacman3left) {
        this._pacman3left = _pacman3left;
    }

    public Image get_pacman3right() {
        return _pacman3right;
    }

    public void set_pacman3right(Image _pacman3right) {
        this._pacman3right = _pacman3right;
    }

    public int getPacman_x() {
        return pacman_x;
    }

    public void setPacman_x(int pacman_x) {
        this.pacman_x = pacman_x;
    }

    public int getPacman_y() {
        return pacman_y;
    }

    public void setPacman_y(int pacman_y) {
        this.pacman_y = pacman_y;
    }

    public int getPacmand_x() {
        return pacmand_x;
    }

    public void setPacmand_x(int pacmand_x) {
        this.pacmand_x = pacmand_x;
    }

    public int getPacmand_y() {
        return pacmand_y;
    }

    public void setPacmand_y(int pacmand_y) {
        this.pacmand_y = pacmand_y;
    }
    abstract String getpacmanType();

    @Override
     public void impact(Visitor visitor) {

    }
}
