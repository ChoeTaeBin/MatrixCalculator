package ��İ��;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Calculator {
	private Matrix[] matrix; //��� �迭
	
	public Calculator() {
		//empty
	}
	
	public void run() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //�Է��� �ޱ� ����
		StringTokenizer st; //�Է��� �ޱ�����
		
		final int MULTIPLY = 1, DETERMINANT = 2, INVERSE = 3, EQUATION = 4, EXIT = 5; //���۵�
		
		while(true) {
			System.out.print("���Ͻô� �׸��� ������ �ּ���(1 ��İ� ����, 2 ��Ľ� ����, 3 �����, 4 ������ ,5 ����): ");
			st = new StringTokenizer(br.readLine());
			int command  = Integer.parseInt(st.nextToken()); //����� �о����

			switch(command) {
			case MULTIPLY:
				getMatrix(2);
				System.out.println("����� �� ���\n" + Matrix.multiply(matrix[0],matrix[1]));
				break;
				
			case DETERMINANT:
				getMatrix(1);
				System.out.println("��Ľ�: "+matrix[0].getDeterminant());
				break;
				
			case INVERSE:
				getMatrix(1);
				System.out.println("�����:\n"+matrix[0].getInverse());
				break;	

			case EQUATION:
				System.out.println("AX = B���� A, B������ �Է� �� �ּ���");
				getMatrix(2);
				System.out.println("��\n" + matrix[0].getSolution(matrix[1]));
				break;
				
			case EXIT:
				System.out.println("���α׷��� �����մϴ�. �̿��� �ּż� �����մϴ�.");
				return;
				
			default:
				System.out.println("�߸��� �Է�!")	;
			}
		}
		
	}
	
	public void getMatrix(int numMatrix) throws IOException{//numMatrix��ŭ ����� �о����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		RationalNum[][] matrixBuff; //��� buffer;
		
		matrix = new Matrix[numMatrix];
	
		for(int i=0; i<numMatrix; i++) {
			System.out.println("���ϴ� ����� ũ�⸦ �Է��Ͻÿ�(��, ��)");
			st = new StringTokenizer(br.readLine());//�о����
			matrixBuff = new RationalNum[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			//�Է��� ����
			System.out.println("����� �Է��Ͻÿ�.");
			if(i==0) {
				System.out.println("(����)\n1 2\n3 4");
			}
			for(int r=0; r<matrixBuff.length; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<matrixBuff[0].length; c++) {
					matrixBuff[r][c] = new RationalNum(1,Integer.parseInt(st.nextToken()));
				}
			}
			matrix[i]= new Matrix(matrixBuff); //��� ��ü�� ���� ����
			System.out.println("�Է��Ͻ� ���:");
			System.out.println(matrix[i]);
		}
	}
}
