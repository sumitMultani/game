package com.example.coding.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.coding.controller.FightGameController;
import com.example.coding.main.Application;
import com.example.coding.model.Player;
import com.example.coding.service.FightGameService;

@SuppressWarnings("deprecation")
@SpringBootTest(classes = { Application.class, FightGameService.class, FightGameController.class, IConstants.class })
@RunWith(MockitoJUnitRunner.Silent.class)
public class CommonUtilTest {

	@InjectMocks
	private PlayerUtil commonUtil;
	
	@Mock
	private Scanner scan;
	
	@Mock
	private PlayerActions playerActions;
	
	private final String name1 = "sp";
	private final String name2 = "sp";
	private final Integer P1Health = 0;
	private final Integer P2Health = 0;
	
	Player p1 = new Player(name1, P1Health, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN, name2 ,IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN);
	Player p2 = new Player(name1, IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN, name2 ,P2Health, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN);
	
	public List<String> getPlayers(){
		List<String> names = new ArrayList<>();
		names.add(name1);
		names.add(name2);
		return names;
	}
	
	@Test
	public void testLoadGame() throws Exception {
		when(scan.next()).thenReturn("sp");
		List<String> playerNames = commonUtil.getPlayerNames();
		assertEquals(playerNames.get(0), name1);
		assertEquals(playerNames.get(1), name2);
	}
	
	@Test
	public void testIfIsP2Winner(){
		when(scan.nextInt()).thenReturn(1);
		boolean response = commonUtil.decideWinnerAndContinue(p1, false);
		assertEquals(false, response);
	}
	
	@Test
	public void testIfIsP1Winner(){
		when(scan.nextInt()).thenReturn(1);
		when(playerActions.performActions(p2, true)).thenReturn(false);
		boolean actionResponse = playerActions.performActions(p2, true);
		assertEquals(false, actionResponse);
		boolean response = commonUtil.decideWinnerAndContinue(p2, true);
		assertEquals(false, response);
	}
	
	@Test
	public void testPrintTitle(){
		commonUtil.printTitle();
	}
	
	@Test
	public void testPrintPlayersName(){
		commonUtil.printPlayersName(p1);
	}
	
	@Test
	public void testPrintPlayersDetail(){
		commonUtil.printPlayersDetail(p1);
	}
	
	@Test
	public void testPrintActions(){
		commonUtil.printActions(p1);
	}
	
}
