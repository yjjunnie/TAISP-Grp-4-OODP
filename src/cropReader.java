import java.io.*;
import java.util.*;

public class cropReader {
    String filePath;
    private List<List<String>> cropData;
    private ArrayList<Crop> cropsList;

    // create createCrops and pass filepath into constructor
    // use "get" to get the object list
    public cropReader(String filePath) {
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

            String line = reader.readLine();

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


        for (List<String> dataLine: this.cropData) {
            String name = dataLine.get(0);
            String Type = dataLine.get(1);
            int seedlingWeeks = Integer.parseInt(dataLine.get(2)) / 7;
            int matureWeeks = Integer.parseInt(dataLine.get(3)) / 7;

            int[] temperature = {Integer.parseInt(dataLine.get(4)), Integer.parseInt(dataLine.get(5))};
            int[] humidity = {Integer.parseInt(dataLine.get(6)), Integer.parseInt(dataLine.get(7))};
            int[] lightExposure = {Integer.parseInt(dataLine.get(8)), Integer.parseInt(dataLine.get(9))};

            switch(Type) {
                case "Aquatic":
                    cropsList.add(new AquaticCrop(name, seedlingWeeks, matureWeeks, temperature, humidity, lightExposure));
                    break;
                case "Land":
                    int[] soilMoisture = {Integer.parseInt(dataLine.get(10)), Integer.parseInt(dataLine.get(11))};
                    cropsList.add(new LandCrop(name, seedlingWeeks, matureWeeks, temperature, humidity, lightExposure, soilMoisture));
                    break;
                default:
                    break;
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