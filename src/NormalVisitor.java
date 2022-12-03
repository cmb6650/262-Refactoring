import java.util.Arrays;

public class NormalVisitor implements LaneScoreVisitor{
    private int index;
    private int[] curScore;
    private int bowlIndex;
    private int[][] cumulScores;

    public NormalVisitor(Lane l, int index, int[] curScore) {
        this.index = index;
        this.curScore = curScore;
        this.bowlIndex = l.getBowlIndex();
        this.cumulScores = l.getCumulScores();
    }

    public int[] getCumulScores() {
        return cumulScores[bowlIndex];
    }

    @Override
    public void visitStrike() {
    }

    @Override
    public void visitNormal() {
        if( index%2 == 0 && index < 18){
            if ( index/2 == 0 ) {
                //First frame, first ball.  Set his cumul score to the first ball
                if(curScore[index] != -2){
                    cumulScores[bowlIndex][index/2] += curScore[index];
                }
            } else if (index/2 != 9){
                //add his last frame's cumul to this ball, make it this frame's cumul.
                if(curScore[index] != -2){
                    cumulScores[bowlIndex][index/2] += cumulScores[bowlIndex][index/2 - 1] + curScore[index];
                } else {
                    cumulScores[bowlIndex][index/2] += cumulScores[bowlIndex][index/2 - 1];
                }
            }
        } else if (index < 18){
            if(curScore[index] != -1 && index > 2){
                if(curScore[index] != -2){
                    cumulScores[bowlIndex][index/2] += curScore[index];
                }
            }
        }
        if (index/2 == 9){
            if (index == 18){
                cumulScores[bowlIndex][9] += cumulScores[bowlIndex][8];
            }
            if(curScore[index] != -2){
                cumulScores[bowlIndex][9] += curScore[index];
            }
        } else if (index/2 == 10) {
            if(curScore[index] != -2){
                cumulScores[bowlIndex][9] += curScore[index];
            }
        }
    }
}
