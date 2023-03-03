import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    Board board;

    @BeforeAll
    public static void beforeBoardTest(){
        System.out.println("All started");
    }
    @AfterAll
    public static void afterBoardTest(){
        System.out.println("All finished");
    }

    @BeforeEach
    public void beforeTest(){
        board = new Board();
        System.out.println("a small test is started");}
    @AfterEach
    public void afterTest(){ System.out.println("a small test is completed");}

    @Test
    //@RepeatedTest(value = 3)
    public void dotInitializeTest(){
        assertEquals(3,board.getDots());
    }
    @Test
    public void loadImageTest(){
        assertNotNull(board.getApple());
        assertThat(board.getBall(),instanceOf(Image.class));
        assertThat(board.getHead(),instanceOf(Image.class));
    }
    @Test
    public void eatingAppleTest(){
        int initial_dots =board.getDots();
        board.setApple_x(board.getXsnake()[0]);
        board.setApple_y(board.getYsnake()[0]);
        board.checkApple();
        assertNotEquals(initial_dots,board.getDots());
    }

    @ParameterizedTest
    @CsvSource({
            "-10, 0",  //x[0] < 0
            "310, 0",  //x[0] >= B_WIDTH
            "0, -10",  //y[0] < 0
            "0, 310",  //y[0] >= B_HEIGHT
    })
    public void checkCollisionTest(int x, int y) {
        board.locateSnake(x,y);
        board.checkCollision();
        assertFalse(board.isInGame());
    }
}

