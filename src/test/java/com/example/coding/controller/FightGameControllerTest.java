package com.example.coding.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.coding.main.Application;
import com.example.coding.service.FightGameService;
import com.example.coding.util.IConstants;

@SuppressWarnings("deprecation")
@SpringBootTest(classes = { Application.class, FightGameService.class, FightGameController.class, IConstants.class })
@RunWith(MockitoJUnitRunner.Silent.class)
public class FightGameControllerTest {

	
	private final String successResponse = "GAME FINISHED SUCCESSFULLY.";
	private final String failResponse = "GAME FAILED DUE TO MISMATCH VALUE.";
	
	@InjectMocks
    private FightGameController fightGameController;
	
	@Mock
	private FightGameService fightGameService;
	
	@Test
	public void testLoadGame() throws Exception {
		int loadGameInput = 2;
		when(fightGameService.loadGame()).thenReturn(true);
		assertEquals(true, fightGameService.loadGame());
        String response = fightGameController.startGame(loadGameInput);
		assertEquals(successResponse, response);
	}
	
	@Test
	public void testStartGame() throws Exception {
		int saveGameInput = 1;
		when(fightGameService.startNewGame()).thenReturn(true);
		assertEquals(true, fightGameService.startNewGame());
        String response = fightGameController.startGame(saveGameInput);
		assertEquals(successResponse, response);
	}
	
	@Test
	public void testStartGameFailure() throws Exception {
		int saveGameInput = 3;
        String response = fightGameController.startGame(saveGameInput);
		assertEquals(failResponse, response);
	}
	
	@Test
	public void testStartGameFailure2() throws Exception {
		when(fightGameService.startNewGame()).thenReturn(false);
		boolean response = fightGameService.startNewGame();
		assertEquals(false, response);
		String startGameRespone = fightGameController.startGame(3);
		assertEquals(IConstants.Response.FAIL, startGameRespone);
	}
	
	@Test
	public void testStartGameFailure3() throws Exception {
		when(fightGameService.loadGame()).thenReturn(false);
		boolean response = fightGameService.loadGame();
		assertEquals(false, response);
		String loadGameResponse = fightGameController.startGame(3);
		assertEquals(IConstants.Response.FAIL, loadGameResponse);
	}
}
