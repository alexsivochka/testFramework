package at.dao;

import at.entity.Document;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.config.RegisterFieldMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(Document.class)
@RegisterFieldMapper(Document.class)
public interface DocumentDAO {

    @SqlQuery("SELECT * FROM bonds.bondorder")
    List<Document> getAll();

    @SqlQuery("SELECT * FROM bonds.bondorder WHERE cpcode=:cpcode")
    Document getDocByCpCode(@Bind("cpcode") String cpCode);

}
