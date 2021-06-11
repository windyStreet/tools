package com.windy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * @author windyStreet
 * @codetime 2021-05-20 16:03
 */
public class CsvData {
    private String separation = ",";

    public String getSeparation() {
        return separation;
    }

    public void setSeparation(String separation) {
        this.separation = separation;
    }

    private String header;
    private int headerLen;

    public int getKeepFiledCount() {
        return keepFiledCount;
    }

    public void setKeepFiledCount(int keepFiledCount) {
        this.keepFiledCount = keepFiledCount;
    }

    private int keepFiledCount;
    private HashSet<String> sqlSet = new HashSet<>();
    private HashSet<String> valueStrSet = new HashSet<>();
    private String sqlTable = "";

    public HashSet<String> getSqlSet(SqlFile sqlFile) {

        String headerStr = header;
        String table = sqlFile.getSqlTable();
        Iterator<String> iterator = valueStrSet.iterator();

        while (iterator.hasNext()) {
            String valueStr = iterator.next();
            String sql = String.format("INSERT INTO %s (%s) VALUES (%s);\n", table, headerStr, valueStr);
//            String.format("UPDATE %s set x1 = y1 , x2=y2 where ccc=xxx;\n" ,table,)
            sqlSet.add(sql);
        }
        return sqlSet;
    }

    public void setHeader(String header) {

        if (null != header)
            header = header.replaceAll(" ", "");
        
        if (header.contains(separation)) {
            header = header.replace(separation, ",");
        }
        if (header.split(separation).length >=keepFiledCount){
            this.headerLen = keepFiledCount;
            String [] sps = header.split(",");
            this.header = Arrays.stream(sps).collect(Collectors.joining(",")).toString();
        }else{


        this.header = header;
        this.headerLen = header.split(separation).length;
        }
    }

    public void addSqlSet(String lineData) {
        this.addSqlContent(lineData);
    }

    private void addSqlContent(String lineData) {
        // 生成sql语句
        System.out.println(lineData);
        System.out.println();
        if (!lineData.contains(separation))
            return;
        String[] strings = lineData.split("\\"+separation);
        String valuesStr = "'" + Arrays.stream(strings).map(String::trim).collect(Collectors.joining("\',\'")).toString() + "'";
        String extStr = "";
        for (int i = strings.length; i < headerLen; i++) {
            extStr += ",''";
        }
        valuesStr = valuesStr + extStr;
        String return_str = valuesStr.replaceAll("''", "null");
        valueStrSet.add(return_str);
    }

}
