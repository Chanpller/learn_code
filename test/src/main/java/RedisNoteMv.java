import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

public class RedisNoteMv {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\迅雷下载\\Learning-in-practice-master\\Redis\\12.Redis高阶篇");
        File[] files = file.listFiles();
        for (File file1 : files) {
            String name = file1.getName();

            Pattern pattern = Pattern.compile("^\\d.*");
            boolean startWithNumber = pattern.matcher(name).find();
           if(startWithNumber){
               String[] split = name.split("\\.");
               String chapterNum = split[0];
               String chapterName = split[1];
               String fileName = "第"+chapterNum+"章-"+chapterName;
               String path = "D:\\work\\ideaProjects\\learn\\redis\\redis7\\高阶篇\\";

               try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+fileName+".md",true))){
                   bufferedWriter.write("# "+fileName);
                   bufferedWriter.newLine();
               } catch (IOException e) {
                   e.printStackTrace();
               }

               File[] files1 = file1.listFiles();
               for (File file2 : files1) {
                   if(file2.isDirectory()){
                       File[] files2 = file2.listFiles();
                       for (File file3 : files2) {
                           Path source = Paths.get(file3.getAbsolutePath());
                           Path target = Paths.get("D:\\work\\ideaProjects\\learn\\redis\\redis7\\image2\\"+file3.getName());
                           Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                       }
                   }else {
                       try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file2))){
                           String readLine = null;
                           while (( readLine = bufferedReader.readLine())!=null){
                               readLine = readLine.replace("](images/","](../image2/")
                                       .replace("](images/","](../image2/")
                                       .replace("{\\large","")
                                       .replace("}$","")
                                       .replace("$\\textcolor{green}","")
                                       .replace("$\\textcolor{red}","")
                                       .replace("$\\textcolor{balck}","")
                                       .replace("$\\textcolor{blue}","");
                               try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path+fileName+".md",true))){
                                   bufferedWriter.write(readLine);
                                   bufferedWriter.newLine();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               }
                           }

                       } catch (FileNotFoundException e) {
                           e.printStackTrace();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
        }
    }
}
