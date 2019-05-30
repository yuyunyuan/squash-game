import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class assignment1 extends PApplet {

//uni id: u6092441
// name: Yunyuan Yu
float startingposx=500;
float startingposy=500;
float angle;
float ballsize;
boolean dir;
float diff=3;
boolean status=false;
String str="click anywhere to start";
int highest=0;
int counter;
float a=10;
float c1, c2, c3;
public void setup() {
  
  background(255);
}

public void mouseClicked() {
  if (status==false) {
    status=true;
    startingposx=random(250, 750);
    startingposy=random(450, 550);
    angle=random(-5, 5);
    ballsize=70;
    diff=3;
    dir=true;
    counter=0;
    c1=random(255);
    c2=random(255);
    c3=random(255);
  }
}
//https://processing.org/reference/mouseClicked_.html
// use mouse click to start

public void draw() {
  background(255);
  textSize(25);
  fill(0);
   if (frameCount % 150 == 0)
   {
     diff++;
   }
  strokeWeight(5);
  stroke(65, 55, 55);
  line(0, 600, 150, 400);
  line(150, 0, 150, 400);
  line(1000, 600, 850, 400);
  line(850, 0, 850, 400);
  line(150, 400, 850, 400);
  fill(20, 70, 200);
  rect(0, 0, 1000, 50);
  fill(70, 100, 200);
  rect(0, 50, 1000, 50);
  textSize(30);
  fill(0);
  text("current score:"+ counter, 750, 80);
  if (counter>highest)
    highest=counter;
  if (a<800) {
    text("the highest score is:"+highest, a, 30);
    a+=3;
  } else
    a=10;
  if (status==false)
  {  
    fill(170, 0, 0);
    text(str, 10, 80);
  }
  fill(0, 102, 153);
  if (status==true) {
    startingposx=startingposx+angle/10;
    if (dir==true) {
      startingposy=startingposy-1*diff;
      ballsize=ballsize-0.12f*diff;
    } else {
      startingposy=startingposy+1*diff;
      ballsize=ballsize+0.12f*diff;
    }
    if (ballsize<36)
    {
      dir=false;
     
    }
    noStroke();
    if (frameCount % 300 == 0)
    {
      c1=random(c1-50, c1+50);
      c2=random(c2-50, c2+50);
      c3=random(c3-50, c3+50);
    }
    c1=c1+random(-5, 5);
    c2=c2+random(-5, 5);
    c3=c3+random(-5, 5);
    fill(c1, c2, c3);
    ellipse(startingposx, startingposy, ballsize, ballsize);
    float a=0;
    float rackety;
    if (mouseY<500)
      rackety=500;
    else
      rackety=mouseY;
    if (mouseX<500)
    {
      a=(500-mouseX)/10;
    }
    if (mouseX>500)
    {
      a=-(mouseX-500)/10;
    }

    strokeWeight(2);
    stroke(160, 160, 160);
    fill(224, 224, 224);
    quad(mouseX-10-a, rackety-40, mouseX+10-a, rackety-40, mouseX+10, rackety+40, mouseX-10, rackety+40);
    ellipse( mouseX-a, rackety-50, 80, 80);
    if (dir==false) {
      if (startingposx<(mouseX-a+45)&&startingposx>(mouseX-a-45)&&startingposy>(rackety-50-45)&&startingposy<(rackety-50+45))
      {
        fill(255, 100, 100);
        ellipse( mouseX-a, rackety-50, 80, 80);
        counter++;
        dir=true;
        if (startingposx<mouseX-a) {
          angle=((mouseX-a-startingposx)/45)*diff*5;
          if (angle>0)
            angle=-angle;
        } else if (startingposx>mouseX-a) {
          angle=((mouseX-a-startingposx)/45)*diff*5;
          if (angle<0)
            angle=-angle;
        }
      }
    }
  }
  if (startingposx<150||startingposx>850)
  {
    status=false;
    str="the ball is out of bounds, click anywhere to restart";
  }
  if (startingposy>550)
  {
    status=false;
    str="you missed the ball, click anywhere to restart";
  }
}
  public void settings() {  size(1000, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "assignment1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
