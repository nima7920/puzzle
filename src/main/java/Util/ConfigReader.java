package Util;

import Models.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ConfigReader {

    @Expose
    private ArrayList<Configuration> allConfigurations;
    @Expose
    private HashMap<Integer, String> imagePaths;

    private File configFile = new File(".//src//main//resources//Config.JSON");
    private GsonBuilder gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();

    private static ConfigReader configReader;
    private ConfigReader() {

    }

    public static ConfigReader getInstance() {
        if (configReader == null)
            configReader = new ConfigReader();
        return configReader;
    }

    public void loadInfo(){

        Gson gson = gsonBuilder.setPrettyPrinting().create();
        try {
            FileReader fileReader = new FileReader(configFile);
            ConfigReader cnr = gson.fromJson(fileReader, ConfigReader.class);
            this.allConfigurations = cnr.allConfigurations;
            Collections.shuffle(this.allConfigurations);

            this.imagePaths = cnr.imagePaths;
            fileReader.close();
        } catch (IOException e) {


        }
    }

    public Configuration getConfiguration(){

        return allConfigurations.get(0);
    }

    public String getImagePath(int i){

        return imagePaths.get(i);
    }


//    public static void main(String[] args) {
//        File configFile=new File(".//src//main//resources//Config.JSON");
//
//        GsonBuilder gsonBuilder=new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
//        gsonBuilder.serializeNulls();
//        Gson gson=gsonBuilder.setPrettyPrinting().create();
//        ConfigReader configReader=new ConfigReader();
//
//        try {
//            FileReader fileReader=new FileReader(configFile);
//            configReader=gson.fromJson(fileReader,ConfigReader.class);
//            System.out.println(configReader.imagePaths.toString());
//        }catch (IOException e){
//
//        }
////        Configuration configuration;
////        configReader.allConfigurations=new ArrayList<>();
////        configReader.imagePaths=new HashMap<>();
////        for (int i = 0; i <9 ; i++) {
////            if(i==0)
////                configReader.imagePaths.put(0,".//src//main//java//assets//missing.png");
////            else
////                configReader.imagePaths.put(i,".//src//main//java//assets//"+i+".png");
////
////        }
////        configuration=new Configuration(new int[] {1,5,7,4,8,2,3,0,6});
////        configReader.allConfigurations.add(configuration.getClone());
////
////        configuration=new Configuration(new int[] {1,6,2,8,0,3,7,4,5});
////        configReader.allConfigurations.add(configuration.getClone());
////
////        configuration=new Configuration(new int[] {1,8,4,3,5,2,6,7,0});
////        configReader.allConfigurations.add(configuration.getClone());
////        configuration=new Configuration(new int[] {1,4,5,0,6,3,2,8,7});
////        configReader.allConfigurations.add(configuration.getClone());
////        configuration=new Configuration(new int[] {1,7,0,6,2,8,5,3,4});
////        configReader.allConfigurations.add(configuration.getClone());
////
////        try{
////            FileWriter fileWriter=new FileWriter(configFile);
////            gson.toJson(configReader,fileWriter);
////            fileWriter.close();
////        }catch (IOException e){
////
////        }
//    }
}
