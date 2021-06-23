package at;

import org.aeonbits.owner.Config;
import static org.aeonbits.owner.Config.*;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "classpath:config.properties"
})

public interface SimpleConfig extends Config {

    String browser();
    String remoteBrowserVersion();
    String homePage();
    String totp_Url();
    String docApiUrl();
    String userLogin();
    String userPassword();
    String clientFullName();
    String clientInn();
    String dataBaseUrl();


}
