package duke;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * Storage manages reading and writing of data from and to txt file.
 */
public class Storage {
    protected String filePath;

    /**
     * Constructor that takes in path of txt tile.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to read data from txt file and put into ArrayList.
     *
     * @return
     */
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            if (file.length() != 0) {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream oit = new ObjectInputStream(fin);
                taskList = (ArrayList<Task>) oit.readObject();
            }
        } catch (Exception ex) {
            Ui ui = new Ui();
            ui.showLoadingError();
        }

        return taskList;
    }

    /**
     * Method to store TaskList into txt file.
     *
     * @param taskList
     */
    public void store(TaskList taskList) {
        try {
            FileOutputStream fout = new FileOutputStream(this.filePath);
            ObjectOutputStream oot = new ObjectOutputStream(fout);
            oot.writeObject(taskList.tasks);
            oot.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}