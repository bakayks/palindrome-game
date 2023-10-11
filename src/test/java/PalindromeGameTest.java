import org.example.exception.CustomGameException;
import org.example.model.User;
import org.example.service.PalindromeService;
import org.example.service.PalindromeServiceImpl;
import org.example.service.UserPointServiceImpl;
import org.example.service.UserPointsService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalindromeGameTest {
    private PalindromeService palindromeService;
    private UserPointsService userPointsService;

    @Before
    public void setUp() {
        palindromeService = new PalindromeServiceImpl();
        userPointsService = new UserPointServiceImpl();
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindromeService.isPalindrome("level"));
        assertTrue(palindromeService.isPalindrome("radar"));
        assertFalse(palindromeService.isPalindrome("hello"));
    }

    @Test
    public void testSubmitPalindrome() throws CustomGameException {
        User user1 = new User("Bakai");

        int points = palindromeService.submitPalindrome(user1, "level");
        assertEquals(5, points);

        points = palindromeService.submitPalindrome(user1, "radar");
        assertEquals(5, points);
    }

    @Test
    public void testLeaderboard() throws CustomGameException {
        User user1 = new User("Bakai");
        User user2 = new User("Jeckie Chan");

        userPointsService.addNewUser(user1);
        userPointsService.addNewUser(user2);

        userPointsService.addPoints(user1, palindromeService.submitPalindrome(user1, "level")); //5 points
        userPointsService.addPoints(user1, palindromeService.submitPalindrome(user1, "radar")); //5 points
        userPointsService.addPoints(user1, palindromeService.submitPalindrome(user1, "lol")); //3 points
        userPointsService.addPoints(user1, palindromeService.submitPalindrome(user1, "tacocat")); //7 points

        userPointsService.addPoints(user2, palindromeService.submitPalindrome(user2, "level")); //5 points
        userPointsService.addPoints(user2, palindromeService.submitPalindrome(user2, "lol")); //3 points

        var users = userPointsService.getLeaderboard().toArray();

        var firstPlace = (User) users[0];
        assertEquals(20, firstPlace.getPoints().intValue());
        assertEquals("Bakai", firstPlace.getName());



        var secondPlace = (User) users[1];
        assertEquals(8, secondPlace.getPoints().intValue());
        assertEquals("Jeckie Chan", secondPlace.getName());
    }
}