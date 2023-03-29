package helper;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pages.Prebilling_processing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Read_write_Excel
{
    static String uname;
    static String pwd;
    static XSSFWorkbook wb;
    public void read_users() throws Exception
    {
        File src= new File("C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\testdata\\Permissions.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.println("************ Loading Data From Excel *******************");
        XSSFSheet st=wb.getSheet("Users");
        int i =st.getLastRowNum();
        for(int j=1;j<i;j++)
        {
            String uname=st.getRow(j).getCell(0).getStringCellValue();
            String pwd =st.getRow(j).getCell(1).getStringCellValue();
            System.out.println("Username and password is "+uname+" and " +pwd );
        }
        wb.close();
    }

    public void read_DBI_Permission() throws Exception
    {
        Boolean result;
        Prebilling_processing PB= new Prebilling_processing();
        File src= new File("C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\testdata\\DBI.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.println("************ Loading Data From Excel *******************");
        XSSFSheet st=wb.getSheet("DBI");
        int i =st.getLastRowNum();
        System.out.println("Number of rows are"+i);
        for(int j=1;j<=i;j++)
        {
            String Category=st.getRow(j).getCell(0).getStringCellValue();
            String Privilege =st.getRow(j).getCell(1).getStringCellValue();
            String Granted=st.getRow(j).getCell(2).getStringCellValue();
            System.out.println( " Category is " +Category+"Privilege is" +Privilege+ "Granted is " +Granted);
            result=PB.test_privilege(Category, Privilege, Granted);
            XSSFCell cell = st.getRow(j).createCell(3);
            if(result==true)
            {
                cell.setCellValue("Pass");
            }
            else
            {
                cell.setCellValue("Fail");
            }
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\dP-PL\\LearnMaven\\LearnMaven\\testdata\\DBI.xlsx");
            wb.write(outputStream);
        }
        wb.close();
    }
}
