/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.pmq24.rfid_guard.BUS;
import com.github.pmq24.rfid_guard.reading.TagReadDto;
import java.util.ArrayList;

/**
 *
 * @author LLOST
 */
public class TagReadBus {
    
    public static ArrayList<TagReadDto> db;
    
    public static ArrayList<TagReadDto> getdb(){
        return db;
    }
}
