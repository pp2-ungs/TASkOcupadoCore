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

		FileReader jsonReader;
		try {
			jsonReader = new FileReader("resources/members.json");
			
			Gson gson = new Gson();
			
            Type memberSetType = new TypeToken<Set<Member>>() {}.getType();
            Member members = gson.fromJson(jsonReader, Member.class);

            System.out.println(members.getName());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
