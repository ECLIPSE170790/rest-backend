package safron.restbackend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import safron.restbackend.domain.UserInfo;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;

public class BankControllerTest {

    static {
        RestAssured.baseURI = "http://localhost:8080";
    }

    private RequestSpecification spec =
            with()
            .baseUri("http://localhost:8080")
            .basePath("/");

    @Test
    void bankControllerTest() {
        UserInfo[] userInfos = spec.get("user/getAll")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .as(UserInfo[].class);
        //System.out.println();

        Stream.of(userInfos)
                .filter(userInfo -> userInfo.getUserName().equals("Dima"))
                .peek(userInfo -> System.out.println(userInfo.getUserName()))
                .map(userInfo -> userInfo.toString())
                .collect(Collectors.toList());
    }
}
