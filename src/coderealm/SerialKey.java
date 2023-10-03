/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coderealm;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class SerialKey {
   public ArrayList<Character> list;
   public ArrayList<Character> shuffledlist;

    public SerialKey(ArrayList<Character> list, ArrayList<Character> shuffledlist) {
        this.list = list;
        this.shuffledlist = shuffledlist;
    }

    public SerialKey() {
    }

    public ArrayList<Character> getList() {
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
   
    
}
