package by.epam.homework3.Reader.impl;

import by.epam.homework3.Reader.FileDataReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XmlFileReader implements FileDataReader
{
    public List<String> read(String file){
        InputStream fileInputStream = null;
        List<String> appliancesList = new ArrayList<>();
        StringBuilder stringOfXml = new StringBuilder();
        try {
            fileInputStream = new BufferedInputStream(new FileInputStream(getClass().getResource("/"+file).getPath()));
            int i = fileInputStream.read();
            boolean valid = true;
            while (i != -1) {
                stringOfXml.append((char) i);
                while ((char) i != '\n' && i != -1) {
                    i = fileInputStream.read();
                    stringOfXml.append((char) i);
                }
                for (char c : stringOfXml.toString().toCharArray()) {
                    if (!Character.isLetterOrDigit(c)) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    appliancesList.add(stringOfXml.toString());
                }

                i = fileInputStream.read();
                stringOfXml = new StringBuilder();
                valid = true;
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }finally {
            try {

                fileInputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
            return appliancesList;
    }
}

