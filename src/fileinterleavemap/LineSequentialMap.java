
package fileinterleavemap;

import java.io.*;
import java.util.TreeMap;

public class LineSequentialMap implements AutoCloseable        
{                           
    //Map has key and values.
                            //<key, value>
    private static TreeMap <String, BufferedReader> readFilesMap = 
            new TreeMap<String, BufferedReader> ();
    private static TreeMap <String, PrintWriter> writeFilesMap = 
            new TreeMap <String, PrintWriter> ();
    
    public void add (String fileName, String fileStream, String ioMode)
                                    //Key
    {
        try
        {
            if(ioMode == "input")
            {                   //extended name
                readFilesMap.put(fileStream, new BufferedReader(new FileReader(fileName)));
            }
            else
            {
                writeFilesMap.put(fileStream, new PrintWriter 
                (new BufferedWriter (new FileWriter(fileName))));
            }
        }
        catch(IOException E)
        {
            System.out.println("IO Error" + E.getMessage());
        }
    }
    
    public String read(String readFileStream)
            //readFileStream is the internal file name and key
    {
        String inputLine = "";
        
        try
        {
            inputLine = readFilesMap.get(readFileStream).readLine();
            //readFilesMap.get - method returns value for key
            //.readLine(); reads the next record in file
            //readFilesMap.get(readFileStream).readLine(); - 
            //evaluates to BufferedReader
        }
        catch(IOException F)
        {
            System.out.println("IO Error" + F.getMessage());
        }
    
        return inputLine;
    
    }
    
    public void write(String writeFileStream, String outputLine)
    {
        //writeFileSTream is the internal file name and key
        //outputLine is the record to wrtie
        
        writeFilesMap.get(writeFileStream).println(outputLine);
        //Places CRLF at the end of the string writes it to file
        //associated with the printwriter. 
        //writeFilesMap.get(writeFileStream).println(outputLine); -
        //evaluates to PrintWriter
    }
    
    public void close() //this method is needed for autocloseable....
            //its a contract
    {
        try
        {
            String key = readFilesMap.firstKey(); //returns the first key in map
            String nextKey;
            
            (readFilesMap.get(key)).close();
            //Closes the first value in the map/the buffered reader
            
            for(int i = 1; i < readFilesMap.size(); i++)
            {
                nextKey = readFilesMap.higherKey(key);
                //gets the next based on sort order on tree map
                //higherKey - relative to this key.
                (readFilesMap.get(nextKey)).close(); 
                key = nextKey; //updates
            
            }
            
            key = writeFilesMap.firstKey();
            
            (writeFilesMap.get(key)).close();
            
            for (int i = 1; i < writeFilesMap.size(); i++)
            {
                nextKey = writeFilesMap.higherKey(key);
                (writeFilesMap.get(nextKey)).close();
                key = nextKey;
            }
            
            
        }
        catch(IOException E)
        {
            System.out.println("IO Error" + E.getMessage());
        }
    
    
    }
}
