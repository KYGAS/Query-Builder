package queryBuilder.Validator;

import errorHandler.Handler;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


public class Checker {

    private Rules rules = new Rules();

    public ArrayList<String> declaredQueries;

    public Checker() {
        declaredQueries = new ArrayList<String>();
    }

    private ArrayList<String> getTypes(String t){
        ArrayList<String> ret = new ArrayList<String>();
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        //let x = new Query("owo").Where("uwu",">",5);

        for(String typ : t.split(",\\s*") ) {
            try {
                if (declaredQueries.contains(typ)) {
                    ret.add("query");
                } else if(typ.contains("Jan") || typ.contains("Feb") || typ.contains("Mar") || typ.contains("Apr") ||
                        typ.contains("Maj") || typ.contains("Jun") || typ.contains("Jul") || typ.contains("Avg") ||
                        typ.contains("Sep") || typ.contains("Okt") || typ.contains("Nov") || typ.contains("Dec")){
                    ret.add("number");
                } else {
                    System.out.println(typ);
                    Object result = engine.eval("typeof( " + typ + ")");
                    ret.add((String) result);
                }
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public int Query(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return 1;
    }

    public int Select(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if((int) methodL.invoke(rules,null) == Integer.MAX_VALUE){
                if( types.size() == 0) return 2;
                for( String t : types){
                    if( !((ArrayList<String>) methodT.invoke(rules, null)).contains(t) ){
                        return 3;
                    }
                }
            }
            else {
                if (types.size() != (int) methodL.invoke(rules, null)) {
                    return 2;
                }
                if (!types.equals((ArrayList<String>) methodT.invoke(rules, null))) {
                    return 3;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    }

    public int OrderBy(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if((int) methodL.invoke(rules,null) == Integer.MAX_VALUE){
                if( types.size() == 0) return 2;
                for( String t : types){
                    if( !((ArrayList<String>) methodT.invoke(rules, null)).contains(t) ){
                        return 3;
                    }
                }
            }
            else {
                if (types.size() != (int) methodL.invoke(rules, null)) {
                    return 2;
                }
                if (!types.equals((ArrayList<String>) methodT.invoke(rules, null))) {
                    return 3;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int OrderByDesc(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if((int) methodL.invoke(rules,null) == Integer.MAX_VALUE){
                if( types.size() == 0) return 2;
                for( String t : types){
                    if( !((ArrayList<String>) methodT.invoke(rules, null)).contains(t) ){
                        return 3;
                    }
                }
            }
            else {
                if (types.size() != (int) methodL.invoke(rules, null)) {
                    return 2;
                }
                if (!types.equals((ArrayList<String>) methodT.invoke(rules, null))) {
                    return 3;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int Where(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(types.get(0).equals( ( (ArrayList<String> ) methodT.invoke(rules, null)).get(0)) ) {
                if (types.get(1).equals(((ArrayList<String>) methodT.invoke(rules, null)).get(1))) {
                    switch(q.split(",\\s*")[1].replace("\"","")) {
                        case ">":
                        case "<":
                        case ">=":
                        case "<=":
                         //   return types.get(2).equals("number")?1:3;
                        case "=":
                        case "<>":
                            return 1;
                        case "like":
                            return types.get(2).equals("string")?1:3;
                        default:
                            return 3;
                    }

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int OrWhere(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(types.get(0).equals( ( (ArrayList<String> ) methodT.invoke(rules, null)).get(0)) ) {
                if (types.get(1).equals(((ArrayList<String>) methodT.invoke(rules, null)).get(1))) {
                    switch(q.split(",.")[1].replace("\"","")) {
                        case ">":
                        case "<":
                        case ">=":
                        case "<=":
                        //    return types.get(2).equals("number")?1:3;
                        case "=":
                        case "<>":
                            return 1;
                        case "like":
                            return types.get(2).equals("string")?1:3;
                        default:
                            return 3;
                    }

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int AndWhere(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(types.get(0).equals( ( (ArrayList<String> ) methodT.invoke(rules, null)).get(0)) ) {
                if (types.get(1).equals(((ArrayList<String>) methodT.invoke(rules, null)).get(1))) {
                    switch(q.split(",.")[1].replace("\"","")) {
                        case ">":
                        case "<":
                        case ">=":
                        case "<=":
                        //    return types.get(2).equals("number")?1:3;
                        case "=":
                        case "<>":
                            return 1;
                        case "like":
                            return types.get(2).equals("string")?1:3;
                        default:
                            return 3;
                    }

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int WhereBetween(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();

        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int WhereIn(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };


    public int ParametarList(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if((int) methodL.invoke(rules,null) == Integer.MAX_VALUE){
                if( types.size() == 0) return 2;
                for( String t : types){
                    if( !((ArrayList<String>) methodT.invoke(rules, null)).contains(t) ){
                        return 3;
                    }
                }
            }
            else {
                if (types.size() != (int) methodL.invoke(rules, null)) {
                    return 2;
                }
                if (!types.equals((ArrayList<String>) methodT.invoke(rules, null))) {
                    return 3;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int Join(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int On(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
            //.split(",\\s*")
            if(!Arrays.stream(rules.getLogicalOperators()).anyMatch(
                    q.split(",\\s*")[1].replace("\"","")::equals
            )){
                return 3;
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int WhereEndsWith(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int WhereStartsWith(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int WhereContains(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int Avg(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int Count(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int Min(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int Max(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int GroupBy(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if((int) methodL.invoke(rules,null) == Integer.MAX_VALUE){
                if( types.size() == 0) return 2;
                for( String t : types){
                    if( !((ArrayList<String>) methodT.invoke(rules, null)).contains(t) ){
                        return 3;
                    }
                }
            }
            else {
                if (types.size() != (int) methodL.invoke(rules, null)) {
                    return 2;
                }
                if (!types.equals((ArrayList<String>) methodT.invoke(rules, null))) {
                    return 3;
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public int Having(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(types.get(0).equals( ( (ArrayList<String> ) methodT.invoke(rules, null)).get(0)) ) {
                if (types.get(1).equals(((ArrayList<String>) methodT.invoke(rules, null)).get(1))) {
                    switch(q.split(",.")[1].replace("\"","")) {
                        case ">":
                        case "<":
                        case ">=":
                        case "<=":
                         //   return types.get(2).equals("number")?1:3;
                        case "=":
                        case "<>":
                            return 1;
                        case "like":
                            return types.get(2).equals("string")?1:3;
                        default:
                            return 3;
                    }

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return 1;
    };

    public int AndHaving(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(types.get(0).equals( ( (ArrayList<String> ) methodT.invoke(rules, null)).get(0)) ) {
                if (types.get(1).equals(((ArrayList<String>) methodT.invoke(rules, null)).get(1))) {
                    switch(q.split(",.")[1].replace("\"","")) {
                        case ">":
                        case "<":
                        case ">=":
                        case "<=":
                        //    return types.get(2).equals("number")?1:3;
                        case "=":
                        case "<>":
                            return 1;
                        case "like":
                            return types.get(2).equals("string")?1:3;
                        default:
                            return 3;
                    }

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int OrHaving(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(types.get(0).equals( ( (ArrayList<String> ) methodT.invoke(rules, null)).get(0)) ) {
                if (types.get(1).equals(((ArrayList<String>) methodT.invoke(rules, null)).get(1))) {
                    switch(q.split(",.")[1].replace("\"","")) {
                        case ">":
                        case "<":
                        case ">=":
                        case "<=":
                         //   return types.get(2).equals("number")?1:3;
                        case "=":
                        case "<>":
                            return 1;
                        case "like":
                            return types.get(2).equals("string")?1:3;
                        default:
                            return 3;
                    }

                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int WhereInQ(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return 1;
    };

    public int WhereEqQ(String q){
        ArrayList<String> types = this.getTypes(q);
        String name = new Checker() {}.getClass().getEnclosingMethod().getName();
        try {
            Method methodT = (Method) rules.getClass().getDeclaredMethod("get" + name + "Types", null);
            Method methodL = (Method) rules.getClass().getDeclaredMethod("get" + name + "Length", null);
            if(types.size() != (int) methodL.invoke(rules,null)) {
                return 2;
            }
            if(!types.equals( ( ArrayList<String> ) methodT.invoke(rules, null) ) ) {
                return 3;
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        return 1;
    };

    public boolean declaration(String s){
        for( String keyword : rules.getDeclarationKeywords() ){
            if(s.equals(keyword)) return true;
        }
        return false;
    };

    public boolean naming(String s){
        return (Character.isDigit( s.charAt(0) ) || s.contains("/") || s.contains("-") || s.contains(",") || s.contains(".") || s.contains("\\") || s.contains("*") || s.contains("!") || s.contains("@")||
                s.contains("#") || s.contains("$") || s.contains("%") || s.contains("^") || s.contains("&") || s.contains("(") || s.contains(")") || s.contains("[") || s.contains("]") || s.contains("{") ||
                s.contains("'") || s.contains("\"") || s.contains("|") || s.contains(">") || s.contains("<") || s.contains("?") || s.contains("\n") || s.contains("\t") || s.equals("string") || s.equals("number") ||
                s.equals("var") || s.contains("new") || s.equals("let") || s.contains("=") || s.contains("+")) ?false:true;
    }

    public boolean operation(String s){
        for( String keyword : rules.getAllowedOperations() ){
            if(s.equals(keyword)) return true;
        }
        return false;
    }

    public boolean allocation(String s){
        for( String keyword : rules.getAllocationKeywords() ){
            if(s.equals(keyword)) return true;
        }
        return false;
    }

    public boolean validateCustomRules(String s, Handler errorHandler){



        return true;
    }

}
