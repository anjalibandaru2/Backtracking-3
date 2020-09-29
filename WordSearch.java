package s30;
/*
* Approach: 
 1. Traverse through each character in the given board and check if it is same as the first character of the given search word.
 2.When we encounter the first character of the search word, perform depth first search
 3. Recursively check all the neighboring elements for the next character.
 4. Process only those neighbors whose character value is same as that of the current character to be checked.
 5. By doing so, if we found the search word and reached last index of the word,
 return true. Then, stop checking for all other elements in the grid.
 6. else, if we are not able to find the complete search word i.e., if there are no possible paths from the current search character, go to step 1 and repeat the next steps.
 
 Time complexity: O(N*N) in the worst case, where N is the number of elements in the board.
 if the given search word is not present in the board and length of search word is greater than N, then for each character, we traverse through the entire board and check if it is present or not.
 
 Space Complexity: O(L) where L is length of search word, which is the maximum size of the recursion stack
*/
class WordSearch {
	public static void main(String[] args) {
		WordSearch w = new WordSearch();
		char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		System.out.println(w.exist(board, "ABCCED"));
		System.out.println(w.exist(board, "SEE"));
		System.out.println(w.exist(board, "ABCB"));
	}
	
    int[][] dirs = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    int rows, cols;
    
    public boolean exist(char[][] board, String word) {
       if(board == null || board.length == 0){
           return false;
       }
        rows = board.length;
        cols = board[0].length;
        
        for(int i=0; i< board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(dfs(i, j, 1, board, word)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean dfs(int row, int col, int index, char[][] board, String word){
        
        //base condition to stop recursion
        //return true when end of the search word is reached
        if(index == word.length()){
            return true;
        }
        
        /* store the current character in a temporary variable and 
        * update the value in board to some special character say '$', 
        * which indicates that current row and column is already visited.*/
        char temp = board[row][col];
        board[row][col] = '$';
        boolean found = false;
        
        //check all neighboring elements as per the directions array
        for(int[] dir: dirs){
            int nextRow = row+dir[0];
            int nextCol = col + dir[1];
            
            //If nextRow and nextColumn are valid and if it is not visited, perform depth first search
            if(nextRow >=0 && nextRow < rows && nextCol >=0 && nextCol < cols
              &&  board[nextRow][nextCol] == word.charAt(index)){
                
                //early return. Stop checking for other neighbors if the word is already found
                if(dfs(nextRow, nextCol, index+1, board, word)){
                	found = true;
                	break;
                }
            }
        }
        
        //backtracking step
        //restore the character at current position by replacing $ with the stored temporary character
        board[row][col] = temp;
        
        return found;
    }
}