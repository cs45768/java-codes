package annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangchunhui on 2018/7/23.
 */
public class TableCreator {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        System.out.println("----------------");
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if (dbTable == null) {
                System.out.println("No DBTable annotations in class " + className);
                continue;
            }
            String tableName = dbTable.name();
            if (tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if (anns.length < 1) {
                    continue;
                }
                if (anns[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if (sInt.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.contraints()));
                }
                if (anns[0] instanceof SQLString) {
                    SQLString sqlString = (SQLString) anns[0];
                    if (sqlString.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sqlString.name();
                    }
                    columnDefs.add(columnName + " VARCHAR(" + sqlString.value() + ")" + getConstraints(sqlString.contraints()));
                }
                if (anns[0] instanceof SQLDouble) {
                    SQLDouble sqlDouble = (SQLDouble) anns[0];
                    if (sqlDouble.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sqlDouble.name();
                    }
                    columnDefs.add(columnName + " decimal(" + sqlDouble.size()+","+sqlDouble.d() + ")" + getConstraints(sqlDouble.contraints()));
                }
                if (anns[0] instanceof SQLDate) {
                    SQLDate sqlDate = (SQLDate) anns[0];
                    if (sqlDate.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sqlDate.name();
                    }
                    columnDefs.add(columnName + " datetime "+ getConstraints(sqlDate.contraints()));
                }
                StringBuilder creatCommand = new StringBuilder("CREATE TABLE " + tableName + "(");
                for (String colemnDef : columnDefs) {
                    creatCommand.append("\n   " + colemnDef + ",");
                }
                String tableCreate = creatCommand.substring(0, creatCommand.length() - 1) + ");";
                System.out.println("Table Creation SQL for " + tableName + " is :\n" + tableCreate);
            }
        }
    }

    private static String getConstraints(Constraints con) {
        String constrains = "";
        if (!con.allowNull()) {
            constrains += " NOT NULL";
        }
        if (con.primaryKey()) {
            constrains += " PRIMARY KEY";
        }
        if (con.unique()) {
            constrains += " UNIQUE";
        }
        return constrains;
    }
}
