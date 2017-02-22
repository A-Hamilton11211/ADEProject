package src.main;

abstract public class AbstractJourney {
	//abstract superclass for sorting purposes in places class
	private String destName;
	
	public AbstractJourney(String newdest){
		this.destName=newdest;
	}
	
	public String getDest()
    {
        return destName;
    }
	
	public void setDest(String newdest){
		destName=newdest;
	}
}
