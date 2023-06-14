package pl.aeh.file.controller;

import java.io.IOException;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aeh.file.excel.ReadExcel;

@RequiredArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {

  private final ReadExcel readExcel;

  @GetMapping
  public void readFile() throws SQLException, IOException {
    readExcel.readFromExcelFile();
  }
}
