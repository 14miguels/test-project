package ControllerTest;

import Controller.PawnController;
import Model.Player.Player;
import Model.Position;
import View.BoardView.Board;
import View.BoardView.PawnView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class PawnControllerTest {

    @Mock
    private Board mockBoard;

    @Mock
    private PawnView mockPawnView;

    @Mock
    private Player mockPlayer;

    private PawnController pawnController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pawnController = new PawnController(mockBoard, 100, 200);
    }

    @Test
    public void testShowSelf_DrawsPawnCorrectly() throws IOException {
        // Arrange
        when(mockPlayer.ref).thenReturn(1);

        // Act
        PawnController.showSelf(mockPlayer);

        // Assert
        verify(mockPlayer).setPawnX(91);
        verify(mockPlayer).setPawnY(44);
        verify(mockPawnView).draw(new Position(91, 44));
    }

    @Test
    public void testMove_UpdatesPlayerPosition() throws IOException {
        // Arrange
        when(mockPlayer.getPawnX()).thenReturn(91);
        when(mockPlayer.getPawnY()).thenReturn(44);
        when(mockPlayer.getPosition_index()).thenReturn(0);

        // Act
        PawnController.move(5, mockPlayer);

        // Assert
        verify(mockPawnView).removePawn(new Position(91, 44));
        verify(mockPlayer).setPosition_index(5);
        verify(mockPawnView).draw(any(Position.class));
    }

    @Test
    public void testShowJail_SetsPlayerToJailPosition() throws IOException {
        // Arrange
        when(mockPlayer.getPawnX()).thenReturn(91);
        when(mockPlayer.getState()).thenReturn(Boolean.valueOf("inJail"));
        when(mockPlayer.ref).thenReturn(1);

        // Act
        PawnController.showJail(mockPlayer);

        // Assert
        verify(mockPlayer).setPawnX(91 - 4 * 22);
        verify(mockPlayer).setPawnY(44);
        verify(mockPawnView).draw(new Position(91 - 4 * 22, 44));
    }


}
