package queryBuilder.Compiler;

import javafx.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Builder {

    Map<String, String> builtQueries;

    public Builder() {
        builtQueries = new HashMap<String, String>();
    }
    public void buildQuery(String name, String q){
        Organizer org = new Organizer();
        StringBuilder sqlQ = new StringBuilder("");

        ArrayList<String> calls = new ArrayList<>(Arrays.asList(q.split(
                "\\).?"
        )));

        for(String call : calls){
            String function = call.split("\\(")[0];
            String args = call.split("\\(")[1];

            org.addCall(function, args);
        }

        for(Pair<Integer,String> call : org.getOrderedCalls()) {
            Method method = null;
            try {
                method = (Method) this.getClass().getDeclaredMethod("build" + Priority.values()[call.getKey()].name(), String.class);
                sqlQ.append( (String) method.invoke(this, call.getValue()) );
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                try {
                    method = (Method) this.getClass().getDeclaredMethod("build" + Priority.values()[call.getKey()].name(), String.class, StringBuilder.class);
                    sqlQ.append( (String) method.invoke(this, call.getValue(), sqlQ ) );
                } catch (NoSuchMethodException noSuchMethodException) {
                    noSuchMethodException.printStackTrace();
                } catch (InvocationTargetException invocationTargetException) {
                    invocationTargetException.printStackTrace();
                } catch (IllegalAccessException illegalAccessException) {
                    illegalAccessException.printStackTrace();
                }
            }
        }

        builtQueries.put(name, sqlQ.toString());
    }

    private String buildQUERY(String params){
        return "SELECT * FROM " + params.replaceFirst("\"", "[").replaceFirst("\"","] ");
    }
    private String buildSELECT(String params, StringBuilder currQ){//var query = new Query("Departments").OrderBy("manager_id").Where("department_id", ">", 50).OrWhere("department_name","like","C%")
        String q = "SELECT " + params.replace("\"", "").replace(",", ", ") + " " + currQ.substring(0).replace("SELECT * ", "");
        currQ.delete(0,currQ.length());
        return q;
    }
    private String buildAVG(String params, StringBuilder currQ){params = params.replace("\"","");//var query = new Query("Departments").OrderBy("manager_id").Where("department_id", ">", 50).OrWhere("department_name","like","C%")
        String q = currQ.toString().replace(
                params.split(",")[1].replace("\"",""),
                "AVG(" + params.split(",")[0].replace("\"","") + ") AS " + params.split(",")[1].replace("\"","") + " "
        );
        currQ.delete(0,currQ.length());
        return q;
    }
    private String buildCOUNT(String params, StringBuilder currQ){params = params.replace("\"","");//var query = new Query("Departments").OrderBy("manager_id").Where("department_id", ">", 50).OrWhere("department_name","like","C%")
        String q = currQ.toString().replace(
                params.split(",")[1].replace("\"",""),
                "COUNT(" + params.split(",")[0].replace("\"","") + ") AS " + params.split(",")[1].replace("\"","") + " "
        );
        currQ.delete(0,currQ.length());
        return q;
    }
    private String buildMIN(String params, StringBuilder currQ){params = params.replace("\"","");//var query = new Query("Departments").OrderBy("manager_id").Where("department_id", ">", 50).OrWhere("department_name","like","C%")
        String q = currQ.toString().replace(
                params.split(",")[1].replace("\"",""),
                "MIN(" + params.split(",")[0].replace("\"","") + ") AS " + params.split(",")[1].replace("\"","") + " "
        );
        currQ.delete(0,currQ.length());
        return q;
    }
    private String buildMAX(String params, StringBuilder currQ){params = params.replace("\"","");//var query = new Query("Departments").OrderBy("manager_id").Where("department_id", ">", 50).OrWhere("department_name","like","C%")
        String q = currQ.toString().replace(
                params.split(",")[1].replace("\"",""),
                "MAX(" + params.split(",")[0].replace("\"","") + ") AS " + params.split(",")[1].replace("\"","") + " "
        );
        currQ.delete(0,currQ.length());
        return q;
    }
    private String buildJOIN(String params){params = params.replace("\"","");//
        return "JOIN " + params.split(",")[0] + " ";
    }
    private String buildON(String params){params = params.replace("\"","");
        return "ON " + params.split(",")[0] + " " + params.split(",")[1] + " " + params.split(",")[2] + " ";
    }
    private String buildWHERE(String params){//var query = new Query("Departments").OrderBy("manager_id").Where("department_id", ">", 50).OrWhere("department_name","like","C%")
        return "WHERE " + params.split(",")[0].replace("\"", "") + " " + params.split(",")[1].replace("\"", "") + " " +(params.split(",")[2].replace("\"","'").contains("-")?
                        "'"+params.split(",")[2].replace("\"","'")+"'" :
                        params.split(",")[2].replace("\"","'").contains("-")) + " ";
    }
    private String buildWHEREINQ(String params){params = params.replace("\"","");
        return "WHERE " + params.split(",")[0] + " IN (" + builtQueries.get(params.split(",")[1]) + ") ";
    }
    private String buildWHEREEQQ(String params){params = params.replace("\"","");
        return "WHERE " + params.split(",")[0] + " = (" + builtQueries.get(params.split(",")[1]) + ") ";
    }
    private String buildWHEREENDSWITH(String params){params = params.replace("\"","");//params.split(",")[0]
        return "WHERE + " + params.split(",")[0] + " like \"%" + params.split(",")[1] + "\" ";
    }
    private String buildWHERESTARTSWITH(String params){params = params.replace("\"","");
        return "WHERE + " + params.split(",")[0] + " like \"" + params.split(",")[1] + "%\" ";
    }
    private String buildWHERECONTAINS(String params){params = params.replace("\"","");
        return "WHERE + " + params.split(",")[0] + " like \"%" + params.split(",")[1] + "%\" ";
    }
    private String buildWHEREBETWEEN(String params){params = params.replace("\"","");
        return "WHERE " + params.split(",")[0] + " BETWEEN " + params.split(",")[1] + " AND " + params.split(",")[2] + " ";
    }
    private String buildWHEREIN(String params){params = params.replace("\"","");
        return "WHERE " + params.split(",")[0] + " IN ";
    }
    private String buildPARAMETARLIST(String params){params = params.replace("\"","");
        return "(" + params + ") ";
    }
    private String buildORWHERE(String params){
        return "OR " + params.split(",")[0].replace("\"", "") + " " + params.split(",")[1].replace("\"", "") + " " + (params.split(",")[2].replace("\"","'").contains("-")?
                        "'"+params.split(",")[2].replace("\"","'")+"'" :
                        params.split(",")[2].replace("\"","'").contains("-")) + " ";
    }
    private String buildANDWHERE(String params){
        System.out.println(params);
        return "AND " + params.split(",")[0].replace("\"", "") + " " + params.split(",")[1].replace("\"", "") + " " +
                (params.split(",")[2].replace("\"","'").contains("-")?
                        "'"+params.split(",")[2].replace("\"","'")+"'" :
                        params.split(",")[2].replace("\"","'"))
                        + " ";
    }
    private String buildGROUPBY(String params){params = params.replace("\"","");
        return "GROUP BY " + params + " ";
    }
    private String buildHAVING(String params){
        return "HAVING " + params.split(",")[0].replace("\"", "") + " " + params.split(",")[1].replace("\"", "") + " " +(params.split(",")[2].replace("\"","'").contains("-")?
                "'"+params.split(",")[2].replace("\"","'")+"'" :
                params.split(",")[2].replace("\"","'").contains("-")) + " ";
    }
    private String buildORHAVING(String params){
        return "OR " + params.split(",")[0].replace("\"", "") + " " + params.split(",")[1].replace("\"", "") + " " +(params.split(",")[2].replace("\"","'").contains("-")?
                "'"+params.split(",")[2].replace("\"","'")+"'" :
                params.split(",")[2].replace("\"","'").contains("-")) + " ";
    }
    private String buildANDHAVING(String params){
        return "AND " + params.split(",")[0].replace("\"", "") + " " + params.split(",")[1].replace("\"", "") + " " +(params.split(",")[2].replace("\"","'").contains("-")?
                "'"+params.split(",")[2].replace("\"","'")+"'" :
                params.split(",")[2].replace("\"","'").contains("-")) + " ";
    }
    private String buildORDERBY(String params){params = params.replace("\"","");
        return "ORDER BY " + params + " ";
    }
    private String buildORDERBYDESC(String params){params = params.replace("\"","");
        return "ORDER BY " + params + "DESC ";
    }
    public String getBuiltQuery(String name){
        return builtQueries.get(name);
    }
}
