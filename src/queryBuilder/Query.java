package queryBuilder;

import errorHandler.ErrorType;
import errorHandler.Handler;
import gui.MainFrame;
import queryBuilder.Compiler.Builder;
import queryBuilder.Validator.Checker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Query {
    private String query;

    public Query(String query) {
        this.query = query;
    }

    public boolean validateQuery(){

        boolean valid = true;
        Checker checker = new Checker();

        Handler errorHandler = Handler.getInstance();
        MainFrame.getInstance().setErrorHandler(errorHandler);

        String[] queries = query.split(";");

        for(String s : queries) {
            String[] parts = s.split("\\s+");
            if(parts.length == 0 || parts[0].equals("")) {
                valid = false;
                errorHandler.addError( ErrorType.NODATA, "", "");
            }else if(!checker.declaration( parts[0] )){
                valid = false;
                errorHandler.addError( ErrorType.NOTAVALIDTYPE, parts[0], "let/var/string");
            }
            if(parts.length < 2) {
                valid = false;
                errorHandler.addError( ErrorType.NONAME,"","");
            }
            else if(!checker.naming( parts[1] )){
                valid = false;
                errorHandler.addError( ErrorType.NOTAVALIDNAME,parts[1], "iskljucivo od brojeva, donje crte i slova. I mogu pocinjati iskljucivo slovima ili donjom crtom! (Ne smeju biti reservisane reci)");
            }
            if(parts.length < 3) {
                valid = false;
                errorHandler.addError( ErrorType.NOOPERATION,"","");
            }
            else if(!checker.operation( parts[2] )){
                valid = false;
                errorHandler.addError( ErrorType.NOTAVALIDOPERATION,parts[2],"=" );
            }
            if(parts.length < 4) {
                valid = false;
                errorHandler.addError( ErrorType.NOALLOCATION,"","");
            }
            else if(!checker.allocation( parts[3] )){
                valid = false;
                errorHandler.addError( ErrorType.NOTAVALIDKEYWORD, parts[3],"new" );
            }
            if(parts.length < 5){
                valid = false;
                errorHandler.addError( ErrorType.NOQUERY,"","");
            }else{ // Query("abc").Where("department_id", ">", 50).OrWhere("department_name", "like", "C%");
                // .split(").")
                /*
                *
                * Query("abc"
                * Where("department_id", ">", 50
                *
                * */
                String tmp = "";
                for(int i = 4; i < parts.length; i++){
                    tmp += parts[i];
                    tmp += " ";
                }
                String[] calls = tmp.split("\\).");

                for(String call : calls){
                    System.out.println(call);
                    List<String> segments = Arrays.asList(call.split("\\(" ) );
                    System.out.println(segments);

                    if(segments.size() == 1) {
                        valid = false;
                        errorHandler.addError( ErrorType.UNHANDLED ,segments.get(0),"");
                        continue;
                    }

                    try {
                        Method method = (Method) checker.getClass().getDeclaredMethod(segments.get(0), String.class);
                        switch((int) method.invoke(checker, segments.get(1))){
                            case 1:
                                valid = valid;
                                break;
                            case 2://neispravan broj argumenata
                                valid = false;
                                errorHandler.addError( ErrorType.INVALIDARGUMENTLENGTH, segments.get(0),"");
                                break;
                            case 3://neispravan tip argumenata
                                valid = false;
                                errorHandler.addError( ErrorType.INVALIDARGUMENTTYPE, segments.get(0),"");
                                break;
                            default:
                        }

                    } catch (NoSuchMethodException e) {
                        valid = false;
                        errorHandler.addError( ErrorType.NOTAFUNCTION,segments.get(0),"");
                    } catch (InvocationTargetException e) {
                        valid = false;
                        errorHandler.addError( ErrorType.UNHANDLED,segments.get(0),"");
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        valid = false;
                        errorHandler.addError( ErrorType.UNHANDLED,segments.get(0),"");
                        e.printStackTrace();
                    }

                }
                if(valid){
                    valid = checker.validateCustomRules(s, errorHandler);
                }
                checker.declaredQueries.add(parts[1]);
            }

        }


        if(valid){
            System.out.println("poggies");
            return valid;
        }
        else {
            errorHandler.notifyErrorStack();
            return valid;
        }
    }


    public String compileQuery(){//let x   = new   Query("uwu");
        //String[] parts = s.split("\\s+");
        //parts[4]

        Builder x = new Builder();

        String[] queries = query.split(";");
        for(String s : queries) {

            String name = s.split(
                    "\\s+"
            )[1];
            String Qu = s.split(
                    "\\s+"
            )[4];

            for(int i = 5; i < s.split(
                    "\\s+"
            ).length; i++){
                Qu += s.split(
                        "\\s+"
                )[i];
            }

            x.buildQuery(
                    name,
                    Qu
            );
        }
        return x.getBuiltQuery(
                queries[
                        queries.length-1
                        ].split(
                                "\\s+"
                        )[1]
        );
    }


    /*

    prosledi

    validiraj

    kompajluj

    pozovi nad bazom

     */
}
