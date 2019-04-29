//package com.game.src.main;
package com.example.coding.model;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.example.coding.service.impl.FightGameServiceImpl;

public class KeyInput extends KeyAdapter{
 
 FightGameServiceImpl game;
 
 public KeyInput(FightGameServiceImpl game) {
  this.game = game;
 }
 
 public void keyPressed (KeyEvent e){
  game.keyPressed(e);
 }
 public void keyReleased(KeyEvent e){
  game.keyReleased(e);
 }
}
