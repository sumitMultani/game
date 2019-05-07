package com.example.coding.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.coding.controller.FightGameController;
import com.example.coding.main.Application;
import com.example.coding.model.Player;
import com.example.coding.service.impl.FightGameServiceImpl;
import com.example.coding.util.PlayerUtil;
import com.example.coding.util.IConstants;
import com.example.coding.util.PlayerActions;

@SuppressWarnings("deprecation")
@SpringBootTest(classes = { Application.class, FightGameService.class, FightGameController.class, IConstants.class })
@RunWith(MockitoJUnitRunner.Silent.class)
public class FightGameServiceTest {
	
	@InjectMocks
	private FightGameServiceImpl fightGameService;
   
	@Mock
	private PlayerUtil commonUtil;
	
	@Mock
	private PlayerActions playerActions;
	
	public List<String> getNames(){
		List<String> names = new ArrayList<>(); 
		names.add("sumit");
		names.add("prabh");
		return names;
	}
	 
	@Test
	public void testStartSuccessNewGame() throws Exception {
		List<String> names = getNames();
		Player p1 = new Player(names.get(0), IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN, names.get(1) ,IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN);
		boolean isPlay = true;
		when(commonUtil.getPlayerNames()).thenReturn(names);
		assertEquals(names.size(),commonUtil.getPlayerNames().size());
		when(playerActions.performActions(p1, isPlay)).thenReturn(true);
		assertEquals(true,playerActions.performActions(p1, isPlay));
		boolean response = fightGameService.startNewGame();
		assertEquals(true, response);
	}
	
	@Test
	public void testStartFailNewGame() throws Exception {
		when(commonUtil.getPlayerNames()).thenThrow(new RuntimeException("Invalid player list"));
		boolean response = fightGameService.startNewGame();
		assertEquals(false, response);
	}

	@Test
	public void testLoadGame() throws Exception {
		when(playerActions.loadGame()).thenReturn(true);
		boolean response = fightGameService.loadGame();
		assertEquals(true, response);
	}
	
}
