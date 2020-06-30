package positionList;

public class Position {
	private String name;
	private int[] pos;
	private int feature;
	private int timesComment;
	private double feedback;
	private String description;
	
	public Position(String name, int[] pos, int feature, int times, double feedback, String description) {
		this.name = name;
		this.pos = pos;
		this.feature = feature;
		this.timesComment = times;
		this.feedback = feedback;
		this.description = description;
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
	
	public int getTimesComment() {
		return timesComment;
	}
	
	public double getFeedback() {
		return feedback;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void updateFeedback(int score) {
		int sum = (int)(timesComment * feedback) + score;
		timesComment++;
		feedback = sum * 1.0 / timesComment;
	}
}
