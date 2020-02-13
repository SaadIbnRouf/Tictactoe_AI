import java.util.*;

public class Main {
    public static void check_display(int[][] matrix){

        System.out.format("      %d  |  %d  |  %d  \n",matrix[0][0],matrix[0][1],matrix[0][2]);
        System.out.print("    _____ _____ _____\n");
        System.out.format("      %d  |  %d  |  %d  \n",matrix[1][0],matrix[1][1],matrix[1][2]);
        System.out.print("    _____ _____ _____\n");
        System.out.format("      %d  |  %d  |  %d \n",matrix[2][0],matrix[2][1],matrix[2][2]);

    }
    public static double Utility(int[][] board, int depth, int x){
        if (x == 2)
            return -1.0;
        else if (x == 1)
            return 1.0/depth;
        else if (x == 0){
            return 0.0;
        }
        return -0.0;
    }

    public static double max_algo(Matrix matrix,int depth, int a, int b){
//        System.out.println("max_algo " + depth);
        Matrix matrix_PC = new Matrix(matrix.arr);
        int x = matrix.check_move(a, b);
        if (x >= 0){
            return Utility(matrix.arr, depth, x);
        }
        double utility = Double.MIN_VALUE;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (matrix.arr[i][j] == 0){
                    matrix_PC.arr[i][j] = 1;
                    utility = Math.max(utility,min_algo(matrix_PC, depth+1,i, j) );

                }
            }
        }
        return utility;
    }


    public static double min_algo(Matrix matrix,int depth, int a, int b){

//        System.out.println("min_algo " + depth);
        Matrix matrix_user = new Matrix(matrix.arr);
        int x = matrix_user.check_move(a, b);
        if (x >= 0){
            return Utility(matrix_user.arr, depth, x);
        }
        double utility = Double.MAX_VALUE;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (matrix_user.arr[i][j] == 0){
                    matrix_user.arr[i][j] = 2;
                    utility = Math.min(utility,max_algo(matrix_user, depth+1, i, j));

                }
            }
        }
        return utility;
    }
    public static int[][] Choose_pc_move(int [][] arr){
        int [][] myArr = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                myArr[i][j] = arr[i][j];
            }
        }

//        Matrix matrix_pc_move = new Matrix(arr);
        List<Utility> list_utility = new ArrayList<>();
        Matrix matrix_pc_move = new Matrix(arr);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (myArr[i][j] == 0){

                    matrix_pc_move.arr[i][j] = 1;
                    double utility = min_algo(matrix_pc_move,1,i,j);
                    list_utility.add(new Utility(utility, i, j));
                }


            }
        }
        Utility utility = Collections.max(list_utility, Comparator.comparing(s -> s.utility));
//        for (int i = 0; i < list_utility.size(); i++ ){
//            System.out.println(list_utility.get(i).utility + " " +  list_utility.get(i).row_move);
//        }
        myArr[utility.row_move][utility.col_move] = 1;
        System.out.println("PC move");
        check_display(myArr);
        return myArr;

    }
    public static void main(String[] args) {
        System.out.println("Remember you are 2 \nand your opponent PC is 1 \nHappy playing!");
        int tmat [][] = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                tmat[i][j] = 0;
            }
        }
        Scanner val = new Scanner(System.in);

        while (true){
            int flag = 0;
//            boolean res = false;
            System.out.println("enter row & column");
            int row = val.nextInt();
            int col = val.nextInt();
            if (tmat[row][col] == 0)
                tmat[row][col] = 2;
            else {
                System.out.println("invalid move! Try again");
                continue;
            }
            System.out.println("player move");
            check_display(tmat);

            int x [][] = Choose_pc_move(tmat);
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    tmat[i][j] = x[i][j];
                }
            }
            //check win by row
            for (int i = 0; i < 3; i++){
                if (x[i][0] == x[i][1] && x[i][1] == x[i][2]){
                    if (x[i][0] == 1 || x[i][0] == 2){
                        System.out.println("Win by row");
                        flag = 1;
                        break;
                    }

                }
            }
            //check win by col
            for (int i = 0; i < 3; i++){
                if (x[0][i] == x[1][i] && x[1][i] == x[2][i]){
                    if (x[0][i] == 1 || x[0][i] == 2){
                        System.out.println("win by col");
                        flag = 1;
                        break;
                    }
                }
            }

            //check diagonal
            if (x[0][0] == x[1][1] && x[1][1] == x[2][2]){
                if (x[0][0] == 1 || x[0][0] == 2){
                    System.out.println("win by diagonal");;
                    flag = 1;
                    break;
                }
            }


            if (x[0][2] == x[1][1] && x[1][1] == x[2][0]){
                if (x[0][2] == 1 || x[0][2] == 2){
                    System.out.println("win by diagonal");;
                    flag = 1;
                    break;
                }
            }

            if (flag == 1){
                break;
            }

            //check draw
            for (int i = 0; i<3 ; i++){
                for (int j = 0; j<3; j++){
                    if (x[i][j] == 0){
                        flag = 0;
                        break;
                    }
                    else {
                        flag = 1;
                    }

                }
            }
            if (flag == 1){
                System.out.println("draw");
//                check_display(x);
                break;
            }


        }

    }
}
