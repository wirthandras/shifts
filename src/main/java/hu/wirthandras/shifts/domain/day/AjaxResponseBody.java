package hu.wirthandras.shifts.domain.day;

import java.util.List;

public class AjaxResponseBody {
	
    private List<String> result;
    
    public AjaxResponseBody() {
    	super();
    }
    
    public AjaxResponseBody(List<String> result) {
    	super();
    	this.result = result;
    }
    
	public List<String> getResult() {
		return result;
	}
	
	public void setResult(List<String> result) {
		this.result = result;
	}
	

}
