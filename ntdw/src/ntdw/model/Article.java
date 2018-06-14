package ntdw.model;

public class Article {
	private String id;
	private String description;
	private String family;
	private String artClass;
	private String manufact;
	private int prio;
	private String target;
	private String status;
	private String question;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getArtClass() {
		return artClass;
	}
	public void setArtClass(String artClass) {
		this.artClass = artClass;
	}
	public String getManufact() {
		return manufact;
	}
	public void setManufact(String manufact) {
		this.manufact = manufact;
	}
	public int getPrio() {
		return prio;
	}
	public void setPrio(int prio) {
		this.prio = prio;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setQuestion(String string) {
		this.question=string;
		
	}
	public String getQuestion() {
		return question;
	}
	
}
