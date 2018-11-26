import sun.awt.datatransfer.DataTransferer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args)  {
        Path path;
        HashMap<String,Integer> map=new HashMap<>();
        try{
            //args[0]=args[0].replaceAll("\\\\","/");
            System.out.println(args[0]);
            path= Paths.get(args[0]);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
            return;
        }
        List<String> list= null;
        try {
            list = Files.readAllLines(path);
        } catch(MalformedInputException e1){
            System.out.println(e1);
        } catch (IOException|NullPointerException e) {
            System.out.println(e);
        }
        for(String s:list){
            String[] words=s.split(" ");
            for(int i=0;i<words.length;i++){
                String word=words[i];
                word=word.toLowerCase();
                word=word.trim();
                word=word.replaceAll("\\?","");
                word=word.replaceAll("!","");
                word=word.replaceAll(",","");
                word=word.replaceAll("\\.","");
                word=word.replaceAll(";","");
                if(map.containsKey(word)){
                    map.put(word,map.get(word)+1);
                }else{
                    map.put(word,1);
                }
            }
        }
        //map.entrySet().stream().forEach(System.out::println);
       Map<String,Integer> sorted= map.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1, LinkedHashMap::new));
        if(map.size()>10){
            int count=0;
            for(Map.Entry<String,Integer> entry:sorted.entrySet()){
                if(count<10){
                System.out.println(entry.getKey()+" = "+entry.getValue());
                count++;
                }else break;
            }
        }else{
            sorted.entrySet().stream().forEach(System.out::println);
        }
    }
}
