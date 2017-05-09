package gson;

import com.google.gson.Gson;


import java.io.*;

/**
 * Created by jakubinyi on 2017.05.02..
 */
public class DataReader {
    private Gson gson;

    public DataReader(Gson gson) {
        this.gson = gson;
    }

    public Poet getPoet(String name) throws PoetNotFoundException, IOException {
        String fileName = "/data/" + name + ".json";
        // a class objektumtól kérjük el a filestream-et és így a hivatkozás kiindulópontja a classpath gyökér lesz, és nem pedig a filesystem gyökér
        InputStream inputStream = DataReader.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new PoetNotFoundException(fileName + " not found");
        }
        try (InputStreamReader reader = new InputStreamReader(inputStream)) {  // try az aouto-close kedvéért
            //new FileReader("/home/jakubinyi/IdeaProjects/AJAX_Poets2/src/gson/varro.json"));

            //convert the json string back to object
            return gson.fromJson(reader, Poet.class);

//            System.out.println("Name Of Poet: "+worksObj.getName());
//
//            System.out.println("Title: "+worksObj.getTitle());
//
//            System.out.println("Publication: "+worksObj.getPublication());
        }
    }

    public InputStream getWork(String poetName, String title) throws PoetNotFoundException, WorkNotFoundException, IOException {
        Poet poet = getPoet(poetName);
        String fileName = null;
        for (WorkWithPath work: poet.getPoems()) {
            if (work.getTitle().equals(title)) {
                fileName = "/data/" + work.getFilePath();
            }
        }
        if (fileName == null) {
            throw new WorkNotFoundException(title + " not found (poet: " + poetName + ")");
        }
        InputStream inputStream = DataReader.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException(fileName + " not found");
        }
        return inputStream;
    }

    public static void main(String[] args) throws IOException, PoetNotFoundException {
        Gson gson = new Gson();
        DataReader example = new DataReader(gson);
        Poet poet = example.getPoet("varro");
        System.out.println(gson.toJson(poet));
    }
}

