public class NormalScoreState implements LaneScoreState {
    private int index;
    private int[] curScore;
    private int bowlIndex;
    private int[][] cumulScores;

    public NormalScoreState(Lane l, int index, int[] curScore) {
        this.index = index;
        this.curScore = curScore;
        this.bowlIndex = l.getBowlIndex();
        this.cumulScores = l.getCumulScores();
    }

    @Override
    public int[] getCumulScores() {
        return cumulScores[bowlIndex];
    }

    @Override
    public void calcScore() {
        if(index < 18) {
            if (index % 2 == 0) {
                if (index/2 != 0) {
                    cumulScores[bowlIndex][index / 2] += cumulScores[bowlIndex][index / 2 - 1];
                }
                if (curScore[index] != -2) {
                    cumulScores[bowlIndex][index / 2] += curScore[index];
                }
            } else if (curScore[index] != -1 && index > 2 && curScore[index] != -2) {
                cumulScores[bowlIndex][index / 2] += curScore[index];
            }
        }
        if (index/2 == 9 || index/2 == 10 && !(index/2 == 9 && index/2 == 10)){
            if (index == 18){
                cumulScores[bowlIndex][9] += cumulScores[bowlIndex][8];
            }
            if(curScore[index] != -2){
                cumulScores[bowlIndex][9] += curScore[index];
            }
        }
    }
}
