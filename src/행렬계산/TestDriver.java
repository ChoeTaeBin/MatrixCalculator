package ��İ��;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestDriver {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MULTIPLY = 1, DETERMINANT = 2, EXIT = 3;
		StringTokenizer st;
		int command; //���
		RationalNum[][] matrixBuff; //��� buffer;
		Matrix[] matrix; //��� �迭
		
		while(true) {
			System.out.print("���Ͻô� �׸��� ������ �ּ���(1 ��İ� ����, 2 ��Ľ� ����, 3 ����): ");
			st = new StringTokenizer(br.readLine());
			command  = Integer.parseInt(st.nextToken()); //����� �о����

			switch(command) {
			case MULTIPLY:
				matrix = new Matrix[2];
				
				for(int i=0; i<2; i++) {
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
				
				System.out.println("����� �� ���\n" + Matrix.multiply(matrix[0],matrix[1]));
				
				break;
				
			case DETERMINANT:
				matrix = new Matrix[1];
				System.out.println("���ϴ� ����� ũ�⸦ �Է��Ͻÿ�.(��, ��)");
				st = new StringTokenizer(br.readLine());//�о����
				matrixBuff = new RationalNum[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
				//�Է��� ����
				System.out.println("����� �Է��Ͻÿ�. \n(����)\n1 2\n3 4");
				for(int r=0; r<matrixBuff.length; r++) {
					st = new StringTokenizer(br.readLine());
					for(int c=0; c<matrixBuff[0].length; c++) {
						matrixBuff[r][c] = new RationalNum(1,Integer.parseInt(st.nextToken()));
					}
				}
				matrix[0] = new Matrix(matrixBuff); //��� ��ü�� ���� ����
				System.out.println("�Է��Ͻ� ���:");
				System.out.println(matrix[0]);

				System.out.println("��Ľ�: "+matrix[0].getDeterminant());
				break;
				
			case EXIT:
				return;
				
			default:
				System.out.println("�߸��� �Է�!")	;
			}

		}
	}
}
