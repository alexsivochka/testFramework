package at.entity;

import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class Document {

    @ColumnName("cpcode")
    private String cpCode;
    private String type;
    private LocalDate maturity;
    private String currency;
    @ColumnName("bid_price")
    private double bidPrice;
    @ColumnName("bid_yield")
    private int bidYield;
    @ColumnName("ask_price")
    private double askPrice;
    @ColumnName("ask_yield")
    private int askYield;
    private String yield;
    @ColumnName("datmod")
    private Timestamp dateMod;
    private LocalDate date;

}
