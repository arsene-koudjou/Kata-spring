package services;

import com.kata.spring.swagger.SpringBootSwagger3ExampleApplication;
import com.kata.spring.swagger.service.KataService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBootSwagger3ExampleApplication.class)
public class KataServiceTest {

private final KataService kataService = new KataService();

    @Test
    void shouldContainsNumber(){
        int num = 51;

        String res = this.kataService.getCharactersFromString(num);

        Assertions.assertThat(res).contains(String.valueOf(num));
        Assertions.assertThat(res).isNotEmpty();
    }
}
