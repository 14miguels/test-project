package ControllerTest;

import Controller.CLI_Manager.CLI;
import Controller.Cell_Manager.CellManager;
import Controller.TurnController;
import Model.Player.Player;
import View.DisplayView.Display;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class TurnControllerTest {

    @Mock
    private Display mockDisplay;

    @Mock
    private Screen mockScreen;

    @Mock
    private CellManager mockCellManager;

    @Mock
    private Player mockPlayer;

    @Mock
    private CLI mockCLI;

    private TurnController turnController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        turnController = new TurnController(mockDisplay, mockScreen, mockCellManager);
    }

    @Test
    public void testInitTurns_CreatesPlayersAndCLI() throws IOException {
        // Act
        turnController.initTurns();

        // Assert
    }

    @Test
    public void testRun_RemovesPlayersWithNegativeBalance() throws IOException {
        // Arrange
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        when(player1.account_balance).thenReturn(-1);
        when(player2.account_balance).thenReturn(100);

        List<Player> mockPlayerList = List.of(player1, player2);

        // Act & Assert

    }
}