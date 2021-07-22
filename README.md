##  Query-Builder

The mostly finalized version of a university project. MSSQL Database Query Builder!

##  How To Use

Simply download and compile the code.
You will get 2 *boxes* and a button.
In the upper box simply add the query you want to have compiled.

Functions :
- Queried table: new Query(table_name), equivalent SELECT * FROM [table_name]
- Projection: .select(column_list)
- Sorting: .OrderBy(column_list), .OrderByDesc(column_list)
- Filtering: .Where(column_name, operator, criteria), .OrWhere(column_name, operator, criteria), .AndWhere(column_name, operator, criteria), .WhereBetween(column_name, int1, int2), .WhereIn(column_name).ParametarList(p1,p2,p3,p4, …, pn)
- Table merging: .Join(table_name).On(column_name1, operator, column_name2)
- String Operations: .WhereEndsWith(column_name, pattern),  .WhereStartsWith(column_name, pattern),  .WhereContains(column_name, pattern)
- Agregation functions: .Avg(column_name, alias), .Count(column_name, alias), .Min(column_name,alias), .Max(column_name, alias), .GroupBy(column_list), .Having(alias, operator, criteria, ), .AndHaving(alias, operator, criteria), .OrHaving(alias, operator, criteria).
- Subqueries: .WhereInQ(column_name, query)., .WhereEqQ(column_name, query)

*Example*

- > var a = new Query("job_history").Select("employee_id")
- > var b = new Query("jobs").Select("job_title","prosecnaPlata").Avg("salary","prosecnaPlata").GroupBy("job_title").Join("jobs").On("jobs.job_id","=","employees.job_id").WhereInQ("employee_id", a)
 
 Equivalent
> select job_title, avg(salary) as “prosecnaPlata” from jobs join employees using (job_id) where employee_id in (select employee_id from job_history) group by job_title



Project SDK : 1.8.0_211
Project language level: 8
These are tested values and work for me, I had a lot of issues with this since... Java...
