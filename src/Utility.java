public class Utility {
    double utility;
    int row_move;
    int col_move;

    public Utility(double utility, int row_move, int col_move) {
        this.utility = utility;
        this.row_move = row_move;
        this.col_move = col_move;
    }

    public double getUtility() {
        return utility;
    }
}
