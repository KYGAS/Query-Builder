package database;

import resource.data.Row;

import java.util.List;

public interface Database{

    List<Row> readDataFromTable(String tableName);

    List<Row> readDataFromQuery(String tableName, String query);

}
