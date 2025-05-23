package crops;

import java.io.*;
import java.util.*;

public class CropReader {
    String filePath;
    private List<List<String>> cropData;
    private ArrayList<Crop> cropsList;

    // create createCrops and pass filepath into constructor
    // use "get" to get the object list
    public CropReader(String filePath) {
        this.filePath = filePath;
        this.cropData = readCropCSV();
        this.cropsList = makeCrops();
    }

    // returns list of list of strings with all the data
    public List<List<String>> readCropCSV() {
        List<List<String>> cropData = new ArrayList<>();
        BufferedReader reader = null;


        try {
            reader =  new BufferedReader(new FileReader(this.filePath));

            String line;
            reader.readLine(); // Clear Header

            while ((line = reader.readLine()) != null) {
                List<String> items = Arrays.asList(line.split(","));
                cropData.add(items);
            }
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        finally {

            try {
                if (reader != null) reader.close();
            }

            catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

        return cropData;
    }

    // creates the crop objects and returns them
    public ArrayList<Crop> makeCrops() {
        ArrayList<Crop> cropsList = new ArrayList<>();

        if (this.cropData != null) {
            for (List<String> dataLine : this.cropData) {
                
                try {
                    String name = dataLine.get(0);
                    String Type = dataLine.get(1);
                    int seedlingWeeks = Integer.parseInt(dataLine.get(2));
                    int matureWeeks = Integer.parseInt(dataLine.get(3));

                    Integer[] temperature = {Integer.parseInt(dataLine.get(4)), Integer.parseInt(dataLine.get(5))};
                    Integer[] humidity = {Integer.parseInt(dataLine.get(6)), Integer.parseInt(dataLine.get(7))};
                    Integer[] lightExposure = {Integer.parseInt(dataLine.get(8)), Integer.parseInt(dataLine.get(9))};

                    switch (Type) {
                        case "Aquatic":
                            cropsList.add(new AquaticCrop(name, seedlingWeeks, matureWeeks, temperature, humidity, lightExposure));
                            break;
                        case "Land":
                            Integer[] soilMoisture = {Integer.parseInt(dataLine.get(10)), Integer.parseInt(dataLine.get(11))};
                            cropsList.add(new LandCrop(name, seedlingWeeks, matureWeeks, temperature, humidity, lightExposure, soilMoisture));
                            break;
                        default:
                            System.out.println("Unknown crop type: " + Type);
                            break;
                    }
                    
                } 
                
                catch (Exception e) {
                    System.out.println("Error processing line: " + dataLine);
                    System.out.println(e);
                }
                
            }
        }
        return cropsList;
    }

    public List<List<String>> getCropData() {
        return cropData;
    }

    public void setCropData(List<List<String>> cropData) {
        this.cropData = cropData;
    }

    public ArrayList<Crop> getCropsList() {
        return cropsList;
    }

    public void setCropsList(ArrayList<Crop> cropsList) {
        this.cropsList = cropsList;
    }
}