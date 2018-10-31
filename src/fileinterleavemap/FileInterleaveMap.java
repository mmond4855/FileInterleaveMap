
package fileinterleavemap;


public class FileInterleaveMap 
{

    
    public static void main(String[] args) 
    {
      String inFile1 = "E:/CIS2206 - Business Programming I/FileInterleaveMap/PayrollMasterA.dat";
      String inFileStreamName1 = "payrollMasterA";
      
      String inFile2 = "E:/CIS2206 - Business Programming I/FileInterleaveMap/PayrollMasterB.dat";
      String inFileStreamName2 = "payrollMasterB";
      
      String outFile = "E:/CIS2206 - Business Programming I/FileInterleaveMap/PayrollMaster.dat";
      String outFileStreamName = "payrollMaster";
      
      String inputLine;
           
      try (LineSequentialMap lineSequentialMap2 = new LineSequentialMap();)
          //try resources      //open when out of scope closes automatically
      {
          lineSequentialMap2.add(inFile1, inFileStreamName1, "input");
          lineSequentialMap2.add(inFile2, inFileStreamName2, "input");
          lineSequentialMap2.add(outFile, outFileStreamName, "output");
          
          while((inputLine = lineSequentialMap2.read(inFileStreamName1)) != null)
          {
              lineSequentialMap2.write(outFileStreamName, inputLine);
              inputLine = lineSequentialMap2.read(inFileStreamName2);
              lineSequentialMap2.write(outFileStreamName, inputLine);
          
          }
      
          System.out.println("File Complete");
      }
    }
    
}
