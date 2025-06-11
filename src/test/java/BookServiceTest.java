import com.gitforgits.GitforGitsApplication;
import com.gitforgits.repository.BookDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest(classes  = GitforGitsApplication.class)
public class BookServiceTest {
    @MockBean
    private BookDataSource bookDataSource;

    @Test
    public void testGetBookById() {
        when(bookDataSource.findBookById(1L)).thenReturn("Mocked Title");
    }
}
