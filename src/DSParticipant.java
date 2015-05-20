import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;

//Created by Christopher Yang

public class DSParticipant {
    private BOParticipant boParticipant;

    public void setBoParticipant(BOParticipant boParticipant){
        this.boParticipant = boParticipant;
    }

    public void write(){
        try{
            FileOutputStream database = new FileOutputStream("database.csv", true);
            String writeString = boParticipant.getLastName() + ", " + boParticipant.getFirstName()
                    + ", " + boParticipant.getAge() + ", " + boParticipant.getJob() + ", "
                    + boParticipant.getIncome() + "\n";
            database.write(writeString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "One or more fields are empty");
        }
    }
}
