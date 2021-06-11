package com.windy;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author windyStreet
 * @codetime 2021-05-20 16:08
 */
public class SqlFile {

    private String sqlTable;
    private String sql;
    private File sqlFile;

    public SqlFile() {
    }

    public SqlFile(File sqlFile) {
        this.sqlFile = sqlFile;
    }


    public String getSqlTable() {
        return sqlTable;
    }

    public void setSqlTable(String sqlTable) {
        this.sqlTable = sqlTable;
    }


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public File getSqlFile() {
        return sqlFile;
    }

    public void setSqlFile(File sqlFile) {
        this.sqlFile = sqlFile;
    }

    public void writeFile(HashSet<String> sqlSet) {
        System.out.println(sqlFile);
        //写入文件
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(sqlFile, true);
            Iterator<String> iterator = sqlSet.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                fileWriter.write(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
