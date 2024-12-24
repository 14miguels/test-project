package ModelTest;

import Controller.PlayerController;
import Model.Cell_Manager.Property;
import Model.Pawn;
import Model.Player.Player;
import com.googlecode.lanterna.TextColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    private Player player;

    @Mock
    private Pawn mockPawn;

    @Mock
    private Property mockProperty;

    @Mock
    private PlayerController mockController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new Player("TestPlayer", 1, TextColor.ANSI.RED);
        player.pawn = mockPawn;  // Mocking the pawn
    }

    @Test
    public void testWithdraw_DecreasesAccountBalance() {
        // Arrange
        int initialBalance = player.account_balance;
        int withdrawAmount = 200;

        // Act
        player.withdraw(withdrawAmount);

        // Assert
        assertEquals(initialBalance - withdrawAmount, player.account_balance);
    }

    @Test
    public void testDeposit_IncreasesAccountBalance() {
        // Arrange
        int initialBalance = player.account_balance;
        int depositAmount = 300;

        // Act
        player.deposit(depositAmount);

        // Assert
        assertEquals(initialBalance + depositAmount, player.account_balance);
    }

    @Test
    public void testBuyProperty_AddsPropertyToPlayer() {
        // Arrange
        when(mockProperty.getHousePrice()).thenReturn(200);
        player.account_balance = 500;

        // Act
        player.buyProperty(mockProperty);

        // Assert
        assertTrue(player.getProperties().contains(mockProperty));
        assertEquals(300, player.account_balance);
    }

    @Test
    public void testRollDice_DelegatesToController() throws IOException {
        // Arrange
        doNothing().when(mockController).rollDice(player, mockPawn);

        // Act
        player.rollDice();

        // Assert
        verify(mockController, times(1)).rollDice(player, mockPawn);
    }

    @Test
    public void testSetPositionIndex_WithinBounds() {
        // Act
        player.setPosition_index(20);

        // Assert
        assertEquals(4, player.getPosition_index()); // 20 % 16 = 4
    }

    @Test
    public void testBuyHouse_AcquiresHouse() throws IOException {
        // Arrange
        when(mockProperty.getHousePrice()).thenReturn(200);
        player.account_balance = 500;

        // Act
        player.buyHouse(mockProperty);

        // Assert
        verify(mockProperty, times(1)).acquireHouse();
        assertEquals(300, player.account_balance);
    }


}
