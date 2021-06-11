package com.windy;

import java.io.*;

/**
 * @author windyStreet
 * @codetime 2021-05-20 15:07
 */
public class  CsvFileOpt {


    public CsvData readCsvFile(File file,String separation,int keepFiledCount) {
        int line_num = 1;
        CsvData csvData = new CsvData();
        csvData.setSeparation(separation);
        csvData.setKeepFiledCount(keepFiledCount);
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (1 == line_num) {
                    // 记录header
                    csvData.setHeader(line);
                } else {
                    //解析数据
                    csvData.addSqlSet(line);
                }
                line_num++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvData;
    }
}
