package classroot;

public class Subject {
    private String COURSEID;
    private String NAMECOURSE;
    private String PREREQUSITE;
    private int  PASSSTATUS;
    private int YEAR;
    private int SEM;
    private String COLOR;
    private int CREDIT;

    public Subject(){ }
    public Subject(String COURSEID,String NAMECOURSE,int SEM , int YEAR){
        this.COURSEID = COURSEID;
        this.NAMECOURSE = NAMECOURSE;
        this.SEM = SEM;
        this.YEAR = YEAR;

    }

    public Subject(String COURSEID, String NAMECOURSE, String PREREQUSITE, int  PASSSTATUS, int YEAR, int SEM, String COLOR,int CREDIT) {
        this.COURSEID = COURSEID;
        this.NAMECOURSE = NAMECOURSE;
        this.PREREQUSITE = PREREQUSITE;
        this.PASSSTATUS = PASSSTATUS;
        this.YEAR = YEAR;
        this.SEM = SEM;
        this.COLOR = COLOR;
        this.CREDIT = CREDIT;
    }

    public String getCOURSEID() {
        return COURSEID;
    }

    public String getNAMECOURSE() {
        return NAMECOURSE;
    }

    public String getPREREQUSITE() {
        return PREREQUSITE;
    }

    public int getPASSSTATUS() {
        return PASSSTATUS;
    }

    public int getYEAR() {
        return YEAR;
    }

    public int getSEM() {
        return SEM;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public int getCREDIT() {
        return CREDIT;
    }

    public void setCREDIT(int CREDIT) {
        this.CREDIT = CREDIT;
    }
}
