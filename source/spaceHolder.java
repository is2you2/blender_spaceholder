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

public class spaceHolder extends PApplet {

File files;
PImage get;
PrintWriter bash;

public void setup(){
  bash=createWriter("bash");
  bash.println("#!/bin/bash");
  bash.println("cd tmp");
  bash.println("echo \"spaceHolder now work on\"");
  files=new File(sketchPath()+"/tmp");
  File[] getOrig=files.listFiles();
  String[] get={};
  for(File i:getOrig){
    String packed=split(i.toString(),"/tmp/")[1];
    get=append(get,packed);
  }
  get=sort(get);
  int end=0;
  for(int i=0;i<get.length-1;i++){
    int start=PApplet.parseInt(split(get[i],'.')[0]);  end=PApplet.parseInt(split(get[i+1],'.')[0]);
    int copy=start;
    for(int j=start;j<end-1;j++,start++){
      bash.println("cp "+nf(copy,4)+".png "+nf(start+1,4)+".png");
    }
  }
  bash.println("cd ..");
  bash.println("cd root");
  bash.println("blender -b final.blend -e "+end+" -a");
  bash.println("vlc result_0001-"+nf(end,4)+".mp4");
  bash.flush();
  bash.close();
  exit();
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "spaceHolder" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
