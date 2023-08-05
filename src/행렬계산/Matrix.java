package ��İ��;

/*
 * ����� ��Ÿ����
 * �پ��� ��� ��� ����� �����Ѵ�.
 */

public class Matrix{//���
	public static final int UNIQUE_SOLUTION = 1, COUNTLESS_SOLUTION = 2, NO_SOLUTION = 3; //���� ������ ������� ���ϴ� ���
	private RationalNum[][] matrix; //2���� ������ �迭
	private int row; //�� ũ��
	private int col; //�� ũ��
	
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
			throw new RuntimeException("���� ���°� ���� �ʽ��ϴ�.");
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
		if(row != col) {//��������� �ƴѰ��
			throw new RuntimeException("���� ��ĸ� ��Ľ��� ���� �� �ֽ��ϴ�.");
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
			throw new RuntimeException("���� ��ĸ� ������� ���� �� �ֽ��ϴ�.");
		}

		if(determinant.equals(new RationalNum(1,0))) {//�񰡿� ����� ���
			throw new RuntimeException("�񰡿� ����Դϴ�.");
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

	/*
	 * ������� �̿��� ���� �������� �ظ� ��� ���·� ��ȯ�ϴ� �Լ�
	 * (���� ��Ŀ� � ����� ���ؾ� ��� ����� ��������)
	 */

	public Matrix getSolutionByInverse(Matrix result) {
		if(row != result.row) {
			throw new RuntimeException("������ ���� �ʽ��ϴ�.");
		}

		return multiply(getInverse(),result);
	}
	
	/*
	 * ÷�� ��İ� ������ ������ �޾Ƽ� �ظ� ���Ѵ�.
	 * ���� ������ ���� �ذ� �����Ѵٸ� �ذ����� �������͸� �����ͷ� �ϴ� ����� ��ȯ�Ѵ�.
	 * �ذ� ���ٸ� null�� ��ȯ �Ѵ�.
	 * ��� resultBox�� � ����� ���Դ��� �־��ش�.
	 * ������ ���� �ظ� ���� ���������� ǥ���ؾ� �Ѵٸ� soultionBox�� ���� �������� �־ ��ȯ
	 */
	static Matrix getSolutionByGauss(Matrix m, int col, int[] resultBox, String[] solutionBox) {
		
		RationalNum[][] matrix = new RationalNum[m.row+1][m.col+1]; 
		
		//������ ������ 1���� �ش� �࿡�� leading������ ���� ���� �ִٸ� �׿��� ǥ�� ���ٸ� null
		//������ ������ 1���� ��� ���� leading������ ���� ���� �ִٸ� ������ ǥ�� ���ٸ� null
		int rankA = 0; //rank(A)
		resultBox[0] = UNIQUE_SOLUTION; //�ϴ� �����ط� ����
		RationalNum[][] rowAdress = new RationalNum[col][]; //idx���� leading�������ִ� ���� �ּҸ� ����
		
		//copy
		for(int r=0; r<m.row; r++) {
			for (int c =0; c <m.col; c++){
				matrix[r][c] = new RationalNum(m.matrix[r][c].getMom(), m.matrix[r][c].getSon());
			}
		}
		
		for(int startC=0; startC<col; startC++) {//leading������ ����� ���� ��
			//leading 1�� ���� �� ��� 0��� ��ȯ�Ѵ�.
			boolean flag = false;
			for(int r=0; r<m.row; r++) {
				if(matrix[r][startC].getSon() !=0 && matrix[r][m.col] == null) {

					matrix[r][m.col] = matrix[r][startC]; //�� ���� leading������ ���� �Ǽ���ü�� �ּҸ� ������ ���� ����
					matrix[m.row][startC] = matrix[r][startC]; //�� ���� leading������ ���� �Ǽ���ü�� �ּҸ� ������ �࿡ ����
					
					//������ ���� �� ���� �ø�
					rowAdress[startC] = matrix[r];
					matrix[r] = matrix[0];
					matrix[0] = rowAdress[startC];
					
					rankA++; //rank����
					
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				resultBox[0] = COUNTLESS_SOLUTION;//�ذ� ���� ��� �� ���� ������ �ϴ� ���������� ������ ������ ����
				break;
			}
			
			for(int r=0; r<m.row; r++) {//startC ���� 1�� ����
				if(!((matrix[r][startC].getSon() == 1 && matrix[r][startC].getMom() == 1)|| matrix[r][startC].getSon() == 0)) { //1�̳� 0�� �ƴϸ�
					int toSon = matrix[r][startC].getMom(); //���ڿ� ���ؾ� �� ��
					int toMom = matrix[r][startC].getSon(); //�и� ���ؾ� �� ��

					for(int c=0; c<m.col; c++) {
						matrix[r][c].setSon(matrix[r][c].getSon() * toSon);
						matrix[r][c].setMom(matrix[r][c].getMom() * toMom);
					}	
				}
			}
			

			for(int r=1; r<m.row; r++) {//�ؿ� �࿡ ��� ����.
				if(matrix[r][startC].getSon() != 0) {//startC ���� 0�� �ƴ� �࿡�� 0���� ���ش�.
					for(int c=startC; c<m.col; c++) {
						matrix[r][c].subtract(matrix[0][c]);
					}
				}
			}

		}
		
		for(int r=0; r<m.row; r++) {
			if(matrix[r][m.col] == null) {//���������� ���� ���ε�
				for(int c=col; c<m.col; c++) { //B�� ������ �ƴ϶�� 
					if(matrix[r][c].getSon() != 0) {//�ذ����� ���, 000|��� ��
						resultBox[0] = NO_SOLUTION;
						//System.out.println("�ذ� ����.");
						return null;
					}
				}		
			}
		}
		
		//rank(A) == rank(A|B) �̹Ƿ� �ظ� ����
		RationalNum[][] solution;
		if(resultBox[0] == UNIQUE_SOLUTION) {//���� �ظ� ������ ���
			solution = new RationalNum[col][m.col - col]; //A|B�� ���� - A�� ���� = B�� ����	
			for(int c=0; c<col; c++) {
				for(int i=0; i<solution[0].length; i++) {
					solution[c][i] = new RationalNum(RationalNum.divide(rowAdress[c][col+i],matrix[m.row][c]));
					solution[c][i].makeSimple();//���
				}
			}
			return new Matrix(solution);
			
		}else {//������ ���� �ظ� ������ ���		
			if(m.col - col != 1) {//B�� 1���� �ƴ� ���
				solutionBox[0] = new String("B�� 1���� �ƴ� ���� ������� ���� �� �����ϴ�.");
				return null;
			}
			
			StringBuffer solutionStr = new StringBuffer();
			int numV = 0; //�������� ��ȣ
			for(int oC = 0; oC < col; oC++) {
				
				if(matrix[m.row][oC] == null) {//�ش翭�� ���������� ����
					//���������� �� ���� ������ ǥ���Ͽ� ( , , , col��ŭ) �÷� ��������
					solutionStr.append("( ");
					for(int iC = 0; iC < col; iC++ ) {
						//iC���� ���ຯ���� ������ ���� ���������� ǥ��
						if(iC == oC) { //�������� ��� �ִ� ���� 1
							solutionStr.append("1 ");
						}else if(matrix[m.row][iC] != null) {
							RationalNum value = new RationalNum(rowAdress[iC][oC]);//���� ���ຯ���� ������ ���� ������������ ��
							value.multiply(-1); //����
							value.divide(matrix[m.row][iC]); //���������� ����
							value.makeSimple();//���
							
							solutionStr.append(value.toString() + " ");
						}else { //0�� ��
							solutionStr.append("0 ");
						}
					}
					
					solutionStr.append(")X" + numV++ + " + ");
				}
			}
			
			//������ ����� ó��
			solutionStr.append("( ");
			for(int oC = 0; oC < col; oC++) {
				if(matrix[m.row][oC] != null) { //leadig ������ �����ٸ�
					//�ش� ���� leading������ ������ ���� -������� �������� ����
					//solutionStr.append(RationalNum.multiply(rowAdress[oC][col],new RationalNum(1,-1)).toString() + " ");
					rowAdress[oC][col].makeSimple();
					solutionStr.append(rowAdress[oC][col].toString() + " ");
				}else {
					solutionStr.append("0 ");
				}
			}
			solutionStr.append(" )");
			
			//����
			solutionBox[0] = solutionStr.toString();
			return null;			
		}		
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
