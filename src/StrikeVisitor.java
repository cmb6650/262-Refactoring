import java.util.Arrays;

public class StrikeVisitor implements LaneScoreVisitor{
    private int index;
    private int[] curScore;
    private int bowlIndex;
    private int[][] cumulScores;

    public StrikeVisitor(Lane l, int index, int[] curScore) {
        this.index = index;
        this.curScore = curScore;
        this.bowlIndex = l.getBowlIndex();
        this.cumulScores = l.getCumulScores();
    }

    public int[] getCumulScores() {
        return cumulScores[bowlIndex];
    }

    @Override
    public void calcScore() {
        cumulScores[bowlIndex][index/2] += 10;
        if(curScore[index+1] != -1) {
            cumulScores[bowlIndex][index/2] += curScore[index+1] + cumulScores[bowlIndex][(index/2)-1];
            if (curScore[index+2] != -1 && curScore[index+2] != -2){
                cumulScores[bowlIndex][(index/2)] += curScore[index+2];
            } else if( curScore[index+3] != -2){
                cumulScores[bowlIndex][(index/2)] += curScore[index+3];
            }
        } else {
            if ( index/2 > 0 ){
                cumulScores[bowlIndex][index/2] += curScore[index+2] + cumulScores[bowlIndex][(index/2)-1];
            } else {
                cumulScores[bowlIndex][index/2] += curScore[index+2];
            }
            if (curScore[index+3] != -1 && curScore[index+3] != -2){
                cumulScores[bowlIndex][(index/2)] += curScore[index+3];
            } else {
                cumulScores[bowlIndex][(index/2)] += curScore[index+4];
            }
        }
    }
}
