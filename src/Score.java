public class Score implements Comparable<Score>{
    private String correctness;
    private String date;

    public Score(String correctness, String date){
        this.correctness = correctness;
        this.date = date;
    }

    public String getCorrectness(){
        return this.correctness;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public int compareTo(Score score) {
        String[] temp1 = this.correctness.split("/");
        String[] temp2 = score.getCorrectness().split("/");
        return Integer.parseInt(temp2[0]) - Integer.parseInt(temp1[0]);
    }
}
