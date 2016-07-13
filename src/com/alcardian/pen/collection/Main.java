package com.alcardian.pen.collection;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;

/**
 * 
 * @author Alcardian
 * @version 1.0
 */
public class Main {
	public static String name = "Alc Pen Collector";	//Name of the Application.
	public static String version = "1.1";	//Version of the Application.
	public static String fileName = "PenDB.txt";	//File Name
	
	public static ArrayList<String> pens = new ArrayList<String>();	//List of all pens.
	
	//FileManager fileIO = new FileManager();
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		getPens();
		
		for(int a=0; a<args.length; a++){
			System.out.println(args[a]);
		}
		
		JFrame frame = new JFrame();
		PenPanel panel = new PenPanel();
		
		frame.setSize(280, 350);
		frame.setVisible(true);
		frame.setTitle(name + " " + version);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
	}
	
	
	/**
	 * Function to read pens from file and sort it.
	 */
	public static void getPens(){
		pens = FileManager.ReadFile(fileName);
		sortPens();
	}
	
	/**
	 * Function to sort pens list and write it to file.
	 */
	public static void updatePens(){
		sortPens();
		FileManager.writeToFile(pens, fileName);
	}
	
	public static void sortPens(){
		//Collections.sort(pens);
		//Arrays.sort(stringArray);
		
		String[] stringArray = new String[pens.size()];	//creating an Array of Strings to be used for sorting
		for (int a=0; a<pens.size(); a++){
			stringArray[a] = pens.get(a);
		}
		Arrays.sort(stringArray);
		pens.clear();
		for(int a=0; a<stringArray.length; a++){
			pens.add(stringArray[a]);
		}
	}
	
	public static void addPen(String pen){
		pens.add(pen);
		updatePens();
	}
	
	/**
	 * Deletes the item in the pens list at the index number(first item got index 0).
	 * @param index
	 */
	public static void deletePen(int index){
		ArrayList<String> temp = new ArrayList<String>();
		for(int a=0; a<pens.size(); a++){
			if(a != index){
				temp.add(pens.get(a));
			}
		}
		pens = temp;
		updatePens();
	}
	
	
	public static String findPen(String pen){
		String temp = "";
		for(int a=0; a<pens.size(); a++){
			if(pens.get(a).equals(pen)){
				temp = "";
				temp += (a+1);
			}
		}
		return temp;
	}
}
