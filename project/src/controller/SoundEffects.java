//Programmed: Christopher Grundy
//Created: 4/14/15
//DLM: 4/14/15
//This is a simple sound class that will add sounds to essentially
//	a playlist, and play them.


package controller;

import java.io.*;
import javax.sound.sampled.*;
import java.util.*;

public class SoundEffects {
   
   private static ArrayList<Clip> clips = new ArrayList<Clip>();
   public static ArrayList<String> clipNames = new ArrayList<String>();
   
   private SoundEffects(){
	   
   }
   
   	public static void addSound(String soundFileName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFileName));
			clips.add(AudioSystem.getClip());
			clipNames.add(soundFileName);
			
			Clip currClip = clips.get(clips.size() - 1);
         
			currClip.open(audioInputStream);
			currClip.start();
         
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
   	}
   	
   	public static void update() {
   		ArrayList<Integer> cleanup = new ArrayList<Integer>();
   		
   		for(int i = 0; i < clips.size(); i++) {
   			Clip currClip = clips.get(i);
   			if (currClip.getMicrosecondPosition() >= currClip.getMicrosecondLength()) {
   				cleanup.add(i);
   			}
   		}
   		if (clips.size() > 0) {
	   		for(int i = 0; i < cleanup.size(); i++) {
	   			if (clips.size() > (int) cleanup.get(i)) {
	   				Clip currClip = clips.get((int) cleanup.get(i));
	   				clipNames.remove((int)cleanup.get(i));

	   				clips.get((int)cleanup.get(i)).close();
	   				clips.remove((int) cleanup.get(i));
	   			}
	   		}
   		}

   	}
   	
   	public static void clearSoundClips(){
   		for(int i = 0; i < clips.size(); i++){
   			clips.get(i).stop();
   			clips.remove(i);
   		}
   	}
   	
   	public static boolean containsSoundClip(String soundFileName){
   		
   		for(String s : clipNames){
   			if(s.equals(soundFileName))
   				return true;
   		}
   		return false;
   	}
   	
   	public static void removeSoundClip(String soundFileName){
   		
   		if(clips.size() > 0){
	   		for(int i = 0; i < clipNames.size(); i++){
	   			if(clipNames.get(i).equals(soundFileName)){
	   				clips.get(i).stop();
	   				clips.remove(i);
	   				clipNames.remove(i);
	   			}
	   		}
   		}
   		
   	}
   	
   	public static int getSoundClipArraySize(){
   		return clips.size();
   	}
   	
   	public static void showSize() {
   		System.out.println("Clips: " + clips.size());
   	}
}