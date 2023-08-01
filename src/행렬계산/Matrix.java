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
		boolean[] isHidden = new boolean[col]; //������ ���� ǥ��
		
		return determinant(0,0,row, isHidden); //0��0�� ���� size�� row�� ����� ���� ���ؼ� ��ȯ�Ѵ�.
	}
	
	//����Ľ��� ���ϴ� �Լ�, ��������� ����
	private RationalNum determinant(int startRow, int startCol,int size, boolean[] isHidden) {//size*size ũ���� ����Ľ��� ����
		if(size == 1) {//���� ����
			return matrix[startRow][startCol];
		}
		
		RationalNum result = new RationalNum();//����� ����
		
		int curCol = startCol; //startCol�� �������� �����ָ� ��� �� �� ����
		for(int i=0; i<size; i++) {//size���� ����Ľ��� ���� ���ؾ���
			isHidden[curCol] = true; //���� �� ����
			
			int c = 0; //���� ���� ã�� ���� 
			while(isHidden[c]) {//�� ���� ���� ���� ���� col���� ���� �ؾ���
				c++;
			}
			
			if(i%2 == 0){//Ȧ����°�� ����
				result.add(RationalNum.multiply(matrix[startRow][curCol], determinant(startRow+1,c,size-1,isHidden)));
				//System.out.println(result);
			}else {//¦����°�� ��
				result.subtract(RationalNum.multiply(matrix[startRow][curCol], determinant(startRow+1,c,size-1,isHidden)));
				//System.out.println(result);
			}
			
			isHidden[curCol] = false; //���� ����
			
			curCol = nextCol(curCol,isHidden); //���� ������ ���� ���� ã��
		}
		return result;
	}
	
	//�������� ���� ���� ���� ã�� �ִ� �Լ�
	private int nextCol(int curCol,boolean[] isHidden) {
		int nextCol = curCol+1;
		
		while(nextCol < col) {
			if(!isHidden[nextCol]) {//���� ���� �ʾҴٸ�
				return nextCol;
			}
			nextCol++;
		}
		
		return -1; //���� �� ����
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
