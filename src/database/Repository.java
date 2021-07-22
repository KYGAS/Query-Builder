package database;

import resource.data.Row;

import java.util.List;

public interface Repository {

    List<Row> get(String from);
    List<Row> get(String from, String query);
}
