import java.util.Iterator;
import java.util.List;

public class Result implements Comparable<Result>{
    private String type;
    private int number;
    private int times;
    private List<Score> score;

    public Result(String type, int number, int times, List<Score> score) {
        this.type = type;
        this.number = number;
        this.times = times;
        this.score = score;
    }

    public String getName(){
        return this.type + this.number;
    }

    public int getNumber(){
        return this.number;
    }

    public List<Score> getScore(){
        return this.score;
    }

    public void addTimes(){
        this.times++;
    }

    public void addScore(Score score){
        this.score.add(score);
    }

    public String print(){
        String scoreResult = "";
        Iterator<Score> it = this.score.iterator();
        while (it.hasNext()) {
            Score score = it.next();
            scoreResult += " " + score.getCorrectness() + " " + score.getDate();
        }
        return this.type + " " + this.number + " " + this.times + scoreResult;
    }

    @Override
    public int compareTo(Result result) {
        return this.number - result.getNumber();
    }
}
