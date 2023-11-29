package org.example;

import org.example.system_dto.Api;
import org.example.system_dto.ApiList;
import org.example.system_dto.Initialization;
import org.example.system_dto.SystemRoot;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, InterruptedException {
        Document doc = getDocument("C:\\Users\\maste\\IdeaProjects\\xml parser maven project\\src\\main\\java\\org\\example\\conf.xml");
        doc.getDocumentElement().normalize();

        SystemRoot systemRoot = new SystemRoot();
        Initialization initialization = new Initialization();
        ApiList apiList = new ApiList();
        List<Api> apis = new ArrayList<>();


        //for initialization part
        Element initializationElement = (Element) doc.getElementsByTagName("initialization").item(0);
        Element ddls = (Element) initializationElement.getElementsByTagName("ddls").item(0);
        List<String> ddlList1 = new ArrayList<>();
        NodeList ddlList = ddls.getElementsByTagName("ddl");
        for (int i = 0; i < ddlList.getLength(); i++) {
            Element ddl = (Element) ddlList.item(i);
            ddlList1.add(getComment(ddl));
        }
        initialization.setDdls(ddlList1);


        // parsing api
        Element apiListElement = (Element) doc.getElementsByTagName("api-list").item(0);
        NodeList apiNodeList = apiListElement.getElementsByTagName("api");
        for (int i = 0; i < apiNodeList.getLength(); i++) {
            Api api = new Api();
            Element apiNode = ((Element) apiNodeList.item(i));
            String route = apiNode.getAttribute("route");
            String httpMethod = apiNode.getAttribute("http-method");
            Element dtos = (Element) apiNode.getElementsByTagName("dto").item(0);
            Element sqlQueryList = (Element) apiNode.getElementsByTagName("sql-query-list")
                    .item(0);
            Element javaCodelst = (Element) apiNode.getElementsByTagName("java-codelet").item(0);

            //extracting dtos
            Element requestDto = (Element) dtos.getElementsByTagName("request").item(0);
            Element responseDto = (Element) dtos.getElementsByTagName("response").item(0);


            // extracting params from request dto
            HashMap<String, String> paramMap = new HashMap<>();
            NodeList paramNodeList = requestDto.getElementsByTagName("param");
            for (int j = 0; j < paramNodeList.getLength(); j++) {
                Element param = (Element) paramNodeList.item(j);
                String name = param.getAttribute("name");
                String dataType = param.getAttribute("data_type");
                paramMap.put(name, dataType);
            }

            String response = getComment(responseDto);

            //extracting sql query list
            NodeList queryList = sqlQueryList.getElementsByTagName("query");
            HashMap<String, String> queryMap = new HashMap<>();
            for (int j = 0; j < queryList.getLength(); j++) {
                Element query = (Element) queryList.item(j);
                String queryIdentifier = query.getAttribute("identifier");
                String queryValue = getComment(query);
                queryMap.put(queryIdentifier, queryValue);
            }

            String javaCode = getComment(javaCodelst);
            savetoFile(javaCode,route);

            api.setRoute(route);
            api.setHttpMethod(httpMethod);
            Api.Dto dto = new Api.Dto();
            dto.setRequest(paramMap);
            dto.setResponse(response);
            api.setDto(dto);
            api.setSqlQueriesList(queryMap);
            api.setJavaCodelet(javaCode);
            apis.add(api);

        }
        apiList.setApiList(apis);
        systemRoot.setInitialization(initialization);
        systemRoot.setApiList(apiList);
        System.out.println(systemRoot);



    }

    static Document getDocument(String path) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newDefaultInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        File xmlFile = new File(path);
        Document doc = dBuilder.parse(xmlFile);
        return doc;
    }

    // Function to get the commented value of an XML element
    static String getComment(Element element) {
        Node node = element.getFirstChild();
        while (node != null && node.getNodeType() != Node.COMMENT_NODE) {
            node = node.getNextSibling();
        }

        if (node != null) {
            Comment comment = (Comment) node;
            return comment.getData().trim();
        }

        return null;
    }

    static void savetoFile(String javaCode, String name) throws IOException, InterruptedException {

        name = name.replace("/","___");
        String dirName= "C:"+File.separator+"java_files"+ File.separator+name ;
        String fileName = dirName +File.separator+"Main.java";

        File file = new File(fileName);
        File dir = new File(dirName);
        if(!dir.exists()){
            dir.mkdir();
        }
      if(!file.exists()){
          file.createNewFile();
      }
        OutputStream outputStream = new FileOutputStream(file);
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write(javaCode);
        printWriter.flush();
        printWriter.close();
        outputStream.close();

        String[] changeDirCommand = {"cmd.exe", "/c", "cd", dirName};
        ProcessBuilder changeDirProcessBuilder = new ProcessBuilder(changeDirCommand);
        Process changeDirProcess = changeDirProcessBuilder.start();
       System.out.println( changeDirProcess.waitFor());

        String[] compileCommand = { "javac" , dirName+File.separator+"Main.java" };
        ProcessBuilder processBuilder = new ProcessBuilder(compileCommand);
        Process process = processBuilder.start();

        ProcessBuilder changeDirProcessBuilderx = new ProcessBuilder(changeDirCommand);
        Process changeDirProcessx = changeDirProcessBuilderx.start();
        System.out.println( changeDirProcessx.waitFor());

        System.out.println(process.waitFor());
//        String[] compileCommand1 = {"cmd.exe", "/c", "ls" };
//        ProcessBuilder processBuilder1 = new ProcessBuilder(compileCommand1);
//        Process process1 = processBuilder1.start();
//        // Get the input stream
//        InputStream inputStream = process1.getErrorStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//        // Read the output
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        System.out.println(process1.waitFor());
        runJava(dirName);

    }

   public static void runJava(String directory){
        try {
            // Command to execute


            // Create ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder();

            // Set the working directory
            processBuilder.directory(new File(directory));

            // Command to execute (you can add your actual command here)
            processBuilder.command("cmd", "/c", "java Main");

            // Redirect error stream to output stream (optional)
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the command
            // You can use InputStream, BufferedReader, etc.
            // Here, we use BufferedReader to read lines
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            int exitCode = process.waitFor();

            // Print the exit code
            System.out.println("Exit Code: " + exitCode);


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}