package ��İ��;

/*
 * ������ �� ��Ÿ���� Ŭ�����̴�.
 */

class  RationalNum{
	private int mom; //�и�
	private int son; //����
	
	RationalNum(){ //�⺻ ��
		mom = 1;
		son = 0;
	}
	
	
	RationalNum(int mom, int son) {
		this.mom = mom;
		this.son = son;
		makeSimple(); //�⺻������ ���м��� ������
	}
	
	public int getMom() {//�и� ��ȯ
		return mom;
	}
	
	public int getSon() {//���ڸ� ��ȯ
		return son;
	}
	
	public void setMom(int mom) {
		this.mom = mom;
	}
	
	public void setSon(int son) {
		this.son = son;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof RationalNum)) {
			return false;
		}

		//x �ڷ� ���ؼ� ������
		return (((RationalNum)o).mom *son == ((RationalNum)o).son*mom );	
	}

	public void makeSimple() {//����Ѵ�.
		if(son == 0){//0�� �⺻ ���� 0/1�̴�.
			mom = 1; 
			return;
		}

		if(son<0) { //���ڰ� 0���� ū ��Ȳ���� ���� �Ѵ�.
			son *= -1;
			mom *= -1;
		}

		for(int divisor=1; divisor<=son; divisor++) {
			if(son % divisor == 0 && mom % divisor == 0) {
				son /= divisor;
				mom /= divisor;
				divisor = 1;
			}
		}
		
		if(mom < 0) {//���ڰ� ���� �� ���·� ������
			son *= -1;
			mom *= -1;
		}
	}
	
	//���� ������
	void add(RationalNum f) {
		RationalNum result = add(this,f);
		mom = result.mom;
		son = result.son;
	}
	
	void subtract(RationalNum f) {
		RationalNum result = subtract(this,f);
		mom = result.mom;
		son = result.son;
	}
	
	void multiply(RationalNum f) {
		RationalNum result = multiply(this,f);
		mom = result.mom;
		son = result.son;
	}
	
	void divide(RationalNum f) {
		RationalNum result = divide(this,f);
		mom = result.mom;
		son = result.son;
	}
	
	//���� ������
	public static RationalNum add(RationalNum f1, RationalNum f2) {
		return new RationalNum(f1.mom*f2.mom, f1.son*f2.mom + f2.son*f1.mom);
		
	}
	
	public static RationalNum subtract(RationalNum f1, RationalNum f2) {
		return add(f1,new RationalNum(f2.mom, -f2.son));
	}
	
	public static RationalNum multiply(RationalNum f1, RationalNum f2) {
		return new RationalNum(f1.mom * f2.mom, f1.son * f2.son);
	}
	
	public static RationalNum divide(RationalNum f1, RationalNum f2) {
		return new RationalNum(f1.mom * f2.son, f1.son * f2.mom);
	}
	
	public String toString() {
		if(son == 0) {
			if(mom!=0) {
				return "0";
			}else {
				return "?"; //���� �� 0/0
			}	
		}else if(mom == 0){//���Ѵ��϶�
			return "��";
		}else if(mom == 1){//�����϶�
			return son + "";
		}else {//������ �ƴ� ������ �϶�
			return son + "/" + mom;
		}
	}
	
}
