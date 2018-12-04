package classroot;

public class StudentData {
    private String id;
    private String name;
    private int sem;
    private int year;
    private String gendar;
    private String lastname;
    private String middlename;
    private String Campus;

    public StudentData(String id, String name, int sem, int year, String  gendar, String lastname, String middlename, String campus) {
        this.id = id;
        this.name = name;
        this.sem = sem;
        this.year = year;
        this.gendar = gendar;
        this.lastname = lastname;
        this.middlename = middlename;
        Campus = campus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String  getGendar() {
        return gendar;
    }

    public void setGendar(String  gendar) {
        this.gendar = gendar;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getCampus() {
        return Campus;
    }

    public void setCampus(String campus) {
        Campus = campus;
    }
}
