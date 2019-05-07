package com.example.coding.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
public class PlayerActionsTest {

	Player playerDetail = new Player("P1", IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN, "P2" ,IConstants.Default.HEALTH, IConstants.Default.GOLD, IConstants.Default.ENERGY_DRINK, IConstants.Default.SHORT_GUN, IConstants.Default.BIG_GUN);

	@InjectMocks
	private PlayerActions playerActions;
	
	@Mock
	private PlayerUtil commonUtil;
	
	@Mock
	private Scanner scan;
	
	
	@Test
	public void testPlayer1_Punch() throws Exception {
		when(scan.nextInt()).thenReturn(1);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer2_Punch() throws Exception {
		when(scan.nextInt()).thenReturn(5);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer1_Kick() throws Exception {
		when(scan.nextInt()).thenReturn(2);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer2_Kick() throws Exception {
		when(scan.nextInt()).thenReturn(6);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer1_Explore_Drink() throws Exception {
		when(scan.nextInt()).thenReturn(1);
		playerActions.explorePlayer(playerDetail, 3);
	}
	
	@Test
	public void testPlayer1_Explore_ShortGun() throws Exception {
		when(scan.nextInt()).thenReturn(2);
		playerActions.explorePlayer(playerDetail, 3);
	}
 
	@Test
	public void testPlayer1_Explore_BigGun() throws Exception {
		when(scan.nextInt()).thenReturn(3);
		playerActions.explorePlayer(playerDetail, 3);
	}

	@Test
	public void testPlayer2_Explore_Drink() throws Exception {
		when(scan.nextInt()).thenReturn(1);
		playerActions.explorePlayer(playerDetail, 7);
	}
	
	@Test
	public void testPlayer2_Explore_ShortGun() throws Exception {
		when(scan.nextInt()).thenReturn(2);
		playerActions.explorePlayer(playerDetail, 7);
	}
 
	@Test
	public void testPlayer2_Explore_BigGun() throws Exception {
		when(scan.nextInt()).thenReturn(3);
		playerActions.explorePlayer(playerDetail, 7);
	}
	
	// write code
	@Test
	public void testPlayer1_BoostEnergy() throws Exception {
		when(scan.nextInt()).thenReturn(4);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer2_BoostEnergy() throws Exception {
		when(scan.nextInt()).thenReturn(8);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testSaveGame() throws Exception {
		when(scan.nextInt()).thenReturn(9);
		//when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(false, response);
	}
	
	@Test
	public void testLoadGame() throws Exception {
		when(scan.nextInt()).thenReturn(1);
		//when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.loadGame();
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer1_ShortGun() throws Exception {
		when(scan.nextInt()).thenReturn(12);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer1_BigGun() throws Exception {
		when(scan.nextInt()).thenReturn(13);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer2_ShortGun() throws Exception {
		when(scan.nextInt()).thenReturn(14);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
	
	@Test
	public void testPlayer2_BigGun() throws Exception {
		when(scan.nextInt()).thenReturn(15);
		when(commonUtil.decideWinnerAndContinue(playerDetail, true)).thenReturn(true);
		boolean response = playerActions.performActions(playerDetail, true);
		assertEquals(true, response);
	}
}
