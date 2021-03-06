package sample.game.objects.scene;

public class Score  {
    private String name;
    private double score;

    @Override
    public String toString() {
        return "игрок='" + name + '\'' +
                ", счёт=" + score;
    }

    public Score(String name , double score){
        this.name = name;
        this.score = score;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
