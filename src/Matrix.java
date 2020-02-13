public class Matrix {
    int arr [][] = new int[3][3];
    int flag = 0;

    public Matrix(int[][] arr) {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.arr[i][j] = arr[i][j];
            }
        }
    }

    public int check_move(int x, int y){
        int s = this.arr[x][y];

        if (s == 2){
            //check col
            for(int i = 0; i < 3; i++){
                if(arr[i][y] != s)
                    break;
                if(i == 2){
                    return 2;
                }
            }
            // check row
            for(int i = 0; i < 3; i++){
                if(arr[x][i] != s)
                    break;
                if(i == 2){
                    //report win for s
                    return 2;
                }
            }

            //check diagonal
            if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[2][2] == 2)
                return 2;

            if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && arr[2][0] == 2)
                return 2;
        }
        else if (s == 1){
            //check col
            for(int i = 0; i < 3; i++){
                if(arr[i][y] != s)
                    break;
                if(i == 2){
                    return 1;
                }
            }
            // check row
            for(int i = 0; i < 3; i++){
                if(arr[x][i] != s)
                    break;
                if(i == 2){
                    //report win for s
                    return 1;
                }
            }

            //check diagonal
            if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && arr[2][2] == 1)
                return 1;

            if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && arr[2][0] == 1)
                return 1;
        }
        //check draw
        for (int i = 0; i<3 ; i++){
            for (int j = 0; j<3; j++){
                if (arr[i][j] == 0){
                    flag = 1;
                }
            }
        }
        if (flag == 0){
            return 0;
        }
        else {
            return -1;
        }



    }


}
