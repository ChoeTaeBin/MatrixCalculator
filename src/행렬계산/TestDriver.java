package ��İ��;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TestDriver {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Matrix matrix;//���
		
		System.out.println("���ϴ� ����� ũ�⸦ �Է��Ͻÿ�(��, ��)");
		
		StringTokenizer st = new StringTokenizer(br.readLine());//�о����
		RationalNum[][] matrixBuff = new RationalNum[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		//�Է��� ����
		System.out.println("����� �Է��Ͻÿ�");
		for(int r=0; r<matrixBuff.length; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<matrixBuff[0].length; c++) {
				matrixBuff[r][c] = new RationalNum(1,Integer.parseInt(st.nextToken()));
			}
		}
		matrix = new Matrix(matrixBuff); //��� ��ü�� ���� ����
		System.out.println("�Է��Ͻ� ���:");
		matrix.print();

		System.out.println("��Ľ�: "+matrix.getDeterminant());

	}
}
