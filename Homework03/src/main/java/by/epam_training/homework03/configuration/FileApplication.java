package by.epam_training.homework03.configuration;

import by.epam_training.homework03.entity.Element;
import by.epam_training.homework03.factory.FileAbstractFactory;
import by.epam_training.homework03.factory.factories.XmlFactory;
import by.epam_training.homework03.parser.FileParser;
import by.epam_training.homework03.reader.FileDataReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileApplication {

    private  FileDataReader fileDataReader;
    private  FileParser fileParser;
    private  String path;

    public FileApplication(FileAbstractFactory factory, String path) {
        fileDataReader = factory.getFileDataReader();
        fileParser = factory.getFileParser();
        this.path = path;
    }

    public FileDataReader getFileDataReader() {
        return fileDataReader;
    }

    public void setFileDataReader(FileDataReader fileDataReader) {
        this.fileDataReader = fileDataReader;
    }

    public FileParser getFileParser() {
        return fileParser;
    }

    public void setFileParser(FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static FileApplication configurationFile(String path){
        String TYPE_OF_FILE = "\\..+";
        FileAbstractFactory factory;
        FileApplication fileApplication = null;

        Pattern pattern = Pattern.compile(TYPE_OF_FILE);
        Matcher matcher = pattern.matcher(path);
        if (matcher.find()){
            if (".xml".equals(matcher.group())){
                factory = new XmlFactory();
                fileApplication = new FileApplication(factory, path);
            }
        }
        return fileApplication;
    }

    public List<String> executeRead(){
        return fileDataReader.read(path);
    }

    public Element executeBuildFileStructure(List<String> file){
        List<String> filteredData;
        filteredData = fileParser.filterData(file);
        return fileParser.buildFileStructure(filteredData);
    }
}
