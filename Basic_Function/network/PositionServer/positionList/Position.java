package positionList;

public class Position {
	private String name;
	private int[] pos;
	private int feature;
	private String description;
	private int timesComment;
	private double feedback;
	
	public Position(String name, int[] pos, int feature, String description, int times, double feedback) {
		this.name = name;
		this.pos = pos;
		this.feature = feature;
		this.description = description;
		this.timesComment = times;
		this.feedback = feedback;
	}

	public String getName() {
		return name;
	}
	
	public int[] getPosition() {
		return pos;
	}
	
	public int getFeature() {
		return feature;
	}
	
	public String getDescription() {
		return description;
	}
	
	public int getTimesComment() {
		return timesComment;
	}
	
	public double getFeedback() {
		return feedback;
	}
	
	public void updateFeedback(int score) {
		int sum = (int)(timesComment * feedback) + score;
		timesComment++;
		feedback = sum * 1.0 / timesComment;
	}
}
