package pl.aeh.file.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReadExcel {


  private static final String REPLACE_CHARACTERS = "[<>=-().^:, ]";
  private static final String FILE_LOCATION = "C:/Users/a.pazniak/IdeaProjects/1/ARMTEK_OTHER_40019330_202306131514.xlsx";
  @Value("${spring.datasource.url}")
  private String url;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.password}")
  private String password;

  public void readFromExcelFile() throws SQLException, IOException {
    var connection = DriverManager.getConnection(url, username, password);
    Statement stmt = connection.createStatement();

    var fileInputStream = new FileInputStream(FILE_LOCATION);
    var workbook = new XSSFWorkbook(fileInputStream);
    var sheet = workbook.getSheetAt(0);
    var dataFormatter = new DataFormatter();

    var rows = sheet.getLastRowNum();

    for (int i = 1; i <= rows; i++) {
      var row = sheet.getRow(i);
      var brand = row.getCell(0).getStringCellValue();
//      var category = row.getCell(0).getStringCellValue();
      var description = row.getCell(2).getStringCellValue();
      var partNumber = dataFormatter.formatCellValue(row.getCell(4));
      var cost = BigDecimal.valueOf(Double.parseDouble(row.getCell(5).getStringCellValue()));
//      var quantity = Integer.parseInt(row.getCell(6).getStringCellValue().replace(REPLACE_CHARACTERS, ""));
//      var countryOfOrigin = String.valueOf(row.getCell(5));

      stmt.execute(sqlInsertInto(brand, description, partNumber, cost/*, quantity*/));
      stmt.execute("commit");
    }

    workbook.close();
    fileInputStream.close();
    connection.close();
    log.info("File was read and data was saved to the database");
  }

  private String sqlInsertInto(
      final String brand, final String description, final String partNumber, final BigDecimal cost/*, final Integer quantity*/
  ) {
    return "insert into products(brand, part_number, description, quantity, unit_weight) values('" + brand + "', '"
        + partNumber + "', '" + description + /*"', '" + quantity + "', '" + */cost + "', 1.0)";
  }

}
