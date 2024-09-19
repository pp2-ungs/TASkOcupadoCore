package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Set;

import ext.SMSNotificator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.*;

public class Main {

	// startdebugging
	public final static Task taskLoca = new Task("Bañarse");
	public final static Member xime = new Member("Ebertz");
	public final static Member gonza = new Member("López");
	public final static Member hdr = new Member("Rondelli");
	//stopdebugging

	public static void main(String[] args) {
		TASkOcupado taskOcupado = new TASkOcupado();
		
		String[] members = taskOcupado.getMembers();
		String[] tasks = taskOcupado.getTasks();
		
		for(String s : members) {
			System.out.println(s);
		}
		
		for(String s : tasks) {
			System.out.println(s);
		}
		
	}
}
