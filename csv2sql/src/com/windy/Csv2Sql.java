package com.windy;

import java.io.File;
import java.util.HashSet;

/**
 * @author windyStreet
 * @codetime 2021-05-20 15:07
 */


public class Csv2Sql {
    public static void main(String[] args) {

        String tableName = "leapresourcetable";
        String separation = "|";
        int keepFiledCount = 50;

        String projectPath = "E:\\projects\\tools\\csv2sql";

        CsvFileOpt csvFileOpt = new CsvFileOpt();

        File csvFilePath = new File(projectPath + "\\csvfile");
        File[] files = csvFilePath.listFiles();

        assert files != null;
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.endsWith(".csv")) {
                File csvFile = new File(projectPath + "\\csvfile\\" + fileName);
                String sqlFileName = fileName.replace(".csv", ".sql");
                File outFile = new File(projectPath + "\\sqlfile\\" + sqlFileName);
                if (outFile.exists()) {
                    outFile.delete();
                }
                SqlFile sqlFile = new SqlFile();
                sqlFile.setSqlFile(outFile);
                sqlFile.setSqlTable(tableName);
                CsvData csvData = csvFileOpt.readCsvFile(csvFile, separation, keepFiledCount);
                HashSet<String> sqlSet = csvData.getSqlSet(sqlFile);
                sqlFile.writeFile(sqlSet);
            }
        }
    }
}
