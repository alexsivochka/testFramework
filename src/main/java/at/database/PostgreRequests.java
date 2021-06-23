package at.database;

import at.SimpleConfig;
import at.dao.DocumentDAO;
import at.entity.Document;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.FieldMapper;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.math.BigDecimal;
import java.util.List;

public class PostgreRequests extends DatabaseConnection implements DocumentDAO {

    private final SimpleConfig config = ConfigFactory.create(SimpleConfig.class, System.getProperties());
    private Jdbi jdbi = getConnection(config.dataBaseUrl(), config.userLogin(), config.userPassword())
            .installPlugin(new SqlObjectPlugin());


    @Override
    @Step("Получить из БД данные по всем документам")
    public List<Document> getAll() {
//        jdbi.installPlugin(new SqlObjectPlugin()).onDemand(DocumentDAO.class);
        List<Document> documents = jdbi.withExtension(DocumentDAO.class, dao -> dao.getAll());
        return documents;
    }

    @Override
    public Document getDocByCpCode(String cpCode) {
//        jdbi.installPlugin(new SqlObjectPlugin())
//                .registerRowMapper(FieldMapper.factory(Document.class))
//                .onDemand(DocumentDAO.class);
        Document document = jdbi.withExtension(DocumentDAO.class, dao -> dao.getDocByCpCode(cpCode));
        return document;
    }

    @Step("Получить из БД данные поля bid_price документа с cpcode - {cpCode}")
    public double getDocBidPriceByCpCode(String cpCode) {
        BigDecimal val = (BigDecimal) jdbi.withHandle(handle ->
                handle.registerRowMapper(FieldMapper.factory(Document.class))
                        .createQuery("SELECT * FROM bonds.bondorder WHERE cpcode=:cpcode")
                        .bind("cpcode", cpCode)
                        .mapToMap().one().get("bid_price"));
        return val.doubleValue();
    }
}
