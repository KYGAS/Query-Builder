package queryBuilder.Validator;

import java.util.ArrayList;

public class Rules {

    private String[] declarationKeywords = {
            "var",
            "let",
            "string"
    };

    private String[] allowedOperations = {
            "="
    };

    private String[] allocationKeywords = {
            "new"
    };

    private String[] logicalOperators = {
                         ">",
                         "<",
                         ">=",
                         "<=",
                         "=",
                         "<>",
                         "like"
    };

    private final ArrayList<String> queryTypes = new ArrayList<String>(){{
        add("string");
    }};

    private final int queryLength = queryTypes.size();

    private final ArrayList<String> selectTypes = new ArrayList<String>(){{//VARYING LENGTH v
        add("string");
    }};

    private final int selectLength = Integer.MAX_VALUE;

    private final ArrayList<String> orderByTypes = new ArrayList<String>(){{
        add("string");
    }};

    private final int orderByLength = Integer.MAX_VALUE;

    private final ArrayList<String> orderByDescTypes = new ArrayList<String>(){{
        add("string");
    }};

    private final int orderByDescLength = Integer.MAX_VALUE;// varying length ^

    private final ArrayList<String> whereTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("var");
    }};

    private final int whereLength = whereTypes.size();

    private final ArrayList<String> orWhereTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("var");
    }};

    private final int orWhereLength = orWhereTypes.size();

    private final ArrayList<String> andWhereTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("var");
    }};

    private final int andWhereLength = andWhereTypes.size();

    private final ArrayList<String> whereBetweenTypes = new ArrayList<String>(){{
        add("string");
        add("number");
        add("number");
    }};

    private final int whereBetweenLength = whereBetweenTypes.size();

    private final ArrayList<String> whereInTypes = new ArrayList<String>(){{
        add("string");
    }};

    private final int whereInLength = whereInTypes.size();

    private final ArrayList<String> parametarListTypes = new ArrayList<String>(){{
        add("string");
        add("number");
    }};

    private final int parametarListLength = Integer.MAX_VALUE; // varying lengthx

    private final ArrayList<String> joinTypes = new ArrayList<String>(){{
        add("string");
    }};

    private final int joinLength = joinTypes.size();


    private final ArrayList<String> onTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("string");
    }};

    private final int onLength = onTypes.size();

    private final ArrayList<String> whereEndsWithTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int whereEndsWithLength = whereEndsWithTypes.size();

    private final ArrayList<String> whereStartsWithTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int whereStartsWithLength = whereStartsWithTypes.size();

    private final ArrayList<String> whereContainsTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int whereContainsLength = whereContainsTypes.size();

    private final ArrayList<String> avgTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int avgLength = avgTypes.size();

    private final ArrayList<String> countTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int countLength = countTypes.size();


    private final ArrayList<String> minTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int minLength = minTypes.size();


    private final ArrayList<String> maxTypes = new ArrayList<String>(){{
        add("string");
        add("string");
    }};

    private final int maxLength = maxTypes.size();


    private final ArrayList<String> groupByTypes = new ArrayList<String>(){{
        add("string");
    }};

    private final int groupByLength = Integer.MAX_VALUE;


    private final ArrayList<String> havingTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("var");
    }};

    private final int havingLength = havingTypes.size();

    private final ArrayList<String> andHavingTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("var");
    }};

    private final int andHavingLength = andHavingTypes.size();

    private final ArrayList<String> orHavingTypes = new ArrayList<String>(){{
        add("string");
        add("string");
        add("var");
    }};

    private final int orHavingLength = orHavingTypes.size();


    private final ArrayList<String> whereInQTypes = new ArrayList<String>(){{
        add("string");
        add("query");
    }};

    private final int whereInQLength = whereInQTypes.size();

    private final ArrayList<String> whereEqQTypes = new ArrayList<String>(){{
        add("string");
        add("query");
    }};

    private final int whereEqQLength = whereEqQTypes.size();

    public Rules() {

    }

    public String[] getDeclarationKeywords(){
        return declarationKeywords;
    }

    public String[] getAllowedOperations(){
        return allowedOperations;
    }

    public String[] getAllocationKeywords(){
        return allocationKeywords;
    }

    public ArrayList<String> getQueryTypes(){
        return  queryTypes;
    }

    public int getQueryLength() {
        return queryLength;
    }

    public ArrayList<String> getSelectTypes() {
        return selectTypes;
    }

    public int getSelectLength() {
        return selectLength;
    }

    public ArrayList<String> getOrderByTypes() {
        return orderByTypes;
    }

    public int getOrderByLength() {
        return orderByLength;
    }

    public ArrayList<String> getOrderByDescTypes() {
        return orderByDescTypes;
    }

    public int getOrderByDescLength() {
        return orderByDescLength;
    }

    public ArrayList<String> getWhereTypes() {
        return whereTypes;
    }

    public int getWhereLength() {
        return whereLength;
    }

    public ArrayList<String> getOrWhereTypes() {
        return orWhereTypes;
    }

    public int getOrWhereLength() {
        return orWhereLength;
    }

    public ArrayList<String> getAndWhereTypes() {
        return andWhereTypes;
    }

    public int getAndWhereLength() {
        return andWhereLength;
    }

    public ArrayList<String> getWhereBetweenTypes() {
        return whereBetweenTypes;
    }

    public int getWhereBetweenLength() {
        return whereBetweenLength;
    }

    public ArrayList<String> getWhereInTypes() {
        return whereInTypes;
    }

    public int getWhereInLength() {
        return whereInLength;
    }

    public ArrayList<String> getParametarListTypes() {
        return parametarListTypes;
    }

    public int getParametarListLength() {
        return parametarListLength;
    }

    public ArrayList<String> getJoinTypes() {
        return joinTypes;
    }

    public int getJoinLength() {
        return joinLength;
    }

    public ArrayList<String> getOnTypes() {
        return onTypes;
    }

    public int getOnLength() {
        return onLength;
    }

    public ArrayList<String> getWhereEndsWithTypes() {
        return whereEndsWithTypes;
    }

    public int getWhereEndsWithLength() {
        return whereEndsWithLength;
    }

    public ArrayList<String> getWhereStartsWithTypes() {
        return whereStartsWithTypes;
    }

    public int getWhereStartsWithLength() {
        return whereStartsWithLength;
    }

    public ArrayList<String> getWhereContainsTypes() {
        return whereContainsTypes;
    }

    public int getWhereContainsLength() {
        return whereContainsLength;
    }

    public ArrayList<String> getAvgTypes() {
        return avgTypes;
    }

    public int getAvgLength() {
        return avgLength;
    }

    public ArrayList<String> getCountTypes() {
        return countTypes;
    }

    public int getCountLength() {
        return countLength;
    }

    public ArrayList<String> getMinTypes() {
        return minTypes;
    }

    public int getMinLength() {
        return minLength;
    }

    public ArrayList<String> getMaxTypes() {
        return maxTypes;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public ArrayList<String> getGroupByTypes() {
        return groupByTypes;
    }

    public int getGroupByLength() {
        return groupByLength;
    }

    public ArrayList<String> getHavingTypes() {
        return havingTypes;
    }

    public int getHavingLength() {
        return havingLength;
    }

    public ArrayList<String> getAndHavingTypes() {
        return andHavingTypes;
    }

    public int getAndHavingLength() {
        return andHavingLength;
    }

    public ArrayList<String> getOrHavingTypes() {
        return orHavingTypes;
    }

    public int getOrHavingLength() {
        return orHavingLength;
    }

    public ArrayList<String> getWhereInQTypes() {
        return whereInQTypes;
    }

    public int getWhereInQLength() {
        return whereInQLength;
    }

    public ArrayList<String> getWhereEqQTypes() {
        return whereEqQTypes;
    }

    public int getWhereEqQLength() {
        return whereEqQLength;
    }

    public String[] getLogicalOperators() {
        return logicalOperators;
    }
}
