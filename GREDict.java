/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;
/**
 *
 * @author landini
 */
@SuppressWarnings("unchecked")
public class GREDict implements java.io.Serializable {
    private static HashMap<String,String> e = new HashMap<>();

    public static void read(){
        try {

            FileInputStream fileIn = new FileInputStream("GREDict.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (HashMap<String, String>) in.readObject();
            in.close();
        }catch(IOException i){
        i.printStackTrace();
        }
            catch(ClassNotFoundException c){
            System.out.println("couldn't find file");
            c.printStackTrace();
        }
    }



    public static void write(){
        try{
            FileOutputStream fileOut = new FileOutputStream("GREDict.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
        }catch(IOException i){
            i.printStackTrace();
        }

    }


    
    public static void add(String key, String value){

        read();
        e.put(key, value);
        write();
        System.out.println("Wrote "+key+" to GREDict");
        System.out.println();
    }

    private static Set<String> alphabetize(){
        SortedSet<String> keys = new TreeSet<>(e.keySet());
        return keys;
    }

    public static void delete(String key) {
        read();
        if (e.containsKey(key)) {
            e.remove(key);
        } else {
            System.out.println("Key not contained");
            return;
        }
        write();
        System.out.println("Deleted " + key + " from GREDict");
        System.out.println();
    }

    public static void defs(){
        read();
        if(e.isEmpty()){
            System.out.println("No keys in Dict"); return;
        }

        for(String i: alphabetize()){
            System.out.println(i+" : "+e.get(i));
        }
        System.out.println();
    }
    
    public static void get(String key){
        read();
        if(e.containsKey(key)){
            System.out.println(e.get(key));
        }
        else{System.out.println("Key not contained in Dict");}
	    System.out.println();
    }
    
    public static void keys(){
        read();
        if(e.isEmpty()){
            System.out.println("No keys in Dict"); return;
        }
        
        for(String i: alphabetize()){
            System.out.println(i);
        }
	    System.out.println();
    }
    
    public static void clear(){
        e = new HashMap<>();
        write();
        System.out.println("GREDict Cleared");
        System.out.println();
    }

    public static void main(String[] args) {

        String input = null;
        String[] tokens;
        do{
            Scanner readIn = new Scanner(System.in);
            input = readIn.nextLine();
            tokens = input.split(" ");
            
            if(tokens.length<1 || tokens.length >= 3){
                if(tokens[0].equals("add")){
                    String output = "";    
                    for(int i=2;i<tokens.length;i++){
                        output+= tokens[i] + " ";
                    }
                    add(tokens[1],output);
                }
                else{
                    System.out.println("Bad Input");
		    System.out.println();
                }
            }
            

            
            if(tokens.length == 2){
                if(tokens[0].equals("get")){
                        get(tokens[1]);
                }
                else if(tokens[0].equals("delete")){
                    delete(tokens[1]); 
                }
                else{System.out.println("Unauthorized command");System.out.println();}
            }
            
            if(tokens.length == 1){
                if(tokens[0].equals("defs")){
                    defs();
                }
                else if(tokens[0].equals("keys")){
                    keys();
                }
                else if(tokens[0].equals("clear")){
                    clear();
                }
                else if("q".equals(tokens[0]) || "Q".equals(tokens[0]) || "quit".equals(tokens[0]) || "Quit".equals(tokens[0])){
                    break;
                }
                else{System.out.println("Unauthorized command"); System.out.println();}
            }
            
        }while(!"q".equals(tokens[0]) && !"Q".equals(tokens[0]) && !"quit".equals(tokens[0]) && !"Quit".equals(tokens[0]));
     }

}