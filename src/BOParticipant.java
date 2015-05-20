//Created by Christopher Yang

public class BOParticipant {
    private DSParticipant dsParticipant;
    private final String firstName;
    private final String lastName;
    private final String job;
    private final int age;
    private final double income;

    public BOParticipant(String firstName, String lastName, int age, String job, double income){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.job = job;
        this.income = income;
        dsParticipant = new DSParticipant();
        dsParticipant.setBoParticipant(this);
    }

    public void write(){
        this.dsParticipant.write();
    }

    public DSParticipant getDsParticipant() {
        return dsParticipant;
    }

    public void setDsParticipant(DSParticipant dsParticipant) {
        this.dsParticipant = dsParticipant;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getIncome() {
        return income;
    }

    public String getJob() {
        return job;
    }
}
