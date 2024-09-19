package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
            Set<Member> members = gson.fromJson(jsonReader, memberSetType);

            for(Member m : members) {
            	System.out.println(m.getName());
            }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
