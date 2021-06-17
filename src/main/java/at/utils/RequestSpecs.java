package at.utils;


import at.SimpleConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.log4j.Log4j;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.specification.ProxySpecification.host;

@Log4j
public class RequestSpecs {

    SimpleConfig config = ConfigFactory.create(SimpleConfig.class, System.getProperties());

    public RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setProxy(host("proxy-2.pbank.com.ua").withPort(8080).withAuth(config.userLogin(), config.userPassword()))
                .build();
    }

    public ResponseSpecification responseSpecSuccess() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

}
