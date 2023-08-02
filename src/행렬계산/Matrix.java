package ��İ��;

/*
 * ����� ��Ÿ����
 * �پ��� ��� ��� ����� �����Ѵ�.
 */

public class Matrix{//���
	RationalNum[][] matrix; //2���� ������ �迭
	int row; //�� ũ��
	int col; //�� ũ��

	public Matrix(RationalNum[][] matrix){
		this.matrix = matrix;
		row = matrix.length;
		col = matrix[0].length;
	}
	
	/*
	 * �Ѱܹ��� ������� ���ؼ� ����� ��ȯ�Ѵ�.
	 */
	public static Matrix multiply(Matrix m1, Matrix m2){ //���ʿ� ���� ����� ��ȯ
		
		if(m1.col != m2.row) {
			System.out.println("���� ���°� ���� �ʽ��ϴ�.");
			return null;
		}

		RationalNum[][] result = new RationalNum[m1.row][m2.col];//����� ����

		for(int r=0; r<m1.row; r++) {
			for(int c=0; c<m2.col; c++) {
				//this�� r�຤�Ϳ� cMatrix�� c�� ���͸� �����Ͽ� ��� ����� r�� c���� ���� �Ѵ�.
				result[r][c] = new RationalNum();
				for(int i=0; i<m1.col; i++) {
					result[r][c].add(RationalNum.multiply(m1.matrix[r][i],m2.matrix[i][c]));
				}
			}
		}

		return new Matrix(result);
	}
	
	/*
	 * ��Ľ��� ���ϴ� �Լ�
	 */
	public RationalNum getDeterminant() {
		if(row != col) {
			System.out.println("���� ��ĸ� ��Ľ��� ���� �� �ֽ��ϴ�.");
		}
		boolean[][] isHidden = new boolean[2][col]; //������ �� ���� ǥ�� 0��-�� 1��-��
		
		return determinant(row, isHidden); //0��0�� ���� size�� row�� ����� ���� ���ؼ� ��ȯ�Ѵ�.
	}
	
	//����Ľ��� ���ϴ� �Լ�, ��������� ����
	private RationalNum determinant(int size, boolean[][] isHidden) {//size*size ũ���� ����Ľ��� ����
		int startRow = 0;//���� ��
		int startCol = 0;//���� ��
		
		while(isHidden[0][startRow]) {//�������� ���� �� ��� ���� ã��
			startRow++;
		}
		while(isHidden[1][startCol]) {//�������� ���� ���� ���� ���� ã��
			startCol++;
		}
		
		if(size == 1) {//���� ����
			return matrix[startRow][startCol];
		}
		isHidden[0][startRow] = true; //�������� ����
		
		RationalNum result = new RationalNum();//����� ����
		
		int curCol = startCol;
		for(int i=0; i<size; i++) {//size���� ����Ľ��� ���� ���ؾ���
			
			isHidden[1][curCol] = true; //���� �� ����
		
			if(i%2 == 0){//Ȧ����°�� ����
				result.add(RationalNum.multiply(matrix[startRow][curCol], determinant(size-1,isHidden)));
				//System.out.println(result);
			}else {//¦����°�� ��
				result.subtract(RationalNum.multiply(matrix[startRow][curCol], determinant(size-1,isHidden)));
				//System.out.println(result);
			}
			
			isHidden[1][curCol] = false; //���� ����
			
			curCol = nextCol(curCol,isHidden); //���� ������ ���� ���� ã��
		}
		
		isHidden[0][startRow] = false; //���翭 ���� ����
		return result;
	}
	
	//�������� ���� ���� ���� ���� ã�� �ִ� �Լ�
	private int nextCol(int curCol,boolean[][] isHidden) {
		int nextCol = curCol+1;
		
		while(nextCol < col) {
			if(!isHidden[1][nextCol]) {//���� ���� �ʾҴٸ�
				return nextCol;
			}
			nextCol++;
		}
		
		return -1; //���� �� ����
	}
	
	/*
	 * ������� ���ؼ� ��ȯ �ϴ� �Լ�
	 */
	public Matrix getInverse() {
		RationalNum determinant = getDeterminant(); //��Ľ�
		
		if(row != col) {//��������� �ƴѰ��
			System.out.println("���� ��ĸ� ��Ľ��� ���� �� �ֽ��ϴ�.");
			return null;
		}
		
		if(determinant.equals(new RationalNum(1,0))) {//�񰡿� ����� ���
			System.out.println("�񰡿� ����Դϴ�.");
			return null;
		}
		
		RationalNum[][] adjointArr = new RationalNum[row][col];
		
		boolean[][] isHidden = new boolean[2][col]; //������ �� ���� ǥ�� 0��-�� 1��-��
		
		for(int r=0; r<row; r++) {
			
			for(int c=0; c<col; c++) {
				//r�� c�� ����
				isHidden[0][r] = true;
				isHidden[1][c] = true;
				//Crc�� ����ؼ� determinant�� ���� �� c�� r���� ����
				
				adjointArr[c][r] = RationalNum.divide(determinant(col-1,isHidden),determinant); 
				if((r+c) %2 == 1) adjointArr[c][r].multiply(new RationalNum(1,-1));
				
				//���� ����
				isHidden[0][r] = false;
				isHidden[1][c] = false;
				
			}
		}
		
		
		return new Matrix(adjointArr);
	}
	
	
	public String toString() {
		StringBuffer rStr = new StringBuffer();
		
		for(int r=0; r<row ; r++) {
			for(int c=0; c<col; c++) {
				rStr.append(matrix[r][c] + "\t");
			}
			rStr.append("\n");
		}
		
		return rStr.toString();
	}
	
}
