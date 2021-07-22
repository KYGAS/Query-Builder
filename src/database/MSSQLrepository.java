package database;

import database.settings.Settings;
import errorHandler.ErrorType;
import errorHandler.Handler;
import lombok.Data;
import resource.data.Row;

import java.sql.*;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

@Data
public class MSSQLrepository implements Repository{

    private Settings settings;
    private Connection connection;

    public MSSQLrepository(Settings settings) {
        this.settings = settings;
    }

    private void initConnection() throws SQLException, ClassNotFoundException{
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        String ip = (String) settings.getParameter("mssql_ip");
        String database = (String) settings.getParameter("mssql_database");
        String username = (String) settings.getParameter("mssql_username");
        String password = (String) settings.getParameter("mssql_password");
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/"+database,username,password);
    }

    private void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            connection = null;
        }
    }

    public ResultSetMetaData getDB(){

        String query = "SELECT * FROM *";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            return rs.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }//let x = new Query("uwu");

    @Override
    public List<Row> get(String from) {

        List<Row> rows = new ArrayList<>();

        try{
            this.initConnection();

            String query = "SELECT * FROM " + from;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Row row = new Row();
                row.setName(from);

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }

    public List<Row> get(String from, String query) {

        List<Row> rows = new ArrayList<>();

        try{
            this.initConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Row row = new Row();
                row.setName(from);

                ResultSetMetaData resultSetMetaData = rs.getMetaData();
                for (int i = 1; i<=resultSetMetaData.getColumnCount(); i++){//var a = new Query("job_history").Select("start_date", "end_date").Where("start_date", "<", 2003-06-17)
                    if(resultSetMetaData.getColumnType(i) == 93){//yyyy-mm-dd time dd/mm/yyyy
                        String date =  new StringBuilder( (new StringBuilder(rs.getString(i).split(" ")[0]).reverse().toString().replace("-","/").split("/")[0]) ).reverse().toString() + "/" +
                                new DateFormatSymbols().getMonths()[Integer.parseInt(
                                        new StringBuilder( (new StringBuilder(rs.getString(i).split(" ")[0]).reverse().toString().replace("-","/").split("/")[1]) ).reverse().toString()
                                )-1].substring(0,3) + "/" +
                                new StringBuilder( (new StringBuilder(rs.getString(i).split(" ")[0]).reverse().toString().replace("-","/").split("/")[2]) ).reverse().toString();
                        row.addField(
                                resultSetMetaData.getColumnName(i),
                                date
                        );
                    }
                    else row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        }
        catch (Exception e) {
            System.out.println("test");
            Handler errorHandler = Handler.getInstance();
            errorHandler.addError(ErrorType.EMPTYQUERY, query,"");
            errorHandler.notifyErrorStack();
        }
        finally {
            this.closeConnection();
        }

        return rows;
    }
}
