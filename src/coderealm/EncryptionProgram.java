/*/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coderealm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class EncryptionProgram {
   /* private Scanner scanner;
    private Random random;
    private /*ArrayList<Character>String list;
    private /*ArrayList<Character>String shuffledlist;
    List<SerialKey> slist; 
    
    private char character;
    private String line;
    private char[] letters;
    private char[] secretletters;
    SerialKey sk = new SerialKey();
    
    
   /* public ArrayList<Character> getList() {
        return list;
    }

    public ArrayList<Character> getShuffledlist() {
        return shuffledlist;
    }

    public void setList(ArrayList<Character> list) {
        this.list = list;
    }

    public void setShuffledlist(ArrayList<Character> shuffledlist) {
        this.shuffledlist = shuffledlist;
    }
    */

  /*  public  EncryptionProgram(Scanner scanner, Random random, ArrayList<Character> list, ArrayList<Character> shuffledlist, char character) {
        this.list = new ArrayList<>();
        this.scanner = scanner;
        this.random = random;
        this.list = list;
        this.shuffledlist = shuffledlist;
        this.character = character;
        
        
    }

    

    public  EncryptionProgram() throws IOException {
        //this.list = new ArrayList<>();
        scanner = new Scanner(System.in);
        random = new Random();
        list = new String();
        shuffledlist = new String();
        character = ' ';
        
        newKey();
        askQuestion();
    }

    private void askQuestion() throws IOException {
        while(true){
            System.out.println("*********************************************");
            System.out.println("What do you want to do?");
            System.out.println("(N)ewKey,(G)etKey,(E)ncrypt,(D)ecrypt,(Q)uit");
            char r;
            r = Character.toUpperCase(scanner.nextLine().charAt(0));
            
            switch(r){
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
               
                case 'Q':
                   quit();
                   break;
                default:
                    System.out.println("not valid input");
                    
            }
        }
    }

    private void newKey() throws IOException {
        character = ' ';
        ///list.clear();
       
        //shuffledlist.clear();
        
        for(int i = 32;i<127;i++){
             list = list+(Character.valueOf(character));
             character++;
        }
       
        shuffledlist = new String(list);
        Collections.shuffle(shuffledlist);
        sk.setList(list);
        sk.setShuffledlist(shuffledlist);
        slist.add( sk);
        fileWrite(slist);
        System.out.println("a new key");
    }

    private void getKey() throws NullPointerException, IOException {
        fileRead();
        System.out.println("key: ");
        for(Character x : list){
            System.out.println(x);     
        }
        System.out.println();
        for(Character x : shuffledlist){
            System.out.println(x);     
        }
        System.out.println();
    }

    public StringBuffer encrypt(String psw) throws NullPointerException, IOException {
        
        fileRead();
        
        String message = psw;
        StringBuffer pass=null;
        letters = message.toCharArray();
        for(int i = 0;i < letters.length;i++){
             for(int j = 0;j < list.size();j++){
                 if(letters[i] == list.get(j)){
                     letters[i] = shuffledlist.get(j);
                     break;
                 }
             }
        }
        System.out.println("encrypted");
        for(char x: letters){
            pass.append(letters[x]);
        }
        return pass;
    }

    public void decrypt(String psw) throws NullPointerException, IOException {
        
        fileRead();
        
        String message = psw;
        letters = message.toCharArray();
        for(int i = 0;i < letters.length;i++){
             for(int j = 0;j < shuffledlist.size();j++){
                 if(letters[i] == shuffledlist.get(j)){
                     letters[i] = list.get(j);
                     break;
                 }
             }
        }
        System.out.println("decrypted");
        for(char x: letters){
            System.out.println(x);
        }
    }

    private void quit() {
        System.exit(0);
    }
    
    /*public void fileWrite() throws IOException{
        FileWriter  wri = new FileWriter("SerialKey.txt");
      
       
        wri.write(list.toString().replaceAll(",", "")+"\n");   
           
           
        wri.close();
    }
     public void fileRead() throws NullPointerException, IOException{
         String lists = null;
         String shuffledlists = null;
         
         BufferedReader reader = new  BufferedReader(new FileReader("SerialKey.txt"));
        
        
        while((lists = reader.readLine()) != null){
            shuffledlists = reader.readLine(); 
        }
        
        for(int i = 0; i < lists.length();i++){
             list.add(lists.charAt(i));
             shuffledlist.add(shuffledlists.charAt(i));
        }
        reader.close();
     }
    
    
    public void fileWrite(List<SerialKey> obj) throws IOException{
        FileWriter  wri = new FileWriter("SerialKey.txt");
       // PrintWriter w = new PrintWriter(wri);
         int size = obj.size();
       for(int i =0; i < size;i++){
            wri.write/*println(obj.get(i).list.toString().replaceAll(",", "")+"\n");
            wri.write/*println(obj.get(i).shuffledlist.toString().replaceAll(",", "")+"\n");        
            }
            //w.close();
            wri.close();
    }
     public void fileRead() throws NullPointerException, IOException{
         String lists = null;
         String shuffledlists = null;
         
         BufferedReader reader = new  BufferedReader(new FileReader("SerialKey.txt"));
        
        
        while((lists = reader.readLine()) != null){
            shuffledlists = reader.readLine();
           
            
            SerialKey obj1 = new SerialKey(list,shuffledlist);
            slist.add(obj1);
        }
        reader.close();
     }*/
}
