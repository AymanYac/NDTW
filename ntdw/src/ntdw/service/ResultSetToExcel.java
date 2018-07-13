package ntdw.service;

import org.apache.commons.lang.exception.NestableException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;

public class ResultSetToExcel {

  private HSSFWorkbook workbook;
  private HSSFSheet sheet;
  private HSSFFont boldFont;
  private HSSFDataFormat format;
  private ResultSet resultSet;
  private FormatType[] formatTypes;
  
  public static final String DATA_FORMAT = "dataFormat";
  public static final String FILL_BACKGROUND_COLOR = "fillBackgroundColor";
  public static final String FILL_FOREGROUND_COLOR = "fillForegroundColor";
  public static final String FILL_PATTERN = "fillPattern";
  public static final boolean BOLDWEIGHT_BOLD = true;
  public static final String SOLID_FOREGROUND = "solidForeground";

  public ResultSetToExcel(ResultSet resultSet, FormatType[] formatTypes, String sheetName) {
    workbook = new HSSFWorkbook();
    this.resultSet = resultSet;
    sheet = workbook.createSheet(sheetName);
    boldFont = workbook.createFont();
    boldFont.setBold(BOLDWEIGHT_BOLD);
    format = workbook.createDataFormat();
    this.formatTypes = formatTypes;
  }

  public ResultSetToExcel(ResultSet resultSet, String sheetName) {
    this(resultSet, null, sheetName);
  }

  private FormatType getFormatType(Class _class) {
    if (_class == Integer.class || _class == Long.class) {
      return FormatType.INTEGER;
    } else if (_class == Float.class || _class == Double.class) {
      return FormatType.FLOAT;
    } else if (_class == Timestamp.class || _class == java.sql.Date.class) {
      return FormatType.DATE;
    } else {
      return FormatType.TEXT;
    }
  }

  public void generate(OutputStream outputStream) throws Exception {
    try {
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
      if (formatTypes != null && formatTypes.length != resultSetMetaData.getColumnCount()) {
        throw new IllegalStateException("Number of types is not identical to number of resultset columns. " +
            "Number of types: " + formatTypes.length + ". Number of columns: " + resultSetMetaData.getColumnCount());
      }

      int currentRow = 0;
      HSSFRow row = sheet.createRow(currentRow);
      int numCols = resultSetMetaData.getColumnCount();
      boolean isAutoDecideFormatTypes;
      if (isAutoDecideFormatTypes = (formatTypes == null)) {
        formatTypes = new FormatType[numCols];
      }

      for (int i = 0; i < numCols; i++) {
        String title = resultSetMetaData.getColumnName(i + 1);
        writeCell(row, i, title, FormatType.TEXT, boldFont);
        if (isAutoDecideFormatTypes) {
          Class _class = Class.forName(resultSetMetaData.getColumnClassName(i + 1));
          formatTypes[i] = getFormatType(_class);
        }
      }

      currentRow++;

      // Write report rows
      while (resultSet.next()) {
        row = sheet.createRow(currentRow++);
        for (int i = 0; i < numCols; i++) {
          Object value = resultSet.getObject(i + 1);
          writeCell(row, i, value, formatTypes[i]);
        }
      }

      // Autosize columns
      for (int i = 0; i < numCols; i++) {
        sheet.autoSizeColumn((short) i);
      }

      workbook.write(outputStream);
    }
    finally {
      outputStream.close();
    }
  }

  public void generate(File file) throws Exception {
    generate(new FileOutputStream(file));
  }

  private void writeCell(HSSFRow row, int col, Object value, FormatType formatType) throws NestableException {
    writeCell(row, col, value, formatType, null, null);
  }

  private void writeCell(HSSFRow row, int col, Object value, FormatType formatType, HSSFFont font) throws NestableException {
    writeCell(row, col, value, formatType, null, font);
  }

  private void writeCell(HSSFRow row, int col, Object value, FormatType formatType,
                         Short bgColor, HSSFFont font) throws NestableException {
    Cell cell = CellUtil.createCell(row, col, null);
    if (value == null) {
      return;
    }

    if (font != null) {
      HSSFCellStyle style = workbook.createCellStyle();
      style.setFont(font);
      cell.setCellStyle(style);
    }

    switch (formatType) {
      case TEXT:
        cell.setCellValue(value.toString());
        break;
      case INTEGER:
        cell.setCellValue(((Number) value).intValue());
        CellUtil.setCellStyleProperty(cell, DATA_FORMAT,
            HSSFDataFormat.getBuiltinFormat(("#,##0")));
        break;
      case FLOAT:
        cell.setCellValue(((Number) value).doubleValue());
        CellUtil.setCellStyleProperty(cell, DATA_FORMAT,
            HSSFDataFormat.getBuiltinFormat(("#,##0.00")));
        break;
      case DATE:
        cell.setCellValue((Date) value);
        CellUtil.setCellStyleProperty(cell, DATA_FORMAT,
            HSSFDataFormat.getBuiltinFormat(("m/d/yy")));
        break;
      case MONEY:
        cell.setCellValue(((Number) value).intValue());
        CellUtil.setCellStyleProperty(cell, DATA_FORMAT, format.getFormat("($#,##0.00);($#,##0.00)"));
        break;
      case PERCENTAGE:
        cell.setCellValue(((Number) value).doubleValue());
        CellUtil.setCellStyleProperty(cell, DATA_FORMAT, HSSFDataFormat.getBuiltinFormat("0.00%"));
    }

    if (bgColor != null) {
      CellUtil.setCellStyleProperty(cell, FILL_FOREGROUND_COLOR, bgColor);
      CellUtil.setCellStyleProperty(cell, FILL_PATTERN, SOLID_FOREGROUND);
    }
  }

  public enum FormatType {
    TEXT,
    INTEGER,
    FLOAT,
    DATE,
    MONEY,
    PERCENTAGE
  }
}