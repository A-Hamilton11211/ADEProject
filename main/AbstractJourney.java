package main;

abstract public class AbstractJourney {
	
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
