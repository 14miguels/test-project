
import Controller.PawnController;
import Model.Player.Player;
import View.BoardView.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;

import static org.mockito.Mockito.*;

class PawnControllerTest {
    private Board mockBoard;
    private Player mockPlayer;
    private PawnController pawnController;
    //rbgvrdgt
    @BeforeEach
    void setUp() {
        mockBoard = Mockito.mock(Board.class);
        mockPlayer = Mockito.mock(Player.class);

        // Create PawnController instance
        pawnController = new PawnController(mockBoard, 100, 100); // Adjust height and width as needed
    }


    @Test
    void testMoveRight() throws IOException {
        when(mockPlayer.getPawnX()).thenReturn(91);
        when(mockPlayer.getPawnY()).thenReturn(44);
        when(mockPlayer.getPosition_index()).thenReturn(8);

        PawnController.move(4, mockPlayer);

        verify(mockPlayer).setPawnX(91 + 22 * 4); // Assuming `cellWidth = 22`
        verify(mockPlayer).setPawnY(44);
        verify(mockPlayer).setPosition_index((8 + 4) % 16);
    }

    @Test
    void testMoveDown() throws IOException {
        when(mockPlayer.getPawnX()).thenReturn(91);
        when(mockPlayer.getPawnY()).thenReturn(44);
        when(mockPlayer.getPosition_index()).thenReturn(12);

        PawnController.move(2, mockPlayer);

        verify(mockPlayer).setPawnX(91);
        verify(mockPlayer).setPawnY(44 + 10 * 2); // Assuming `cellHeight = 10`
        verify(mockPlayer).setPosition_index((12 + 2) % 16);
    }

    @Test
    void testShowJail() throws IOException {
        when(mockPlayer.getPawnX()).thenReturn(91);
        when(mockPlayer.getPawnY()).thenReturn(44);

        PawnController.showJail(mockPlayer);

        verify(mockPlayer).setPawnX(91 - 4 * 22); // Assuming `cellWidth = 22`
        verify(mockPlayer).setPawnY(44);
    }
}