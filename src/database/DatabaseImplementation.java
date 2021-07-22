package database;

import lombok.AllArgsConstructor;
import lombok.Data;
import resource.data.Row;

import java.util.List;

@Data
@AllArgsConstructor
public class DatabaseImplementation implements Database {

    private Repository repository;


    @Override
    public List<Row> readDataFromTable(String tableName) {
        return repository.get(tableName);
    }

    public List<Row> readDataFromQuery(String tableName, String query) {
        return repository.get(tableName, query);
    }
}
